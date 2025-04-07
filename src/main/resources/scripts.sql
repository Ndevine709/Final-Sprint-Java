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
    role TEXT CHECK (role IN ('ADMIN', 'TRAINER', 'MEMBER')) NOT NULL
);
