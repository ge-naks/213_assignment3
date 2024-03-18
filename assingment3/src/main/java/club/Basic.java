package club;

/**
 * Represents a basic membership in the club.
 * Extends the Member class.
 *
 * @author George Nakhla
 *
 */
public class Basic extends Member {
    private int numClasses; // Number of classes attended by the basic member

    /**
     * Constructs a Basic object with the provided profile, expiration date, and home studio location.
     *
     * @param profile    the profile information of the basic member
     * @param expire     the expiration date of the membership
     * @param homeStudio the home studio location of the basic member
     */
    public Basic(Profile profile, Date expire, Location homeStudio) {
        super(profile, expire, homeStudio);
        numClasses = 0;
    }

    /**
     * Retrieves the number of classes attended by the basic member.
     *
     * @return the number of classes attended
     */
    public int getNumClasses() {
        return this.numClasses;
    }

    /**
     * Sets the number of classes attended by the basic member.
     *
     * @param numClasses the number of classes attended
     */
    public void setNumClasses(int numClasses) {
        this.numClasses = numClasses;
    }

    /**
     * Gets the guest status for the basic membership.
     *
     * @return a string indicating the guest status ("not eligible" for basic membership)
     */
    public String guestStatus() {
        return "not eligible";
    }

    /**
     * Increments the number of classes attended by the basic member.
     */
    public void attendClasses() {
        this.numClasses++;
    }

    /**
     * Calculates the bill for the basic membership.
     *
     * @return the total bill amount for the basic membership
     */
    @Override
    public double bill() {
        final double BASE_FEE = 39.99;
        final int EXTRA_CLASS_FEE = 10;
        double totalBasicFee = BASE_FEE;
        if (numClasses > 4) {
            totalBasicFee += (numClasses - 4) * EXTRA_CLASS_FEE;
        }
        return totalBasicFee;
    }

    /**
     * Generates a string representation of the Basic object.
     *
     * @return a string containing information about the basic membership
     */
    @Override
    public String toString() {
        return super.toString() + ", (Basic) number of classes attended: " + numClasses;
    }
}
