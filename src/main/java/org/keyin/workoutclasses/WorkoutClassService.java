package org.keyin.workoutclasses;

import java.sql.Connection;
import java.sql.SQLException;


import org.keyin.database.DatabaseConnection;

public class WorkoutClassService {
    // Inject in the DAO to be able to use it in the service
    private WorkoutClassDAO workoutDAO;

    public WorkoutClassService(){
        try{
        Connection connection = DatabaseConnection.getConnection();
        this.workoutDAO = new WorkoutClassDAO(connection);
        }
        catch(SQLException exception) {
            throw new RuntimeException("Error initializing WorkoutClassDAO: " + exception.getMessage(), exception);
            
        }
    }

    public void createNewWorkoutClass(WorkoutClass workoutClass){
        try{
         workoutDAO.createNewWorkoutClass(workoutClass); 
        System.out.println("Workout Created successfully...");
        }catch(SQLException exception) {
            System.err.println("Error creating workout class: " + exception.getMessage());
        }
    }

    public void updateWorkOutClass(WorkoutClass workoutClass){
        try{
        workoutDAO.updateWorkoutClass(workoutClass);
        System.out.println("Workout Updated successfully...");
        } catch(SQLException exception) {
            System.err.println("Error updating workout class: " + exception.getMessage());
        }
    }

    public void deleteWorkoutClass(WorkoutClass workoutClass){
        try{
            workoutDAO.deleteWorkoutClass(0);
            System.out.println("Workout Class deleted successfully...");
        }catch(SQLException exception){
            System.err.println("Error deleting workout class: " + exception.getMessage());
        }
        
    }

    
};