package com.medical.proadoc.Models;

import java.util.ArrayList;

/**
 * Created by hp on 2/16/2016.
 */
public class FamilyMembersModel {

    public String Password;
    public String Name;
    public String Email;

    public String PhoneNumber;
    public String DOB;

    public int getMemberId() {
        return MemberId;
    }

    public void setMemberId(int memberId) {
        MemberId = memberId;
    }

    public int MemberId;

    public RelationModel relation;



    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public RelationModel getRelation() {
        return relation;
    }

    public void setRelation(RelationModel relation) {
        this.relation = relation;
    }
}