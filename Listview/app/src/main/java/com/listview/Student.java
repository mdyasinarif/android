package com.listview;

public class Student {
    private final long id;
    private final String name;
    private final String district;

    public Student(long id, String name, String district) {
        this.id = id;
        this.name = name;
        this.district = district;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDistrict() {
        return district;
    }
}
