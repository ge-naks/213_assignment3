package club;

import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.util.StringTokenizer;

/**
 * Represents a list of club members.
 */
public class MemberList {
    private Member[] members; //holds Basic, Family, or Premium objects
    private int size; //number of objects in the array
    private final int NOT_FOUND = -1;

    /**
     * Constructs an empty MemberList with an initial capacity of 4.
     */
    public MemberList() {
        this.size = 0;
        int INITIAL_CAPACITY = 4;
        this.members = new Member[INITIAL_CAPACITY];
    }

    /**
     * Gets the size of the member list.
     *
     * @return the number of members in the list
     */
    public int getSize() {
        return size;
    }

    /**
     * Gets the array of members.
     *
     * @return the array of members
     */
    public Member[] getMembers() {
        return members;
    }

    private int find(Member member) {
        for (int i = 0; i < size; i++) {
            if (this.members[i].getProfile().equals(member.getProfile())) return i;
        }
        return NOT_FOUND;
    }

    public int findProfileIndex(Profile profile) {
        for (int i = 0; i < size; i++) {
            if (this.members[i].getProfile().equals(profile)) return i;
        }
        return NOT_FOUND;
    }


    public boolean foundProfile(Profile profile){
        for (int i = 0; i < size; i++) {
            if (this.members[i].getProfile().equals(profile)) return true;
        }
        return false;
    }

    private void grow() {
        int CAPACITY_INCREMENT = 4;
        Member[] longer = new Member[size + CAPACITY_INCREMENT];
        for (int i = 0; i < size; i++) {
            longer[i] = this.members[i];
        }
        this.members = longer;
    }

    public boolean contains(Member member) {
        return this.find(member) != NOT_FOUND;
    }

    public boolean forceAdd(Member member){
        if (this.size == this.members.length) this.grow();
        this.members[size] = member;
        this.size++;
        return true;
    }

    public boolean add(Member member) {
        if(!member.getProfile().validDOB()) return false;
        if (!this.contains(member)) {
            if (this.size == this.members.length) this.grow();
            this.members[size] = member;
            this.size++;
            return true;
        }
        return false;
    }

    public boolean remove(Member member) {
        if (!this.contains(member)) {
            return false;
        }
        int indexToRemove = this.find(member);
        this.members[indexToRemove] = null;

        for (int i = indexToRemove; i < this.size - 1; i++) {
            this.members[i] = this.members[i + 1];
        }
        this.size--;
        return true;

    } //shift up to remove

    public void load(File file) throws IOException {
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                StringTokenizer tokens = new StringTokenizer(line, " ");
                parseTokens(tokens);
            }
        }
    }

    public void parseTokens(StringTokenizer tokens) {
        String planType = tokens.nextToken();
        switch (planType) {
            case "B":
                parseB(tokens);
                break;
            case "F":
                parseF(tokens);
                break;
            case "P":
                parseP(tokens);
                break;
        }
    }

    public void parseB(StringTokenizer tokens) {
        String fname = tokens.nextToken();
        String lname = tokens.nextToken();
        Date dob = new Date(tokens.nextToken());

        Profile profile = new Profile(fname, lname, dob);


        Date expire = new Date(tokens.nextToken());
        Location homeStudio = Location.valueOf(tokens.nextToken());

        Basic newBasic = new Basic(profile, expire, homeStudio);

        this.add(newBasic);
    }


    public void parseF(StringTokenizer tokens) {
        String fname = tokens.nextToken();
        String lname = tokens.nextToken();
        Date dob = new Date(tokens.nextToken());

        Profile profile = new Profile(fname, lname, dob);

        Date expire = new Date(tokens.nextToken());
        Location homeStudio = Location.valueOf(tokens.nextToken());

        Family newFamily = new Family(profile, expire, homeStudio);

        this.add(newFamily);
    }

    public void parseP(StringTokenizer tokens) {
        int numGuests = 0;
        String fname = tokens.nextToken();
        String lname = tokens.nextToken();
        Date dob = new Date(tokens.nextToken());

        Profile profile = new Profile(fname, lname, dob);


        Date expire = new Date(tokens.nextToken());
        Location homeStudio = Location.valueOf(tokens.nextToken());

        Premium newPremium = new Premium(profile, expire, homeStudio);


        this.add(newPremium);
    }



    public void printByCounty() {
        System.out.println();
        System.out.println("-list of members sorted by county then zipcode-");

        // Bubble sort implementation
        for (int i = 0; i < this.size - 1; i++) {
            for (int j = 0; j < this.size - i - 1; j++) {
                // Compare counties
                int countyComparison = members[j].getHomeStudio().getCounty().compareTo(members[j + 1].getHomeStudio().getCounty());

                if (countyComparison > 0 || (countyComparison == 0 && members[j].getHomeStudio().getZipCode().compareTo(members[j + 1].getHomeStudio().getZipCode()) > 0)) {
                    // Swap members
                    Member temp = members[j];
                    members[j] = members[j + 1];
                    members[j + 1] = temp;
                }
            }
        }

        // Print the sorted members
        for (int i = 0; i < this.size; i++) {
            System.out.println(members[i]);
        }
        System.out.println("-end of list-");
        System.out.println();
    }


    public void printByMember() {
        // Bubble sort implementation
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                // Compare member profiles using last names
                int lastNameComparison = members[j].getProfile().getLname().compareToIgnoreCase(members[j + 1].getProfile().getLname());
                if (lastNameComparison > 0 ||
                        (lastNameComparison == 0 &&
                                members[j].getProfile().getFname().compareToIgnoreCase(members[j + 1].getProfile().getFname()) > 0) ||
                        (lastNameComparison == 0 &&
                                members[j].getProfile().getFname().equalsIgnoreCase(members[j + 1].getProfile().getFname()) &&
                                members[j].getProfile().getDob().compareTo(members[j + 1].getProfile().getDob()) > 0)) {
                    // Swap members
                    Member temp = members[j];
                    members[j] = members[j + 1];
                    members[j + 1] = temp;
                }
            }
        }
        // Print the sorted members
        for (int i = 0; i < size; i++) {
            System.out.println(members[i]);
        }
    }


    public void printFees() {

        System.out.println();
        System.out.println("-list of members with next dues-");
        for(int i = 0; i < size; i ++){
            System.out.println(this.members[i]  +
                      " [next due: $" + this.members[i].bill() + "]");
        }
        System.out.println("-end of list-");
        System.out.println();

    }


}
