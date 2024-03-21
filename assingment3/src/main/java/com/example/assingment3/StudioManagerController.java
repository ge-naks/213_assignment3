package com.example.assingment3;

import club.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.security.Key;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.StringTokenizer;


public class StudioManagerController implements Initializable {
    @FXML
    private Label guestPassLabel, errorMsg, classSchedMsg, classErrorMsg;

    @FXML
    private RadioButton basicButton, familyButton, premiumButton;

    @FXML
    private RadioButton studioBridgewater, studioEdison, studioFranklin, studioPiscataway, studioSomerville,
            classBridgewater, classEdison, classFranklin, classPiscataway, classSomerville;

    @FXML
    private TextField firstName, lastName, classFName, classLName, remainingLabel;

    @FXML
    private DatePicker dob, classDOB;

    private Schedule schedule = new Schedule();

    private String fname, lname, strDob, studio, membershipType;


    @FXML
    private RadioButton instructorJennifer, instructorKim, instructorDenise, instructorDavis, instructorEmma;

    @FXML
    private RadioButton buttonCardio, buttonPilates, buttonSpinning;

    @FXML
    private Button addButton, removeMember, loadMembers, loadSchedule;

    @FXML
    private TableView<LocationData> studioLocationTable;

    @FXML
    private TableView<FitnessClass> scheduleTable;

    @FXML
    private TableColumn<FitnessClass, String> offer;

    @FXML
    private TableColumn<FitnessClass, String> instructor;

    @FXML
    private TableColumn<FitnessClass, String> studioLocation;

    @FXML
    private TableColumn<FitnessClass, String> time;



    @FXML
    private TableColumn<LocationData, String> city;
    @FXML
    private TableColumn<LocationData, String> zip;
    @FXML
    private TableColumn<LocationData, String> county;

    @FXML
    private TextArea printByTextArea;

    private Date dobFinal;

    private MemberList memberList = new MemberList();
    private boolean loadedMembersFlag = false;
    private boolean loadedScheduleFlag = false;

    private String selectedOffer, selectedInstructor, selectedClassLocation, typedClassFName, typedClassLName;

    private Date classDOBFinal;

    public void classButtonClick(ActionEvent event){
        if(buttonPilates.isSelected()){
            selectedOffer = "PILATES";
        }else if(buttonSpinning.isSelected()){
            selectedOffer = "SPINNING";
        }else{
            selectedOffer = "CARDIO";
        }
    }

    public void classInstructorClick(ActionEvent event){
        if(instructorJennifer.isSelected()){
            selectedInstructor = "Jennifer";
        } else if (instructorKim.isSelected()) {
            selectedInstructor = "Kim";
        } else if (instructorDenise.isSelected()) {
            selectedInstructor = "Denise";
        }else if (instructorDavis.isSelected()){
            selectedInstructor = "Davis";
        }else{
            selectedInstructor = "Emma";
        }
    }

    public void classStudioClick(ActionEvent event){
        if(classBridgewater.isSelected()){
            selectedClassLocation = "BRIDGEWATER";
        } else if (classEdison.isSelected()) {
            selectedClassLocation = "EDISON";
        } else if (classFranklin.isSelected()) {
            selectedClassLocation = "FRANKLIN";
        }else if(classPiscataway.isSelected()){
            selectedClassLocation = "PISCATAWAY";
        }else{
            selectedClassLocation = "SOMERVILLE";
        }
    }

    public void setClassFName(KeyEvent event){
        classErrorMsg.setText("");
        typedClassFName = classFName.getText();
        classErrorMsg.setText(typedClassFName);
    }

    public void setClassLName(KeyEvent event){
        classErrorMsg.setText("");
        typedClassLName = classLName.getText();
        classErrorMsg.setText(typedClassLName);
    }

    public void setClassDOB(ActionEvent event) {
        classErrorMsg.setText("");
        String strDob = formatDate(classDOB.getValue().toString());
        Date testDate = new Date(strDob);
        if (testDate.isFuture()) {
            classErrorMsg.setText("Can't be Future Date!");
        } else if (!testDate.validDOB()) {
            classErrorMsg.setText("Must be 18 or older!");
        } else {
            classDOBFinal = testDate;
        }
    }






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

    public void setFname(KeyEvent event) {
        errorMsg.setText("");
        fname = firstName.getText();
    }

    public void setLname(KeyEvent event) {
        errorMsg.setText("");
        lname = lastName.getText();
    }

    public String formatDate(String date) {
        StringTokenizer tokenizer = new StringTokenizer(date, "-");
        String y = tokenizer.nextToken();
        String m = tokenizer.nextToken();
        String d = tokenizer.nextToken();

        return m + "/" + d + "/" + y;
    }

    public void setDOB(ActionEvent event) {
        errorMsg.setText("");
        String strDob = formatDate(dob.getValue().toString());
        Date testDate = new Date(strDob);
        if (testDate.isFuture()) {
            errorMsg.setText("Can't be Future Date!");
        } else if (!testDate.validDOB()) {
            errorMsg.setText("Must be 18 or older!");
        } else {
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

    public void clickAddMember(ActionEvent event) {
        errorMsg.setText("");
        if (!loadedMembersFlag) {
            errorMsg.setText("Load members first!");
            return;
        }


        if (fname == null || lname == null || dobFinal == null || studio == null || membershipType == null) {
            errorMsg.setText("Please fill all fields!");
            return;
        }
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, 1);
        Date expire = calendarToDate(cal);


        if (membershipType.equals("Basic")) {
            Profile newProfile = new Profile(fname, lname, dobFinal);
            Basic newBasic = new Basic(newProfile, expire, Location.valueOf(studio));
            if (!memberList.add(newBasic)) errorMsg.setText("Member Exists!");
        } else if (membershipType.equals("Family")) {
            Profile newProfile = new Profile(fname, lname, dobFinal);
            Family newFamily = new Family(newProfile, expire, Location.valueOf(studio));
            if (!memberList.add(newFamily)) errorMsg.setText("Member Exists!");
        } else {
            Profile newProfile = new Profile(fname, lname, dobFinal);
            Premium newPremium = new Premium(newProfile, expire, Location.valueOf(studio));
            if (!memberList.add(newPremium)) errorMsg.setText("Member Exists!");
        }
    }


    public void clickRemoveMember(ActionEvent event) {
        errorMsg.setText("");
        if (fname == null || lname == null || dobFinal == null) {
            errorMsg.setText("Please fill First Name, Last Name and DOB!");
            return;
        }

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, 1);
        Date expire = calendarToDate(cal);
        Profile newProfile = new Profile(fname, lname, dobFinal);
        Member memberToRemove = new Member(newProfile);

        if (!memberList.remove(memberToRemove)) errorMsg.setText("Member not in list!");
    }

    public void clickLoadMembers(ActionEvent event) throws IOException {
        errorMsg.setText("");
        Stage stage = (Stage) addButton.getScene().getWindow();

        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Text files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        File selectedFile = fileChooser.showOpenDialog(stage);
        
        if (selectedFile != null) {
            try {
                memberList.load(selectedFile);
                loadedMembersFlag = true;
                errorMsg.setText("File Loaded Successfully");
            } catch (Exception e) {
                errorMsg.setText("Error loading file. Wrong file, perchance?");
            }
        } else {
            errorMsg.setText("File empty!");
        }
    }

    ObservableList<LocationData> list = FXCollections.observableArrayList(
            new LocationData("BRIDGEWATER", "08807", "SOMERSET"),
            new LocationData("EDISON", "08837", "MIDDLESEX"),
            new LocationData("FRANKLIN", "08873", "SOMERSET"),
            new LocationData("PISCATAWAY", "08854", "MIDDLESEX"),
            new LocationData("SOMERVILLE", "08876", "SOMERSET")
    );



    public void clickPrintProfile(ActionEvent event){
        printByTextArea.setText(memberList.printByMember());
    }

    public void clickPrintCounty(ActionEvent event){
        printByTextArea.setText(memberList.printByCounty());
    }

    public void clickPrintDues(ActionEvent event){
        printByTextArea.setText(memberList.printFees());
    }

    public void clickClear(ActionEvent event){
        printByTextArea.setText("");
    }


    public void clickLoadSchedule(ActionEvent event){
        errorMsg.setText("");
        Stage stage = (Stage) addButton.getScene().getWindow();

        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Text files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        File selectedFile = fileChooser.showOpenDialog(stage);

        if (selectedFile != null) {
            try {
                schedule.load(selectedFile);
                loadedScheduleFlag = true;
                classSchedMsg.setText("File Loaded Successfully");
                ObservableList<FitnessClass> scheduleData = FXCollections.observableArrayList(schedule.getClasses());
                scheduleTable.setItems(scheduleData);
            } catch (Exception e) {
                classSchedMsg.setText("Error loading file. Wrong file, perchance?");
            }
        } else {
            classSchedMsg.setText("File empty!");
        }
    }

    public static String getStringFromObservableList(ObservableList<LocationData> list) {
        StringBuilder stringBuilder = new StringBuilder();

        for (LocationData locationData : list) {
            stringBuilder.append("City: ").append(locationData.getCity())
                    .append(", Zip: ").append(locationData.getZip())
                    .append(", County: ").append(locationData.getCounty())
                    .append("\n");
        }

        // Remove the last newline character if the list is not empty
        if (!list.isEmpty()) {
            stringBuilder.setLength(stringBuilder.length() - 1);
        }

        return stringBuilder.toString();
    }

    public void clickShowLocations(ActionEvent event){
        printByTextArea.setText(getStringFromObservableList(list));
    }


    public Time findClassTime(String offer, String instructor, String location){
        Offer classType = Offer.valueOf(offer);
        Instructor teacher = Instructor.valueOf(instructor);
        Location studio = Location.valueOf(location);

        FitnessClass[] currentClasses = schedule.getClasses();

        for(int i = 0; i < schedule.getNumClasses(); i++){

            if(currentClasses[i].getStudio() == studio && currentClasses[i].getClassInfo() == classType
                    && currentClasses[i].getInstructor() == teacher){
                return currentClasses[i].getTime();
            }
        }
        return null;
    }



    public boolean findTimeConflict(Member member, Time time, Location location, Instructor instructor) {
        for (FitnessClass fitnessClass : schedule.getClasses()) {
            if (fitnessClass.getMembers().contains(member)) {
                if (fitnessClass.getTime() == time) {
                    return true;
                }
            }
        }
        return false;
    }


    public void classAddMember(ActionEvent event){

        int NOT_FOUND = -1;

        if(!loadedScheduleFlag){
            classErrorMsg.setText("Load schedule first!");
            return;
        }

        if( selectedClassLocation == null || selectedInstructor == null || selectedOffer == null ||
                classFName == null || classLName == null || classDOBFinal == null){
            classErrorMsg.setText("Fill out all fields!");
            return;
        }
        if(schedule.validGrouping(selectedOffer,selectedInstructor,selectedClassLocation) == NOT_FOUND){
            classErrorMsg.setText("Invalid class! Refer to schedule for valid classes");
            return;
        }

        Profile newProfile = new Profile(typedClassFName, typedClassLName, classDOBFinal);
        Member newMember = new Member(newProfile);

        if(!memberList.contains(newMember)){
            classErrorMsg.setText("Member not in list!");
            return;
        }

        int profileIndex = memberList.findProfileIndex(newProfile);
        Date exp = memberList.getMembers()[profileIndex].getExpire();
        Location studio = memberList.getMembers()[profileIndex].getHomeStudio();



        Member fullMember = new Member(newProfile, exp, studio);

        int index = this.schedule.validGrouping(selectedOffer,selectedInstructor,selectedClassLocation);
        if(!schedule.getClasses()[index].addMember(fullMember)){
            classErrorMsg.setText("Member already in class!");
        }
        Time classTime = findClassTime();
        if(findTimeConflict(member, Time time, Location location, Instructor instructor))

        remainingLabel.setText(fullMember.guestStatus());
    }

    public void classRemoveMember(ActionEvent event){

    }

    public void classAddGuest(ActionEvent event){

    }


    public void classRemoveGuest(ActionEvent event){

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        city.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCity()));
        zip.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getZip()));
        county.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCounty()));

        offer.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getClassInfo().toString()));
        instructor.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getInstructor().toString()));
        studioLocation.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStudio().toString()));
        time.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTime().toString()));

        studioLocationTable.setItems(list);
    }


}

class LocationData {
    private final String city;
    private final String zip;
    private final String county;

    public LocationData(String city, String zip, String county) {
        this.city = city;
        this.zip = zip;
        this.county = county;
    }

    public String getCity() {
        return city;
    }

    public String getZip() {
        return zip;
    }

    public String getCounty() {
        return county;
    }
}
