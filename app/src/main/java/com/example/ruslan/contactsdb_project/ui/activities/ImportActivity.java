package com.example.ruslan.contactsdb_project.ui.activities;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.ruslan.contactsdb_project.R;
import com.example.ruslan.contactsdb_project.adapters.DataAdapter;
import com.example.ruslan.contactsdb_project.data.Contact;
import com.example.ruslan.contactsdb_project.data.DBHandler;

import java.util.ArrayList;
import java.util.HashMap;

public class ImportActivity extends AppCompatActivity implements View.OnClickListener, DataAdapter.SizeSelectedListener, DataAdapter.ClickItemListener {

    private RecyclerView rvImport;
    private Button btnImportContacts, btnImportCancel;
    private DataAdapter dataAdapter;
    private LinearLayoutManager layoutManager;
    private final DBHandler db = new DBHandler(this);
    private ArrayList<Contact> mContactArrayList = new ArrayList<>();
    private String[] projection;
    private Uri mContactUri;
    private SimpleCursorAdapter mCursorAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_import);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_import); // Attaching the layout to the toolbar object
        setSupportActionBar(toolbar);


        layoutManager = new LinearLayoutManager(this);

        rvImport = (RecyclerView) findViewById(R.id.recycler_view_import);
        btnImportContacts = (Button) findViewById(R.id.btnImportContacts);
        btnImportCancel = (Button) findViewById(R.id.btnImportCancel);
        btnImportContacts.setText(getResources().getString(R.string.import_contacts, 0));
        btnImportContacts.setOnClickListener(this);
        btnImportCancel.setOnClickListener(this);
        dataAdapter = new DataAdapter(this, mContactArrayList);
        dataAdapter.changeMode();
        dataAdapter.setClickItemListener(this);
        dataAdapter.setSizeSelectedListener(this);
        rvImport.setAdapter(dataAdapter);
        rvImport.setLayoutManager(layoutManager);
        rvImport.setHasFixedSize(true);


        ContentResolver cr = getContentResolver();
        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI,
                null, null, null, null);

        if (cur.getCount() > 0) {

            while (cur.moveToNext()) {

                String id = cur.getString(cur.getColumnIndex(ContactsContract.Contacts._ID));
                String name = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));

                if (Integer.parseInt(cur.getString(cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {

                    Cursor pCur = cr.query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                            new String[]{id}, null);

                    while (pCur.moveToNext()) {
                        String phoneNo = pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        //  Toast.makeText(ImportActivity.this, "Name: " + name + ", Phone No: " + phoneNo, Toast.LENGTH_SHORT).show();

                        Contact contact = new Contact();
                        contact.setFirstName(name);
                        contact.setPhoneNumber(phoneNo);
                        mContactArrayList.add(contact);
                    }
                    pCur.close();
                }
            }
        }

        listAccounts(this);

    }

    public void listAccounts(Context ctx) {

        AccountManager am = AccountManager.get(ctx);
        Account[] accounts = am.getAccounts();

        for (Account ac: accounts){
            String acname=ac.name;
            String actype=ac.type;
            Log.d("accountinfo", acname + " : " + actype);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_import, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.itemSelectAll:

                if (dataAdapter.getSelectedHashMap().size() == dataAdapter.getItemCount()) {
                    dataAdapter.getSelectedHashMap().clear();
                    btnImportContacts.setText(getResources().getString(R.string.import_contacts, 0));
                    dataAdapter.notifyDataSetChanged();
                } else {
                    dataAdapter.getSelectedHashMap().clear();
                    btnImportContacts.setText(getResources().getString(R.string.import_contacts, 0));

                    for (int i = 0; i < dataAdapter.getItemCount(); i++) {
                        dataAdapter.getSelectedHashMap().put(i, i);
                    }
                    dataAdapter.notifyDataSetChanged();
                }
                break;

            case R.id.itemClearSelection:

                dataAdapter.getSelectedHashMap().clear();
                btnImportContacts.setText(getResources().getString(R.string.import_contacts, 0));
                dataAdapter.notifyDataSetChanged();

                break;

            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnImportContacts:

                HashMap<Integer, Integer> selectedHashMap = dataAdapter.getSelectedHashMap();
                for (HashMap.Entry<Integer, Integer> entry : selectedHashMap.entrySet()) {
                    Contact contact = mContactArrayList.get(entry.getKey());
                    contact.setMaritalStatus(Contact.MaritalStatus.single);
                    contact.setGender(Contact.Gender.male);
                    db.addContact(contact);

                    //   dataAdapter.notifyDataSetChanged();
                    Intent intent = new Intent();
                    setResult(ImportActivity.RESULT_OK, intent);
                }
                finish();
                break;

            case R.id.btnImportCancel:
                finish();
                break;
        }
    }

    @Override
    public void selectedSize(int size) {
        btnImportContacts.setText(getResources().getString(R.string.import_contacts, size));

    }

    @Override
    public void onItemClick(int position) {

    }


}









/*
    Cursor contactsCursor = getContentResolver()
            .query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);



while (contactsCursor.moveToNext()) {

        // получаем каждый контакт
        String contactName = contactsCursor.getString(
        contactsCursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME_PRIMARY));

        Contact contact = new Contact();
        contact.setFirstName(contactName);
        mContactArrayList.add(contact);
        }*/











  /*   Cursor cursor = contentResolver.query(ContactsContract.Data.CONTENT_URI, other_query_params_for_filtering);

        int indexGivenName = cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME);
        int indexFamilyName = cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.StructuredName.FAMILY_NAME);
        int indexDisplayName = cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME);

        while (cursor.moveToNext()) {
            String given = cursor.getString(indexGivenName);
            String family = cursor.getString(indexFamilyName);
            String display = cursor.getString(indexDisplayName);*/

    /*    int hasPhone = 1;

        Cursor contactsCursor = getContentResolver().query(Uri.parse("content://icc/adn"), null, ContactsContract.Data.HAS_PHONE_NUMBER + "=?" + " AND "
                + ContactsContract.Data.MIMETYPE + "='" + ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE + "'", new String[]{String.valueOf(hasPhone)}, null);*/


//   Cursor contactsCursor = getContentResolver().query(ContactsContract.RawContacts.CONTENT_URI, null, null, null, null);
//    Cursor contactsCursor = getContentResolver().query();

       /* Cursor c = getContentResolver().query(ContactsContract.Data.CONTENT_URI,
                new String[] {ContactsContract.Data._ID, ContactsContract.CommonDataKinds.Phone.NUMBER, ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.LABEL},
                ContactsContract.Data.RAW_CONTACT_ID + "=?" + " AND "
                        + ContactsContract.Data.MIMETYPE + "='" + ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE + "'",
                new String[] {String.valueOf(rawContactId)}, null);*/



/*        Cursor contactsCursor = getContentResolver().query(ContactsContract.Data.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.HAS_PHONE_NUMBER + "=?", new String[]{String.valueOf(1)}, null);


        int indexGivenName = contactsCursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME);
        int indexFamilyName = contactsCursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.StructuredName.FAMILY_NAME);
        //   int indexPhone = contactsCursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.StructuredName.HAS_PHONE_NUMBER);
        int indexPhone = contactsCursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER);
        int indexPhone = contactsCursor.getColumnIndexOrThrow(ContactsContract.Contacts.);
        int indexEmail = contactsCursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Email.ADDRESS);

        while (contactsCursor.moveToNext()) {


            String contactFirstName = contactsCursor.getString(indexGivenName);
            String contactLastName = contactsCursor.getString(indexFamilyName);
            String contactPhone = contactsCursor.getString(indexPhone);
            String contactEmail = contactsCursor.getString(indexEmail);

                Contact contact = new Contact();
                contact.setFirstName(contactFirstName);
                contact.setLastName(contactLastName);
                contact.setPhoneNumber(contactPhone);
                contact.setEmail(contactEmail);
                mContactArrayList.add(contact);
        }*/















/*

    ContentResolver cr = getBaseContext().getContentResolver();
    Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI,
            null,
            null,
            null,
            null);

if (cur.getCount() > 0) {
        Log.i("Content provider", "Reading contact  emails");
        while (cur.moveToNext()) {

        String contactId = cur.getString(cur.getColumnIndex(ContactsContract.Contacts._ID));

        // Create query to use CommonDataKinds classes to fetch emails
        Cursor phoneNumbers = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
        null,
        ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = " + contactId,
        null,
        null);


        //You can use all columns defined for ContactsContract.Data
                            // Query to get phone numbers by directly call data table column

                            Cursor c = getContentResolver().query(ContactsContract.Data.CONTENT_URI,
                                      new String[] {ContactsContract.Data._ID, ContactsContract.CommonDataKinds.Phone.NUMBER, ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.LABEL},
                                      ContactsContract.Data.CONTACT_ID + "=?" + " AND "
                                              + ContactsContract.Data.MIMETYPE + "= + Phone.CONTENT_ITEM_TYPE + ",
                                      new String[] {String.valueOf(contactId)}, null);


        //      int indexGivenName = contactsCursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME);
        //      int indexFamilyName = contactsCursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.StructuredName.FAMILY_NAME);


        while (phoneNumbers.moveToNext()) {

        // This would allow you get several email addresses
        String phoneNumbersString = cur.getString(phoneNumbers.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DATA));

        //  String firstNameString = cur.getString(cur.getColumnIndex(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME));
        //  String lasttNameString = cur.getString(cur.getColumnIndex(ContactsContract.CommonDataKinds.StructuredName.FAMILY_NAME));

        //Log.e("email==>", emailAddress);
        Contact contact = new Contact();
        contact.setPhoneNumber(phoneNumbersString);
        //    contact.setFirstName(firstNameString);
        //    contact.setLastName(lasttNameString);
        mContactArrayList.add(contact);

        }
        phoneNumbers.close();
        }

        }
else
        {
            emaildata +="
            Data not found.
            ";

        }

        cur.close();


*/





