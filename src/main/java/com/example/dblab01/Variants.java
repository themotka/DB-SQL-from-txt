package com.example.dblab01;

import javafx.beans.property.*;

public class Variants {
    private final IntegerProperty id;
    private final StringProperty path;


    public Variants(Integer id, String path) {
        this.id = new SimpleIntegerProperty(id);
        this.path =  new SimpleStringProperty(path);
    }
    public IntegerProperty idProperty() {return id;}
    public void setId(int id) {this.id.set(id);}
    public int getId() {return id.get();}

    public StringProperty nameProperty() {return path;}
    public String getName() {return path.get();}
    public void setName(String value) {path.set(value);}

}
