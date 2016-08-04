package com.example.ruslan.contactsdb_project.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ruslan.contactsdb_project.R;
import com.example.ruslan.contactsdb_project.data.Contact;
import com.example.ruslan.contactsdb_project.data.DBHandler;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersAdapter;

import java.util.HashMap;
import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> implements StickyRecyclerHeadersAdapter {

    public static final int MODE_SIMPLE = 0;
    public static final int MODE_SELECT = 1;
    private Context mContext;
    private List<Contact> mContactList;
    private ClickItemListener clickItemListener;
    private SizeSelectedListener sizeSelectedListener;
    private int mode = MODE_SIMPLE;
    private HashMap<Integer, Integer> selected = new HashMap<>();


    public interface ClickItemListener {
        void onItemClick(int position);
    }

    public interface SizeSelectedListener {
        void selectedSize(int size);
    }


    public void setClickItemListener(ClickItemListener clickItemListener) {
        this.clickItemListener = clickItemListener;
    }

    public void setSizeSelectedListener(SizeSelectedListener sizeSelectedListener) {
        this.sizeSelectedListener = sizeSelectedListener;
    }


    public DataAdapter(Context context, List<Contact> objects) {
        mContext = context;
        mContactList = objects;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        CardView mCardView;
        TextView tvShowFirstName, tvShowLastName;
        LinearLayout content;

        CheckBox chkMultipleMode;


        public ViewHolder(View itemView) {
            super(itemView);

            //   mCardView = (CardView) itemView.findViewById(R.id.card_view);
            content = (LinearLayout) itemView.findViewById(R.id.content);
            tvShowFirstName = (TextView) itemView.findViewById(R.id.tvShowFirstName);
            tvShowLastName = (TextView) itemView.findViewById(R.id.tvShowLastName);

            chkMultipleMode = (CheckBox) itemView.findViewById(R.id.chkMultipleMode);
            chkMultipleMode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        if (!selected.containsKey(getAdapterPosition())) {
                            selected.put(getAdapterPosition(), mContactList.get(getAdapterPosition()).getID());
                        }
                    } else if (selected.containsKey(getAdapterPosition())) {
                        selected.remove(getAdapterPosition());

                    }
                    if (sizeSelectedListener != null)
                        sizeSelectedListener.selectedSize(selected.size());

                }
            });

            //   mCardView.setOnClickListener(this);
            content.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION && view.getId() == R.id.content) {
                if (clickItemListener != null) {
                    if (mode == MODE_SIMPLE)
                        clickItemListener.onItemClick(position);
                    else
                        chkMultipleMode.setChecked(!chkMultipleMode.isChecked());
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

        Log.i(DBHandler.class.getName(), "Contact id" + contact.getID());


        holder.chkMultipleMode.setVisibility((mode == MODE_SELECT) ? View.VISIBLE : View.GONE);
        holder.chkMultipleMode.setChecked(selected.containsKey(position));

    }

    public void changeMode() {
        if (mode == MODE_SIMPLE)
            mode = MODE_SELECT;
        else {
            mode = MODE_SIMPLE;
            selected.clear();
        }
        notifyDataSetChanged();
    }

    public int getCurrentMode() {
        return mode;
    }

    public HashMap<Integer, Integer> getSelectedHashMap() {
        return selected;
    }


    @Override
    public int getItemCount() {
        return mContactList.size();
    }

    public void selectAllItem() {

        for (int i = 0; i < mContactList.size(); i++) {

        }
        notifyDataSetChanged();
    }


    public void addItem(Contact item) {

        mContactList.add(item);
        notifyItemInserted(mContactList.size());
    }

    public void changeItem(Contact item, int position) {

        mContactList.set(position, item);
        notifyItemChanged(position);
    }

    public void deleteItem(int position) {
        mContactList.remove(position);
        notifyItemRemoved(position);
    }

    public void deleteItemChoiceMode(int position) {
        mContactList.remove(position);
    }


    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


    @Override
    public long getHeaderId(int position) {
        return mContactList.get(position).getFirstName().charAt(0);
    }

    @Override
    public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
         View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_header, parent, false);
        return new HeaderHolder(view);

    }

    class HeaderHolder extends RecyclerView.ViewHolder {
        TextView header;

        public HeaderHolder(View itemView) {
            super(itemView);
            header = (TextView) itemView.findViewById(R.id.header);
        }
    }


    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {

        TextView textView = (TextView) holder.itemView.findViewById(R.id.header);
        textView.setText(String.valueOf(mContactList.get(position).getFirstName().charAt(0)));

    }

}
