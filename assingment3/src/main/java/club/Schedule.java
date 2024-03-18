package club;

import javax.print.attribute.standard.NumberOfDocuments;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Schedule {
    private FitnessClass[] classes;
    private int numClasses;

    public Schedule() {
        final int NUM_CLASSES_FILE = 15;

        this.classes = new FitnessClass[NUM_CLASSES_FILE];
        this.numClasses = 0;
    }




    public FitnessClass[] getClasses() {
        return classes;
    }

    public int getNumClasses() {
        return numClasses;
    }

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

    public int findClassOffer(Time time, Instructor instructor, Offer offer){
        final int NOT_FOUND = -1;
        for(int i = 0; i < this.numClasses; i++){
            if(this.classes[i].getInstructor() == instructor && this.classes[i].getTime() == time &&
                    this.classes[i].getClassInfo() == offer){
                return i;
            }
        }
        return NOT_FOUND;
    }


    public int findClass(Time time, Instructor instructor){
        final int NOT_FOUND = -1;
        for(int i = 0; i < this.numClasses; i++){
            if(this.classes[i].getInstructor() == instructor && this.classes[i].getTime() == time){
                return i;
            }
        }
        return NOT_FOUND;
    }

    public boolean addMemberToClass(Member member, Time time, Instructor instructor){
        int index = findClass(time, instructor);
        return this.classes[index].addMember(member);
    }

    public boolean removeMemberFromClass(Member member, Time time, Instructor instructor){
        int index = findClass(time, instructor);
        return this.classes[index].removeMember(member);
    }


    public boolean addGuestToClass(Member member, Time time, Instructor instructor){
        int index = findClass(time, instructor);
        return this.classes[index].addGuest(member);
    }

    public boolean removeGuestFromClass(Member member, Time time, Instructor instructor, Offer offer, Location location){
        int index = findIndexByAll(instructor, offer, location, time);
        return this.classes[index].removeGuest(member);
    }







    private void parseTokens(StringTokenizer tokens) {
        if (tokens.countTokens() >= 4) {
            Offer offer = Offer.valueOf(tokens.nextToken().toUpperCase());
            Instructor instructor = Instructor.valueOf(tokens.nextToken());
            Time time =  Time.valueOf(tokens.nextToken().toUpperCase());
            Location location =  Location.valueOf(tokens.nextToken().toUpperCase()); // Location

            FitnessClass newClass = new FitnessClass(offer, instructor, location, time);


            this.classes[this.numClasses] = newClass;
            this.numClasses++;
        }
    }

    public boolean validClassStudio(Location location, Offer offer, Instructor instructor){
        for(int i = 0; i < this.numClasses; i++){
            if(this.classes[i].getClassInfo() != offer ||this.classes[i].getInstructor() != instructor ||
                    this.classes[i].getStudio() != location){
                return false;
            }
        }
        return true;
    }


    public Time findClassTime(Instructor instructor, Offer offer) {
        for (FitnessClass fitnessClass : classes) {
            if (fitnessClass.getInstructor().equals(instructor) && fitnessClass.getClassInfo().equals(offer)) {
                return fitnessClass.getTime(); // Return the time of the class
            }
        }
        return null; // If no matching class is found, return null
    }






    public Time findClassTimeByLocation(Instructor instructor, Offer offer, Location location) {

        for (FitnessClass fitnessClass : classes) {
            if (fitnessClass.getInstructor() == instructor &&
                    fitnessClass.getClassInfo() == offer &&
                    fitnessClass.getStudio() == location) {
                return fitnessClass.getTime();
            }
        }
        return null; // If no matching class is found, return null
    }

    public int findIndexByAll(Instructor instructor, Offer offer, Location location, Time time) {
        int NOT_FOUND = -1;
        for (int i = 0; i < classes.length; i++) {
            FitnessClass fitnessClass = classes[i];
            if (fitnessClass.getInstructor() == instructor &&
                    fitnessClass.getClassInfo() == offer &&
                    fitnessClass.getStudio() == location
                    && fitnessClass.getTime() == time) {
                return i; // Return the time of the class
            }
        }
        return NOT_FOUND;
    }









}