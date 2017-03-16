package com.medical.proadoc.Models;

import java.util.ArrayList;

/**
 * Created by user on 1/12/2016.
 */
public class MedicalRecord {


    public String Name;
    public String Ailment;

    public int MedicalRecordId;

    public int getMedicalRecordId() {
        return MedicalRecordId;
    }

    public void setMedicalRecordId(int medicalRecordId) {
        MedicalRecordId = medicalRecordId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAilment() {
        return Ailment;
    }

    public void setAilment(String ailment) {
        Ailment = ailment;
    }

    public String getDoctorName() {
        return DoctorName;
    }

    public void setDoctorName(String doctorName) {
        DoctorName = doctorName;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String DoctorName;
    public String Date;


}
