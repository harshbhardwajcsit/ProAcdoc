package com.medical.proadoc.Adaptors;


import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.medical.proadoc.Models.NotificationModel;
import com.medical.proadoc.R;

import java.util.List;

/**
 * Created by florentchampigny on 24/04/15.
 */
public class NotificationsRecyclerViewAdapter extends

        RecyclerView.Adapter<NotificationsRecyclerViewAdapter.SimpleItemViewHolder> {

    private List<NotificationModel> items;

    // Provide a reference to the views for each data item
    // Provide access to all the views for a data item in a view holder
    public final static class SimpleItemViewHolder extends RecyclerView.ViewHolder {

        TextView textViewTitle;
        TextView textViewAuthor;
        TextView  textViewDate;
        ImageView tvNotificationType;

        CardView cardView;

        public SimpleItemViewHolder(View itemView) {
            super(itemView);

            textViewTitle = (TextView) itemView.findViewById(R.id.textViewTitle);
            textViewAuthor = (TextView) itemView.findViewById(R.id.textViewAuthor);
            textViewDate= (TextView) itemView.findViewById(R.id.textViewDate);
            tvNotificationType=(ImageView) itemView.findViewById(R.id.tvNotificationType);

        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public NotificationsRecyclerViewAdapter(List<NotificationModel> items) {
        this.items = items;
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
                inflate(R.layout.row_notification, viewGroup, false);
        return new SimpleItemViewHolder(itemView);
    }

    // Replace the contents of a view (invoked by the layout manager)
    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(SimpleItemViewHolder viewHolder, int position) {



        viewHolder.textViewTitle.setText(items.get(position).getTitle());
        viewHolder.textViewAuthor.setText(items.get(position).getAuthor());
        viewHolder .textViewDate.setText(items.get(position).getDated());
        viewHolder.    tvNotificationType.setBackgroundColor(Color.RED);





    }
}