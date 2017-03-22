package org.insacc.toddssyndrome.Model;

/**
 * Created by can on 18.02.2017.
 */

public class PatientTest {

    private int mAge;
    private int mHasMigraine;
    private int mGender;
    private int mTestResult;
    private int mIsUsedHallucinogenicDrugs;


    private long mPatientID;

    public PatientTest(int mAge, int mHasMigraine, int mGender, int mIsUsedHallucinogenicDrugs) {
        this.mAge = mAge;
        this.mHasMigraine = mHasMigraine;
        this.mGender = mGender;
        this.mIsUsedHallucinogenicDrugs = mIsUsedHallucinogenicDrugs;
    }

    public int getAge() {
        return mAge;
    }

    public void setAge(int mAge) {
        this.mAge = mAge;
    }

    public int hasMigraine() {
        return mHasMigraine;
    }

    public void setHasMigraine(int mHasMigraine) {
        this.mHasMigraine = mHasMigraine;
    }

    public int getGender() {
        return mGender;
    }

    public void setGender(int mGender) {
        this.mGender = mGender;
    }

    public int isUsedHallucinogenicDrugs() {
        return mIsUsedHallucinogenicDrugs;
    }

    public void setUsedHallucinogenicDrugs(int mIsUsedHallucinogenicDrugs) {
        this.mIsUsedHallucinogenicDrugs = mIsUsedHallucinogenicDrugs;
    }


    public int getTestResult() {
        return mTestResult;
    }

    public void setTestResult(int mTestResult) {
        this.mTestResult = mTestResult;
    }


    public long getPatientID() {
        return mPatientID;
    }

    public void setPatientID(long mPatientID) {
        this.mPatientID = mPatientID;
    }


}
