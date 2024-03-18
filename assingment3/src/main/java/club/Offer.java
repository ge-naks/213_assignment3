package club;

/**
 * Types of classes offered
 *
 * @author George Nakhla
 *
 *
 */
public enum Offer {
    CARDIO, PILATES, SPINNING;

    /**
     * Checks if the given string matches any of the defined Offer enum constants.
     *
     * @param offer the string representation of the offer to check
     * @return true if the string matches any of the Offer enum constants, false otherwise
     */
    public static boolean tryOffer(String offer){
        try {
            Offer.valueOf(offer.toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
