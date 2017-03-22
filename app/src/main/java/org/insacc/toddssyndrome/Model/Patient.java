package org.insacc.toddssyndrome.Model;

import java.util.List;

/**
 * Created by can on 18.02.2017.
 */

public class Patient {

    private String mFirstName;

    private String mLastName;

    private long mPatientID;

    private int mGender;

    private List<PatientTest> mPatientTest;


    public Patient(String firstName, String lastName) {
        this.mFirstName = firstName;
        this.mLastName = lastName;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String mFirstName) {
        this.mFirstName = mFirstName;
    }

    public int getGender() {
        return mGender;
    }

    public void setGender(int mGender) {
        this.mGender = mGender;
    }

    public String getLastName() {
        return mLastName;
    }

    public void setLastName(String mLastName) {
        this.mLastName = mLastName;
    }

    public long getPatientID() {
        return mPatientID;
    }

    public void setPatientID(long mPatientID) {
        this.mPatientID = mPatientID;
    }

    public List<PatientTest> getPatientTest() {
        return mPatientTest;
    }

    public void setPatientTest(List<PatientTest> mPatientTest) {
        this.mPatientTest = mPatientTest;
    }
}
