package club;

/**
 * Represents a family membership in the club.
 * Extends the Member class.
 */
public class Family extends Member {
    private boolean guest; // Indicates whether the family has a guest pass or not

    /**
     * Constructs a Family object with the provided profile, expiration date, and home studio location.
     *
     * @param profile    the profile information of the family
     * @param expire     the expiration date of the membership
     * @param homeStudio the home studio location of the family
     */
    public Family(Profile profile, Date expire, Location homeStudio) {
        super(profile, expire, homeStudio);
    }

    /**
     * Marks that the family has used a guest pass.
     */
    public void broughtGuest() {
        this.guest = true;
    }

    /**
     * Marks that the family has lost the guest pass.
     */
    public void lostGuest() {
        this.guest = false;
    }

    /**
     * Checks if the family currently has a guest pass out.
     *
     * @return true if the family has a guest pass out, otherwise false
     */
    public boolean hasGuestOut() {
        return this.guest;
    }

    /**
     * Gets the status of the guest pass.
     *
     * @return a string representing the guest status ("0" for has a guest, "1" for no guest)
     */
    public String guestStatus() {
        final String HAS_GUEST = "0";
        final String HAS_NO_GUEST = "1";
        if (guest) return HAS_GUEST;
        return HAS_NO_GUEST;
    }

    /**
     * Calculates the bill for the family membership.
     *
     * @return the total bill amount for the family membership (billed quarterly)
     */
    @Override
    public double bill() {
        // billed quarterly (3 mos)
        final int NUM_MONTHS = 3;
        final double FAMILY_FEE = 49.99;
        return FAMILY_FEE * NUM_MONTHS;
    }

    /**
     * Generates a string representation of the Family object.
     *
     * @return a string containing information about the family membership
     */
    @Override
    public String toString() {
        String membershipType = "(Family) guest-pass remaining: ";
        if (expired()) {
            membershipType += "not eligible";
        } else {
            membershipType += !guest ? "1" : "0";
        }
        return super.toString() + ", " + membershipType;
    }
}
