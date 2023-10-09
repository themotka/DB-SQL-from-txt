package com.example.dblab01;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;

import javax.swing.*;
import static com.example.dblab01.HelloApplication.*;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.WARNING_MESSAGE;

public class HelloController {
    @FXML
    private Tab markLbl;
    @FXML
    private Tab studentLbl;
    @FXML
    private Tab testLbl;
    @FXML
    private Tab variantsLbl;

    @FXML
    private TextField fieldIdStudents;
    @FXML
    private TextField fieldNameStudents;
    @FXML
    private TextField fieldPatronymicStudents;
    @FXML
    private TextField fieldSurnameStudents;
    @FXML
    private TextField fieldIdVar;
    @FXML
    private TextField fieldPathVar;
    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private Button deleteStudentBtn;
    @FXML
    private Button deleteVarBtn;

    @FXML
    private TableColumn<Marks, String> markField;
    @FXML
    private TableColumn<Marks, String> markName;
    @FXML
    private TableColumn<Marks, String> markPath;
    @FXML
    private TableView<Marks> marksTbl = new TableView<>(markData);

    @FXML
    private MenuItem menuCloseBtn;
    @FXML
    private MenuItem menuCreateBackup;
    @FXML
    private MenuItem menuOpenBackup;
    @FXML
    private MenuItem menuOpenBtn;
    @FXML
    private Button newStudentBtn;
    @FXML
    private Button newVarBtn;
    @FXML
    private Button generateBtn;
    @FXML
    private Button generateBtn1;
    @FXML
    private TableColumn<Students, Integer> studentsId;
    @FXML
    private TableColumn<Students, String> studentsName;
    @FXML
    private TableColumn<Students, String> studentsPatronymic;
    @FXML
    private TableColumn<Students, String> studentsSurname;
    @FXML
    private TableView<Students> studentsTbl = new TableView<>(studentData);

    @FXML
    private TableView<Variants> variantsTbl = new TableView<>(varData);
    @FXML
    private TableColumn<Variants, Integer> varId;
    @FXML
    private TableColumn<Variants, String> pathId;

    @FXML
    private TableColumn<Testing, Integer> testStudId;
    @FXML
    private TableColumn<Testing, Integer> testVarId;
    @FXML
    private TableView<Testing> testTbl = new TableView<>(testData);


    @FXML
    void closeApp(ActionEvent event) {

    }
    @FXML
    void editBtnClick(ActionEvent event) {
    }

    @FXML
    void markTab(ActionEvent event) {

    }
    @FXML
    void openExisting(ActionEvent event) {

    }
    @FXML
    void save(ActionEvent event) {

    }
    @FXML
    void stuTab(ActionEvent event) {

    }

    @FXML
    void testTab(ActionEvent event) {

    }

    @FXML
    void varTab(ActionEvent event) {

    }
    void IncorrectData(String string){
        JFrame jFrame = new JFrame();
        JOptionPane.showMessageDialog(jFrame, string, "Error", ERROR_MESSAGE);
    }
    @FXML
    void delStBtnClick(ActionEvent event) {
//        IncorrectData("aboba");
        int row = studentsTbl.getSelectionModel().getSelectedIndex();
        delete(studentsTbl, row);
    }
    @FXML
    void delVarBtnClick(ActionEvent event) {
        int row = variantsTbl.getSelectionModel().getSelectedIndex();
        delete(variantsTbl, row);
    }
    public void delete(TableView table, int index){
       if(index >= 0) table.getItems().remove(index);
    }

    @FXML
    void newStBtnClick(ActionEvent event) {
        int id = Integer.parseInt(fieldIdStudents.getText());
        String name = fieldNameStudents.getText();
        String surname = fieldSurnameStudents.getText();
        String patronymic = fieldPatronymicStudents.getText();
        studentData.add(new Students(
                id,
                name,
                surname,
                patronymic));
        fieldIdStudents.clear();
        fieldNameStudents.clear();
        fieldSurnameStudents.clear();
        fieldPatronymicStudents.clear();
        fieldIdStudents.requestFocus();
    }
    @FXML
    void newVarBtnClick(ActionEvent event) {
        varData.add(new Variants(
                Integer.parseInt(fieldIdVar.getText()),
                fieldPathVar.getText()));
        fieldIdVar.clear();
        fieldPathVar.clear();
        fieldIdVar.requestFocus();
    }
    @FXML
    void generateMarks(){
        for (Students studentDatum : studentData) {
            int var = (int) (Math.random() * (varData.size()));
            testData.add(new Testing(studentDatum.getId(), var));
            markData.add(new Marks(studentDatum.getName()+" "+studentDatum.getSurname()+" "+studentDatum.getPatronymic(),
                    varData.get(var).getName(), ""));
        }
        initializeTesting();
        initializeMarks();
    }
    void initializeTesting(){
        testStudId.setCellValueFactory(data -> data.getValue().studentIdProperty().asObject());
        testStudId.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

        testVarId.setCellValueFactory(data -> data.getValue().varIdProperty().asObject());
        testVarId.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

        testTbl.setItems(testData);
    }
    void initializeMarks(){
        marksTbl.setEditable(true);
        markName.setCellValueFactory(data -> data.getValue().nameProperty());
        markName.setCellFactory(TextFieldTableCell.forTableColumn());


        markPath.setCellValueFactory(data -> data.getValue().pathProperty());
        markPath.setCellFactory(TextFieldTableCell.forTableColumn());

        markField.setCellValueFactory(data -> data.getValue().markProperty());
        markField.setCellFactory(TextFieldTableCell.forTableColumn());
        markField.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Marks, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Marks, String> studentsStringCellEditEvent) {
                Marks marks  = studentsStringCellEditEvent.getRowValue();
                marks.setMark(studentsStringCellEditEvent.getNewValue());
            }
        });

        marksTbl.setItems(markData);
    }

    void initializeStudents(){
        studentsTbl.setEditable(true);

        studentsId.setCellValueFactory(data -> data.getValue().idProperty().asObject());
        studentsId.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        studentsId.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Students, Integer>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Students, Integer> studentsStringCellEditEvent) {
                Students student  = studentsStringCellEditEvent.getRowValue();
                student.setId(studentsStringCellEditEvent.getNewValue());
            }
        });

        studentsName.setCellValueFactory(data -> data.getValue().nameProperty());
        studentsName.setCellFactory(TextFieldTableCell.forTableColumn());
        studentsName.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Students, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Students, String> studentsStringCellEditEvent) {
                Students student  = studentsStringCellEditEvent.getRowValue();
                student.setName(studentsStringCellEditEvent.getNewValue());
            }
        });

        studentsSurname.setCellValueFactory(data -> data.getValue().surnameProperty());
        studentsSurname.setCellFactory(TextFieldTableCell.forTableColumn());
        studentsSurname.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Students, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Students, String> studentsStringCellEditEvent) {
                Students student  = studentsStringCellEditEvent.getRowValue();
                student.setSurname(studentsStringCellEditEvent.getNewValue());
            }
        });

        studentsPatronymic.setCellValueFactory(data -> data.getValue().patronymicProperty());
        studentsPatronymic.setCellFactory(TextFieldTableCell.forTableColumn());
        studentsPatronymic.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Students, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Students, String> studentsStringCellEditEvent) {
                Students student  = studentsStringCellEditEvent.getRowValue();
                student.setPatronymic(studentsStringCellEditEvent.getNewValue());
            }
        });

        studentsTbl.setItems(studentData);
    }
    void initializeVars(){
        variantsTbl.setEditable(true);

        varId.setCellValueFactory(data -> data.getValue().idProperty().asObject());
        varId.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        varId.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Variants, Integer>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Variants, Integer> studentsStringCellEditEvent) {
                Variants var  = studentsStringCellEditEvent.getRowValue();
                var.setId(studentsStringCellEditEvent.getNewValue());
            }
        });

        pathId.setCellValueFactory(data -> data.getValue().nameProperty());
        pathId.setCellFactory(TextFieldTableCell.forTableColumn());
        pathId.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Variants, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Variants, String> studentsStringCellEditEvent) {
                Variants var  = studentsStringCellEditEvent.getRowValue();
                var.setName(studentsStringCellEditEvent.getNewValue());
            }
        });

        variantsTbl.setItems(varData);
    }
    @FXML
    void initialize() {
        initializeStudents();
        initializeVars();
    }



}
