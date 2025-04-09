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

    public List<Membership> getAllMemberships(){
        return dao.getAllMemberships();
    }
}
