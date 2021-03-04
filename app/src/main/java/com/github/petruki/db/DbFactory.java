package com.github.petruki.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.github.petruki.db.wrapper.EntityWrapper;
import com.github.petruki.repository.BookingWrapper;
import com.github.petruki.repository.UserWrapper;

public class DbFactory extends SQLiteOpenHelper {

    private static final String TAG = DbFactory.class.getSimpleName();

    // change DATABASE_NAME, DATABASE_VERSION and DATABASE_WRAPPERS
    private static final String DATABASE_NAME = "DBLITE";
    private static final int DATABASE_VERSION = 1;
    private static final Class<EntityWrapper<?>>[] DATABASE_WRAPPERS = new Class[] {
            UserWrapper.class,
            BookingWrapper.class
    };

    private static DbFactory instance;

    private DbFactory(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        for (Class<EntityWrapper<?>> wrapper : DATABASE_WRAPPERS) {
            try {
                EntityWrapper<?> entityWrapper = wrapper.newInstance();
                sqLiteDatabase.execSQL(entityWrapper.getCreateTable());
            } catch (Exception e) {
                Log.e(TAG, e.getMessage(), e);
            }
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        final String dropTable = "DROP TABLE IF EXISTS %s";
        for (Class<EntityWrapper<?>> wrapper : DATABASE_WRAPPERS) {
            try {
                EntityWrapper<?> entityWrapper = wrapper.newInstance();
                sqLiteDatabase.execSQL(String.format(dropTable, entityWrapper.getTableName()));
            } catch (Exception e) {
                Log.e(TAG, e.getMessage(), e);
            }
        }

        onCreate(sqLiteDatabase);
    }

    public static DbFactory getInstance(Context context) {
        if (instance == null) {
            instance = new DbFactory(context);
        }
        return instance;
    }

    public SQLiteDatabase getDbWriter() {
        return getWritableDatabase();
    }

    public SQLiteDatabase getDbReader() {
        return getReadableDatabase();
    }
}
