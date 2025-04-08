package org.keyin.workoutclasses;

public class WorkoutClass {
    private int classId;
    private String name;
    private String description;
    private String trainer;
    private String schedule;

    // Default constructor
    public WorkoutClass(){
    }


    public WorkoutClass(int classId, String name, String description, String trainer, String schedule){
        this.classId = classId;
        this.name = name;
        this.description = description;
        this.trainer = trainer;
        this.schedule = schedule;
    }


    public WorkoutClass(String name, String description, String trainer, String schedule){
        this.name = name;
        this.description = description;
        this.trainer = trainer;
        this.schedule = schedule;
    }


    // Getters and Setters 

    public int getClassId(){
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
    this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTrainer() {
        return trainer;
    }

    public void setTrainer(String trainer) {
        this.trainer = trainer;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    // Methods

  @Override
  public String toString() {
      
      return("Class ID: " + classId + "Class Name:" + name + "Description:" + description + "Trainer:" + trainer + "Schedule:" + schedule );
  }
}
