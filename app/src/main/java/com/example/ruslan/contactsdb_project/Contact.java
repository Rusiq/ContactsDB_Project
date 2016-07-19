package com.example.ruslan.contactsdb_project;


import android.database.Cursor;

public class Contact {

    //private variables
    int _id;
    String _firstName;
    String _lastName;
    String _phone_number;
    String _address;
    String _job;
    MaritalStatus _marital_status;
    Gender _gender;
    String _email;

    // Empty constructor
    public Contact() {

    }

    public Contact(Cursor cursor) {
        _firstName = (cursor.getString(1));
        _lastName = (cursor.getString(2));
        _phone_number = (cursor.getString(3));
        _address = (cursor.getString(4));
        _job = (cursor.getString(5));
        _marital_status = (Contact.MaritalStatus.fromString(cursor.getString(6)));
        _gender = (Contact.Gender.fromString(cursor.getString(7)));
        _email = (cursor.getString(8));
    }

    // constructor
    public Contact(int id, String firstName, String lastName, String _phone_number, String address, String job, MaritalStatus marital_status, Gender gender, String email) {
        this._id = id;
        this._firstName = firstName;
        this._lastName = lastName;
        this._phone_number = _phone_number;
        this._address = address;

        this._job = job;
        this._marital_status = marital_status;
        this._gender = gender;
        this._email = email;

    }

    // constructor
    public Contact(String firstName, String lastName, String _phone_number, String address, String job, MaritalStatus marital_status, Gender gender, String email) {
        this._firstName = firstName;
        this._lastName = lastName;
        this._phone_number = _phone_number;
        this._address = address;

        this._job = job;
        this._marital_status = marital_status;
        this._gender = gender;
        this._email = email;

    }

    // getting ID
    public int getID() {
        return this._id;
    }

    // setting id
    public void setID(int id) {
        this._id = id;
    }

    // getting first name
    public String getFirstName() {
        return this._firstName;
    }

    // setting first name
    public void setFirstName(String firstName) {
        this._firstName = firstName;
    }

    // getting last name
    public String getLastName() {
        return this._lastName;
    }

    // setting last name
    public void setLastName(String lastName) {
        this._lastName = lastName;
    }

    // getting address
    public String getAddress() {
        return this._address;
    }

    // setting address
    public void setAddress(String address) {
        this._address = address;
    }

    // getting phone number
    public String getPhoneNumber() {
        return this._phone_number;
    }

    // setting phone number
    public void setPhoneNumber(String phone_number) {
        this._phone_number = phone_number;
    }

    // getting job
    public String getJob() {
        return this._job;
    }

    // setting job
    public void setJob(String job) {
        this._job = job;
    }

    // getting marital status
    public MaritalStatus getMaritalStatus() {
        return this._marital_status;
    }

    // setting marital status
    public void setMaritalStatus(MaritalStatus marital_status) {
        this._marital_status = marital_status;
    }

    // getting gender
    public Gender getGender() {
        return this._gender;
    }

    // setting gender
    public void setGender(Gender gender) {
        this._gender = gender;
    }

    // getting email
    public String getEmail() {
        return this._email;
    }

    // setting email
    public void setEmail(String email) {
        this._email = email;
    }


    public enum Gender {
        female("female"), male("male");

        private String text;


        Gender(String text) {
            this.text = text;
        }

        public String getText() {
            return text;
        }

        public static Gender fromString(String text) {
            if (text != null) {
                for (Gender b : Gender.values()) {
                    if (text.contains(b.text)) {
                        return b;
                    }
                }
            }
            return null;
        }


    }

    public enum MaritalStatus {
        married("married"), single("single");

        private String text;


        MaritalStatus(String text) {
            this.text = text;
        }

        public String getText() {
            return text;
        }

        public static MaritalStatus fromString(String text) {
            if (text != null) {
                for (MaritalStatus b : MaritalStatus.values()) {
                    if (text.contains(b.text)) {
                        return b;
                    }
                }
            }
            return null;
        }

    }

}
