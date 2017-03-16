package com.medical.proadoc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.medical.proadoc.Adaptors.TopicOfTheMonthRecyclerViewAdapter;
import com.medical.proadoc.Models.ChatQuestions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;
import java.util.List;

public class ViewAllQuestions extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;


    public ProgressBar progressBar;

    private List<ChatQuestions> arrayModel = new ArrayList<ChatQuestions>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_questions);

        ImageLoader imageLoader = ImageLoader.getInstance();

        imageLoader.init(ImageLoaderConfiguration.createDefault(getApplicationContext()));
        progressBar=(ProgressBar)findViewById(R.id.progress_bar);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);

        mAdapter = new TopicOfTheMonthRecyclerViewAdapter(arrayModel, getApplicationContext(), this,progressBar);
        mRecyclerView.setAdapter(mAdapter);
        for (int i = 0; i < ChatQuestions.GetDummyTopicOfTheMonth().size(); i++) {
            arrayModel.add(i,ChatQuestions.GetDummyTopicOfTheMonth().get(i));
            mAdapter.notifyDataSetChanged();
        }


    }
}
