package org.keyin.workoutclasses;

public class WorkoutClass {
    private int class_id;
    private String class_type;
    private String class_description;
    private int trainer_id;
    
    // Default constructor
    public WorkoutClass(){
    }

    // Constructors
    public WorkoutClass(int class_id, String class_type, String class_description, int trainer_id){
        this.class_id = class_id;
        this.class_type = class_type;
        this.class_description = class_description;
        this.trainer_id = trainer_id;  
    }

    public WorkoutClass(String class_type, String class_description, int trainer_id){
        this.class_type = class_type;
        this.class_description = class_description;
        this.trainer_id = trainer_id;
    }

    // Getters and Setters 

    public int getClassId(){
        return class_id;
    }

    public void setClassId(int class_id) {
        this.class_id = class_id;
    }

    public String getClassType() {
        return class_type;
    }

    public void setClassType(String class_type) {
        this.class_type = class_type;
    }

    public String getClassDescription() {
        return class_description;
    }

    public void setClassDescription(String class_description) {
        this.class_description = class_description;
    }

    public int getTrainerId() {
        return trainer_id;
    }

    public void setTrainerId(int trainer_id) {
        this.trainer_id = trainer_id;
    }

    // Methods

  @Override
  public String toString() {
      
      return("Class ID: " + class_id + "Class Type: " + class_type + "Description:" + class_description + "Trainer: " + trainer_id );
  }
}
