package com.medical.proadoc.Models;

import java.util.ArrayList;

/**
 * Created by hp on 2/16/2016.
 */
public class AilmentModel {
    public String AilmentId;

    public String getAilmentName() {
        return AilmentName;
    }

    public void setAilmentName(String ailmentName) {
        AilmentName = ailmentName;
    }

    public String getAilmentId() {
        return AilmentId;
    }

    public void setAilmentId(String ailmentId) {
        AilmentId = ailmentId;
    }

    public String AilmentName;
    public static ArrayList<AilmentModel> GetDummyAilments()
    {

        ArrayList<AilmentModel> ArrayListTopicOFTheMonth=new ArrayList<AilmentModel>(10);

        AilmentModel topicOFTheMonthModel = new AilmentModel();
        topicOFTheMonthModel.setAilmentName("Cancer");

        ArrayListTopicOFTheMonth.add(0, topicOFTheMonthModel);

        topicOFTheMonthModel = new AilmentModel();
        topicOFTheMonthModel.setAilmentName("Artheritis");
        ArrayListTopicOFTheMonth.add(1, topicOFTheMonthModel);




        topicOFTheMonthModel = new AilmentModel();
        topicOFTheMonthModel.setAilmentName("Bipolar Disorder");
        ArrayListTopicOFTheMonth.add(2, topicOFTheMonthModel);


        topicOFTheMonthModel = new AilmentModel();
        topicOFTheMonthModel.setAilmentName("Burn Injury");
        ArrayListTopicOFTheMonth.add(3, topicOFTheMonthModel);


        topicOFTheMonthModel = new AilmentModel();
        topicOFTheMonthModel.setAilmentName("Heart Disease");
        ArrayListTopicOFTheMonth.add(4, topicOFTheMonthModel);


        topicOFTheMonthModel = new AilmentModel();
        topicOFTheMonthModel.setAilmentName("Alzhimer's Disease");
        ArrayListTopicOFTheMonth.add(5, topicOFTheMonthModel);


        topicOFTheMonthModel = new AilmentModel();
        topicOFTheMonthModel.setAilmentName("Kidney Disorders");
        ArrayListTopicOFTheMonth.add(6, topicOFTheMonthModel);


        topicOFTheMonthModel = new AilmentModel();
        topicOFTheMonthModel.setAilmentName("Neck Pain");
        ArrayListTopicOFTheMonth.add(7, topicOFTheMonthModel);


        topicOFTheMonthModel = new AilmentModel();
        topicOFTheMonthModel.setAilmentName("Tooth Problems");
        ArrayListTopicOFTheMonth.add(8, topicOFTheMonthModel);


        topicOFTheMonthModel = new AilmentModel();
        topicOFTheMonthModel.setAilmentName("Migrain");
        ArrayListTopicOFTheMonth.add(9,topicOFTheMonthModel);










        return ArrayListTopicOFTheMonth;


    }

    public static AilmentModel ailmentModel;

    public static AilmentModel getAilmentModel() {
        return ailmentModel;
    }

    public static void setAilmentModel(AilmentModel ailmentModel) {
        AilmentModel.ailmentModel = ailmentModel;
    }
}
