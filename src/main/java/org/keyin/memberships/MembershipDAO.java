package org.keyin.memberships;

import org.keyin.database.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MembershipDAO {
    private Connection getConnection() throws SQLException {
        return DatabaseConnection.getConnection();
    }

    /**
     * Adds a new membership to the database.
     * @param membership The membership object to be added
     */
    public void addMembership(Membership membership) {
        String sql = "INSERT INTO memberships (membership_type, membership_description, membership_cost, member_id) " +
                "VALUES (?, ?, ?, ?)";
        try (Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, membership.getMembershipType());
            preparedStatement.setString(2, membership.getMembershipDescription());
            preparedStatement.setDouble(3, membership.getMembershipCost());
            preparedStatement.setInt(4, membership.getMemberId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves a membership from the database by its ID.
     * @param membershipId The ID of the membership to retrieve
     * @return Membership object if found, null if not found
     * @throws SQLException if there's an error executing the SQL statement
     */
    public Membership getMembershipById(int membershipId) {
        String sql = "SELECT * FROM memberships WHERE membership_id = ?";
        try (Connection connection = getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, membershipId);
            try (ResultSet resultSet = ps.executeQuery()) {
                if (resultSet.next()) {
                    return new Membership(
                            resultSet.getInt("membership_id"),
                            resultSet.getString("membership_type"),
                            resultSet.getString("membership_description"),
                            resultSet.getDouble("membership_cost"),
                            resultSet.getInt("member_id"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Retrieves all memberships associated with a specific member.
     * @param memberId The ID of the member
     * @return List of memberships associated with the member
     * @throws SQLException if there's an error executing the SQL statement
     */
    public List<Membership> getMembershipsByMemberId(int memberId) {
        List<Membership> memberships = new ArrayList<>();
        String sql = "SELECT * FROM memberships WHERE member_id = ?";
        try (Connection connection = getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, memberId);
            try (ResultSet resultSet = ps.executeQuery()) {
                while (resultSet.next()) {
                    Membership membership = new Membership(
                            resultSet.getInt("membership_id"),
                            resultSet.getString("membership_type"),
                            resultSet.getString("membership_description"),
                            resultSet.getDouble("membership_cost"),
                            resultSet.getInt("member_id"));
                    memberships.add(membership);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return memberships;
    }

    /**
     * Updates an existing membership in the database.
     * @param membership The membership object to be updated
     * @return true if membership was successfully updated, false otherwise
     * @throws SQLException if there's an error executing the SQL statement
     */
    public boolean updateMembership(Membership membership) {
        String sql = "UPDATE memberships SET membership_type = ?, membership_description = ?, " +
                "membership_cost = ?, member_id = ? WHERE membership_id = ?";
        try (Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, membership.getMembershipType());
            preparedStatement.setString(2, membership.getMembershipDescription());
            preparedStatement.setDouble(3, membership.getMembershipCost());
            preparedStatement.setInt(4, membership.getMemberId());
            preparedStatement.setInt(5, membership.getMembershipId());
            int rowsUpdated = preparedStatement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Deletes a membership from the database by its ID.
     * @param membershipId The ID of the membership to delete
     * @return true if membership was successfully deleted, false otherwise
     * @throws SQLException if there's an error executing the SQL statement
     */
    public boolean deleteMembership(int membershipId) {
        String sql = "DELETE FROM memberships WHERE membership_id = ?";
        try (Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, membershipId);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Retrieves all memberships from the database.
     * @return List of all memberships
     * @throws SQLException if there's an error executing the SQL statement
     */
    public List<Membership> getAllMemberships() {
        List<Membership> memberships = new ArrayList<>();
        String sql = "SELECT * FROM memberships";
        try (Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Membership membership = new Membership(
                            resultSet.getInt("membership_id"),
                            resultSet.getString("membership_type"),
                            resultSet.getString("membership_description"),
                            resultSet.getDouble("membership_cost"),
                            resultSet.getInt("member_id"));
                    memberships.add(membership);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return memberships;
    }
}
