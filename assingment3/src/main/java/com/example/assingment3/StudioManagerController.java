package com.example.assingment3;

import club.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;


public class StudioManagerController {
    @FXML
    private Label guestPassLabel, errorMsg;

    @FXML
    private RadioButton basicButton, familyButton, premiumButton;

    @FXML
    private RadioButton studioBridgewater, studioEdison, studioFranklin, studioPiscataway, studioSomerville;

    @FXML
    private TextField firstName, lastName;

    @FXML
    private DatePicker dob;



    private String fname, lname, strDob, studio, membershipType;



    public void memberButtonClick(ActionEvent event) {
        if (basicButton.isSelected()) {
            guestPassLabel.setText("0");
            membershipType = "basic";
        } else if (familyButton.isSelected()) {
            guestPassLabel.setText("1");
            membershipType = "family";
        } else if (premiumButton.isSelected()) {
            guestPassLabel.setText("3");
            membershipType = "premium";
        }
    }

    public void setFname(KeyEvent event){
        fname = firstName.getText();
    }

    public void setLname(KeyEvent event){
        lname = lastName.getText();
    }

    public void setDOB(){
        
    }


    public void studioButtonClick(ActionEvent event) {
        if (studioBridgewater.isSelected()) {
            studio = "Bridgewater";
        } else if (studioEdison.isSelected()) {
            studio = "Edison";
        } else if (studioFranklin.isSelected()) {
            studio = "Franklin";
        } else if (studioPiscataway.isSelected()) {
            studio = "Piscataway";
        } else if (studioSomerville.isSelected()) {
            studio = "Somerville";
        }
    }

}