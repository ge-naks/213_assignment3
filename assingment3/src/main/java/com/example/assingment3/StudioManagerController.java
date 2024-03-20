package com.example.assingment3;

import club.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

import java.util.Scanner;
import java.nio.file.attribute.AttributeView;
import java.util.Calendar;
import java.util.StringTokenizer;


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


    @FXML
    private Button addButton, removeMember, loadMembers;

    private Date dobFinal;

    private MemberList memberList = new MemberList();
    private boolean loadedFlag = false;


    public void memberButtonClick(ActionEvent event) {
        errorMsg.setText("");
        if (basicButton.isSelected()) {
            guestPassLabel.setText("0");
            membershipType = "Basic";
        } else if (familyButton.isSelected()) {
            guestPassLabel.setText("1");
            membershipType = "Family";
        } else if (premiumButton.isSelected()) {
            guestPassLabel.setText("3");

            membershipType = "Premium";
        }
    }

    public void setFname(KeyEvent event){
        errorMsg.setText("");
        fname = firstName.getText();
    }

    public void setLname(KeyEvent event){
        errorMsg.setText("");
        lname = lastName.getText();
    }

    public String formatDate(String date){
        StringTokenizer tokenizer = new StringTokenizer(date, "-");
        String y = tokenizer.nextToken();
        String m = tokenizer.nextToken();
        String d = tokenizer.nextToken();

        return m + "/" + d + "/" + y;
    }

    public void setDOB(ActionEvent event){
        errorMsg.setText("");
        String strDob = formatDate(dob.getValue().toString());
        Date testDate = new Date(strDob);
        if(testDate.isFuture()){
            errorMsg.setText("Can't be Future Date!");
        }else if(!testDate.validDOB()){
            errorMsg.setText("Must be 18 or older!");
        }else{
            dobFinal = testDate;
        }
    }


    public void studioButtonClick(ActionEvent event) {
        errorMsg.setText("");
        if (studioBridgewater.isSelected()) {
            studio = "BRIDGEWATER";
        } else if (studioEdison.isSelected()) {
            studio = "EDISON";
        } else if (studioFranklin.isSelected()) {
            studio = "FRANKLIN";
        } else if (studioPiscataway.isSelected()) {
            studio = "PISCATAWAY";
        } else if (studioSomerville.isSelected()) {
            studio = "SOMERVILLE";
        }
    }

    public Date calendarToDate(Calendar calendar) {
        int todayYear = calendar.get(Calendar.YEAR);
        int todayMonth = calendar.get(Calendar.MONTH) + 1; // Note: Month is zero-based, so add 1
        int todayDay = calendar.get(Calendar.DAY_OF_MONTH);

        return new Date(todayMonth, todayDay, todayYear);
    }

    public void clickAddMember(ActionEvent event){
        errorMsg.setText("");
        if(!loadedFlag){
            errorMsg.setText("Load members first!");
            return;
        }


        if(fname == null || lname == null || dobFinal == null || studio == null || membershipType == null){
            errorMsg.setText("Please fill all fields!");
            return;
        }
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, 1);
        Date expire = calendarToDate(cal);


        if(membershipType.equals("Basic")){
            Profile newProfile =  new Profile(fname, lname, dobFinal);
            Basic newBasic = new Basic(newProfile, expire, Location.valueOf(studio));
            if(!memberList.add(newBasic)) errorMsg.setText("Member Exists!");
        }else if(membershipType.equals("Family")){
            Profile newProfile =  new Profile(fname, lname, dobFinal);
            Family newFamily = new Family(newProfile,expire ,Location.valueOf(studio));
            if(!memberList.add(newFamily)) errorMsg.setText("Member Exists!");
        }else{
            Profile newProfile =  new Profile(fname, lname, dobFinal);
            Premium newPremium = new Premium(newProfile,expire ,Location.valueOf(studio));
            if(!memberList.add(newPremium)) errorMsg.setText("Member Exists!");
        }
        tempCheck();
    }

    public void tempCheck(){
        if(memberList.getSize() == 0) System.out.println("empty!");
        for(int i = 0; i < memberList.getSize(); i++){
            System.out.println(memberList.getMembers()[i]);
        }
        System.out.println();
    }

    public void clickRemoveMember(ActionEvent event){
        errorMsg.setText("");
        if(fname == null || lname == null || dobFinal == null){
            errorMsg.setText("Please fill First Name, Last Name and DOB!");
            return;
        }

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, 1);
        Date expire = calendarToDate(cal);
        Profile newProfile =  new Profile(fname, lname, dobFinal);
        Member memberToRemove = new Member(newProfile);

        if(!memberList.remove(memberToRemove)) errorMsg.setText("Member not in list!");
        tempCheck();
    }

    public void clickLoadMembers(ActionEvent event) throws IOException {
        errorMsg.setText("");
        Stage stage = (Stage) addButton.getScene().getWindow();

        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        File selectedFile = fileChooser.showOpenDialog(stage);

        if (selectedFile != null) {
            memberList.load(selectedFile);
            loadedFlag = true;
            errorMsg.setText("File Loaded Successfully");
        } else {
            errorMsg.setText("File not loaded!");
        }
    }

    }
