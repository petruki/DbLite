package com.github.petruki.model;

public class Plan {

    private String _id;
    private String name;
    private boolean enable_ads;

    public String getId() {
        return _id;
    }

    public void setId(String id) {
        this._id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isEnable_ads() {
        return enable_ads;
    }

    public void setEnable_ads(boolean enable_ads) {
        this.enable_ads = enable_ads;
    }

    @Override
    public String toString() {
        return "Plan{" +
                "_id='" + _id + '\'' +
                ", name='" + name + '\'' +
                ", enable_ads=" + enable_ads +
                '}';
    }
}
