package com.example.dblab01;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Marks {
    private final StringProperty name;
    private final StringProperty path;
    private final StringProperty mark;

    public Marks(String name, String path, String mark) {
        this.name = new SimpleStringProperty(name);
        this.path = new SimpleStringProperty(path);
        this.mark = new SimpleStringProperty(mark);

    }

    public StringProperty nameProperty() {return name;}
    public String getName() {return name.get();}
    public void setName(String value) {name.set(value);}

    public StringProperty pathProperty() {return path;}
    public String getPath() {return path.get();}
    public void setPath(String value) {path.set(value);}

    public StringProperty markProperty() {return mark;}
    public String getMark() {return mark.get();}
    public void setMark(String value) {mark.set(value);}
}
