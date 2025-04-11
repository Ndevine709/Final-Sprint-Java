package org.keyin.workoutclasses;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

/**
 * Data Access Object (DAO) for handling workout class operations.
 * Provides methods to create, update, delete, and retrieve workout classes.
 */
public class WorkoutClassDAO{
    private Connection connection;

    /**
     * Constructor for WorkoutClassDAO.
     * @param connection The database connection to be used
     */
    public WorkoutClassDAO(Connection connection){
        this.connection = connection;
    }

    /**
     * Creates a new workout class in the database.
     * @param workoutClass The workout class object to be created
     * @throws SQLException if there's an error executing the SQL statement
     */
    public void createNewWorkoutClass(WorkoutClass workoutClass) throws SQLException{
        String sql = "INSERT INTO public.WorkoutClass(class_type, class_description, trainer_id) VALUES(?, ?, ?)";

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

    /**
     * Updates an existing workout class in the database.
     * @param workoutClass The workout class object to be updated
     * @throws SQLException if there's an error executing the SQL statement
     */
    public void updateWorkoutClass(WorkoutClass workoutClass) throws SQLException{
        String sql = "UPDATE public.WorkoutClass SET class_type = ?, class_description = ? WHERE class_id = ? ";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            
            preparedStatement.setString(1, workoutClass.getClassType());
            preparedStatement.setString(2, workoutClass.getClassDescription());
            preparedStatement.setInt(3, workoutClass.getClassId());
            preparedStatement.executeUpdate();        
        } catch (SQLException exception){
            exception.printStackTrace();
            throw new RuntimeException("Error updating workout class...");
        }
    }

    /**
     * Deletes a workout class from the database by its ID.
     * @param class_id The ID of the workout class to delete
     * @throws SQLException if there's an error executing the SQL statement
     */
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

    /**
     * Retrieves all workout classes associated with a specific trainer.
     * @param trainerId The ID of the trainer
     * @return List of workout classes associated with the trainer
     * @throws SQLException if there's an error executing the SQL statement
     */
    public List<WorkoutClass> getAllWorkoutClassesByTrainer(int trainerId) throws SQLException{
        String sql = "SELECT * FROM public.WorkoutClass WHERE trainer_id = ?";
        List<WorkoutClass> workoutClasses = new ArrayList<>();

        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1, trainerId);

            try (ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()){
                    WorkoutClass class1 = new WorkoutClass(
                        resultSet.getInt("class_id"),
                        resultSet.getString("class_type"),
                        resultSet.getString("class_description"),
                        resultSet.getInt("trainer_id")
                    );
                    workoutClasses.add(class1);
                }
            }
            return workoutClasses;
        }catch(SQLException exception){
            exception.printStackTrace();
            throw new RuntimeException("Error retrieving workout classes...");
        }
    }

    /**
     * Retrieves all workout classes from the database.
     * @return List of all workout classes
     * @throws SQLException if there's an error executing the SQL statement
     */
    public List<WorkoutClass> getAllWorkoutClasses() throws SQLException {
        String sql = "SELECT * FROM public.WorkoutClass";
        List<WorkoutClass> workoutClasses = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    WorkoutClass wc = new WorkoutClass(
                        resultSet.getInt("class_id"),
                        resultSet.getString("class_type"),
                        resultSet.getString("class_description"),
                        resultSet.getInt("trainer_id")
                    );
                    workoutClasses.add(wc);
                }
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
            throw new RuntimeException("Error retrieving all workout classes...", exception);
        }
        return workoutClasses;
    }
    
};
