package com.example.assingment3;

import club.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.time.LocalDate;


public class StudioManagerController {
    @FXML
    private Label guestPassLabel, errorMsg;

    @FXML
    private RadioButton basicButton, familyButton, premiumButton, LoadScheduleButton;

    @FXML
    private RadioButton studioBridgewater, studioEdison, studioFranklin, studioPiscataway, studioSomerville,
    classBridgewater, classEdison, classFranklin, classPiscataway, classSomerville;

    @FXML
    private TextField firstName, lastName;

    @FXML
    private DatePicker dob;
    @FXML
    private Schedule schedule;

    private String fname, lname, strDob, studio, membershipType;

    public void load(File file) throws IOException {
        final int NOT_FOUND = -1;
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                StringTokenizer tokens = new StringTokenizer(line, " ");
                parseTokens(tokens);
            }
        }
    }


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

    public String formatDate(String date){
        StringTokenizer tokenizer = new StringTokenizer(date, "-");
        String y = tokenizer.nextToken();
        String m = tokenizer.nextToken();
        String d = tokenizer.nextToken();

        return m + "/"+d+"/"+y;
    }

    public void setDOB(ActionEvent event){
        LocalDate date = dob.getValue();
        errorMsg.setText(date.toString());
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

        public void LoadScheduleClick(ActionEvent event) throws IOException {
            Stage stage = (Stage)

            FileChooser fileChooser = new FileChooser();
            File selectedFile = fileChooser.showOpenDialog(schedule.(stage))
            if (LoadScheduleButton.isSelected()) {

            }
        }
    }
