package com.github.petruki.repository;

import android.content.Context;

import com.github.petruki.db.AbstractRepository;
import com.github.petruki.model.Booking;
import com.github.petruki.model.User;

import java.util.List;

public class UserRepository extends AbstractRepository<User> {

    private final BookingRepository bookingRepository;

    public UserRepository(Context context) {
        super(context, new UserWrapper());
        bookingRepository = new BookingRepository(context, this);
    }

    public List<Booking> findUserBooking(String id) throws Exception {
        return bookingRepository.findByUser(id);
    }

}
