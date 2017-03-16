package com.medical.proadoc.Adaptors;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.medical.proadoc.Models.AilmentModel;
import com.medical.proadoc.Models.MedicalServicesProviderModel;
import com.medical.proadoc.Models.NotificationModel;
import com.medical.proadoc.R;
import com.medical.proadoc.ViewAllQuestions;
import com.medical.proadoc.ViewMedicalProviderLists;

import java.util.List;

/**
 * Created by florentchampigny on 24/04/15.
 */
public class AilmentsChatsRecyclerViewAdapter extends

        RecyclerView.Adapter<AilmentsChatsRecyclerViewAdapter.SimpleItemViewHolder> {

    private List<AilmentModel> items;
    private Activity activity;

    // Provide a reference to the views for each data item
    // Provide access to all the views for a data item in a view holder
    public final static class SimpleItemViewHolder extends RecyclerView.ViewHolder {

        TextView textViewTitle;
        CardView cardView;

        public SimpleItemViewHolder(View itemView) {
            super(itemView);

            textViewTitle = (TextView) itemView.findViewById(R.id.textViewTitle);
            cardView = (CardView) itemView.findViewById(R.id.cardView);

        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public AilmentsChatsRecyclerViewAdapter(List<AilmentModel> items,Activity _activity) {
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
                inflate(R.layout.row_ailmentschats, viewGroup, false);
        return new SimpleItemViewHolder(itemView);
    }

    // Replace the contents of a view (invoked by the layout manager)
    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(SimpleItemViewHolder viewHolder,final int position) {



        viewHolder.textViewTitle.setText(items.get(position).getAilmentName());
        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AilmentModel.setAilmentModel(items.get(position));
              Intent i=new Intent(activity, ViewAllQuestions.class);
                activity.startActivity(i);
            }
        });






    }
}