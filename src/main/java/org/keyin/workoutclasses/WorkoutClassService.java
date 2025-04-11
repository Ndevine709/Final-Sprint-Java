package org.keyin.workoutclasses;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import org.keyin.database.DatabaseConnection;

/**
 * Service class for handling workout class operations.
 * Provides methods to create, update, delete, and retrieve workout classes.
 */
public class WorkoutClassService {
    // Inject in the DAO to be able to use it in the service
    private WorkoutClassDAO workoutDAO;

    public WorkoutClassService(){
        try{
        Connection connection = DatabaseConnection.getConnection();
        this.workoutDAO = new WorkoutClassDAO(connection);
        }catch(SQLException exception) {
            throw new RuntimeException("Error initializing WorkoutClassDAO: " + exception.getMessage(), exception);
        }
    }

    /**
     * Creates a new workout class in the database.
     * @param workoutClass The workout class object to be created
     * @return true if workout class was successfully created, false otherwise
     * @throws SQLException if there's an error executing the SQL statement
     */
    public boolean createNewWorkoutClass(WorkoutClass workoutClass){
        try{
         workoutDAO.createNewWorkoutClass(workoutClass); 
        System.out.println("Workout Created successfully...");
        return true;
        }catch(SQLException exception) {
            System.err.println("Error creating workout class: " + exception.getMessage());
        }return false;
    }

    /**
     * Updates an existing workout class in the database.
     * @param workoutClass The workout class object to be updated
     * @return true if workout class was successfully updated, false otherwise
     * @throws SQLException if there's an error executing the SQL statement
     */
    public boolean updateWorkOutClass(WorkoutClass workoutClass){
        try{
        workoutDAO.updateWorkoutClass(workoutClass);
        System.out.println("Workout Updated successfully...");
        return true;
        } catch(SQLException exception) {
            System.err.println("Error updating workout class: " + exception.getMessage());
        }return false;
    }

    /**
     * Deletes a workout class from the database by its ID.
     * @param classId The ID of the workout class to delete
     * @return true if workout class was successfully deleted, false otherwise
     * @throws SQLException if there's an error executing the SQL statement
     */
    public boolean deleteWorkoutClass(int classId){
        try{
            workoutDAO.deleteWorkoutClass(classId);
            System.out.println("Workout Class deleted successfully...");
            return true;
        } catch(SQLException exception){
            System.err.println("Error deleting workout class: " + exception.getMessage());
        }return false;
    }

    /**
     * Retrieves all workout classes associated with a specific trainer.
     * @param trainer_id The ID of the trainer
     * @return List of workout classes associated with the trainer
     * @throws SQLException if there's an error executing the SQL statement
     */
    public List<WorkoutClass> getAllWorkoutClassesByTrainer(int trainer_id){
        try { 
            return workoutDAO.getAllWorkoutClassesByTrainer(trainer_id);
        }catch(SQLException exception) {
            System.err.println("Error retrieving workout classes: " + exception.getMessage());
        }return new ArrayList<>();
    }

    /**
     * Retrieves all workout classes from the database.
     * @return List of all workout classes
     * @throws SQLException if there's an error executing the SQL statement
     */
    public List<WorkoutClass> getAllWorkoutClasses() {
        try { 
            return workoutDAO.getAllWorkoutClasses();
        } catch (SQLException exception) {
            System.err.println("Error retrieving all workout classes: " + exception.getMessage());
            return new ArrayList<>();
        }
    }
    
};