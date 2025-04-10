package org.keyin.memberships;

import java.util.List;

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

    public List<Membership> getMembershipsForUser(int memberId) {
        return dao.getMembershipsByMemberId(memberId);
    }

    public Membership getMembershipById(int membershipId) {
        return dao.getMembershipById(membershipId);
    }

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

    public boolean deleteMembership(int membershipId) {
        boolean deleted = dao.deleteMembership(membershipId);
        if (deleted) {
            System.out.println("Membership deleted successfully!");
        }
        return deleted;
    }

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
