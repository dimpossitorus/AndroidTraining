package com.dimpossitorus.membersmonitor;

/**
 * Created by Dimpos Sitorus on 07/06/2016.
 */
public class Member {
    private int id;
    private String name;
    private String address;

    public Member() {

    }

    public Member(int _id, String _name, String _address) {
        id = _id;
        name = _name;
        address = _address;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
