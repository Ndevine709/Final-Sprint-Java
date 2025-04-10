-- Create the database
CREATE DATABASE gym_management;

-- Create users table
CREATE TABLE users (
    user_id SERIAL PRIMARY KEY,
    username TEXT UNIQUE NOT NULL,
    password TEXT NOT NULL,
    address TEXT NOT NULL,
    email TEXT UNIQUE NOT NULL,
    phone_number TEXT NOT NULL,
    role TEXT NOT NULL
);
 -- Create memberships table
CREATE TABLE memberships (
    membership_id SERIAL PRIMARY KEY,
    membership_type TEXT NOT NULL,
    membership_description TEXT,
    membership_cost DECIMAL(10,2) NOT NULL,
    member_id INT,
    FOREIGN KEY (member_id) REFERENCES users(user_id) ON DELETE CASCADE
);

-- Create workout classes table
CREATE TABLE workoutClass(
	class_id SERIAL PRIMARY KEY,
	class_type TEXT NOT NULL,
	class_description TEXT NOT NULL,
    trainer_id INT,
	FOREIGN KEY (trainer_id) REFERENCES users(user_id) ON DELETE CASCADE
);