package com.medical.proadoc.Adaptors;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.medical.proadoc.AddNewFamilyMember;
import com.medical.proadoc.Models.CityModel;
import com.medical.proadoc.R;
import com.medical.proadoc.Register;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;


public class AddNewMemberCityAdapter extends RecyclerView.Adapter<AddNewMemberCityAdapter.SimpleItemViewHolder> {

    private List<CityModel> items;
    public static Context context;

    // Provide a reference to the views for each data item
    // Provide access to all the views for a data item in a view holder
    public final static class SimpleItemViewHolder extends RecyclerView.ViewHolder {

        TextView tvCountryName;
        LinearLayout llCountryRow;


        public SimpleItemViewHolder(View itemView) {
            super(itemView);

            tvCountryName = (TextView) itemView.findViewById(R.id.tvCountryName);

            llCountryRow = (LinearLayout) itemView.findViewById(R.id.llCountryRow);

        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public AddNewMemberCityAdapter(List<CityModel> items, Context context) {
        this.items = items;
        this.context = context;
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
                inflate(R.layout.row_city_selector, viewGroup, false);
        return new SimpleItemViewHolder(itemView);
    }

    // Replace the contents of a view (invoked by the layout manager)
    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(SimpleItemViewHolder viewHolder, final int position) {

        ImageLoader imageLoader = ImageLoader.getInstance();

        viewHolder.tvCountryName.setText(items.get(position).getCityName());
        viewHolder.llCountryRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddNewFamilyMember.dFragment.dismiss();
                AddNewFamilyMember.editTextCity.setText(items.get(position).getCityName());
                AddNewFamilyMember.CityId= items.get(position).getCityId();



            }
        });

    }
}