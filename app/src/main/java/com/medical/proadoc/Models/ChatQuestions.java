package com.medical.proadoc.Models;

import java.util.ArrayList;

/**
 * Created by user on 1/12/2016.
 */
public class ChatQuestions {

    public String Image;
    public String Title;



    public String Dated;
    public String TotalLikes;

    public String getTotalLikes() {
        return TotalLikes;
    }

    public void setTotalLikes(String totalLikes) {
        TotalLikes = totalLikes;
    }

    public String getTotalComments() {
        return TotalComments;
    }

    public void setTotalComments(String totalComments) {
        TotalComments = totalComments;
    }



    public String TotalComments;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String ID;
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


    public String getDated() {
        return Dated;
    }

    public void setDated(String dated) {
        Dated = dated;
    }

     public String getDetails() {
        return Details;
    }

    public void setDetails(String details) {
        Details = details;
    }

    public String Details;

    public static ArrayList<ChatQuestions> GetDummyTopicOfTheMonth() {

        ArrayList<ChatQuestions> ArrayListTopicOFTheMonth = new ArrayList<ChatQuestions>(10);

        ChatQuestions topicOFTheMonthModel = new ChatQuestions();

        topicOFTheMonthModel.setDated("21-October");

        topicOFTheMonthModel.setImage("http://outofthisworldmarketing.net/wp-content/uploads/2015/01/doctors-offices.jpg");

        topicOFTheMonthModel.setTitle("Programmed to Overeat in Winter?");
        topicOFTheMonthModel.setDetails(
                "Sorry but this doesn't really answer the problems relative with the new design library. Sure having the toolbar as actionbar is the objective, but having multiple coordinator layouts for different types of toolbars can be tricky. What I found is that it is possible to animate the toolbar as needed. I still need to test it better but it seems there is good results\t\n" +
                        "sorry but this doesn't really answer the problems relative with the new design library. Sure having the toolbar as actionbar is the objective, but having multiple coordinator layouts for different types of toolbars can be tricky. What I found is that it is possible to animate the toolbar as needed. I still need to test it better but it seems there is good results\t\n" +
                        "sorry but this doesn't really answer the problems relative with the new design library. Sure having the toolbar as actionbar is the objective, but having multiple coordinator layouts for different types of toolbars can be tricky. What I found is that it is possible to animate the toolbar as needed. I still need to test it better but it seems there is good results\t\n" +
                        "sorry but this doesn't really answer the problems relative with the new design library. Sure having the toolbar as actionbar is the objective, but having multiple coordinator layouts for different types of toolbars can be tricky. What I found is that it is possible to animate the toolbar as needed. I still need to test it better but it seems there is good results\t\n" +
                        "sorry but this doesn't really answer the problems relative with the new design library. Sure having the toolbar as actionbar is the objective, but having multiple coordinator layouts for different types of toolbars can be tricky. What I found is that it is possible to animate the toolbar as needed. I still need to test it better but it seems there is good results");
        ArrayListTopicOFTheMonth.add(0, topicOFTheMonthModel);

        topicOFTheMonthModel = new ChatQuestions();

        topicOFTheMonthModel.setDated("21-October");
               topicOFTheMonthModel.setImage("http://img.webmd.com/dtmcms/live/webmd/consumer_assets/site_images/article_thumbnails/news/2016/01_2016/melanoma_exam/650x350_melanoma_exam.jpg");

        topicOFTheMonthModel.setTitle("Zika Virus: What You Should Know");
        ArrayListTopicOFTheMonth.add(1, topicOFTheMonthModel);


        topicOFTheMonthModel = new ChatQuestions();

        topicOFTheMonthModel.setDated("21-October");

        topicOFTheMonthModel.setImage("http://img.webmd.com/dtmcms/live/webmd/consumer_assets/site_images/articles/health_tools/habits_that_wreck_your_teeth_slideshow/getty_rm_photo_of_woman_eating_ice.jpg");

        topicOFTheMonthModel.setTitle("What Does Psoriasis Look Like?");
        ArrayListTopicOFTheMonth.add(2, topicOFTheMonthModel);


        topicOFTheMonthModel = new ChatQuestions();

        topicOFTheMonthModel.setDated("21-October");

        topicOFTheMonthModel.setImage("http://outofthisworldmarketing.net/wp-content/uploads/2015/01/doctors-offices.jpg");

        topicOFTheMonthModel.setTitle("Zika Virus: What You Should Know");
        ArrayListTopicOFTheMonth.add(3, topicOFTheMonthModel);


        topicOFTheMonthModel = new ChatQuestions();

        topicOFTheMonthModel.setDated("21-October");

        topicOFTheMonthModel.setImage("http://outofthisworldmarketing.net/wp-content/uploads/2015/01/doctors-offices.jpg");

        topicOFTheMonthModel.setTitle("Zika Virus: What You Should Know");
        ArrayListTopicOFTheMonth.add(4, topicOFTheMonthModel);


        topicOFTheMonthModel = new ChatQuestions();

        topicOFTheMonthModel.setDated("21-October");

        topicOFTheMonthModel.setImage("http://outofthisworldmarketing.net/wp-content/uploads/2015/01/doctors-offices.jpg");

        topicOFTheMonthModel.setTitle("Zika Virus: What You Should Know");
        ArrayListTopicOFTheMonth.add(5, topicOFTheMonthModel);


        topicOFTheMonthModel = new ChatQuestions();

        topicOFTheMonthModel.setDated("21-October");

        topicOFTheMonthModel.setImage("http://outofthisworldmarketing.net/wp-content/uploads/2015/01/doctors-offices.jpg");

        topicOFTheMonthModel.setTitle("Zika Virus: What You Should Know");
        ArrayListTopicOFTheMonth.add(6, topicOFTheMonthModel);


        topicOFTheMonthModel = new ChatQuestions();

        topicOFTheMonthModel.setDated("21-October");

        topicOFTheMonthModel.setImage("http://outofthisworldmarketing.net/wp-content/uploads/2015/01/doctors-offices.jpg");

        topicOFTheMonthModel.setTitle("Zika Virus: What You Should Know");
        ArrayListTopicOFTheMonth.add(7, topicOFTheMonthModel);


        topicOFTheMonthModel = new ChatQuestions();

        topicOFTheMonthModel.setDated("21-October");

        topicOFTheMonthModel.setImage("http://outofthisworldmarketing.net/wp-content/uploads/2015/01/doctors-offices.jpg");

        topicOFTheMonthModel.setTitle("Zika Virus: What You Should Know");
        ArrayListTopicOFTheMonth.add(8, topicOFTheMonthModel);


        topicOFTheMonthModel = new ChatQuestions();

        topicOFTheMonthModel.setDated("21-October");

        topicOFTheMonthModel.setImage("http://outofthisworldmarketing.net/wp-content/uploads/2015/01/doctors-offices.jpg");

        topicOFTheMonthModel.setTitle("Zika Virus: What You Should Know");
        ArrayListTopicOFTheMonth.add(9, topicOFTheMonthModel);


        return ArrayListTopicOFTheMonth;


    }

    public static ChatQuestions getTopicOfTheMonthModel() {
        return topicOfTheMonthModel;
    }

    public static void setTopicOfTheMonthModel(ChatQuestions topicOfTheMonthModel) {
        ChatQuestions.topicOfTheMonthModel = topicOfTheMonthModel;
    }

    public static ChatQuestions topicOfTheMonthModel;
}
