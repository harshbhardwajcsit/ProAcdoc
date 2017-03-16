package com.medical.proadoc.Adaptors;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.medical.proadoc.Models.MedicalRecord;
import com.medical.proadoc.Models.NotificationModel;
import com.medical.proadoc.R;
import com.medical.proadoc.WalletDetails;

import java.util.List;

/**
 * Created by florentchampigny on 24/04/15.
 */
public class MedicalWalletRecordsRecyclerViewAdapter extends

        RecyclerView.Adapter<MedicalWalletRecordsRecyclerViewAdapter.SimpleItemViewHolder> {

    private List<MedicalRecord> items;
    public  Activity activity;

    // Provide a reference to the views for each data item
    // Provide access to all the views for a data item in a view holder
    public final static class SimpleItemViewHolder extends RecyclerView.ViewHolder {

        TextView textViewName;
        TextView textViewAilment;
        TextView  textViewDate;
        TextView textViewDoctor;




        public SimpleItemViewHolder(View itemView) {
            super(itemView);

            textViewName = (TextView) itemView.findViewById(R.id.textViewName);
            textViewAilment = (TextView) itemView.findViewById(R.id.textViewAilment);
            textViewDate= (TextView) itemView.findViewById(R.id.textViewDate);
            textViewDoctor=(TextView) itemView.findViewById(R.id.textViewDoctor);

        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MedicalWalletRecordsRecyclerViewAdapter(List<MedicalRecord> items, Activity _activity) {
        this.items = items;
        this.activity=_activity;
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return this.items.size();
    }

    // Create new items (invoked by the layout manager)
    // Usually involves inflating a layout from XML and returning the holder
    @Override
    public SimpleItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.row_medicalrecords, viewGroup, false);
        return new SimpleItemViewHolder(itemView);
    }

    // Replace the contents of a view (invoked by the layout manager)
    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(SimpleItemViewHolder viewHolder, int position) {



        viewHolder.textViewName.setText(items.get(position).getName());
        viewHolder.textViewAilment.setText(items.get(position).getAilment());
        viewHolder .textViewDate.setText(items.get(position).getDate());
        viewHolder .textViewDoctor.setText(items.get(position).getDoctorName());
        viewHolder.textViewName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           //     Intent i=new Intent(activity, WalletDetails.class);
           //     activity.startActivity(i);

            }
        });


    }

}