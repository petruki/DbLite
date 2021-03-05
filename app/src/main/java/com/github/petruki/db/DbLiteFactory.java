package com.github.petruki.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;

import com.github.petruki.db.wrapper.DbLiteWrapper;

public class DbLiteFactory extends SQLiteOpenHelper {

    private final Class<?>[] DATABASE_WRAPPERS;

    private static DbLiteFactory instance;

    protected DbLiteFactory(Context context, Class<?> dbFactoryClass) {
        super(context, getDbName(dbFactoryClass), null, getDbVersion(dbFactoryClass));
        this.DATABASE_WRAPPERS = getDbWrappers(dbFactoryClass);
    }

    private static String getDbName(Class<?> dbFactoryClass) {
        DbLite dbLiteAnnotation = dbFactoryClass.getAnnotation(DbLite.class);
        assert dbLiteAnnotation != null;
        return dbLiteAnnotation.dbName();
    }

    private static int getDbVersion(Class<?> dbFactoryClass) {
        DbLite dbLiteAnnotation = dbFactoryClass.getAnnotation(DbLite.class);
        assert dbLiteAnnotation != null;
        return dbLiteAnnotation.version();
    }

    private static Class<?>[] getDbWrappers(Class<?> dbFactoryClass) {
        DbLite dbLiteAnnotation = dbFactoryClass.getAnnotation(DbLite.class);
        assert dbLiteAnnotation != null;
        return dbLiteAnnotation.wrappers();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        for (Class<?> wrapper : DATABASE_WRAPPERS) {
            DbLiteWrapper wrapperEntity = wrapper.getAnnotation(DbLiteWrapper.class);
            assert wrapperEntity != null;
            String statement = String.format("CREATE TABLE %s (%s)",
                    wrapperEntity.entityName(), TextUtils.join(",", wrapperEntity.columns()));
            sqLiteDatabase.execSQL(statement);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        final String dropTable = "DROP TABLE IF EXISTS %s";
        for (Class<?> wrapper : DATABASE_WRAPPERS) {
            DbLiteWrapper dbWrapperAnnotation = wrapper.getAnnotation(DbLiteWrapper.class);
            assert dbWrapperAnnotation != null;
            sqLiteDatabase.execSQL(String.format(dropTable, dbWrapperAnnotation.entityName()));
        }

        onCreate(sqLiteDatabase);
    }

    public static DbLiteFactory getInstance(Context context, Class<?> dbFactory) {
        if (instance == null) {
            instance = new DbLiteFactory(context, dbFactory);
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
