package club;

/**
 * Represents a premium membership in the club.
 * Extends the Member class.
 */
public class Premium extends Member {
    private int guestPass; // Number of guest passes associated with the premium membership

    /**
     * Constructs a Premium object with the provided profile, expiration date, and home studio location.
     *
     * @param profile    the profile information of the premium member
     * @param expire     the expiration date of the membership
     * @param homeStudio the home studio location of the premium member
     */
    public Premium(Profile profile, Date expire, Location homeStudio) {
        super(profile, expire, homeStudio);
    }

    /**
     * Retrieves the number of outstanding guest passes associated with the premium membership.
     *
     * @return the number of outstanding guest passes
     */
    public int getPassOutstanding() {
        return this.guestPass;
    }

    /**
     * Checks if the premium member can have more guest passes.
     *
     * @return true if the premium member can have more guest passes, otherwise false
     */
    public boolean hasMorePass() {
        final int MAX_GUESTS = 3;
        return this.guestPass < MAX_GUESTS;
    }

    /**
     * Adds a guest pass to the premium membership if there is space available.
     *
     * @return true if the guest pass was successfully added, otherwise false
     */
    public boolean addGuest() {
        final int MAX_GUESTS = 3;
        if (this.guestPass < MAX_GUESTS) {
            this.guestPass++;
            return true;
        }
        return false;
    }

    /**
     * Removes a guest pass from the premium membership if available.
     *
     * @return true if a guest pass was successfully removed, otherwise false
     */
    public boolean removeGuest() {
        final int NO_GUESTS = 0;
        if (this.guestPass == NO_GUESTS) return false;
        this.guestPass--;
        return true;
    }

    /**
     * Gets the status of the guest passes associated with the premium membership.
     *
     * @return a string representing the number of remaining guest passes
     */
    public String guestStatus() {
        final int MAX_GUESTS = 3;
        return Integer.toString(MAX_GUESTS - this.guestPass);
    }

    /**
     * Calculates the bill for the premium membership.
     *
     * @return the total bill amount for the premium membership (billed annually for 11 months)
     */
    @Override
    public double bill() {
        // billed annually for 11 months
        final int NUM_MONTHS = 11;
        final double PREMIUM_FEE = 59.99;
        return PREMIUM_FEE * NUM_MONTHS;
    }

    /**
     * Generates a string representation of the Premium object.
     *
     * @return a string containing information about the premium membership
     */
    @Override
    public String toString() {
        final int MAX_GUESTS = 3;
        String membershipType;
        if (!expired()) {
            membershipType = "(Premium) guest-pass remaining: ";
            if (guestPass == MAX_GUESTS) {
                membershipType += "0";
                return super.toString() + " " + membershipType;
            } else {
                membershipType += (MAX_GUESTS - guestPass);
                return super.toString() + " " + membershipType;
            }
        } else {
            membershipType = "(Premium) guest-pass remaining: not eligible";
            return super.toString() + ", " + membershipType;
        }

    }
}
