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
