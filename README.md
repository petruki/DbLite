### About

DbLite is a simple API implementation which allows you to interact with Relational and Non-relational data.
Most of the libraries provide better and clean implementaion to interact with SQLite, but they lack of features when the data can be both relational and non-relational. This common scenario happens when you consume JSON data from external APIs and need to store it on a local DB.

My solution provides with a versatile way to implement and resolve model object dependencies easier.

Here are some of the features:
- Independent object persistence implementation (Wrappers)
- CRUD operations out-of-the-box
- Entity resolver for creating relationship from non-relational data

### The Scenario

The example provided is a booking operation that contains 3 entities, User, Plan and Booking.

User is a simple entity that contains only user data and its plan.

Booking is a separated entity that contains booking data and a User relationship.
 
In order to both User and Booking model classes to interact with each other, both Repositories need to implement EntityResolver that is responsible to translate User into Booking.
 
### Concept

- **Model**: it defines your classes business classes
- **Repository**: it defines all Database operations
- **Wrapper**: it defines your how your model classes will interact with SQLite
- **DBFactory**: this is the DB access manager which will provide the resources to access the local DB
 
### How-to

Below is the step-by-step you must follow to implement new Repository classes.

1. Create model class: model/User.java
2. Create wrapper class: repository/UserWrapper.java
3. Create repository class: repository/UserRepository.java
4. Add wrapper class to DbFactory at db/DbFactory.java - DATABASE_WRAPPERS