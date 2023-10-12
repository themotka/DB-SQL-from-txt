package com.example.dblab01;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
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
    private TabPane tab;
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
        switch (tab.getSelectionModel().getSelectedIndex()){
            case 0: openStudent();
                break;
            case 1: openVariant();
                break;
            default: IncorrectData("Choose 'Students' or 'Variants' tab");
                break;
        }
    }
    void openVariant() {
        JFileChooser fileopen = new JFileChooser();
        fileopen.setCurrentDirectory(new File("D:\\Programming\\Projects\\IDEAProjects\\DBlab01\\src\\main\\resources\\com\\example\\dblab01\\results"));
        int ret = fileopen.showDialog(null, "Open variants");
        if (ret == JFileChooser.APPROVE_OPTION) {
            File file = fileopen.getSelectedFile();
            try (BufferedReader reader = new BufferedReader(new FileReader(file))){
                String line = reader.readLine();
                varData.clear();
                while (line != null) {
                    String[] s = line.split(";");
                    System.out.println(Arrays.toString(s));
                    varData.add(new Variants(Integer.parseInt(s[0]), s[1]));
                    line = reader.readLine();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    void openStudent(){
        JFileChooser fileopen = new JFileChooser();
        fileopen.setCurrentDirectory(new File("D:\\Programming\\Projects\\IDEAProjects\\DBlab01\\src\\main\\resources\\com\\example\\dblab01\\results"));
        int ret = fileopen.showDialog(null, "Open students");
        if (ret == JFileChooser.APPROVE_OPTION) {
            File file = fileopen.getSelectedFile();
            try (BufferedReader reader = new BufferedReader(new FileReader(file))){
                String line = reader.readLine();
                studentData.clear();
                while (line != null) {
                    String[] s = line.split(";");
                    System.out.println(Arrays.toString(s));
                    studentData.add(new Students(Integer.parseInt(s[0]), s[1], s[2], s[3]));
                    line = reader.readLine();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
    @FXML
    void save(ActionEvent event) throws IOException {//отдельно писать варианты
        if (markData.isEmpty()){
            IncorrectData("Generate marks for backup");
        }
        else {
            SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH-mm-ss");
            Date date = new Date(System.currentTimeMillis());
            String dateS = formatter.format(date);
            File outputFileName = Files.createFile(Path.of("D:\\Programming\\Projects\\IDEAProjects\\DBlab01\\src\\main\\resources\\com\\example\\dblab01\\backups\\"+dateS+".txt")).toFile();
            try (BufferedWriter writter = new BufferedWriter(new FileWriter(outputFileName))) {
                for (int i = 0; i < markData.size(); i++) {
                    String name = markData.get(i).getName().replace(" ", ";");
                    writter.write(testData.get(i).getStudentId()+";"+name+";"+testData.get(i).getVarId()+";"+markData.get(i).getPath()+";"+markData.get(i).getMark()+"\n");
                }
            }
        }
    }
    @FXML
    void openBackup(ActionEvent event){
        JFileChooser fileopen = new JFileChooser();
        fileopen.setCurrentDirectory(new File("D:\\Programming\\Projects\\IDEAProjects\\DBlab01\\src\\main\\resources\\com\\example\\dblab01\\backups"));
        int ret = fileopen.showDialog(null, "Open backup");
        if (ret == JFileChooser.APPROVE_OPTION) {
            File file = fileopen.getSelectedFile();
            try (BufferedReader reader = new BufferedReader(new FileReader(file))){
                String line = reader.readLine();
                studentData.clear();
                markData.clear();
                testData.clear();
                varData.clear();
                while (line != null) {
                    String[] s = line.split(";");
                    System.out.println(Arrays.toString(s));
                    studentData.add(new Students(Integer.parseInt(s[0]), s[1], s[2], s[3]));
                    varData.add(new Variants(Integer.parseInt(s[4]), s[5]));
                    testData.add(new Testing(Integer.parseInt(s[0]),Integer.parseInt(s[4])));
                    markData.add(new Marks( s[1]+" "+s[2]+" "+s[3],s[5], ""));
                    line = reader.readLine();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
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
    private boolean uniqueId(Integer id) {
        for (Students s:
                studentData) {
            if (s.getId() == id){
                return false;
            }
        }
        return true;
    }
    private boolean uniqueIdVar(Integer id) {
        for (Variants s:
                varData) {
            if (s.getId() == id){
                return false;
            }
        }
        return true;
    }
    void IncorrectData(String string){
        JFrame jFrame = new JFrame();
        JOptionPane.showMessageDialog(jFrame, string, "Error", ERROR_MESSAGE);
    }
    public void delete(TableView<?> table, int index){
        if(index >= 0) table.getItems().remove(index);
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

    @FXML
    void newStBtnClick(ActionEvent event) {
        int id = Integer.parseInt(fieldIdStudents.getText());
        String name = fieldNameStudents.getText();
        String surname = fieldSurnameStudents.getText();
        String patronymic = fieldPatronymicStudents.getText();
        if (studentData.contains(new Students(id, name, surname, patronymic))){ IncorrectData("Student already in database");}
        else if (!uniqueId(id)){ IncorrectData("ID already in database");}
        else {
            System.out.println(id+ name+ surname+ patronymic);
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
    }


    @FXML
    void newVarBtnClick(ActionEvent event) {
        int id = Integer.parseInt(fieldIdVar.getText());
        String path = fieldPathVar.getText();
        if (varData.contains(new Variants(id, path))){ IncorrectData("Path already in database");}
        else if (!uniqueIdVar(id)){IncorrectData("ID already in database");}
        else {
            varData.add(new Variants(
                    id,
                    path));
            fieldIdVar.clear();
            fieldPathVar.clear();
            fieldIdVar.requestFocus();
        }
    }
    @FXML
    void generateMarks(){
        markData.clear();
        testData.clear();
        for (Students studentDatum : studentData) {
            int var = (int) (Math.random() * (varData.size()));
            testData.add(new Testing(studentDatum.getId(), varData.get(var).getId()));
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
        markName.setEditable(false);
        markPath.setEditable(false);

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
                System.out.println(studentsStringCellEditEvent.getNewValue());
                marks.setMark(studentsStringCellEditEvent.getNewValue());
            }
        });

        marksTbl.setItems(markData);
    }

    void initializeStudents(){
        studentsTbl.setEditable(true);
        studentsId.setEditable(false);

        studentsId.setCellValueFactory(data -> data.getValue().idProperty().asObject());
        studentsId.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));


        studentsName.setCellValueFactory(data -> data.getValue().nameProperty());
        studentsName.setCellFactory(TextFieldTableCell.forTableColumn());
        studentsName.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Students, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Students, String> studentsStringCellEditEvent) {
                Students student  = studentsStringCellEditEvent.getRowValue();
                String newName = studentsStringCellEditEvent.getNewValue();
                String oldName = student.getName();
                if (studentData.contains(new Students(
                        student.getId(),
                        newName, student.getSurname(),
                        student.getPatronymic()
                ))){
                    IncorrectData("Student already in database");
                    student.setName(newName);
                    student.setName(oldName);
                }else student.setName(newName);

            }
        });

        studentsSurname.setCellValueFactory(data -> data.getValue().surnameProperty());
        studentsSurname.setCellFactory(TextFieldTableCell.forTableColumn());
        studentsSurname.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Students, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Students, String> studentsStringCellEditEvent) {
                Students student  = studentsStringCellEditEvent.getRowValue();
                String newSurname = studentsStringCellEditEvent.getNewValue();
                String oldSurname = student.getSurname();
                if (studentData.contains(new Students(
                        student.getId(),
                        student.getName(),
                        newSurname,
                        student.getPatronymic()
                ))){
                    IncorrectData("Student already in database");
                    student.setSurname(newSurname);
                    student.setSurname(oldSurname);
                }else student.setSurname(newSurname);
            }
        });

        studentsPatronymic.setCellValueFactory(data -> data.getValue().patronymicProperty());
        studentsPatronymic.setCellFactory(TextFieldTableCell.forTableColumn());
        studentsPatronymic.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Students, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Students, String> studentsStringCellEditEvent) {
                Students student  = studentsStringCellEditEvent.getRowValue();
                String newPatronymic = studentsStringCellEditEvent.getNewValue();
                String oldPatronymic = student.getPatronymic();
                if (studentData.contains(new Students(
                        student.getId(),
                        student.getName(),
                        student.getSurname(),
                        newPatronymic
                ))){
                    IncorrectData("Student already in database");
                    student.setPatronymic(newPatronymic);
                    student.setPatronymic(oldPatronymic);
                }else student.setPatronymic(newPatronymic);
            }
        });

        studentsTbl.setItems(studentData);
    }
    void initializeVars(){
        variantsTbl.setEditable(true);
        varId.setEditable(false);

        varId.setCellValueFactory(data -> data.getValue().idProperty().asObject());
        varId.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

        pathId.setCellValueFactory(data -> data.getValue().nameProperty());
        pathId.setCellFactory(TextFieldTableCell.forTableColumn());
        pathId.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Variants, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Variants, String> studentsStringCellEditEvent) {
                Variants var  = studentsStringCellEditEvent.getRowValue();
                String newVar = studentsStringCellEditEvent.getNewValue();
                String oldVar = var.getName();
                if (varData.contains(new Variants(var.getId(), newVar))){
                    IncorrectData("Variant already in database");
                    var.setName(newVar);
                    var.setName(oldVar);
                }else var.setName(newVar);
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
