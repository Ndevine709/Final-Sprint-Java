# 👤 Individual Report – Project Contribution

### 🧱 My Contributions

During the development of the Gym Management System, I mainly focused on the User super class and its child classes. I also focused on setting up the login and logout functionality of the app along with storing hashed passwords and adding new users to the system. For role based menus, I focused on the Admin menu and the methods available to the Admin within this menu. And if needed, helped out my fellow group members when needed. 

- **User Functionality**
  - Focused on the development and refinement of features needed in order to create any type of user (Admin, Trainer, Member).
  - Set up subclasses of User for each role and implemented the necessary methods for each role.
  
- **GymApp Menu Integration**
  - Implemented and refined the **Admin Menu** interface to ensure users could navigate clearly.
  - Set up the logic to allow the user to add a new user and login using their username and password
  - Set up the Admin specific menu which allows an admin to view all members, delete users and view all memberships along with the total annual revenue.

- **Testing & Debugging**
  - Worked with Chris and Laura to confirm that our menu options were working as expected and made suggestions on error fixes when we encountered them.
  - Ran into minor database issues, but I was able to resolve them by making minor changes to our table schemas when needed.
  - Tested as a group.

- **Github**
  - Setup our repo based on the template provided and set up the structure for our documentation files.
  - Used branches for different features of the application. setup a branch and PR for the userService & userDao files along with another branch and PR for the parent User class and its subclasses.
  - Used branches for bigger commits of the application while also making small commits to main when it was determined that the changes were minor.

---

### ⚠️ Challenges Faced

- **Menu Navigation and Flow**
  - One of the main challenges was ensuring the menu remained persistent and user-friendly without restarting the whole app after each action. Worked with Chris to come up with a solution that allowed the menu to remain active after each action. Chris then implmented it while we finished our remaining tasks.

- **Database Schema Issues**
  - Ran into minor database issues, but I was able to resolve them by making minor changes to our table schemas when needed. This included issues with the "Role" column in our database as it was not being recognized correctly when adding a new user. The other database issue was noticed when trying to delete a member who had a gym membership as it caused conflicts with the foreign key constraints in the memberships table. Again, was able to make a small tweak to our database in order to fix this.

- **General Issues**
  - Ran into small issues with the Admin menu methods as discussed above, along with issues with how the user was being stored in the database (which ended up being a small fix in the params of the constructor).