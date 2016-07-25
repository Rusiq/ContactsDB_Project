package com.example.ruslan.contactsdb_project.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.ruslan.contactsdb_project.R;
import com.example.ruslan.contactsdb_project.adapters.DataAdapter;
import com.example.ruslan.contactsdb_project.data.Contact;
import com.example.ruslan.contactsdb_project.data.DBHandler;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity implements DataAdapter.ClickItemListener {


    private final int REQUEST_ADD_CONTACT = 1;
    private final int REQUEST_DETAIL_CONTACT = 2;
    private final int REQUEST_EDIT_CONTACT = 3;
    private final int REQUEST_DELETE_CONTACT = 4;
    private final DBHandler db = new DBHandler(this);
    private RecyclerView rv;
    private LinearLayoutManager layoutManager;
    private DataAdapter dataAdapter;
    private ArrayList<Contact> mContactArrayList = new ArrayList<>();
    private LinearLayout llList;
    private int positionItemClick;

    public ListActivity() {

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_list); // Attaching the layout to the toolbar object
        setSupportActionBar(toolbar);

        rv = (RecyclerView) findViewById(R.id.recycler_view);
        llList = (LinearLayout) findViewById(R.id.llList);

        layoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(layoutManager);
        dataAdapter = new DataAdapter(this, mContactArrayList);
        dataAdapter.setClickItemListener(this);
        rv.setAdapter(dataAdapter);
        rv.setHasFixedSize(true);

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
                startActivityForResult(intent, REQUEST_ADD_CONTACT);
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
                case REQUEST_ADD_CONTACT:
                    Log.d("myLogs", "Add contact");
                    if (data == null) {
                        return;
                    }
                    Long id = data.getLongExtra("id", -1);
                    if (id > 0) {
                        final Contact contact = db.getContactById(id);
                        // db.getAllContacts();
                        if (contact != null) {
                            if (mContactArrayList.size() > 0)
                                rv.scrollToPosition(mContactArrayList.size());

                            rv.getItemAnimator().setAddDuration(1000);
                            dataAdapter.addItem(contact);
                        }
                    }
                    break;

                case REQUEST_EDIT_CONTACT:
                    Log.d("myLogs", "Edit contact");
                    break;

                case REQUEST_DELETE_CONTACT:

                    if (data == null) {
                        return;
                    }
                    Long idForDel = data.getLongExtra("id", -1);
                    if (idForDel > 0) {
                        final Contact contact = db.getContactById(idForDel);
                        //  db.deleteContact(idForDel);
                        if (contact != null) {
                           // rv.getItemAnimator().setAddDuration(1000);

                            //dataAdapter.notifyItemRemoved(positionItemClick);
                            dataAdapter.deleteItem(contact, positionItemClick);
                         //   dataAdapter.notifyDataSetChanged();
                        }
                        db.deleteContact(idForDel);
                    }


                    Log.d("myLogs", "Delete contact");
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


    @Override
    public void onItemClick(int position) {
        Log.d("itemClick", "itemClick" + position);

        positionItemClick = position;
        Intent intent = new Intent(this, DetailActivity.class);
        final Contact contact = mContactArrayList.get(position);
        intent.putExtra("contact", (Parcelable) contact);
        startActivityForResult(intent, REQUEST_DELETE_CONTACT);


        /*db.deleteContact(mContactArrayList.get(position).getID());
        mContactArrayList.clear();
        mContactArrayList.addAll(db.getAllContacts());
        dataAdapter.notifyDataSetChanged();*/

    }
}
