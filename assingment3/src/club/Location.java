package club;

/**
 * An enum class that contains the 5 different locations that the fitness clubs are located at.
 *
 * @author George W Nakhla
 */
public enum Location {

    BRIDGEWATER("08807", "SOMERSET"),
    EDISON("08837", "MIDDLESEX"),
    FRANKLIN("08873", "SOMERSET"),
    PISCATAWAY("08854", "MIDDLESEX"),
    SOMERVILLE("08876", "SOMERSET");

    private final String zipCode;
    private final String county;

    Location(String zipCode, String county) {
        this.zipCode = zipCode;
        this.county = county;
    }

    public static boolean tryLocation(String location){
        try {
            Location.valueOf(location.toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }




    /**
     * A public method that provides a string representation
     * for the zip code for the associated location.
     *
     * @return String representation of the zip code
     */
    public String getZipCode() {
        return zipCode;
    }

    /**
     * A public method that provides a string representation
     * for the County for the associated location.
     *
     * @return String representation of the County
     */
    public String getCounty() {
        return county;
    }

    @Override
    public String toString() {
        return name() + ", " + getZipCode() + ", " + getCounty();
    }


}
