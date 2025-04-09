package org.keyin.workoutclasses;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

public class WorkoutClassDAO{
    private Connection connection;

    public WorkoutClassDAO(Connection connection){
        this.connection = connection;
    }

    public void createNewWorkoutClass(WorkoutClass workoutClass) throws SQLException{
        String sql = "INSERT INTO public.WorkoutClass(class_type, class_description, trainer_id) VALUES(?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1, workoutClass.getClassType());
            preparedStatement.setString(2, workoutClass.getClassDescription());
            preparedStatement.setInt(3, workoutClass.getTrainerId());
            preparedStatement.executeUpdate();        
        } catch (SQLException exception){
            exception.printStackTrace();
            throw new RuntimeException("Error creating workout class...");
        }
    };

    public void updateWorkoutClass(WorkoutClass workoutClass) throws SQLException{
        String sql = "UPDATE public.WorkoutClass SET class_type = ?, class_description = ?, trainer_id= ? WHERE class_id = ? ";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1, workoutClass.getClassId());
            preparedStatement.setString(2, workoutClass.getClassType());
            preparedStatement.setString(3, workoutClass.getClassDescription());
            preparedStatement.setInt(4, workoutClass.getTrainerId());
            preparedStatement.executeUpdate();        
        } catch (SQLException exception){
            exception.printStackTrace();
            throw new RuntimeException("Error updating workout class...");
        }
    }

    public void deleteWorkoutClass(int class_id) throws SQLException{
        String sql = "DELETE FROM public.WorkoutClass WHERE class_id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1, class_id);
            preparedStatement.executeUpdate();        
        } catch (SQLException exception){
            exception.printStackTrace();
            throw new RuntimeException("Error deleting workout class...");
        }
    }

    public List<WorkoutClass> getAllWorkoutClasses() throws SQLException{
        String sql = "SELECT * FROM public.WorkoutClass";
        List<WorkoutClass> workoutClasses = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()){ 
                    WorkoutClass class1 = new WorkoutClass(
                        resultSet.getInt("class_id"),
                        resultSet.getString("class_type"),
                        resultSet.getString("class_description"),
                        resultSet.getInt("trainer_id")
                    );
                  workoutClasses.add(class1);
            }    
        } catch (SQLException exception){
            exception.printStackTrace();
            throw new RuntimeException("Error deleting workout class...");
        }
        return workoutClasses;
    };
};