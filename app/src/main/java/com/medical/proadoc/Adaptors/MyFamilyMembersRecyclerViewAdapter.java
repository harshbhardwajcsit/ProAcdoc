package com.medical.proadoc.Adaptors;


import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.medical.proadoc.Models.FamilyMembersModel;
import com.medical.proadoc.Models.MedicalRecord;
import com.medical.proadoc.R;
import com.medical.proadoc.WalletDetails;

import java.util.List;

/**
 * Created by florentchampigny on 24/04/15.
 */
public class MyFamilyMembersRecyclerViewAdapter extends

        RecyclerView.Adapter<MyFamilyMembersRecyclerViewAdapter.SimpleItemViewHolder> {

    private List<FamilyMembersModel> items;
    public Activity activity;

    // Provide a reference to the views for each data item
    // Provide access to all the views for a data item in a view holder
    public final static class SimpleItemViewHolder extends RecyclerView.ViewHolder {

        TextView textViewName;

        TextView textViewDate, textViewRelation;
        CardView cardView;


        public SimpleItemViewHolder(View itemView) {
            super(itemView);

            textViewName = (TextView) itemView.findViewById(R.id.textViewName);

            textViewDate = (TextView) itemView.findViewById(R.id.textViewDate);
            textViewRelation = (TextView) itemView.findViewById(R.id.textViewRelation);
            cardView = (CardView) itemView.findViewById(R.id.cardView);


        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyFamilyMembersRecyclerViewAdapter(List<FamilyMembersModel> items, Activity _activity) {
        this.items = items;
        this.activity = _activity;
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
                inflate(R.layout.row_myfamilymembers, viewGroup, false);
        return new SimpleItemViewHolder(itemView);
    }

    // Replace the contents of a view (invoked by the layout manager)
    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(final SimpleItemViewHolder viewHolder, int position) {


        viewHolder.textViewName.setText(items.get(position).getName());
//        viewHolder.textViewRelation.setText(items.get(position).getRelation().getRelationName());
        viewHolder.textViewDate.setText(items.get(position).getDOB());
        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                        activity, viewHolder.cardView, "CardViewAnimator");
                ActivityCompat.startActivity(activity, new Intent(activity, WalletDetails.class),
                        options.toBundle());
            }
        });


    }

}