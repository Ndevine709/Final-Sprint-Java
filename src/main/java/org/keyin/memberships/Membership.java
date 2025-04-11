package org.keyin.memberships;

/**
 * Class representing a membership.
 * Holds details about a membership including type, description, cost, and associated member.
 */
public class Membership {
    private int membershipId;
    private String membershipType;
    private String membershipDescription;
    private double membershipCost;
    private int memberId;

    /**
     * Default constructor for Membership.
     */
    public Membership() {
    }

    /**
     * Constructor for Membership with all fields.
     * @param membershipId The unique identifier for the membership
     * @param membershipType The type of membership
     * @param membershipDescription A description of the membership
     * @param membershipCost The cost of the membership
     * @param memberId The ID of the member associated with the membership
     */
    public Membership(int membershipId, String membershipType, String membershipDescription, double membershipCost,
            int memberId) {
        this.membershipId = membershipId;
        this.membershipType = membershipType;
        this.membershipDescription = membershipDescription;
        this.membershipCost = membershipCost;
        this.memberId = memberId;
    }

    /**
     * Constructor for Membership with basic fields.
     * @param membershipType The type of membership
     * @param membershipDescription A description of the membership
     * @param membershipCost The cost of the membership
     * @param memberId The ID of the member associated with the membership
     */
    public Membership(String membershipType, String membershipDescription, double membershipCost, int memberId) {
        this.membershipType = membershipType;
        this.membershipDescription = membershipDescription;
        this.membershipCost = membershipCost;
        this.memberId = memberId;
    }

    /**
     * Returns the unique identifier for the membership.
     * @return The membership ID
     */
    public int getMembershipId() {
        return membershipId;
    }

    /**
     * Sets the unique identifier for the membership.
     * @param membershipId The membership ID
     */
    public void setMembershipId(int membershipId) {
        this.membershipId = membershipId;
    }

    /**
     * Returns the type of membership.
     * @return The membership type
     */
    public String getMembershipType() {
        return membershipType;
    }

    /**
     * Sets the type of membership.
     * @param membershipType The membership type
     */
    public void setMembershipType(String membershipType) {
        this.membershipType = membershipType;
    }

    /**
     * Returns the description of the membership.
     * @return The membership description
     */
    public String getMembershipDescription() {
        return membershipDescription;
    }

    /**
     * Sets the description of the membership.
     * @param membershipDescription The membership description
     */
    public void setMembershipDescription(String membershipDescription) {
        this.membershipDescription = membershipDescription;
    }

    /**
     * Returns the cost of the membership.
     * @return The membership cost
     */
    public double getMembershipCost() {
        return membershipCost;
    }

    /**
     * Sets the cost of the membership.
     * @param membershipCost The membership cost
     */
    public void setMembershipCost(double membershipCost) {
        this.membershipCost = membershipCost;
    }

    /**
     * Returns the ID of the member associated with the membership.
     * @return The member ID
     */
    public int getMemberId() {
        return memberId;
    }

    /**
     * Sets the ID of the member associated with the membership.
     * @param memberId The member ID
     */
    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }
}
