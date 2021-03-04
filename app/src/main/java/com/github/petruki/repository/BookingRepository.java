package com.github.petruki.repository;

import android.content.Context;

import com.github.petruki.db.AbstractRepository;
import com.github.petruki.db.wrapper.EntityResolver;
import com.github.petruki.model.Booking;
import com.github.petruki.model.User;

import java.util.List;

public class BookingRepository extends AbstractRepository<Booking>
        implements EntityResolver<Booking> {

    private final UserRepository userRepository;

    public BookingRepository(Context context, UserRepository userRepository) {
        super(context, new BookingWrapper());
        this.userRepository = userRepository;
    }

    @Override
    public Booking findById(String id) throws Exception {
        return findById(id, this);
    }

    public List<Booking> findByUser(String id) throws Exception {
        return find("contactId = ?", new String[] { id }, this);
    }

    @Override
    public Booking resolve(Booking entity) throws Exception {
        User contact = userRepository.findById(entity.getContactId());
        entity.setContact(contact);
        return entity;
    }
}
