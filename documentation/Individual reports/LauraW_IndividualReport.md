# 👤 Individual Report – Project Contribution

### 🧱 My Contributions

During the development of the Gym Management System, I focused on the Workout Class components. I intregrated the trainer menu into the GymApp as well as helped debug and rework some code to ensure a great user experience.

- **WorkoutClass Functionality**
  - Focused on the development of the WorkoutClass components, including WorkoutClass, WorkoutClassDAO and WorkoutClassService.
  
  
- **GymApp Menu Integration**
  - Implemented and refined the **Trainer Menu** interface to ensure Trainers could navigate clearly.
  - Set up the Trainer specific menu which allows an trainer to create, edit or delete classes, view all upcoming classes and purchase a membership. 

- **Testing & Debugging**
  - Changed last option in each menu to "Logout" instead of "Back to Main Menu", as I noticed when the user returned they were logged out.
  - Tested as a group.

- **Github**
  - Used branches for different features of the application, setup a branch and PR for the WorkoutClassService& WorkoutDAO files.
  - Used branches for bigger commits of the application while also making small commits to main branch when issues where minor or syntax related.
  - Merged and deleted branches before ensuring no conflicts were present.

---

### ⚠️ Challenges Faced

- **Database Integration**
  - Encountered an issue with my password on pgAdmin, I had to research the command to reset my password, which I managed to fix!

- **General Issues**
  - When creating a user, one of the params for the constructor was in the wrong spot, so we had to adjust that so the data was stored correctly. (Role was showing the users phone number, easy fix!).