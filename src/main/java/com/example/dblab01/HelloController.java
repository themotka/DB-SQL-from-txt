package com.example.dblab01;

import java.net.URL;
import javafx.event.ActionEvent;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;

public class HelloController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button deleteMarkBtn;

    @FXML
    private Button deleteStudentBtn;

    @FXML
    private Button deleteVarBtn;

    @FXML
    private Button editMarkBtn;

    @FXML
    private Button editStudentBtn;

    @FXML
    private Button editVarBtn;

    @FXML
    private Tab marksLbl;

    @FXML
    private MenuItem menuCloseBtn;

    @FXML
    private MenuItem menuCreateBackup;

    @FXML
    private MenuItem menuOpenBackup;

    @FXML
    private MenuItem menuOpenBtn;

    @FXML
    private Button newMarkBtn;

    @FXML
    private Button newStudentBtn;

    @FXML
    private Button newVarBtn;

    @FXML
    private Tab studentLbl;

    @FXML
    private Tab testLbl;

    @FXML
    private Tab variantsLbl;

    @FXML
    void closeApp(ActionEvent event) {
        System.exit(0);
    }
    @FXML
    void initialize() {

    }

}
