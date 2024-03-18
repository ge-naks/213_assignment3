package club;

import java.awt.color.ProfileDataException;
import java.nio.channels.CancelledKeyException;
import java.util.Calendar;

/**
 * This class represents a current Member of the fitness club.
 *
 * @author George W Nakhla
 */
public class Member implements Comparable<Member> {
    private Profile profile;
    private Date expire;
    private Location homeStudio;

    /**
     * Default constructor for the Member class.
     */
    public Member() {

    }



    /**
     * Parameterized constructor for the Member class.
     *
     * @param profile    The profile information of the member.
     * @param expire     The expiration date of the membership.
     * @param homeStudio The location of the member's home studio.
     */
    public Member(Profile profile, Date expire, Location homeStudio) {
        this.profile = profile;
        this.expire = expire;
        this.homeStudio = homeStudio;
    }

    public Location getHomeStudio() {
        return homeStudio;

    }




    public Profile getProfile() {
        return profile;
    }

    /**
     * Calculates and returns the next due amount for the membership.
     *
     * @return The next due amount for the membership.
     */
    public double bill() {
        if (this instanceof Basic) {
            return this.bill();
        } else if (this instanceof Family) {
            return this.bill();
        } else if (this instanceof Premium) {
            return this.bill();
        } else {
            // Default behavior
            return 0.0;
        }
    }

    public String guestStatus() {
        if (this instanceof Basic) {
            return ((Basic) this).guestStatus();
        } else if (this instanceof Family) {
            return ((Family) this).guestStatus();
        } else if (this instanceof Premium) {
            return ((Premium) this).guestStatus();
        } else {
            // Default behavior
            return "";
        }
    }


    public boolean expired(){
        Calendar today =  Calendar.getInstance();
        Date asDate = calendarToDate(today);

        // true if today is greater than or equal to today date
        return  asDate.compareTo(this.expire) >= 0;
    }

    public Member(Profile profile) {
        this.profile = profile;
    }

    public Date calendarToDate(Calendar calendar){
        int todayYear = calendar.get(Calendar.YEAR);
        int todayMonth = calendar.get(Calendar.MONTH) + 1; // Note: Month is zero-based, so add 1
        int todayDay = calendar.get(Calendar.DAY_OF_MONTH);

        return new Date(todayMonth, todayDay, todayYear);
    }

    /**
     * Compares this Member object with another Member object for order.
     *
     * @param member The Member object to compare with.
     * @return A negative integer, zero, or a positive integer as this object is less than, equal to, or greater than the specified object.
     */
    @Override
    public int compareTo(Member member) {
        return this.profile.compareTo(member.profile);
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param member The Member object to compare with.
     * @return true if the objects are equal, false otherwise.
     */
    public boolean equals(Member member) {
        if (!this.profile.equals(member.profile)) return false;
        if (!this.expire.equals(member.expire)) return false;
        return this.homeStudio.equals(member.homeStudio);
    }

    /**
     * Returns a string representation of the Member object.
     *
     * @return A string representation of the Member object.
     */
    public String toString() {
        if(this instanceof Premium){
            if(!this.expired()) {
                return this.profile.toString() + ", Membership expires " +
                        this.expire.toString() + ", Home Studio: " +
                        this.homeStudio.toString().toUpperCase();
            }
            return this.profile.toString() + ", Membership expired " +
                    this.expire.toString() + ", Home Studio: " +
                    this.homeStudio.toString().toUpperCase();
        }

        if(!this.expired()) {
            return this.profile.toString() + ", Membership expires " +
                    this.expire.toString() + ", Home Studio: " +
                    this.homeStudio.toString().toUpperCase();
        }
        return this.profile.toString() + ", Membership expired " +
                this.expire.toString() + ", Home Studio: " +
                this.homeStudio.toString().toUpperCase();
    }

    public static void main(String[] args) {
        Date expiry = new Date("6/12/2024");
        Date dob = new Date("12/14/2003");



        Profile profile = new Profile("George", "Nakhla", dob );
        Member member = new Member(profile,expiry,Location.BRIDGEWATER);

        System.out.println(member);

    }

}
