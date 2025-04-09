package org.keyin;

import org.keyin.memberships.Membership;
import org.keyin.memberships.MembershipService;
import org.keyin.user.User;
import org.keyin.user.UserService;
import org.keyin.workoutclasses.WorkoutClassService;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class GymApp {
    public static void main(String[] args) throws SQLException {
        // Initialize services
        UserService userService = new UserService();
        MembershipService membershipService = new MembershipService();
        WorkoutClassService workoutService = new WorkoutClassService();

        // Scanner for user input
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n=== Gym Management System ===");
            System.out.println("1. Add a new user");
            System.out.println("2. Login as a user");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            // Validate input
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input! Please enter a number.");
                scanner.next();
            }

            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addNewUser(scanner, userService);
                    break;
                case 2:
                    logInAsUser(scanner, userService, membershipService, workoutService);
                    break;
                case 3:
                    System.out.println("Exiting the program...");
                    break;
                default:
                    System.out.println("Invalid choice! Please select a valid option.");
            }
        } while (choice != 3);

        scanner.close();
    }

    private static void logInAsUser(Scanner scanner, UserService userService, MembershipService membershipService,
            WorkoutClassService workoutService) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        try {
            User user = userService.login(username, password);
            if (user != null) {
                System.out.println("Login Successful! Welcome " + user.getUsername());
                String role = user.getRole();
                switch (role.toUpperCase()) {
                    case "ADMIN":
                        showAdminMenu(scanner, user, userService, membershipService, workoutService);
                        break;
                    case "TRAINER":
                        showTrainerMenu(scanner, user, userService, workoutService);
                        break;
                    case "MEMBER":
                        showMemberMenu(scanner, user, userService, membershipService);
                        break;
                    default:
                        System.out.println("Invalid role: " + role);
                        break;
                }
            } else {
                System.out.println("Login Failed! Invalid credentials.");
            }
        } catch (SQLException e) {
            System.out.println("An error occurred while logging in.");
            e.printStackTrace();
        }
    }

    // Member menu
    private static void showMemberMenu(Scanner scanner, User user, UserService userService,
            MembershipService membershipService) { 
        System.out.println("Welcome Member! " + user.getUsername());
        System.out.println("1. View all workout classes");
        System.out.println("2. Purchase a membership");
        System.out.println("3. View my membership expenses");
        System.out.println("4. Back to main menu");
        System.out.print("Enter your choice: ");

        try {
            int memberChoice = scanner.nextInt();
            scanner.nextLine();

            switch (memberChoice) {
                case 1:
                    System.out.println("Displaying available workout classes:");
                    System.out.println("Workout classes functionality under construction.");
                    break;
                case 2:
                    System.out.print("Enter membership type (e.g., Basic, Premium): ");
                    String type = scanner.nextLine();
                    System.out.print("Enter membership description: ");
                    String description = scanner.nextLine();
                    System.out.print("Enter membership cost: ");
                    double cost = scanner.nextDouble();
                    scanner.nextLine();

                    Membership newMembership = new Membership(type, description, cost, user.getUserId());
                    if (membershipService.purchaseMembership(newMembership)) {
                        System.out.println("Membership purchased successfully!");
                    } else {
                        System.out.println("Failed to purchase membership.");
                    }
                    break;
                case 3:
                    List<Membership> memberships = membershipService.getMembershipsForUser(user.getUserId());
                    if (memberships.isEmpty()) {
                        System.out.println("You have no memberships.");
                    } else {
                        double total = 0.0;
                        System.out.println("Your memberships:");
                        for (Membership m : memberships) {
                            System.out.println("ID: " + m.getMembershipId() +
                                    ", Type: " + m.getMembershipType() +
                                    ", Cost: $" + m.getMembershipCost());
                            total += m.getMembershipCost();
                        }
                        System.out.println("Total membership expenses: $" + total);
                    }
                    break;
                case 4:
                    System.out.println("Returning to main menu...");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        } catch (Exception e) {
            System.out.println("Error occurred: " + e.getMessage());
            scanner.nextLine();
        }
    }

    // Placeholder for Trainer menu
    private static void showTrainerMenu(Scanner scanner, User user, UserService userService,
            WorkoutClassService workoutService) {
        System.out.println("Trainer menu under construction.");
    }

    // Admin menu with minimal implementation
    private static void showAdminMenu(Scanner scanner, User user, UserService userService,
            MembershipService membershipService, WorkoutClassService workoutService) {
        try {
            System.out.println("\nWelcome Admin " + user.getUsername());
            System.out.println("Please make your choice below:");
            System.out.println("1. View all users");
            System.out.println("2. Delete a user");
            System.out.println("3. View memberships and annual revenue");
            System.out.println("4. Return to main menu");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    userService.viewAllUsers(user);
                    break;
                case 2:
                    System.out.print("Enter username to delete: ");
                    String usernameToDelete = scanner.nextLine();
                    userService.deleteUser(user, usernameToDelete);
                    break;
                case 3:
                    membershipService.getAllMemberships();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } catch (SQLException e) {
            System.out.println("Database error occurred: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            scanner.nextLine(); // Clear any bad input
        }
    }

    // Implementation of adding a new user
    private static void addNewUser(Scanner scanner, UserService userService) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        System.out.print("Enter role (ADMIN/TRAINER/MEMBER): ");
        String role = scanner.nextLine().toUpperCase();
        System.out.print("Enter address: ");
        String address = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter phone number: ");
        String phoneNumber = scanner.nextLine();

        User user = new User(username, password, address, email, phoneNumber, role);
        userService.addUser(user);
        System.out.println("User added successfully!");
    }
}
