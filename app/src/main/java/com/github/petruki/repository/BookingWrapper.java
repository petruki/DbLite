package com.github.petruki.repository;

import android.content.ContentValues;
import android.database.Cursor;

import com.github.petruki.db.wrapper.DbLiteWrapper;
import com.github.petruki.db.wrapper.EntityWrapper;
import com.github.petruki.model.Booking;

@DbLiteWrapper(entityName = "BOOKING", columns = { "id", "contactId" })
public class BookingWrapper implements EntityWrapper<Booking> {

    @Override
    public Class<?> getDbFactoryClass() {
        return MyDatabase.class;
    }

    @Override
    public Booking unWrap(Cursor cursor) {
        Booking booking = new Booking();
        booking.setId(cursor.getString(cursor.getColumnIndex("id")));
        booking.setContactId(cursor.getString(cursor.getColumnIndex("contactId")));
        return booking;
    }

    @Override
    public ContentValues wrap(Booking user) {
        ContentValues values = new ContentValues();
        values.put("id", user.getId());
        values.put("contactId", user.getContactId());
        return values;
    }

}
