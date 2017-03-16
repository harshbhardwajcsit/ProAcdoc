package com.medical.proadoc.Models;

import java.util.ArrayList;

/**
 * Created by user on 1/12/2016.
 */
public class NotificationModel {

    public String Image;
    public String Title;

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }



    public String getDated() {
        return Dated;
    }

    public void setDated(String dated) {
        Dated = dated;
    }

    public String Author ;

    public String Dated;

    public static ArrayList<NotificationModel> GetDummyTopicOfTheMonth()
    {

        ArrayList<NotificationModel> ArrayListTopicOFTheMonth=new ArrayList<NotificationModel>(10);

        NotificationModel topicOFTheMonthModel = new NotificationModel();
        topicOFTheMonthModel.setAuthor("Reena Mathur");
        topicOFTheMonthModel.setDated("21-October");

        topicOFTheMonthModel.setImage("http://outofthisworldmarketing.net/wp-content/uploads/2015/01/doctors-offices.jpg");

        topicOFTheMonthModel.setTitle("Programmed to Overeat ");
        ArrayListTopicOFTheMonth.add(0, topicOFTheMonthModel);

         topicOFTheMonthModel = new NotificationModel();
        topicOFTheMonthModel.setAuthor("Reena Mathur");
        topicOFTheMonthModel.setDated("21-October");
        topicOFTheMonthModel.setImage("http://img.webmd.com/dtmcms/live/webmd/consumer_assets/site_images/article_thumbnails/news/2016/01_2016/melanoma_exam/650x350_melanoma_exam.jpg");

        topicOFTheMonthModel.setTitle("Zika Virus: What");
        ArrayListTopicOFTheMonth.add(1, topicOFTheMonthModel);




         topicOFTheMonthModel = new NotificationModel();
        topicOFTheMonthModel.setAuthor("Reena Mathur");
        topicOFTheMonthModel.setDated("21-October");

        topicOFTheMonthModel.setImage("http://img.webmd.com/dtmcms/live/webmd/consumer_assets/site_images/articles/health_tools/habits_that_wreck_your_teeth_slideshow/getty_rm_photo_of_woman_eating_ice.jpg");

        topicOFTheMonthModel.setTitle("What Does Psoriasis ");
        ArrayListTopicOFTheMonth.add(2, topicOFTheMonthModel);


        topicOFTheMonthModel = new NotificationModel();
        topicOFTheMonthModel.setAuthor("Reena Mathur");
        topicOFTheMonthModel.setDated("21-October");

        topicOFTheMonthModel.setImage("http://outofthisworldmarketing.net/wp-content/uploads/2015/01/doctors-offices.jpg");

        topicOFTheMonthModel.setTitle("Zika Virus: What");
        ArrayListTopicOFTheMonth.add(3, topicOFTheMonthModel);


        topicOFTheMonthModel = new NotificationModel();
        topicOFTheMonthModel.setAuthor("Reena Mathur");
        topicOFTheMonthModel.setDated("21-October");
        topicOFTheMonthModel.setImage("http://outofthisworldmarketing.net/wp-content/uploads/2015/01/doctors-offices.jpg");
        topicOFTheMonthModel.setTitle("Zika Virus: What You");
        ArrayListTopicOFTheMonth.add(4, topicOFTheMonthModel);


        topicOFTheMonthModel = new NotificationModel();
        topicOFTheMonthModel.setAuthor("Reena Mathur");
        topicOFTheMonthModel.setDated("21-October");
        topicOFTheMonthModel.setImage("http://outofthisworldmarketing.net/wp-content/uploads/2015/01/doctors-offices.jpg");
        topicOFTheMonthModel.setTitle("Zika Virus: What You");
        ArrayListTopicOFTheMonth.add(5, topicOFTheMonthModel);


        topicOFTheMonthModel = new NotificationModel();
        topicOFTheMonthModel.setAuthor("Reena Mathur");
        topicOFTheMonthModel.setDated("21-October");

        topicOFTheMonthModel.setImage("http://outofthisworldmarketing.net/wp-content/uploads/2015/01/doctors-offices.jpg");

        topicOFTheMonthModel.setTitle("Zika Virus: What You ");
        ArrayListTopicOFTheMonth.add(6, topicOFTheMonthModel);


        topicOFTheMonthModel = new NotificationModel();
        topicOFTheMonthModel.setAuthor("Reena Mathur");
        topicOFTheMonthModel.setDated("21-October");

        topicOFTheMonthModel.setImage("http://outofthisworldmarketing.net/wp-content/uploads/2015/01/doctors-offices.jpg");

        topicOFTheMonthModel.setTitle("Zika Virus: What You ");
        ArrayListTopicOFTheMonth.add(7, topicOFTheMonthModel);


        topicOFTheMonthModel = new NotificationModel();
        topicOFTheMonthModel.setAuthor("Reena Mathur");
        topicOFTheMonthModel.setDated("21-October");

        topicOFTheMonthModel.setImage("http://outofthisworldmarketing.net/wp-content/uploads/2015/01/doctors-offices.jpg");

        topicOFTheMonthModel.setTitle("Zika Virus: What You ");
        ArrayListTopicOFTheMonth.add(8, topicOFTheMonthModel);


        topicOFTheMonthModel = new NotificationModel();
        topicOFTheMonthModel.setAuthor("Reena Mathur");
        topicOFTheMonthModel.setDated("21-October");

        topicOFTheMonthModel.setImage("http://outofthisworldmarketing.net/wp-content/uploads/2015/01/doctors-offices.jpg");

        topicOFTheMonthModel.setTitle("Zika Virus: What You ");
        ArrayListTopicOFTheMonth.add(9,topicOFTheMonthModel);










        return ArrayListTopicOFTheMonth;


    }


}
