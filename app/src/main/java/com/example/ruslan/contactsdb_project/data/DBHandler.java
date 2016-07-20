package com.example.ruslan.contactsdb_project.data;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION = 1;

    public static final String DATABASE_NAME = "contactsManager";

    public static final String TABLE_CONTACTS = "contacts";

    public static final String KEY_ID = "id";
    public static final String KEY_FIRST_NAME = "first_name";
    public static final String KEY_LAST_NAME = "last_name";
    public static final String KEY_PH_NO = "phone_number";
    public static final String KEY_ADDRESS = "address";

    public static final String KEY_JOB = "job";
    public static final String KEY_STATUS = "marital_status";
    public static final String KEY_GENDER = "gender";
    public static final String KEY_EMAIL = "email";


    final String selectQuery = "SELECT  * FROM contacts WHERE id ='%s'" ;

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_FIRST_NAME + " TEXT,"
                + KEY_LAST_NAME + " TEXT," + KEY_PH_NO + " TEXT,"
                + KEY_ADDRESS + " TEXT," + KEY_JOB + " TEXT," + KEY_STATUS + " TEXT,"
                + KEY_GENDER + " TEXT," + KEY_EMAIL + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        onCreate(db);
    }

    public long addContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_FIRST_NAME, contact.getFirstName()); // Contact FirstName
        values.put(KEY_LAST_NAME, contact.getLastName()); // Contact LastName
        values.put(KEY_ADDRESS, contact.getAddress()); // Contact Adress
        values.put(KEY_PH_NO, contact.getPhoneNumber()); // Contact Phone

        values.put(KEY_JOB, contact.getJob()); // Contact Job
        values.put(KEY_STATUS, String.valueOf(contact.getMaritalStatus())); // Contact MaritalStatus
        values.put(KEY_GENDER, String.valueOf(contact.getGender())); // Contact Gender
        values.put(KEY_EMAIL, contact.getEmail()); // Contact Email

        // Inserting Row
        long id = db.insert(TABLE_CONTACTS, null, values);
        db.close(); // Closing database connection
        return id;
    }


    // Getting All Contacts
    public List<Contact> getAllContacts() {
        List<Contact> contactList = new ArrayList<Contact>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Contact contact = new Contact(cursor);
              //  Log.d("getAllContacts", "getAllContacts " + contact.getFirstName());
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return contact list
        return contactList;
    }

    public Contact getContactById(long id) {
      //  String query = "SELECT * FROM contacts WHERE id = "  + id;
        String query = String.format(selectQuery, id);
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = null;
        Contact contact = null;
        try {
            cursor = db.rawQuery(query, null);
            if (cursor.moveToFirst()) {
                contact = new Contact(cursor);
            }
        } catch (Exception e) {
            Log.e("DB",  e.getMessage());
        } finally {
            if (cursor != null)
                cursor.close();
            db.close();
        }
        return contact;
    }
}
