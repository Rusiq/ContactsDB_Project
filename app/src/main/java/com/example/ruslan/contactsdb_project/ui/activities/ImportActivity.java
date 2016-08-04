package com.example.ruslan.contactsdb_project.ui.activities;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.ruslan.contactsdb_project.R;
import com.example.ruslan.contactsdb_project.adapters.DataAdapter;
import com.example.ruslan.contactsdb_project.data.Contact;
import com.example.ruslan.contactsdb_project.data.DBHandler;
import com.example.ruslan.contactsdb_project.ui.ItemDivider;

import java.util.ArrayList;
import java.util.HashMap;

public class ImportActivity extends AppCompatActivity implements View.OnClickListener, DataAdapter.SizeSelectedListener, DataAdapter.ClickItemListener {

    private RecyclerView rvImport;
    private Button btnImportContacts, btnImportCancel;
    private DataAdapter dataAdapter;
    private LinearLayoutManager layoutManager;
    private LinearLayout llImportMode;
    private final DBHandler db = new DBHandler(this);
    private ArrayList<Contact> mContactArrayList = new ArrayList<>();
    private String[] projection;
    private Uri mContactUri;
    private SimpleCursorAdapter mCursorAdapter;
    private ImportAsyncTask importTask;
    private ConfirmImportAsyncTask confirmImportTask;
    private ProgressBar pbImport;
    private ProgressDialog pdConfirmImport;
    private MenuItem itemSelectAll;
    private static final int PERMISSIONS_REQUEST_READ_CONTACTS = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_import);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_import); // Attaching the layout to the toolbar object
        setSupportActionBar(toolbar);


        layoutManager = new LinearLayoutManager(this);
        llImportMode = (LinearLayout) findViewById(R.id.llImportMode);
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
        rvImport.addItemDecoration(new ItemDivider(this));
        pbImport = (ProgressBar) findViewById(R.id.progressImport);

        llImportMode.setAlpha(0f);
        llImportMode.setVisibility(View.VISIBLE);
        llImportMode.animate().setDuration(500);
        llImportMode.animate().alpha(1).start();

        importTask = new ImportAsyncTask();
        confirmImportTask = new ConfirmImportAsyncTask();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, PERMISSIONS_REQUEST_READ_CONTACTS);
        } else {
            importTask.execute();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSIONS_REQUEST_READ_CONTACTS) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission is granted
                importTask.execute();
            } else {
                Toast.makeText(this, "Until you grant the permission, we canot display the names", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    class ImportAsyncTask extends AsyncTask<Void, Void, ArrayList<Contact>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pbImport.setVisibility(View.VISIBLE);
            invalidateOptionsMenu();
        }

        @Override
        protected ArrayList<Contact> doInBackground(Void... params) {

            ArrayList<Contact> list = new ArrayList<>();
            ContentResolver cr = getContentResolver();
            Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI,
                    null, null, null, null);

            if (cur.getCount() > 0)

            {

                while (cur.moveToNext()) {

                    String id = cur.getString(cur.getColumnIndex(ContactsContract.Contacts._ID));
                    String name = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                    Contact contact = new Contact();

                    contact.setFirstName(name);
                    if (Integer.parseInt(cur.getString(cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {

                        Cursor pCur = cr.query(
                                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                                null,
                                ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                                new String[]{id}, null);

                        while (pCur.moveToNext()) {
                            String phoneNo = pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                            //  Toast.makeText(ImportActivity.this, "Name: " + name + ", Phone No: " + phoneNo, Toast.LENGTH_SHORT).show();
                            contact.setPhoneNumber(contact.getPhoneNumber() + "; " + phoneNo);

//                            if (TextUtils.isEmpty(contact.getPhoneNumber()))
//                                contact.setPhoneNumber(phoneNo);
//                            else {
//                                String number1 = contact.getPhoneNumber().replace(" ", " ");
//                                number1 = number1.replace("-", " ");
//                                phoneNo = phoneNo.replace("-", " ");
//                                phoneNo = phoneNo.replace(" ", " ");
//                                if (!number1.contains(phoneNo))
//                                    contact.setPhoneNumber(contact.getPhoneNumber() + "; " + phoneNo);
//                            }
                        }
                        pCur.close();
                        list.add(contact);
                    }
                }
            }
            return list;
        }


        @Override
        protected void onPostExecute(ArrayList<Contact> result) {
            super.onPostExecute(result);
            mContactArrayList.clear();
            mContactArrayList.addAll(result);
            dataAdapter.notifyDataSetChanged();
            pbImport.setVisibility(View.INVISIBLE);
            invalidateOptionsMenu();
        }
    }


    class ConfirmImportAsyncTask extends AsyncTask<Void, Integer, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pdConfirmImport = new ProgressDialog(ImportActivity.this);
            pdConfirmImport.setTitle(R.string.importing);
            pdConfirmImport.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            pdConfirmImport.setMax(dataAdapter.getSelectedHashMap().size());
            pdConfirmImport.setCancelable(false);
            //  pdConfirmImport.setIndeterminate(true);
            pdConfirmImport.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            int count = 1;
            HashMap<Integer, Integer> selectedHashMap = dataAdapter.getSelectedHashMap();
            for (HashMap.Entry<Integer, Integer> entry : selectedHashMap.entrySet()) {
                Contact contact = mContactArrayList.get(entry.getKey());
                contact.setMaritalStatus(Contact.MaritalStatus.single);
                contact.setGender(Contact.Gender.male);
                db.addContact(contact);
                //     count = entry.getKey();
                publishProgress(++count);
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            pdConfirmImport.setProgress(values[0]);

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Intent intent = new Intent();
            setResult(ImportActivity.RESULT_OK, intent);
            pdConfirmImport.dismiss();
            finish();
        }
    }

/*    public void listAccounts(Context ctx) {

        AccountManager am = AccountManager.get(ctx);
        Account[] accounts = am.getAccounts();

        for (Account ac : accounts) {
            String acname = ac.name;
            String actype = ac.type;
            Log.d("accountinfo", acname + " : " + actype);
        }
    }*/


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_import, menu);

        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        itemSelectAll = (MenuItem) menu.findItem(R.id.itemSelectAll);
        if (mContactArrayList == null){
            itemSelectAll.setCheckable(false);
        } else itemSelectAll.setCheckable(true);
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
                if (dataAdapter.getSelectedHashMap().size() == 0){
                    Toast.makeText(this, "Please select contacts for import", Toast.LENGTH_SHORT).show();
                } else new ConfirmImportAsyncTask().execute();

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





