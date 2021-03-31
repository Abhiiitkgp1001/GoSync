package com.example.gosyncv11;

import java.io.Serializable;

public class Elements implements Serializable {
    int id;
    public String name;
    public Elements() {
    }

    public Elements(int id, String name) {
        this.id = id;
        this.name = name;
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
}
