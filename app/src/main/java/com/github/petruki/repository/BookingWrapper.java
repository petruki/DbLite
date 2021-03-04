package com.github.petruki.repository;

import android.content.ContentValues;
import android.database.Cursor;

import com.github.petruki.db.wrapper.EntityWrapper;
import com.github.petruki.model.Booking;

public class BookingWrapper implements EntityWrapper<Booking> {

    @Override
    public String getTableName() {
        return "BOOKING";
    }

    @Override
    public String getCreateTable() {
        return String.format("CREATE TABLE %s (id, contactId, contact)", getTableName());
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
