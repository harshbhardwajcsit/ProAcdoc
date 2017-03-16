package com.medical.proadoc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.medical.proadoc.Models.ChatQuestions;
import com.nostra13.universalimageloader.core.ImageLoader;

public class TopicofTheMonthDetails extends AppCompatActivity {


    private ImageView topicImage;
    private Toolbar toolbar;
    private TextView topicDetails, topicTitle, topicDate, topicAuthor, toolbarTitle;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topicof_the_month_details);
        findViewById();


    }

    public void findViewById() {


        // Toolbar Manage
        toolbar = (Toolbar) findViewById(R.id.toolbar);


        toolbarTitle = (TextView) toolbar.findViewById(R.id.toolbarTitle);
        topicTitle = (TextView) findViewById(R.id.topicTitle);
        topicDetails = (TextView) findViewById(R.id.topicDetails);
        topicAuthor = (TextView) findViewById(R.id.topicAuthor);
        topicDate = (TextView) findViewById(R.id.topicDate);
        topicImage=(ImageView) findViewById(R.id.topicImage);
        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.displayImage(ChatQuestions.getTopicOfTheMonthModel().getImage(), topicImage);



        // Toolbar Manage
        toolbarTitle.setText(ChatQuestions.getTopicOfTheMonthModel().getTitle());

        topicTitle.setText(ChatQuestions.getTopicOfTheMonthModel().getTitle());
        topicDetails.setText(ChatQuestions.getTopicOfTheMonthModel().getDetails());

        topicDate.setText(ChatQuestions.getTopicOfTheMonthModel().getDated());



        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.backarrow));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
            }
        });



    }


}
