package club;

import java.io.File;
import java.util.Calendar;
import java.util.Scanner;
import java.util.StringTokenizer;

public class StudioManager {
    private MemberList list;
    private Scanner scanner;
    private Schedule schedule;

    public StudioManager() {
        this.list = new MemberList();
        this.scanner = new Scanner(System.in);
        this.schedule = new Schedule();
    }

    public void run() {

        File existingMembers = new File("src/club/memberList.txt");
        File classSchedule = new File("src/club/classSchedule.txt");

        try {
            System.out.println("-list of members loaded-");
            this.list.load(existingMembers);

            for (int i = 0; i < this.list.getSize(); i++) {
                System.out.println(this.list.getMembers()[i]);
            }
            System.out.println("-end of list-");
            System.out.println();
            System.out.println();
        } catch (Exception e) {
            System.out.println("Error loading members." + e);
        }

        try {
            System.out.println("-Fitness classes loaded-");
            this.schedule.load(classSchedule);
            for (int i = 0; i < this.schedule.getNumClasses(); i++) {
                System.out.println(this.schedule.getClasses()[i]);
            }
            System.out.println("-end of class list-");
            System.out.println();
            System.out.println();
        } catch (Exception e) {
            System.out.println("Error loading members." + e);

        }

        System.out.println("Studio Manager is up running...");
        while (true) {
            String line = scanner.nextLine().trim(); // Trim to remove leading/trailing whitespace
            if (line.equals("Q")) {
                System.out.println("Studio Manager terminated.");
                scanner.close();
                return; // Exit the method and terminate the program
            } else if (!line.isEmpty()) {
                StringTokenizer tokens = new StringTokenizer(line, " ");
                processCommand(tokens);
            }
        }
    }

    public void handleS() {
        System.out.println("-Fitness classes-");
        printAttendance();
        System.out.println("-end of class list.");
        System.out.println();
    }

    public void handlePM() {
        System.out.println();
        System.out.println("-list of members sorted by member profiles-");
        this.list.printByMember();
        System.out.println("-end of list-");
        System.out.println();
    }


    public void processCommand(StringTokenizer tokens) {
        String command = tokens.nextToken();

        switch (command) {
            case "AB":
                parseAB(tokens);
                break;
            case "AF":
                parseAF(tokens);
                break;
            case "AP":
                parseAP(tokens);
                break;
            case "C":
                parseC(tokens);
                break;
            case "S":
                handleS();
                break;
            case "PM":
                handlePM();
                break;
            case "PC":
                this.list.printByCounty();
                break;
            case "PF":
                this.list.printFees();
                break;
            case "R":
                parseR(tokens);
                break;
            case "U":
                parseU(tokens);
                break;
            case "RG":
                parseRG(tokens);
                break;
            case "UG":
                parseUG(tokens);
                break;
            default:
                System.out.println(command + " is an invalid command!");
        }

    }


    public Date calendarToDate(Calendar calendar) {
        int todayYear = calendar.get(Calendar.YEAR);
        int todayMonth = calendar.get(Calendar.MONTH) + 1; // Note: Month is zero-based, so add 1
        int todayDay = calendar.get(Calendar.DAY_OF_MONTH);

        return new Date(todayMonth, todayDay, todayYear);
    }


    private void parseAB(StringTokenizer tokens) {
        if (tokens.countTokens() < 4) {
            System.out.println("Missing data tokens.");
            return;
        }
        String fname = tokens.nextToken();
        String lname = tokens.nextToken();
        String strDate = tokens.nextToken();
        if (!Date.tryDate(strDate)) {
            System.out.println("The date contains characters.");
            return;
        }
        Date dob = new Date(strDate);
        Profile profile = new Profile(fname, lname, dob);
        String strHomeStudio = tokens.nextToken();


        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, 1);
        Date expire = calendarToDate(cal);
        if (!Location.tryLocation(strHomeStudio)) {
            System.out.println(strHomeStudio + ": invalid studio location!");
            return;
        }
        if (!dob.isValid()) {
            System.out.println("DOB " + dob + ": invalid calendar date!");
            return;
        }
        if (dob.isFuture()) {
            System.out.println("DOB " + dob + ": cannot be today or a future date!");
            return;
        }

        if (!profile.validDOB() && dob.isValid()) {
            System.out.println("DOB " + dob + ": must be 18 or older to join!");
            return;
        }
        Location homeStudio = Location.valueOf(strHomeStudio.toUpperCase());
        Basic newBasic = new Basic(profile, expire, homeStudio);
        if (this.list.add(newBasic)) {
            System.out.println(fname + " " + lname + " added.");
        } else {
            System.out.println(fname + " " + lname + " is already in the member database.");
        }
    }

    private void parseAF(StringTokenizer tokens) {
        if (tokens.countTokens() < 4) {
            System.out.println("Missing data tokens.");
            return;
        }
        String fname = tokens.nextToken();
        String lname = tokens.nextToken();
        String strDate = tokens.nextToken();
        if (!Date.tryDate(strDate)) {
            System.out.println("The date contains characters.");
            return;
        }
        Date dob = new Date(strDate);
        Profile profile = new Profile(fname, lname, dob);
        String strHomeStudio = tokens.nextToken();
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, 3);
        Date expire = calendarToDate(cal);
        if (!Location.tryLocation(strHomeStudio)) {
            System.out.println(strHomeStudio + ": invalid studio location!");
            return;
        }
        if (!dob.isValid()) {
            System.out.println("DOB " + dob + ": invalid calendar date!");
            return;
        }
        if (!profile.validDOB() && dob.isValid()) {
            System.out.println("DOB " + dob + ": must be 18 or older to join!");
            return;
        }
        Location homeStudio = Location.valueOf(strHomeStudio.toUpperCase());
        Family newFamily = new Family(profile, expire, homeStudio);
        if (this.list.add(newFamily)) {
            System.out.println(fname + " " + lname + " added.");
        } else {
            System.out.println(fname + " " + lname + " is already in the member database.");
        }
    }

    private void parseAP(StringTokenizer tokens) {
        if (tokens.countTokens() < 4) {
            System.out.println("Missing data tokens.");
            return;
        }
        String fname = tokens.nextToken();
        String lname = tokens.nextToken();
        String strDate = tokens.nextToken();
        if (!Date.tryDate(strDate)) {
            System.out.println("The date contains characters.");
            return;
        }
        Date dob = new Date(strDate);
        Profile profile = new Profile(fname, lname, dob);
        String strHomeStudio = tokens.nextToken();
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, 12);
        Date expire = calendarToDate(cal);
        if (!Location.tryLocation(strHomeStudio)) {
            System.out.println(strHomeStudio + ": invalid studio location!");
            return;
        }
        if (!dob.isValid()) {
            System.out.println("DOB " + dob + ": invalid calendar date!");
            return;
        }
        if (!profile.validDOB() && dob.isValid()) {
            System.out.println("DOB " + dob + ": must be 18 or older to join!");
            return;
        }
        Location homeStudio = Location.valueOf(strHomeStudio.toUpperCase());
        Premium newPremium = new Premium(profile, expire, homeStudio);
        if (this.list.add(newPremium)) {
            System.out.println(fname + " " + lname + " added.");
        } else {
            System.out.println(fname + " " + lname + " is already in the member database.");
        }
    }

    private void parseC(StringTokenizer tokens) {
        if (tokens.countTokens() < 3) {
            System.out.println("Missing data tokens.");
        } else {
            String fname = tokens.nextToken();
            String lname = tokens.nextToken();
            String strDate = tokens.nextToken();
            if (!Date.tryDate(strDate)) {
                System.out.println("The date contains characters.");
                return;
            }
            Date dob = new Date(strDate);

            Profile profile = new Profile(fname, lname, dob);

            Member dummyMember = new Member(profile);
            if (this.list.remove(dummyMember)) {
                System.out.println(fname + " " + lname + " removed.");
            } else {
                System.out.println(fname + " " + lname + " is not in the member database.");
            }
        }
    }

    public static String capitalizeFirstLetter(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        str = str.toLowerCase();
        return Character.toUpperCase(str.charAt(0)) + str.substring(1);
    }



    public void parseR(StringTokenizer tokens) {
        String strOffer = tokens.nextToken();
        String strInstructor = tokens.nextToken();
        String strLocation = tokens.nextToken();
        String fname = tokens.nextToken();
        String lname = tokens.nextToken();
        Date dob = new Date(tokens.nextToken());
        Profile profile = new Profile(fname, lname, dob);
        if (!validateOffer(strOffer, strInstructor, strLocation, profile)) return;
        int index = this.list.findProfileIndex(profile);
        Member member = this.list.getMembers()[index];
        if (checkMemberValidity(member, profile)) return;
        Time time = schedule.findClassTime(Instructor.valueOf(capitalizeFirstLetter(strInstructor)), Offer.valueOf(strOffer.toUpperCase()));
        if (time == null) {
            System.out.println(strOffer + " by " + capitalizeFirstLetter(strInstructor) + " does not exist at " +
                    strLocation);
            return;
        }
        if (alreadyInClass(time, Instructor.valueOf(capitalizeFirstLetter(strInstructor)), member)) {
            System.out.println(fname + " " + lname + " is already in the class.");
            return;
        }
        if (findTimeConflict(member, time, Location.valueOf(strLocation.toUpperCase()), Instructor.valueOf(capitalizeFirstLetter(strInstructor))))
            return;
        if (member.getHomeStudio() != Location.valueOf(strLocation.toUpperCase()) && member instanceof Basic) {
            System.out.println(fname + " " + lname + " is attending a class at " + strLocation.toUpperCase() + " - [BASIC] home studio at " + member.getHomeStudio().name());
            return;
        }
        if (this.schedule.addMemberToClass(member, time, Instructor.valueOf(capitalizeFirstLetter(strInstructor)))) {
            System.out.println(fname + " " + lname + " attendance recorded " + Offer.valueOf(strOffer.toUpperCase()) + " at " + Location.valueOf(strLocation.toUpperCase()));
            if (member instanceof Basic basicMember) {
                basicMember.setNumClasses(basicMember.getNumClasses() + 1);
            }
        }
    }

    private boolean validateOffer(String strOffer, String strInstructor, String strLocation, Profile profile) {
        if (!Offer.tryOffer(strOffer)) {
            System.out.println(strOffer + " - class name does not exist.");
            return false;
        }

        if (!Location.tryLocation(strLocation)) {
            System.out.println(strLocation + " - invalid studio location.");
            return false;
        }

        if (!Instructor.tryInstructor(capitalizeFirstLetter(strInstructor))) {
            System.out.println(strInstructor + " - instructor does not exist.");
            return false;
        }

        if (this.schedule.validClassStudio(Location.valueOf(strLocation.toUpperCase()), Offer.valueOf(strOffer.toUpperCase()), Instructor.valueOf(capitalizeFirstLetter(strInstructor)))) {
            System.out.println(strOffer + " by " + capitalizeFirstLetter(strInstructor) + " does not exist at " + Location.valueOf(strLocation.toUpperCase()));
            return false;
        }

        if (!this.list.foundProfile(profile)) {
            System.out.println(profile.getFname() + " " + profile.getLname() + " " + profile.getDob() + " is not in the member database.");
            return false;
        }

        return true;
    }

    private boolean checkMemberValidity(Member member, Profile profile) {
        if (member.expired()) {
            System.out.println(profile.getFname() + " " + profile.getLname() + " " + member.getProfile().getDob() + " membership expired.");
            return true;
        }
        return false;
    }


    public boolean alreadyInClass(Time time, Instructor instructor, Member member) {
        int index = this.schedule.findClass(time, instructor);
        return this.schedule.getClasses()[index].getMembers().contains(member);
    }

    public boolean alreadyInClassGuestClass(Time time, Instructor instructor, Member member, Offer offer, Location location) {
        int index = this.schedule.findIndexByAll(instructor, offer, location, time);
        return this.schedule.getClasses()[index].getGuests().contains(member);
    }




    public boolean findTimeConflict(Member member, Time time, Location location, Instructor instructor) {
        for (FitnessClass fitnessClass : schedule.getClasses()) {
            if (fitnessClass.getMembers().contains(member)) {
                if (fitnessClass.getTime() == time) {

                    System.out.println("Time conflict - " + member.getProfile().getFname() + " " +
                            member.getProfile().getLname() + " is in another class held at " + time.getTime() +
                            " - " + instructor.toString().toUpperCase() + ", " + time.getTime() + ", " + location.name());
                    return true; //conflict exists
                }
            }
        }
        return false;
    }

    public void printAttendance() {
        FitnessClass[] classes = schedule.getClasses();
        for (int i = 0; i < schedule.getNumClasses(); i++) {
            FitnessClass fitnessClass = classes[i];
            System.out.println(fitnessClass);
            MemberList members = fitnessClass.getMembers();
            MemberList guests = fitnessClass.getGuests();
            if (members.getSize() > 0) {
                System.out.println("[Attendees]");
                for (int j = 0; j < members.getSize(); j++) {
                    System.out.println("   " + members.getMembers()[j]);
                }
            }

            if (guests.getSize() > 0) {
                System.out.println("[Guests]");
                    System.out.println("   " + guests.getMembers()[0]);
            }

        }
    }


    public void parseU(StringTokenizer tokens) {
        Offer offer = Offer.valueOf(tokens.nextToken().toUpperCase());
        Instructor instructor = Instructor.valueOf(capitalizeFirstLetter(tokens.nextToken()));
        Location location = Location.valueOf(tokens.nextToken().toUpperCase());
        String fname = tokens.nextToken();
        String lname = tokens.nextToken();
        Date dob = new Date(tokens.nextToken());

        Profile profile = new Profile(fname, lname, dob);
        Member dummyMember = new Member(profile);


        Time time = this.schedule.findClassTime(instructor, offer);

        if (alreadyInClass(time, instructor, dummyMember)) {
            this.schedule.removeMemberFromClass(dummyMember, time, instructor);
            System.out.println(fname + " " + lname + " is removed from " + instructor.toString().toUpperCase() + ", " +
                    time.getTime() + ", " + location);
        } else {
            System.out.println(fname + " " + lname + " is not in " + instructor.toString().toUpperCase() + ", " +
                    time.getTime() + ", " + location);
        }
    }

    public void parseRG(StringTokenizer tokens) {
        String strOffer = tokens.nextToken();
        String strInstructor = tokens.nextToken();
        String strLocation = tokens.nextToken();
        String fname = tokens.nextToken();
        String lname = tokens.nextToken();
        Date dob = new Date(tokens.nextToken());
        Profile profile = new Profile(fname, lname, dob);
        if (!validateOffer(strOffer, strInstructor, strLocation, profile)) return;
        int index = this.list.findProfileIndex(profile);
        Member member = this.list.getMembers()[index];
        if (checkMemberValidity(member, profile)) return;
        Time time = schedule.findClassTimeByLocation(Instructor.valueOf(capitalizeFirstLetter(strInstructor)), Offer.valueOf(strOffer.toUpperCase()),
                Location.valueOf(strLocation.toUpperCase()));
        if (time == null) {
            System.out.println(strOffer + " by " + capitalizeFirstLetter(strInstructor) + " does not exist at " + strLocation);
            return;
        }
        if (member instanceof Basic) {
            System.out.println(fname + " " + lname + " [BASIC] - no guest pass.");
            return;
        }


        if (member.getHomeStudio() != Location.valueOf(strLocation.toUpperCase())) {
            System.out.println(fname + " " + lname + " (guest) is attending a class at " + strLocation.toUpperCase() + " - home studio at " + member.getHomeStudio().name());
            return;
        }

        if (member instanceof Premium) {
            if(!((Premium) member).hasMorePass()){
                System.out.println(fname + " " + lname + " guest pass not available.");
                return;
            }
            if (this.schedule.addGuestToClass(member, time, Instructor.valueOf(capitalizeFirstLetter(strInstructor)))) {
                System.out.println(fname + " " + lname + " (guest) attendance recorded " + Offer.valueOf(strOffer.toUpperCase()) + " at " + Location.valueOf(strLocation.toUpperCase()));
                ((Premium) member).addGuest();
            }return;}
        if(!((Family) member).hasGuestOut()){
            if (this.schedule.addGuestToClass(member, time, Instructor.valueOf(capitalizeFirstLetter(strInstructor)))) {
                System.out.println(fname + " " + lname + " (guest) attendance recorded " +
                        Offer.valueOf(strOffer.toUpperCase()) + " at " + Location.valueOf(strLocation.toUpperCase()));
                ((Family) member).broughtGuest();
            }
        }else{
            System.out.println(fname + " " + lname + " guest pass not available.");
        }
    }

    public void parseUG(StringTokenizer tokens) {
        Offer offer = Offer.valueOf(tokens.nextToken().toUpperCase());
        Instructor instructor = Instructor.valueOf(capitalizeFirstLetter(tokens.nextToken()));
        Location location = Location.valueOf(tokens.nextToken().toUpperCase());
        String fname = tokens.nextToken();
        String lname = tokens.nextToken();
        Date dob = new Date(tokens.nextToken());


        Profile profile = new Profile(fname, lname, dob);
        int index = this.list.findProfileIndex(profile);
        Member member = this.list.getMembers()[index];





        Time time = this.schedule.findClassTimeByLocation(instructor, offer, location);


        if (alreadyInClassGuestClass(time, instructor, member, offer, location)) {
            this.schedule.removeGuestFromClass(member, time, instructor, offer, location);
            System.out.println(fname + " " + lname + " (guest) is removed from " + instructor.toString().toUpperCase() + ", " +
                    time.getTime() + ", " + location);
            if(member instanceof Premium){
                ((Premium) member).removeGuest();
            }else{
                ((Family) member).lostGuest();
            }
        } else {
            System.out.println(fname + " " + lname + " is not in " + instructor.toString().toUpperCase() + ", " +
                    time.getTime() + ", " + location);
        }
    }
}



