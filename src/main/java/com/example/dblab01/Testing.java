package com.example.dblab01;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Testing {
    private final IntegerProperty studentId;
    private final IntegerProperty varId;


    public Testing(Integer studentId, Integer varId) {
        this.studentId = new SimpleIntegerProperty(studentId);
        this.varId = new SimpleIntegerProperty(varId);
    }

    public IntegerProperty studentIdProperty() {return studentId;}
    public void setStudentId(int id) {this.studentId.set(id);}
    public int getStudentId() {return studentId.get();}


    public IntegerProperty varIdProperty() {return varId;}
    public void setVarId(int id) {this.varId.set(id);}
    public int getVarId() {return varId.get();}

}
