package com.company.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;

import javax.swing.text.html.ImageView;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminPageController implements Initializable {

    @FXML
    private Hyperlink adminListLNK;

    @FXML
    private Hyperlink sellerListLNK;

    @FXML
    private ImageView imageId;

    @FXML
    private Hyperlink customerListLNK;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
