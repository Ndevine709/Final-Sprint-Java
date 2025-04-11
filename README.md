# GymApp - Console-Based Gym Management System

Welcome to GymApp! This simple console-based program helps manage a gym's users, memberships, and workout classes. Whether you're a gym **Admin**, **Trainer**, or **Member**, this system allows you to interact with key services in an easy-to-use format through the terminal.

---

## 🧠 What This App Does (Overview)

GymApp is a role-based gym management system. It supports:

- **User account creation** (Admin, Trainer, or Member)
- **Login and role-specific menus**
- **Membership purchasing and viewing**
- **Workout class creation, update, and deletion**
- **Revenue tracking (Admin only)**
- **Admin controls** such as viewing or deleting users

All data is persisted using a **PostgreSQL database**.

---

## 🧱 Class Breakdown

| Class/Component | Description |
|------------------|-------------|
| `GymApp` | The main entry point of the application. It controls the main menu and role-based navigation. |
| `User` | Represents a user of the system. Stores username, password, role, address, email, and phone. |
| `UserDao` | Handles direct interaction with the database for user-related operations. |
| `UserService` | Contains business logic and validation for users. Calls `UserDao` methods. |
| `Membership` | Represents a membership, including type, cost, and user association. |
| `MembershipDao` | Handles database access for membership operations. |
| `MembershipService` | Contains business logic for handling memberships, calling `MembershipDao`. |
| `WorkoutClass` | Represents a workout class, including class type and description. |
| `WorkoutClassDao` | Handles database access for workout classes. |
| `WorkoutClassService` | Contains business logic and operations for workout classes. |

Classes follow a layered approach:
- **DAO Layer** interacts with the **database**.
- **Service Layer** applies **business logic** and validation.

---

## 💻 How to Clone and Run the Project

To get started with the GymApp project, follow these simple steps:

1. Open **Visual Studio Code** on your computer.
2. Use the built-in Git tools to **clone the repository** from [https://github.com/Ndevine709/Final-Sprint-Java](https://github.com/Ndevine709/Final-Sprint-Java).
3. Once the repo is cloned, **open the project folder** in Visual Studio Code.
4. Make sure your **PostgreSQL server is running**, and you’ve created the necessary **tables** in your `gymdb` database.
5. In Visual Studio Code, locate the file named `GymApp.java` and **run it directly**.

That’s it! You’re now up and running.
---

### 🧑‍💻 Using the Program

- **Choose an option from the main menu:**
  - Add a new user (register)
  - Login as existing user
  - Exit

- **After login**, a specific menu will be shown depending on your role:
  - **Admins** can view/delete users and see revenue.
  - **Trainers** can manage workout classes and purchase memberships.
  - **Members** can view classes, buy memberships, and track expenses.

> Input is handled using your keyboard in the terminal.

---

## 📁 Project Directory Structure

```
src/
└── main/
    └── java/
        └── org/
            └── keyin/
                ├── GymApp.java
                ├── database/
                │   └── DatabaseConnection.java
                ├── memberships/
                │   ├── Membership.java
                │   ├── MembershipDao.java
                │   └── MembershipService.java
                ├── user/
                │   ├── User.java
                │   ├── UserDao.java
                │   └── UserService.java
                └── workoutclasses/
                    ├── WorkoutClass.java
                    ├── WorkoutClassDao.java
                    └── WorkoutClassService.java
```

---

## 🛠️ Setting Up the Database for Development

1. **Install PostgreSQL** if you haven't already.
2. **Create a database** named `gymdb` (or the name used in your connection string).
3. **Run SQL scripts** to create the necessary tables for:
   - Users
   - Memberships
   - Workout Classes

4. **Configure database connection** in your project (likely in `DatabaseConnection` class) to match your local setup:
```java
Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/gymdb", "username", "password");
```

---

## 🧑‍🏫 Support

If you're stuck, unsure how to log in, or getting errors related to the database, double-check:
- Your **database name and credentials**
- That the **database server is running**
- Your **Java version** (recommended: Java 17+)


---

## 📋 Menu Options Explained

### 🔐 Main Menu (Before Login)
| Option | Description |
|--------|-------------|
| `1` | Add a new user (register). Prompts for all user information, including role (ADMIN/TRAINER/MEMBER). |
| `2` | Login using your username and password. Redirects to a role-based menu. |
| `3` | Exit the application. |

---

### 👤 Member Menu
| Option | Description |
|--------|-------------|
| `1` | View all available workout classes (class ID, type, and description). |
| `2` | Purchase a membership by providing type, description, and cost. |
| `3` | View your membership history and the total cost of all memberships purchased. |
| `4` | Return to the main menu. |

---

### 🏋️ Trainer Menu
| Option | Description |
|--------|-------------|
| `1` | Create a new workout class (enter type and description). |
| `2` | Update an existing workout class (enter class ID, new type, and description). |
| `3` | Delete a class (enter class ID to remove it from the system). |
| `4` | View a list of upcoming classes you've created. |
| `5` | Purchase a membership for yourself, similar to the Member option. |
| `6` | Return to the main menu. |

---

### 🛠️ Admin Menu
| Option | Description |
|--------|-------------|
| `1` | View a list of all users (username, email, phone, role, address). |
| `2` | Delete a user by entering their username (Admins cannot delete themselves). |
| `3` | View all memberships in the system and total annual revenue. |
| `4` | Return to the main menu. |

> ⚠️ Admin-only actions require the user to be logged in with an `ADMIN` role.

---

## ✅ Tips

- Use **exact usernames** when trying to login or delete users.
- Input is validated — if you enter a letter where a number is expected, the app will prompt you to try again.
- Your role (ADMIN/TRAINER/MEMBER) determines which menu options you can access.
- Don't forget to start your PostgreSQL database before running the program.







---

## 🗃️ Database SQL Scripts

The SQL scripts required to create the database and its tables are included in the project repository.

You can find the script at:
```
src/main/resources/scripts.sql
```

This script includes:

- Creating the `gym_management` database
- Creating the `users` table
- Creating the `memberships` table (with a foreign key to users)
- Creating the `workoutClass` table (with a foreign key to users)

Make sure to run this script using a PostgreSQL client or pgAdmin **before running the Java application**.

> These scripts will set up your environment with the necessary tables and relationships for full functionality.
---

## 📞 Contact & Support

If you have any questions about using this program, encounter any issues, or want to request features or improvements, feel free to reach out to any of the project contributors below:

- [Ndevine709](https://github.com/Ndevine709)
- [ChrisM709](https://github.com/ChrisM709)
- [lauraawiseman](https://github.com/lauraawiseman)

We're happy to help and appreciate any feedback!
