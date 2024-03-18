package club;

import org.junit.Test;

import static org.junit.Assert.*;

public class MemberListTest {

    @Test
    public void testAddOnce() {
        Date expiry1 = new Date("6/12/2024");
        Date dob1 = new Date("12/14/2003");
        Profile profile1 = new Profile("John", "Doe", dob1);
        Member member1 = new Member(profile1, expiry1, Location.BRIDGEWATER);

        MemberList list = new MemberList();

        assertTrue(list.add(member1));
    }


    @Test
    public void testAddWithGrow() {
        Date expiry1 = new Date("6/12/2024");
        Date dob1 = new Date("12/14/2003");
        Profile profile1 = new Profile("John", "Doe", dob1);
        Member member1 = new Member(profile1, expiry1, Location.BRIDGEWATER);

        Date expiry2 = new Date("6/12/2024");
        Date dob2 = new Date("10/25/1995");
        Profile profile2 = new Profile("Jane", "Smith", dob2);
        Member member2 = new Member(profile2, expiry2, Location.EDISON);

        Date expiry3 = new Date("6/12/2024");
        Date dob3 = new Date("5/8/1989");
        Profile profile3 = new Profile("Michael", "Johnson", dob3);
        Member member3 = new Member(profile3, expiry3, Location.FRANKLIN);

        Date expiry4 = new Date("6/12/2024");
        Date dob4 = new Date("3/21/2001");
        Profile profile4 = new Profile("Emily", "Brown", dob4);
        Member member4 = new Member(profile4, expiry4, Location.PISCATAWAY);

        Date expiry5 = new Date("6/12/2024");
        Date dob5 = new Date("9/30/1976");
        Profile profile5 = new Profile("Sarah", "Williams", dob5);
        Member member5 = new Member(profile5, expiry5, Location.SOMERVILLE);

        MemberList list = new MemberList();

        assertTrue(list.add(member1) && list.add(member2) && list.add(member3) &&
                list.add(member4) && list.add(member5));
    }

    @Test
    public void testAddTwo() {
        Date expiry1 = new Date("6/12/2024");
        Date dob1 = new Date("12/14/2003");
        Profile profile1 = new Profile("John", "Doe", dob1);
        Member member1 = new Member(profile1, expiry1, Location.BRIDGEWATER);

        Date expiry2 = new Date("6/12/2024");
        Date dob2 = new Date("10/25/1995");
        Profile profile2 = new Profile("Jane", "Smith", dob2);
        Member member2 = new Member(profile2, expiry2, Location.EDISON);

        MemberList list = new MemberList();

        assertTrue(list.add(member2) && list.add(member1));

    }

    @Test
    public void testAddInvalidDOB() {
        Date expiry1 = new Date("6/12/2024");
        Date dob1 = new Date("12/14/2013");
        Profile profile1 = new Profile("John", "Doe", dob1);
        Member member1 = new Member(profile1, expiry1, Location.BRIDGEWATER);

        MemberList list = new MemberList();

        assertFalse(list.add(member1));
    }

    @Test
    public void testCaseSensitivity() {
        Date expiry1 = new Date("6/12/2024");
        Date dob1 = new Date("12/14/2013");
        Profile profile1 = new Profile("John", "Doe", dob1);
        Member member1 = new Member(profile1, expiry1, Location.BRIDGEWATER);

        Date expiry2 = new Date("6/12/2024");
        Date dob2 = new Date("12/14/2013");
        Profile profile2 = new Profile("john", "doe", dob1);
        Member member2 = new Member(profile2, expiry2, Location.BRIDGEWATER);

        MemberList list = new MemberList();

        assertFalse(list.add(member1) && list.add(member2));

    }

    @Test
    public void testAddDupe() {
        Date expiry1 = new Date("6/12/2024");
        Date dob1 = new Date("12/14/2003");
        Profile profile1 = new Profile("John", "Doe", dob1);
        Member member1 = new Member(profile1, expiry1, Location.BRIDGEWATER);

        MemberList list = new MemberList();
        list.add(member1);
        assertFalse(list.add(member1));

    }

    @Test
    public void testRemoveOne() {
        Date expiry1 = new Date("6/12/2024");
        Date dob1 = new Date("12/14/2003");
        Profile profile1 = new Profile("John", "Doe", dob1);
        Member member1 = new Member(profile1, expiry1, Location.BRIDGEWATER);

        MemberList list = new MemberList();
        list.add(member1);

        assertTrue(list.remove(member1));
    }


    @Test
    public void testEmptyRemove() {
        Date expiry1 = new Date("6/12/2024");
        Date dob1 = new Date("12/14/2003");
        Profile profile1 = new Profile("John", "Doe", dob1);
        Member member1 = new Member(profile1, expiry1, Location.BRIDGEWATER);

        MemberList list = new MemberList();

        assertFalse(list.remove(member1));
    }

}