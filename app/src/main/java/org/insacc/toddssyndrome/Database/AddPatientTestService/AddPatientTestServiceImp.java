package org.insacc.toddssyndrome.Database.AddPatientTestService;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import org.insacc.toddssyndrome.Database.BaseDatabaseService;
import org.insacc.toddssyndrome.Database.DatabaseHelper;
import org.insacc.toddssyndrome.Model.Patient;
import org.insacc.toddssyndrome.Model.PatientTest;

import java.util.concurrent.Callable;

import rx.Single;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by can on 18.02.2017.
 * Service class which saves the given patient test and the patient information into the database
 */

public class AddPatientTestServiceImp extends BaseDatabaseService implements AddPatientTestService {

    private Subscription mSubscription;


    public AddPatientTestServiceImp(DatabaseHelper databaseHelper) {
        super(databaseHelper);
    }

    @Override
    public void addPatientTest(final Patient patient, final PatientTest patientTest,
                               final AddPatientTestCallback callback) {

        Single<Boolean> addPatientTest = Single.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return addPatientTestHelper(patient, patientTest);
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.computation());

        mSubscription = addPatientTest.subscribe(new Action1<Boolean>() {
            @Override
            public void call(Boolean isAdded) {
                if (isAdded) {
                    callback.onPatientTestAdded();
                } else {
                    callback.onPatientTestAddFail();
                }
            }
        });
    }

    /**
     * Saves the given patient information and syndrome test information into the database. If the
     * patient to be added, is already in the database then gets the id of the patient and
     * adds this value to the test result.
     * @param patient the patient to be added
     * @param patientTest the syndrome test to be added
     * @return true if the @param patient and @param patientTest is saved into the database
     */
    private boolean addPatientTestHelper(Patient patient, PatientTest patientTest) {

        if(patient == null ||patientTest == null)
            return false;

        super.openDatabase();


        ContentValues patientToAdd = new ContentValues();

        patientToAdd.put(DatabaseHelper.PATIENT_FIRST_NAME, patient.getFirstName());
        patientToAdd.put(DatabaseHelper.PATIENT_NAME, patient.getLastName());
        patientToAdd.put(DatabaseHelper.PATIENT_GENDER, patient.getGender());


        long patientId = super.getDatabase().insert(DatabaseHelper.PATIENTS_TABLE,
                null, patientToAdd);

        if (patientId == -1)
            patientId = getPatientID(patient.getFirstName(), patient.getLastName());

        Log.i("patientId", String.valueOf(patientId));
        ContentValues patientTestToAdd = new ContentValues();
        patientTestToAdd.put(DatabaseHelper.PATIENT_TEST_AGE, patientTest.getAge());
        patientTestToAdd.put(DatabaseHelper.TEST_DRUG_RESULT, patientTest.isUsedHallucinogenicDrugs());
        patientTestToAdd.put(DatabaseHelper.TEST_MIGRAINE_RESULT, patientTest.hasMigraine());
        patientTestToAdd.put(DatabaseHelper.TEST_RESULT, patientTest.getTestResult());
        patientTestToAdd.put(DatabaseHelper.PATIENT_TEST_ID, patientId);

        long result = super.getDatabase().insert(DatabaseHelper.TEST_RESULT_TABLE, null, patientTestToAdd);
        super.closeDatabase();

        return result != -1;

    }

    private long getPatientID(String firstName, String lastName) {
        String selectQuery = "SELECT ROWID FROM " + DatabaseHelper.PATIENTS_TABLE
                + " WHERE " + DatabaseHelper.PATIENT_NAME + "='" + lastName + "' AND "
                + DatabaseHelper.PATIENT_FIRST_NAME + "='" + firstName + "'";

        Cursor cursor = super.getDatabase().rawQuery(selectQuery, null);
        long patientID = -1;
        try {

            if (cursor.moveToFirst()) {

                patientID = cursor.getLong(cursor.getColumnIndex("rowid"));


            }
        } catch (Exception e) {


        } finally {

            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }

        }

        return patientID;
    }

    @Override
    public void unSubscribe() {
        if (mSubscription != null && !mSubscription.isUnsubscribed())
            mSubscription.unsubscribe();
    }
}
