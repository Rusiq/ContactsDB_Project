package com.example.ruslan.contactsdb_project.ui.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.ruslan.contactsdb_project.R;
import com.example.ruslan.contactsdb_project.adapters.DataAdapter;
import com.example.ruslan.contactsdb_project.data.Contact;
import com.example.ruslan.contactsdb_project.data.DBHandler;
import com.example.ruslan.contactsdb_project.ui.ItemDivider;

import java.util.ArrayList;
import java.util.HashMap;

public class ListActivity extends AppCompatActivity implements DataAdapter.ClickItemListener, DataAdapter.SizeSelectedListener, View.OnClickListener {


    private final int REQUEST_ADD_CONTACT = 1;
    private final int REQUEST_DETAIL_CONTACT = 2;
    private final int REQUEST_EDIT_CONTACT = 3;
    private final int REQUEST_DELETE_CONTACT = 4;
    private final int REQUEST_IMPORT_CONTACTS = 5;
    private final DBHandler db = new DBHandler(this);
    private RecyclerView rv;
    private LinearLayoutManager layoutManager;
    private DataAdapter dataAdapter;
    private ArrayList<Contact> mContactArrayList = new ArrayList<>();
    private LinearLayout llList, llChoiceMode;
    private int positionItemClick;
    private ViewGroup.MarginLayoutParams params;
    private DisplayMetrics displayMetrics;
    private Button btnMultipleChoiceDelete, btnMultipleChoiceCancel;
    private Context context;
    private MenuItem itemAdd, itemMultipleChoiceDelete, itemImport, itemClearSelection, itemSelectAll;
    private ProgressDialog pdConfirmDelete;
    private ConfirmDeleteAsyncTask confirmDeleteTask;

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
        llChoiceMode = (LinearLayout) findViewById(R.id.llChoiceMode);
        displayMetrics = this.getResources().getDisplayMetrics();
        btnMultipleChoiceDelete = (Button) findViewById(R.id.btnMultipleChoiceDelete);
        btnMultipleChoiceCancel = (Button) findViewById(R.id.btnMultipleChoiceCancel);
        btnMultipleChoiceDelete.setOnClickListener(this);
        btnMultipleChoiceCancel.setOnClickListener(this);
        layoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(layoutManager);
        dataAdapter = new DataAdapter(this, mContactArrayList);
        dataAdapter.setClickItemListener(this);
        dataAdapter.setSizeSelectedListener(this);
        rv.setAdapter(dataAdapter);
        rv.setHasFixedSize(true);
        rv.addItemDecoration(new ItemDivider(this));
        context = getContext();
        confirmDeleteTask = new ConfirmDeleteAsyncTask();
       /* final StickyRecyclerHeadersDecoration headersDecor = new StickyRecyclerHeadersDecoration(dataAdapter);
        rv.addItemDecoration(headersDecor);*/


       /* llChoiceMode.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                llChoiceMode.getHeight();
                if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
                    llChoiceMode.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
                else {
                    llChoiceMode.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                }
            }
        });*/

        updateUI();
        //   FloatingActionButton addButton = (FloatingActionButton) findViewById(R.id.addButton);
    }


    class ConfirmDeleteAsyncTask extends AsyncTask <Void, Integer, Void> {

        public ConfirmDeleteAsyncTask(){

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pdConfirmDelete = new ProgressDialog(ListActivity.this);
            pdConfirmDelete.setTitle(R.string.deleting);
            pdConfirmDelete.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            pdConfirmDelete.setMax(dataAdapter.getSelectedHashMap().size());
            pdConfirmDelete.setCancelable(false);
            pdConfirmDelete.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            int count = 1;
            HashMap<Integer, Integer> selectedHashMap = dataAdapter.getSelectedHashMap();
            for (HashMap.Entry<Integer, Integer> entry : selectedHashMap.entrySet()) {

                Log.d("selectedHashMap", "Position: " + entry.getKey() + " ID: " + entry.getValue());

                db.deleteContact(entry.getValue());
                publishProgress(++count);
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
           // pdConfirmDelete.incrementProgressBy(values[0]);
            pdConfirmDelete.setProgress(values[0]);

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            pdConfirmDelete.dismiss();
            mContactArrayList.clear();
            mContactArrayList.addAll(db.getAllContacts());
            dataAdapter.notifyDataSetChanged();
            disableChoiceMode();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_list, menu);
        return true;


    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        itemAdd = (MenuItem) menu.findItem(R.id.itemAdd);
        itemMultipleChoiceDelete = (MenuItem) menu.findItem(R.id.itemMultipleChoiceDelete);
        itemImport = (MenuItem) menu.findItem(R.id.itemImport);
        itemClearSelection = (MenuItem) menu.findItem(R.id.itemClearSelection);
        itemSelectAll = (MenuItem) menu.findItem(R.id.itemSelectAll);
        itemClearSelection.setVisible(false);
        itemSelectAll.setVisible(false);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.itemAdd:
                Intent intent = new Intent(this, AddActivity.class);
                startActivityForResult(intent, REQUEST_ADD_CONTACT);
                break;

            case R.id.itemMultipleChoiceDelete:
                dataAdapter.changeMode();
                if (dataAdapter.getCurrentMode() == DataAdapter.MODE_SELECT) {
                    int valueInPixels = (int) getResources().getDimension(R.dimen.marginButtonMultipleChoice);
                    rv.setPadding(0, 0, 0, valueInPixels);
                    btnMultipleChoiceDelete.setText(getResources().getString(R.string.multiple_choice_delete, 0));

                    llChoiceMode.setAlpha(0f);
                    llChoiceMode.setVisibility(View.VISIBLE);
                    llChoiceMode.animate().setDuration(500);
                    llChoiceMode.animate().alpha(1).start();
                    getSupportActionBar().setTitle(R.string.choice_mode);
                    itemAdd.setVisible(false);
                    itemMultipleChoiceDelete.setVisible(false);
                    itemImport.setVisible(false);
                    itemClearSelection.setVisible(true);
                    itemSelectAll.setVisible(true);
                }
                break;

            case R.id.itemImport:
                Intent intentImport = new Intent(this, ImportActivity.class);
                startActivityForResult(intentImport, REQUEST_IMPORT_CONTACTS);
                break;

            case R.id.itemClearSelection:

                dataAdapter.getSelectedHashMap().clear();
                btnMultipleChoiceDelete.setText(getResources().getString(R.string.import_contacts, 0));
                dataAdapter.notifyDataSetChanged();
                break;

            case R.id.itemSelectAll:
                if (dataAdapter.getSelectedHashMap().size() == dataAdapter.getItemCount()) {
                    dataAdapter.getSelectedHashMap().clear();
                    btnMultipleChoiceDelete.setText(getResources().getString(R.string.import_contacts, 0));
                    dataAdapter.notifyDataSetChanged();
                } else {
                    dataAdapter.getSelectedHashMap().clear();
                    btnMultipleChoiceDelete.setText(getResources().getString(R.string.import_contacts, 0));

                    for (int i = 0; i < dataAdapter.getItemCount(); i++) {
                        dataAdapter.getSelectedHashMap().put(i ,mContactArrayList.get(i).getID() );
                    }
                    dataAdapter.notifyDataSetChanged();
                }
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
                    Log.d("myLogs", "Delete contact");
                    if (data == null) {
                        return;
                    }

                    final Contact contactEdit = db.getContactById(data.getLongExtra("id", -9999));
                    boolean isEdit = data.getBooleanExtra("edit", false);
                    if (isEdit) {
                        dataAdapter.changeItem(contactEdit, positionItemClick);
                    } else {

                        Long idForDel = data.getLongExtra("id", -1);
                        if (idForDel > 0) {
                            final Contact contact = db.getContactById(idForDel);
                            if (contact != null) {
                                dataAdapter.deleteItem(positionItemClick);
                            }
                            db.deleteContact(idForDel);
                        }
                    }
                    break;

                case REQUEST_IMPORT_CONTACTS:
                    Log.d("myLogs", "Import contacts");
                    mContactArrayList.clear();
                    mContactArrayList.addAll(db.getAllContacts());
                    dataAdapter.notifyDataSetChanged();
                    break;
            }
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        switch (dataAdapter.getCurrentMode()) {
            case DataAdapter.MODE_SELECT:
                disableChoiceMode();
                break;
            case DataAdapter.MODE_SIMPLE:
                super.onBackPressed();
                break;
        }
    }

    public void updateUI() {
        mContactArrayList.clear();
        mContactArrayList.addAll(db.getAllContacts());
        dataAdapter.notifyDataSetChanged();
    }


    public Context getContext() {
        return context;
    }

    @Override
    public void onItemClick(int position) {
        Log.d("itemClick", "itemClick" + position);
        positionItemClick = position;
        Intent intent = new Intent(this, DetailActivity.class);
        final Contact contact = mContactArrayList.get(position);
        intent.putExtra("contact", contact);
        startActivityForResult(intent, REQUEST_DELETE_CONTACT);
    }

    @Override
    public void selectedSize(int size) {
        btnMultipleChoiceDelete.setText(getResources().getString(R.string.multiple_choice_delete, size));
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnMultipleChoiceCancel:
                disableChoiceMode();
                break;

            case R.id.btnMultipleChoiceDelete:
                new ConfirmDeleteAsyncTask().execute();
                break;
        }
    }

    private void disableChoiceMode() {
        dataAdapter.changeMode();
        rv.setPadding(0, 0, 0, 0);
        llChoiceMode.setVisibility(View.GONE);
        getSupportActionBar().setTitle(R.string.app_name);
        itemAdd.setVisible(true);
        itemMultipleChoiceDelete.setVisible(true);
        itemImport.setVisible(true);
        itemClearSelection.setVisible(false);
        itemSelectAll.setVisible(false);
    }

}
