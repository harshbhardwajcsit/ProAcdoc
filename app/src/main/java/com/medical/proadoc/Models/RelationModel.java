package com.medical.proadoc.Models;

import java.util.ArrayList;

/**
 * Created by hp on 2/16/2016.
 */
public class RelationModel {

    public int RelationID;
    public String RelationName;

    public int getRelationID() {
        return RelationID;
    }

    public void setRelationID(int relationID) {
        RelationID = relationID;
    }

    public String getRelationName() {
        return RelationName;
    }

    public void setRelationName(String relationName) {
        RelationName = relationName;
    }

    public ArrayList<RelationModel> arrayListRelations()
    {
        ArrayList<RelationModel> relationModelArrayList=new ArrayList<RelationModel>(4);
        RelationModel relationModel=new RelationModel();
        relationModel.setRelationID(1);
        relationModel.setRelationName("Wife");
        relationModelArrayList.add(0,relationModel);


        relationModel=new RelationModel();
        relationModel.setRelationID(1);
        relationModel.setRelationName("Husband");
        relationModelArrayList.add(1,relationModel);



        relationModel=new RelationModel();
        relationModel.setRelationID(1);
        relationModel.setRelationName("Child 1");
        relationModelArrayList.add(2,relationModel);



        relationModel=new RelationModel();
        relationModel.setRelationID(1);
        relationModel.setRelationName("Child 2");
        relationModelArrayList.add(3,relationModel);
return relationModelArrayList;

    }

}
