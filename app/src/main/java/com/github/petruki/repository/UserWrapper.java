package com.github.petruki.repository;

import android.content.ContentValues;
import android.database.Cursor;

import com.github.petruki.db.wrapper.EntityWrapper;
import com.github.petruki.model.Plan;
import com.github.petruki.model.User;
import com.google.gson.Gson;

public class UserWrapper implements EntityWrapper<User> {

    @Override
    public String getTableName() {
        return "USER";
    }

    @Override
    public String getCreateTable() {
        return String.format("CREATE TABLE %s (id, name, email, plan)", getTableName());
    }

    @Override
    public User unWrap(Cursor cursor) {
        User user = new User();
        user.setId(cursor.getString(cursor.getColumnIndex("id")));
        user.setName(cursor.getString(cursor.getColumnIndex("name")));
        user.setEmail(cursor.getString(cursor.getColumnIndex("email")));

        Gson gson = new Gson();
        user.setPlan(gson.fromJson(cursor.getString(cursor.getColumnIndex("plan")), Plan.class));
        return user;
    }

    @Override
    public ContentValues wrap(User user) {
        ContentValues values = new ContentValues();
        values.put("id", user.getId());
        values.put("name", user.getName());
        values.put("email", user.getEmail());

        Gson gson = new Gson();
        values.put("plan", gson.toJson(user.getPlan()));
        return values;
    }

}
