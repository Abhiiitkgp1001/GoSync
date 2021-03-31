package com.example.gosyncv11;

import java.util.ArrayList;

public class Need {
    ArrayList<Elements> elements = new ArrayList<>();

    public Need() {
    }

    public Need(ArrayList<Elements> elements) {
        this.elements = elements;
    }

    public ArrayList<Elements> getElements() {
        return elements;
    }

    public void setElements(ArrayList<Elements> elements) {
        this.elements = elements;
    }
}
