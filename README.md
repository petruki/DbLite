### About

This project is a showcase app that uses DbLite Library to interact with SQLite DB.

More details about the library can be found here: [dblite-library](https://github.com/petruki/dblite-library)

### The Scenario

The example provided is a booking operation that contains 3 entities, User, Plan and Booking.

User is a simple entity that contains only user data and its plan.

Booking is a separated entity that contains booking data and a User relationship.
 
In order to both User and Booking model classes to interact with each other, both Repositories need to implement EntityResolver that is responsible to translate User into Booking.
 
### Concept

- **Model**: it defines your classes business classes
- **Repository**: it defines all Database operations
- **Wrapper**: it defines your how your model classes will interact with SQLite
- **DbFactory**: this is the DB access manager which will provide the resources to access the local DB