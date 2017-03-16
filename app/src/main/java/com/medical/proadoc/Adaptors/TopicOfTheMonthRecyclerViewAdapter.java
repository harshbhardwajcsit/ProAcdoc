package com.medical.proadoc.Adaptors;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import com.medical.proadoc.Models.ChatQuestions;
import com.medical.proadoc.R;
import com.medical.proadoc.TopicofTheMonthDetails;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by florentchampigny on 24/04/15.
 */
public class TopicOfTheMonthRecyclerViewAdapter extends

        RecyclerView.Adapter<TopicOfTheMonthRecyclerViewAdapter.SimpleItemViewHolder> {

    private List<ChatQuestions> items;
    private Context context;
    private Activity activity;
    private ProgressBar progressBar;
    private RelativeLayout rvMain;

    // Provide a reference to the views for each data item
    // Provide access to all the views for a data item in a view holder
    public final static class SimpleItemViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView title;
        TextView textViewDated;
        TextView textViewAuthor;
        TextView textViewDesgnWorkplace;


        public SimpleItemViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.imageView);
            title = (TextView) itemView.findViewById(R.id.title);
            textViewDated = (TextView) itemView.findViewById(R.id.textViewDated);
            textViewAuthor = (TextView) itemView.findViewById(R.id.textViewAuthor);
            textViewDesgnWorkplace = (TextView) itemView.findViewById(R.id.textViewDesgnWorkplace);

        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public TopicOfTheMonthRecyclerViewAdapter(List<ChatQuestions> items, Context _context, Activity _activity,ProgressBar _progressBar) {
        this.items = items;
        this.context = _context;
        this.activity = _activity;
        this.progressBar=_progressBar;

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
                inflate(R.layout.row_topic_of_the_month, viewGroup, false);
        YoYo.with(Techniques.FadeIn).duration(700).playOn(itemView);

        return new SimpleItemViewHolder(itemView);
    }

    // Replace the contents of a view (invoked by the layout manager)
    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(final SimpleItemViewHolder viewHolder, final int position) {

        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.displayImage(items.get(position).getImage(), viewHolder.image);
        //  viewHolder.image.setImageResource(items.get(position).getImage());
        viewHolder.image.setTag(position);
        viewHolder.title.setText(items.get(position).getTitle());

        viewHolder.textViewDated.setText(items.get(position).getDated());
      //  viewHolder.textViewAuthor.setText(items.get(position).getAuthor());













     //   viewHolder.textViewDesgnWorkplace.setText(items.get(position).getDesignation());
        viewHolder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ChatQuestions.setTopicOfTheMonthModel(items.get(position));


                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                        activity, viewHolder.image, "TopicOfTheMonthImage");
                ActivityCompat.startActivity(activity, new Intent(activity, TopicofTheMonthDetails.class),
                        options.toBundle());
            }
        });


    }


}
