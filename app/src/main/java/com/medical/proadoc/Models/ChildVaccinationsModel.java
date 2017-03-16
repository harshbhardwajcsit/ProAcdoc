package com.medical.proadoc.Models;

import java.util.ArrayList;

/**
 * Created by hp on 2/19/2016.
 */
public class ChildVaccinationsModel {

    public int ChildVaccinationsId;
    public int ChildID;
    public String VaccinationName;
    public String VaccinationAdministrtedBy;
    public String VaccinationImageUrl;
    public String VaccinationNotes;
    public String VaccinationNextData;

    public String getVaccinationNotes() {
        return VaccinationNotes;
    }

    public void setVaccinationNotes(String vaccinationNotes) {
        VaccinationNotes = vaccinationNotes;
    }

    public String getVaccinationNextData() {
        return VaccinationNextData;
    }

    public void setVaccinationNextData(String vaccinationNextData) {
        VaccinationNextData = vaccinationNextData;
    }

    public int getChildVaccinationsId() {
        return ChildVaccinationsId;
    }

    public void setChildVaccinationsId(int childVaccinationsId) {
        ChildVaccinationsId = childVaccinationsId;
    }

    public int getChildID() {
        return ChildID;
    }

    public void setChildID(int childID) {
        ChildID = childID;
    }

    public String getVaccinationName() {
        return VaccinationName;
    }

    public void setVaccinationName(String vaccinationName) {
        VaccinationName = vaccinationName;
    }

    public String getVaccinationAdministrtedBy() {
        return VaccinationAdministrtedBy;
    }

    public void setVaccinationAdministrtedBy(String vaccinationAdministrtedBy) {
        VaccinationAdministrtedBy = vaccinationAdministrtedBy;
    }

    public String getVaccinationImageUrl() {
        return VaccinationImageUrl;
    }

    public void setVaccinationImageUrl(String vaccinationImageUrl) {
        VaccinationImageUrl = vaccinationImageUrl;
    }

    public static ArrayList<ChildVaccinationsModel> getDummyData()
    {

        ArrayList<ChildVaccinationsModel> childVaccinationsModelArrayList=new ArrayList<ChildVaccinationsModel>();
        ChildVaccinationsModel  childVaccinationsModel=new ChildVaccinationsModel();

        childVaccinationsModel.setChildID(1);
        childVaccinationsModel.setChildVaccinationsId(1);
        childVaccinationsModel.setVaccinationAdministrtedBy("Dr Manish Mathur");
        childVaccinationsModel.setVaccinationImageUrl("aaa");
        childVaccinationsModel.setVaccinationName("Hepatitis B1 (HepB)");
        childVaccinationsModel.setVaccinationNextData("13-March-2016");

        childVaccinationsModelArrayList.add(0,childVaccinationsModel);



        childVaccinationsModel=new ChildVaccinationsModel();

        childVaccinationsModel.setChildID(1);
        childVaccinationsModel.setChildVaccinationsId(1);
        childVaccinationsModel.setVaccinationAdministrtedBy("Dr Ravi Jain");
        childVaccinationsModel.setVaccinationImageUrl("aaa");
        childVaccinationsModel.setVaccinationName("Rotavirus2 (RV)");
        childVaccinationsModel.setVaccinationNextData("16-March-2016");

        childVaccinationsModelArrayList.add(1,childVaccinationsModel);


        childVaccinationsModel=new ChildVaccinationsModel();

        childVaccinationsModel.setChildID(1);
        childVaccinationsModel.setChildVaccinationsId(1);
        childVaccinationsModel.setVaccinationAdministrtedBy("Dr Ravi Jain");
        childVaccinationsModel.setVaccinationImageUrl("aaa");
        childVaccinationsModel.setVaccinationName("Rotavirus2 (RV)");
        childVaccinationsModel.setVaccinationNextData("16-March-2016");

        childVaccinationsModelArrayList.add(2,childVaccinationsModel);
        childVaccinationsModel=new ChildVaccinationsModel();

        childVaccinationsModel.setChildID(1);
        childVaccinationsModel.setChildVaccinationsId(1);
        childVaccinationsModel.setVaccinationAdministrtedBy("Dr Ravi Jain");
        childVaccinationsModel.setVaccinationImageUrl("aaa");
        childVaccinationsModel.setVaccinationName("Rotavirus2 (RV)");
        childVaccinationsModel.setVaccinationNextData("16-March-2016");

        childVaccinationsModelArrayList.add(3,childVaccinationsModel);

        return childVaccinationsModelArrayList;
    }

}
