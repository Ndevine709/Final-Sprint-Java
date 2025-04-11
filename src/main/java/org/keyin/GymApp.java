package org.keyin;

import org.keyin.memberships.Membership;
import org.keyin.memberships.MembershipService;
import org.keyin.user.User;
import org.keyin.user.UserService;
import org.keyin.workoutclasses.WorkoutClass;
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
                        showTrainerMenu(scanner, user, userService, membershipService, workoutService);
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
        int memberChoice = -1;
        do {
            System.out.println("\n=== Member Menu ===");
            System.out.println("Welcome Member! " + user.getUsername());
            System.out.println("1. View all workout classes");
            System.out.println("2. Purchase a membership");
            System.out.println("3. View my membership expenses");
            System.out.println("4. Logout");
            System.out.print("Enter your choice: ");

            try {
                memberChoice = scanner.nextInt();
                scanner.nextLine(); // consume newline

                switch (memberChoice) {
                    case 1:
                        try {
                            WorkoutClassService workoutService = new WorkoutClassService();
                            List<WorkoutClass> classes = workoutService.getAllWorkoutClasses();
                            if (classes.isEmpty()) {
                                System.out.println("No workout classes available.");
                            } else {
                                System.out.println("Available workout classes:");
                                for (WorkoutClass wc : classes) {
                                    System.out.println("ID: " + wc.getClassId() +
                                            ", Type: " + wc.getClassType() +
                                            ", Description: " + wc.getClassDescription());
                                }
                            }
                        } catch (Exception ex) {
                            System.out.println("Error retrieving workout classes: " + ex.getMessage());
                        }
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
                        break;
                }
            } catch (Exception e) {
                System.out.println("Error occurred: " + e.getMessage());
                scanner.nextLine();
            }
        } while (memberChoice != 4);
    }

    // Trainer menu
    private static void showTrainerMenu(Scanner scanner, User user, UserService userService,
            MembershipService membershipService, WorkoutClassService workoutService) {
        int trainerChoice = -1;
        do {
            System.out.println("\n=== Trainer Menu ===");
            System.out.println("Welcome Trainer! " + user.getUsername());
            System.out.println("1. Create a Class");
            System.out.println("2. Update a Class");
            System.out.println("3. Delete a Class");
            System.out.println("4. View Upcoming Workout Classes");
            System.out.println("5. Purchase a Membership");
            System.out.println("6. Logout");
            System.out.print("Enter your choice: ");

            try {
                trainerChoice = scanner.nextInt();
                scanner.nextLine();

                switch (trainerChoice) {
                    case 1:
                        System.out.println("Enter class type: ");
                        String classType = scanner.nextLine();
                        System.out.println("Enter class description: ");
                        String classDescription = scanner.nextLine();
                        WorkoutClass newClass = new WorkoutClass(classType, classDescription, user.getUserId());
                        boolean isCreated = workoutService.createNewWorkoutClass(newClass);
                        if (isCreated) {
                            System.out.println("Class created successfully.");
                        } else {
                            System.out.println("Failed to create class.");
                        }
                        break;
                    case 2:
                        System.out.println("Enter class ID to update: ");
                        int classIdToUpdate = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("Enter new class type: ");
                        String newClassType = scanner.nextLine();
                        System.out.println("Enter new class description: ");
                        String newClassDescription = scanner.nextLine();
                        WorkoutClass updatedClass = new WorkoutClass(classIdToUpdate, newClassType, newClassDescription,
                                user.getUserId());
                        boolean isUpdated = workoutService.updateWorkOutClass(updatedClass);
                        if (isUpdated) {
                            System.out.println("Class updated successfully.");
                        } else {
                            System.out.println("Failed to update class.");
                        }
                        break;
                    case 3:
                        System.out.println("Enter class ID to delete: ");
                        int classIdToDelete = scanner.nextInt();
                        scanner.nextLine();
                        boolean isDeleted = workoutService.deleteWorkoutClass(classIdToDelete);
                        if (isDeleted) {
                            System.out.println("Class deleted successfully.");
                        } else {
                            System.out.println("Failed to delete class.");
                        }
                        break;
                    case 4:
                        List<WorkoutClass> workoutClasses = workoutService
                                .getAllWorkoutClassesByTrainer(user.getUserId());
                        if (workoutClasses.isEmpty()) {
                            System.out.println("You have no upcoming classes.");
                        } else {
                            System.out.println("Your Upcoming Classes:");
                            for (WorkoutClass wc : workoutClasses) {
                                System.out.println("ID: " + wc.getClassId() +
                                        ", Type: " + wc.getClassType() +
                                        ", Description: " + wc.getClassDescription());
                            }
                        }
                        break;
                    case 5:
                        System.out.print("Enter membership type (e.g., Basic, Premium): ");
                        String type = scanner.nextLine();
                        System.out.print("Enter membership description: ");
                        String description = scanner.nextLine();
                        System.out.print("Enter membership cost: ");
                        double cost = scanner.nextDouble();
                        scanner.nextLine();
                        Membership newMembership = new Membership(type, description, cost, user.getUserId());
                        if (membershipService.purchaseMembership(newMembership)) {
                            
                        } else {
                            System.out.println("Failed to purchase membership.");
                        }
                        break;
                    case 6:
                        System.out.println("Returning to Main Menu...");
                        break;
                    default:
                        System.out.println("Invalid choice! Please try again.");
                }
            } catch (Exception e) {
                System.out.println("Error occurred: " + e.getMessage());
                scanner.nextLine();
            }
        } while (trainerChoice != 6);
    }

    // Admin menu 
    private static void showAdminMenu(Scanner scanner, User user, UserService userService,
            MembershipService membershipService, WorkoutClassService workoutService) {
        int adminChoice = -1;
        do {
            System.out.println("\n=== Admin Menu ===");
            System.out.println("Welcome Admin " + user.getUsername());
            System.out.println("Please make your choice below:");
            System.out.println("1. View all users");
            System.out.println("2. Delete a user");
            System.out.println("3. View memberships and annual revenue");
            System.out.println("4. Logout");
            System.out.print("Enter your choice: ");

            try {
                adminChoice = scanner.nextInt();
                scanner.nextLine();

                switch (adminChoice) {
                    case 1:
                        userService.viewAllUsers(user);
                        break;
                    case 2:
                        System.out.print("Enter username to delete: ");
                        String usernameToDelete = scanner.nextLine();
                        userService.deleteUser(user, usernameToDelete);
                        break;
                    case 3:
                        membershipService.displayAllMembershipsAndRevenue();
                        break;
                    case 4:
                        System.out.println("Returning to main menu...");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
            } catch (SQLException e) {
                System.out.println("Database error occurred: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
                scanner.nextLine();
            }
        } while (adminChoice != 4);
    }

    // Implementation of adding a new user
    private static void addNewUser(Scanner scanner, UserService userService) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        System.out.print("Enter role (ADMIN/TRAINER/MEMBER): ");
        String role = scanner.nextLine();
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
