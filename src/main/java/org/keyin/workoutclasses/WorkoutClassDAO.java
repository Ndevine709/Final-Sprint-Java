package org.keyin.workoutclasses;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class WorkoutClassDAO{
    private Connection connection;

    public WorkoutClassDAO(Connection connection){
        this.connection = connection;
    }

    public void createNewWorkoutClass(WorkoutClass workoutClass){
        String sql = "INSERT INTO public.WorkoutClass(\n" + 
        "\t name, description, trainer, schedule)\n" +
        "\t VALUES (?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1, workoutClass.getName());
            preparedStatement.setString(2, workoutClass.getDescription());
            preparedStatement.setString(3, workoutClass.getTrainer());
            preparedStatement.setString(4, workoutClass.getSchedule());
            preparedStatement.executeUpdate();        
        } catch (SQLException exception){
            exception.printStackTrace();
            throw new RuntimeException("Error creating workout class...");
        }
    };

    public void updateWorkoutClass(WorkoutClass workoutClass){
        String sql = "UPDATE public.WorkoutClass SET name = ?, description = ?, trainer = ?, schedule = ?WHERE classId = ? ";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1, workoutClass.getName());
            preparedStatement.setString(2, workoutClass.getDescription());
            preparedStatement.setString(3, workoutClass.getTrainer());
            preparedStatement.setString(4, workoutClass.getSchedule());
            preparedStatement.executeUpdate();        
        } catch (SQLException exception){
            exception.printStackTrace();
            throw new RuntimeException("Error updating workout class...");
        }
    }

    public void deleteWorkoutClass(int classId){
        String sql = "DELETE FROM public.WorkoutClass WHERE classId = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1, classId);
            preparedStatement.executeUpdate();        
        } catch (SQLException exception){
            exception.printStackTrace();
            throw new RuntimeException("Error deleting workout class...");
        }
    }
};
