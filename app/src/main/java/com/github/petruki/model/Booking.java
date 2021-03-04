package com.github.petruki.model;

public class Booking {

    private String _id;
    private String contactId;
    private User contact;

    public String getId() {
        return _id;
    }

    public void setId(String id) {
        this._id = id;
    }

    public String getContactId() {
        return contactId;
    }

    public void setContactId(String contactId) {
        this.contactId = contactId;
    }

    public User getContact() {
        return contact;
    }

    public void setContact(User contact) {
        this.contact = contact;
        setContactId(contact.getId());
    }

    @Override
    public String toString() {
        return "Booking{" +
                "_id='" + _id + '\'' +
                ", contactId=" + contactId +
                ", contact=" + contact +
                '}';
    }
}
