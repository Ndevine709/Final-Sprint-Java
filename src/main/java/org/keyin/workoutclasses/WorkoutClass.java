package org.keyin.workoutclasses;

/**
 * Class representing a workout class.
 * Holds details about a workout class including type, description, and associated trainer.
 */
public class WorkoutClass {
    private int class_id;
    private String class_type;
    private String class_description;
    private int trainer_id;
    
    /**
     * Default constructor for WorkoutClass.
     */
    public WorkoutClass(){
    }

    /**
     * Constructor for WorkoutClass with all fields.
     * @param class_id The unique identifier for the workout class
     * @param class_type The type of workout class
     * @param class_description A description of the workout class
     * @param trainer_id The ID of the trainer associated with the workout class
     */
    public WorkoutClass(int class_id, String class_type, String class_description, int trainer_id){
        this.class_id = class_id;
        this.class_type = class_type;
        this.class_description = class_description;
        this.trainer_id = trainer_id;  
    }

    /**
     * Constructor for WorkoutClass with minimal fields.
     * @param class_type The type of workout class
     * @param class_description A description of the workout class
     * @param trainer_id The ID of the trainer associated with the workout class
     */
    public WorkoutClass(String class_type, String class_description, int trainer_id){
        this.class_type = class_type;
        this.class_description = class_description;
        this.trainer_id = trainer_id;
    }

    /**
     * Returns the ID of the workout class.
     * @return The workout class ID
     */
    public int getClassId(){
        return class_id;
    }

    /**
     * Sets the ID of the workout class.
     * @param class_id The workout class ID
     */
    public void setClassId(int class_id) {
        this.class_id = class_id;
    }

    /**
     * Returns the type of the workout class.
     * @return The workout class type
     */
    public String getClassType() {
        return class_type;
    }

    /**
     * Sets the type of the workout class.
     * @param class_type The workout class type
     */
    public void setClassType(String class_type) {
        this.class_type = class_type;
    }

    /**
     * Returns the description of the workout class.
     * @return The workout class description
     */
    public String getClassDescription() {
        return class_description;
    }

    /**
     * Sets the description of the workout class.
     * @param class_description The workout class description
     */
    public void setClassDescription(String class_description) {
        this.class_description = class_description;
    }

    /**
     * Returns the ID of the trainer associated with the workout class.
     * @return The trainer ID
     */
    public int getTrainerId() {
        return trainer_id;
    }

    /**
     * Sets the ID of the trainer associated with the workout class.
     * @param trainer_id The trainer ID
     */
    public void setTrainerId(int trainer_id) {
        this.trainer_id = trainer_id;
    }

    /**
     * Returns a string representation of the workout class.
     * @return A string representation of the workout class
     */
  @Override
  public String toString() {
      
      return("Class ID: " + class_id + "Class Type: " + class_type + "Description:" + class_description + "Trainer: " + trainer_id );
  }
}
