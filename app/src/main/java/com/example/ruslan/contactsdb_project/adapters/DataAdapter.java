package com.example.ruslan.contactsdb_project.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ruslan.contactsdb_project.R;
import com.example.ruslan.contactsdb_project.data.Contact;

import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

    public static final int MODE_SIMPLE = 0;
    public static final int MODE_SELECT = 1;
    private Context mContext;
    private List<Contact> mContactList;
    private ClickItemListener clickItemListener;
    private int mode = MODE_SIMPLE;

    public interface ClickItemListener{
        void onItemClick(int position);
    }

    public void setClickItemListener(ClickItemListener clickItemListener) {
        this.clickItemListener = clickItemListener;
    }

    public DataAdapter(Context context, List<Contact> objects) {
        mContext = context;
        mContactList = objects;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        CardView mCardView;
        TextView tvShowFirstName, tvShowLastName;

        public ViewHolder(View itemView) {
            super(itemView);

            mCardView = (CardView) itemView.findViewById(R.id.card_view);
            tvShowFirstName = (TextView) itemView.findViewById(R.id.tvShowFirstName);
            tvShowLastName = (TextView) itemView.findViewById(R.id.tvShowLastName);

            mCardView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION && view.getId() == R.id.card_view){
                if (clickItemListener !=null){
                    clickItemListener.onItemClick(position);
                }
            }
        }

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Contact contact = mContactList.get(position);
        holder.tvShowFirstName.setText(contact.getFirstName());
        holder.tvShowLastName.setText(contact.getLastName());

      //  holder.tvShowFirstName.setVisibility((mode == MODE_SELECT) ? View.VISIBLE : View.GONE);

    }

    public void setMode(int mode){
        this.mode = mode;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return mContactList.size();
    }


    public void addItem(Contact item) {

        mContactList.add(item);
        notifyItemInserted(mContactList.size());
    }


    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }




}
