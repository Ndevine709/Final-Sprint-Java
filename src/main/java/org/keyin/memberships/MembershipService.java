package org.keyin.memberships;

import java.util.List;

/**
 * Service class for handling membership operations.
 * Provides methods to purchase, retrieve, update, and delete memberships.
 */
public class MembershipService {
    private MembershipDAO dao = new MembershipDAO();

    public boolean purchaseMembership(Membership membership) {
        if (membership == null) {
            System.out.println("Membership cannot be null");
            return false;
        }
        dao.addMembership(membership);
        System.out.println("Membership purchased successfully!");
        return true;
    }

    /**
     * Retrieves all memberships associated with a specific member.
     * @param memberId The ID of the member
     * @return List of memberships associated with the member
     * @throws SQLException if there's an error executing the SQL statement
     */
    public List<Membership> getMembershipsForUser(int memberId) {
        return dao.getMembershipsByMemberId(memberId);
    }

    /**
     * Retrieves a membership from the database by its ID.
     * @param membershipId The ID of the membership to retrieve
     * @return Membership object if found, null if not found
     * @throws SQLException if there's an error executing the SQL statement
     */
    public Membership getMembershipById(int membershipId) {
        return dao.getMembershipById(membershipId);
    }

    /**
     * Updates an existing membership in the database.
     * @param membership The membership object to be updated
     * @return true if membership was successfully updated, false otherwise
     * @throws SQLException if there's an error executing the SQL statement
     */
    public boolean updateMembership(Membership membership) {
        if (membership == null) {
            System.out.println("Membership cannot be null");
            return false;
        }
        boolean updated = dao.updateMembership(membership);
        if (updated) {
            System.out.println("Membership updated successfully!");
        }
        return updated;
    }

    /**
     * Deletes a membership from the database by its ID.
     * @param membershipId The ID of the membership to delete
     * @return true if membership was successfully deleted, false otherwise
     * @throws SQLException if there's an error executing the SQL statement
     */
    public boolean deleteMembership(int membershipId) {
        boolean deleted = dao.deleteMembership(membershipId);
        if (deleted) {
            System.out.println("Membership deleted successfully!");
        }
        return deleted;
    }

    /**
     * Displays all gym memberships and their total revenue.
     */
    public void displayAllMembershipsAndRevenue() {
        List<Membership> memberships = dao.getAllMemberships();
        double totalRevenue = 0.0;

        System.out.println("\n=== All Gym Memberships ===");
        if (memberships.isEmpty()) {
            System.out.println("No memberships found.");
        } else {
            System.out.println("---------------------------");
            for (Membership membership : memberships) {
                System.out.println("\nMembership ID: " + membership.getMembershipId());
                System.out.println("Type: " + membership.getMembershipType());
                System.out.println("Description: " + membership.getMembershipDescription());
                System.out.println("Cost: $" + String.format("%.2f", membership.getMembershipCost()));
                System.out.println("Member ID: " + membership.getMemberId());
                totalRevenue += membership.getMembershipCost();
                System.out.println("---------------------------");
            }
            System.out.println("\nTotal Annual Revenue: $" + String.format("%.2f", totalRevenue));
        }
    }
}
