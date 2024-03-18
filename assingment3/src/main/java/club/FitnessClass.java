package club;

/**
 * Represents a fitness class offered by the club.
 *
 *
 * @author George Nakhla
 *
 */
public class FitnessClass {
    private Offer classInfo;
    private Instructor instructor;
    private Location studio;
    private Time time;
    private MemberList members;
    private MemberList guests;

    /**
     * Constructs a FitnessClass object with the provided class information, instructor, studio location, and time.
     *
     * @param classInfo  the offer information about the class
     * @param instructor the instructor teaching the class
     * @param studio     the location where the class takes place
     * @param time       the time at which the class is scheduled
     */
    public FitnessClass(Offer classInfo, Instructor instructor, Location studio, Time time) {
        this.classInfo = classInfo;
        this.instructor = instructor;
        this.studio = studio;
        this.time = time;
        this.members = new MemberList(); // Initialize members list
        this.guests = new MemberList();  // Initialize guests list
    }

    /**
     * Retrieves the offer information about the class.
     *
     * @return the offer information about the class
     */
    public Offer getClassInfo() {
        return classInfo;
    }

    /**
     * Retrieves the instructor teaching the class.
     *
     * @return the instructor teaching the class
     */
    public Instructor getInstructor() {
        return instructor;
    }

    /**
     * Retrieves the time at which the class is scheduled.
     *
     * @return the time at which the class is scheduled
     */
    public Time getTime() {
        return time;
    }

    /**
     * Retrieves the list of guests registered for the class.
     *
     * @return the list of guests registered for the class
     */
    public MemberList getGuests() {
        return guests;
    }

    /**
     * Retrieves the list of members registered for the class.
     *
     * @return the list of members registered for the class
     */
    public MemberList getMembers() {
        return members;
    }

    /**
     * Retrieves the location where the class takes place.
     *
     * @return the location where the class takes place
     */
    public Location getStudio() {
        return studio;
    }

    /**
     * Adds a member to the class.
     *
     * @param member the member to add
     * @return true if the member is successfully added, false otherwise
     */
    public boolean addMember(Member member) {
        return this.members.add(member);
    }

    /**
     * Removes a member from the class.
     *
     * @param member the member to remove
     * @return true if the member is successfully removed, false otherwise
     */
    public boolean removeMember(Member member) {
        return this.members.remove(member);
    }

    /**
     * Adds a guest to the class.
     *
     * @param member the guest to add
     * @return true if the guest is successfully added, false otherwise
     */
    public boolean addGuest(Member member) {
        return this.guests.forceAdd(member);
    }

    /**
     * Removes a guest from the class.
     *
     * @param member the guest to remove
     * @return true if the guest is successfully removed, false otherwise
     */
    public boolean removeGuest(Member member) {
        return this.guests.remove(member);
    }

    /**
     * Returns a string representation of the fitness class.
     *
     * @return a string representation of the fitness class
     */
    @Override
    public String toString() {
        String strName = this.studio.name();
        return this.classInfo + " - " + this.instructor.toString().toUpperCase() + ", " + this.time.getTime() + ", "
                + this.studio.name();
    }
}
