package com.example.ruslan.contactsdb_project;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

    private Context mContext;
    private List<Contact> mContactList;


    public DataAdapter(Context context, List<Contact> objects) {
        mContext = context;
        mContactList = objects;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(DataAdapter.ViewHolder holder, int position) {

        Contact contact = mContactList.get(position);

        holder.tvShowFirstName.setText(contact.getFirstName());
        holder.tvShowLastName.setText(contact.getLastName());
        holder.tvShowPhone.setText(contact.getPhoneNumber());
        holder.tvShowAddress.setText(contact.getAddress());

        holder.tvShowJob.setText(contact.getJob());
        holder.tvShowStatus.setText(contact.getMaritalStatus());
        holder.tvShowGender.setText(contact.getGender());
        holder.tvShowEmail.setText(contact.getEmail());


    }


    @Override
    public int getItemCount() {
        return mContactList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        CardView cv;
        TextView tvShowFirstName, tvShowLastName, tvShowPhone, tvShowAddress, tvShowJob, tvShowStatus, tvShowGender, tvShowEmail;

        public ViewHolder(View itemView) {
            super(itemView);

            cv = (CardView) itemView.findViewById(R.id.card_view);

            tvShowFirstName = (TextView) itemView.findViewById(R.id.tvShowFirstName);
            tvShowLastName = (TextView) itemView.findViewById(R.id.tvShowLastName);
            tvShowPhone = (TextView) itemView.findViewById(R.id.tvShowPhone);
            tvShowAddress = (TextView) itemView.findViewById(R.id.tvShowAddress);

            tvShowJob = (TextView) itemView.findViewById(R.id.tvShowJob);
            tvShowStatus = (TextView) itemView.findViewById(R.id.tvShowStatus);
            tvShowGender = (TextView) itemView.findViewById(R.id.tvShowGender);
            tvShowEmail = (TextView) itemView.findViewById(R.id.tvShowEmail);
        }
    }
}
