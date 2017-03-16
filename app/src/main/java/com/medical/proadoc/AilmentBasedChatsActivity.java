package com.medical.proadoc;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.medical.proadoc.Adaptors.AilmentsChatsRecyclerViewAdapter;
import com.medical.proadoc.Adaptors.NotificationsRecyclerViewAdapter;
import com.medical.proadoc.Models.AilmentModel;
import com.medical.proadoc.Models.NotificationModel;

import java.util.ArrayList;
import java.util.List;

public class AilmentBasedChatsActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private List<AilmentModel> mContentItems = new ArrayList<AilmentModel>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ailment_based_chats);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Ailment Based Chat");


        toolbar.inflateMenu(R.menu.menu_main);

        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.backarrow));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
            }
        });
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(AilmentBasedChatsActivity.this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);

        mAdapter = new AilmentsChatsRecyclerViewAdapter(mContentItems,this);
        mRecyclerView.setAdapter(mAdapter);

        {
            for (int i = 0; i < AilmentModel.GetDummyAilments().size(); ++i)
                mContentItems.add(AilmentModel.GetDummyAilments().get(i));
            mAdapter.notifyDataSetChanged();
        }




    }


}
