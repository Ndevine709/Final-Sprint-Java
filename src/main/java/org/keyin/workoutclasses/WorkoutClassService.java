package org.keyin.workoutclasses;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import org.keyin.database.DatabaseConnection;

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

    public boolean createNewWorkoutClass(WorkoutClass workoutClass){
        try{
         workoutDAO.createNewWorkoutClass(workoutClass); 
        System.out.println("Workout Created successfully...");
        return true;
        }catch(SQLException exception) {
            System.err.println("Error creating workout class: " + exception.getMessage());
        }return false;
    }

    public boolean updateWorkOutClass(WorkoutClass workoutClass){
        try{
        workoutDAO.updateWorkoutClass(workoutClass);
        System.out.println("Workout Updated successfully...");
        return true;
        } catch(SQLException exception) {
            System.err.println("Error updating workout class: " + exception.getMessage());
        }return false;
    }

    public boolean deleteWorkoutClass(int classId){
        try{
            workoutDAO.deleteWorkoutClass(classId);
            System.out.println("Workout Class deleted successfully...");
            return true;
        } catch(SQLException exception){
            System.err.println("Error deleting workout class: " + exception.getMessage());
        }return false;
    }

    public List<WorkoutClass> getAllWorkoutClassesByTrainer(int trainer_id){
        try { 
            return workoutDAO.getAllWorkoutClassesByTrainer(trainer_id);
        }catch(SQLException exception) {
            System.err.println("Error retrieving workout classes: " + exception.getMessage());
        }return new ArrayList<>();
    }
};