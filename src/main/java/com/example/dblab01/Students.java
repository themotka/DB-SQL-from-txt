package com.example.dblab01;

import javafx.beans.property.*;

public class Students {

    private final IntegerProperty id;
    private final StringProperty name;
    private final StringProperty surname;
    private final StringProperty patronymic;

    public Students(Integer id, String name, String surname, String patronymic) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.surname = new SimpleStringProperty(surname);
        this.patronymic = new SimpleStringProperty(patronymic);

    }

    public IntegerProperty idProperty() {return id;}
    public void setId(int id) {this.id.set(id);}
    public int getId() {return id.get();}

    public StringProperty nameProperty() {return name;}
    public String getName() {return name.get();}
    public void setName(String value) {name.set(value);}

    public StringProperty surnameProperty() {return surname;}
    public String getSurname() {return surname.get();}
    public void setSurname(String value) {surname.set(value);}

    public StringProperty patronymicProperty() {return patronymic;}
    public String getPatronymic() {return patronymic.get();}
    public void setPatronymic(String value) {patronymic.set(value);}
}
