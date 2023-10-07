package com.example.dblab01;

import javafx.beans.property.*;

public class Students{

    private final SimpleStringProperty name;
    private final SimpleStringProperty surname;
    private final SimpleStringProperty patronymic;

    public Students(String name, String surname, String patronymic){
        this.name = new SimpleStringProperty(name);
        this.surname = new SimpleStringProperty(surname);
        this.patronymic = new SimpleStringProperty(patronymic);
    }

    public String getName(){ return name.get();}
    public void setName(String value){ name.set(value);}

    public String getAge(){ return surname.get();}
    public void setAge(String value){ surname.set(value);}

    public String getPatronymic(){ return patronymic.get();}
    public void setPatronymic(String value){ patronymic.set(value);}
}
