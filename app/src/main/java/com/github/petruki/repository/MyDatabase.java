package com.github.petruki.repository;

import android.content.Context;

import com.github.petruki.db.DbLite;
import com.github.petruki.db.DbLiteFactory;

@DbLite(dbName = "BOOKING_DB", version = 1, wrappers = {
        UserWrapper.class,
        BookingWrapper.class
})
abstract class MyDatabase extends DbLiteFactory {

    protected MyDatabase(Context context) {
        super(context, MyDatabase.class);
    }
}
