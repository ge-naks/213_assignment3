package club;

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

    public int validGrouping(String offer, String instructor, String location){
        int NOT_FOUND = -1;
        Offer classType = Offer.valueOf(offer);
        Instructor teacher = Instructor.valueOf(instructor);
        Location studio = Location.valueOf(location);

        FitnessClass[] currentClasses = this.getClasses();

        for(int i = 0; i < this.getNumClasses(); i++){

            if(currentClasses[i].getStudio() == studio && currentClasses[i].getClassInfo() == classType
                    && currentClasses[i].getInstructor() == teacher) return i;
        }
        return NOT_FOUND;

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