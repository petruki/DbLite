package com.github.petruki;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.github.petruki.repository.BookingRepository;
import com.github.petruki.repository.UserRepository;
import com.github.petruki.model.Booking;
import com.github.petruki.model.Plan;
import com.github.petruki.model.User;

import java.util.List;

/**
 * This code snippet shows how to implement a simple interchangeable Relational to Non-relational
 * Database interaction.
 *
 * It is a useful solution when working with APIs that require non-relational objects such as JSON
 * to interact with Relational storage such as SQLiteDB.
 *
 * The example provided is a booking operation that contains 3 entities, User, Plan and Booking.
 * User is a simple entity that contains only user data and its plan.
 * Booking is a separated entity that contains booking data and a User relationship.
 *
 * In order to both User and Booking model classes to interact with each other, both Repositories
 * need to implement EntityResolver that is responsible to translate User into Booking.
 *
 * DB classes nomenclature:
 * Model: it defines your classes business classes
 * Repository: it defines all Database operations
 * Wrapper: it defines your how your model classes will interact with SQLite
 * DBFactory: this is the DB access manager which will provide the resources to access the local DB
 *
 * Below is the step-by-step you must follow to implement new Repository classes.
 *
 * 1. Create model class: model/User.java
 * 2. Create wrapper class: repository/UserWrapper.java
 * 3. Create repository class: repository/UserRepository.java
 * 4. Add wrapper class to DbFactory at db/DbFactory.java - DATABASE_WRAPPERS
 *
 * @author Roger Floriano (petruki)
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private TextView logOnScreen;
    private UserRepository userRepository;
    private BookingRepository bookingRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userRepository = new UserRepository(this);
        bookingRepository = new BookingRepository(this, userRepository);

        logOnScreen = findViewById(R.id.logOnScreen);

        test1();
        test1_assert();
    }

    /**
     * A scenario where User has a Plan and a Booking
     */
    private void test1() {
        Plan plan = new Plan();
        plan.setId("planId");
        plan.setName("Free");
        plan.setEnable_ads(false);

        User user = new User();
        user.setId("userId");
        user.setEmail("mail@mail.com");
        user.setName("My Name");
        user.setPlan(plan);

        Booking booking = new Booking();
        booking.setId("bookingId");
        booking.setContact(user);

        try {
            userRepository.save(user);
            bookingRepository.save(booking);
        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), e);
        }
    }

    /**
     * Get the saved User from the Database and find all bookings from this user
     */
    private void test1_assert() {
        try {
            User user = userRepository.findById("userId");
            List<Booking> userBooking = userRepository.findUserBooking(user.getId());

            logOnScreen.setText(userBooking.toString());
        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), e);
        }
    }
}