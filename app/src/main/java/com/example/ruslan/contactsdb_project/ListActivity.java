package com.example.ruslan.contactsdb_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.ruslan.contactsdb_project.data.DBHandler;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {


    private final int REQUEST_CODE_ADD_CONTACT = 1;
    private final int REQUEST_CODE_DELETE_CONTACT = 2;
    private final int REQUEST_CODE_EDIT_CONTACT = 3;
    private final DBHandler db = new DBHandler(this);
    private RecyclerView rv;
    private LinearLayoutManager layoutManager;
    private DataAdapter dataAdapter;
    private ArrayList<Contact> mContactArrayList = new ArrayList<>();

    public ListActivity() {

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar); // Attaching the layout to the toolbar object
        setSupportActionBar(toolbar);

        //db = new DBHandler(this);

        rv = (RecyclerView) findViewById(R.id.recycler_view);

        layoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(layoutManager);

        dataAdapter = new DataAdapter(this, mContactArrayList);
        rv.setAdapter(dataAdapter);
        updateUI();
        //   FloatingActionButton addButton = (FloatingActionButton) findViewById(R.id.addButton);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;


    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.itemAdd:
                Intent intent = new Intent(this, AddActivity.class);
                startActivityForResult(intent, REQUEST_CODE_ADD_CONTACT);
                break;

            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        Log.d("myLogs", "requestCode = " + requestCode + ", resultCode = " + resultCode);

        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_CODE_ADD_CONTACT:
                    Log.d("myLogs", "Add contact");

                    if (data == null) {
                        return;
                    }
                    Long id = data.getLongExtra("id", -1);
                    if (id > 0) {
                        final Contact contact = db.getContactById(id);
                        if (mContactArrayList.size() > 0)
                            rv.scrollToPosition(mContactArrayList.size());
                        dataAdapter.addItem(contact);

                        // dataAdapter.notifyDataSetChanged();
                    }

                    break;
                case REQUEST_CODE_DELETE_CONTACT:
                    Log.d("myLogs", "Delete contact");
                    break;
                case REQUEST_CODE_EDIT_CONTACT:
                    Log.d("myLogs", "Edit contact");
                    break;
            }
        } else {
            Toast.makeText(this, "Wrong result", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    protected void onResume() {
        super.onResume();


    }


    public DBHandler getDB() {
        return db;
    }

    public void updateUI() {
        mContactArrayList.clear();
        mContactArrayList.addAll((ArrayList<Contact>) db.getAllContacts());
        dataAdapter.notifyDataSetChanged();
    }





   /* public void updateUI(){
        mContacts.clear();
        mContacts.addAll((ArrayList<Contact>) db.getAllContacts());
        dataAdapter = new DataAdapter(this, mContacts);
        rv.setAdapter(dataAdapter);
    }*/
}
