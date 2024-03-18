package club;


import java.util.Calendar;

/**
 * Represents a profile for a previous or current member of the fitness club. Contains
 * name and dob
 *
 * @author George W Nakhla
 */
public class Profile implements Comparable<Profile> {
    private String fname;
    private String lname;
    private Date dob;


    /**
     * Empty constructor for a Profile object
     */
    public Profile() {

    }

    /**
     * Constructor for a Profile object that allows user control over first name,
     * last name and DOB
     *
     * @param fname string representation of first name
     * @param lname string representation of last name
     * @param dob   Date object representing DOB
     */
    public Profile(String fname, String lname, Date dob) {
        this.fname = fname;
        this.lname = lname;
        this.dob = dob;
    }

    public Date getDob(){
        return this.dob;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public boolean validDOB(){
        Calendar today = Calendar.getInstance();
        int currentYear = today.get(Calendar.YEAR);
        int currentMonth = today.get(Calendar.MONTH) + 1;

        Date dob = this.getDob();

        int age = currentYear - dob.getYear();
        if (dob.getMonth() > currentMonth || (dob.getMonth() == currentMonth && dob.getDay() > today.get(Calendar.DAY_OF_MONTH))) {
            age--; // Reduce age if the birthday hasn't occurred yet this year
        }

        return age >= 18;

    }


    /**
     * This method compares the current Profile object with another Profile object based on their first names,
     * last names, and dates of birth.
     *
     * @param profile The Profile object to compare with.
     * @return A negative integer, zero, or a positive integer as this object is less than, equal to, or greater than the specified object.
     */
    @Override
    public int compareTo(Profile profile) {
        if (!this.fname.equalsIgnoreCase(profile.fname)) return this.fname.compareToIgnoreCase(profile.fname);
        if (!this.lname.equalsIgnoreCase(profile.lname)) return this.lname.compareToIgnoreCase(profile.lname);
        return this.dob.compareTo(profile.dob);
    }

    /**
     * This method checks whether the current Profile object is equal to another Profile object.
     * Two Profile objects are considered equal if their first names, last names, and dates of birth are equal.
     *
     * @param profile The Profile object to compare with.
     * @return true if the objects are equal, false otherwise.
     */
    public boolean equals(Profile profile) {
        if (!this.lname.equalsIgnoreCase(profile.lname)) return false;
        if (!this.fname.equalsIgnoreCase(profile.fname)) return false;
        return this.dob.equals(profile.dob);
    }

    /**
     * This method returns a string representation of the Profile object.
     * The string contains the first name, last name, and date of birth of the Profile object.
     *
     * @return A string representation of the Profile object.
     */
    public String toString() {
        return this.fname + ":" + this.lname + ":" + this.dob.toString();
    }

    public static void main(String[] args) {

    }
}

