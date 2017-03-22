package org.insacc.toddssyndrome.Database.GetPatientTestsService;

import android.database.Cursor;

import org.insacc.toddssyndrome.Database.BaseDatabaseService;
import org.insacc.toddssyndrome.Database.DatabaseHelper;
import org.insacc.toddssyndrome.Model.Patient;
import org.insacc.toddssyndrome.Model.PatientTest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import rx.Single;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by can on 18.02.2017.
 * Service class to retrieve the list of test results of a given patient.
 */

public class GetPatientTestsServiceImp extends BaseDatabaseService implements GetPatientTestsService {

    private Subscription mSubscription;

    public GetPatientTestsServiceImp(DatabaseHelper databaseHelper) {
        super(databaseHelper);
    }

    @Override
    public void getPatientsTests(final long patientId, final GetPatientTestsCallback callback) {


        Single<Patient> getPatient = Single.fromCallable(new Callable<Patient>() {
            @Override
            public Patient call() throws Exception {
                return getPatientTestHelper(patientId);
            }
        }).subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread());

        mSubscription = getPatient.subscribe(new Action1<Patient>() {
            @Override
            public void call(Patient patient) {
                if (patient != null) {
                    callback.onPatientTestsLoaded(patient);
                } else {
                    callback.onPatientTestLoadFail();
                }
            }
        });
    }

    private Patient getPatientTestHelper(long patientID) {

        super.openDatabase();

        String selectQuery = "SELECT * FROM " + DatabaseHelper.PATIENTS_TABLE + " INNER JOIN "
                + DatabaseHelper.TEST_RESULT_TABLE + " ON " + DatabaseHelper.PATIENTS_TABLE + ".ROWID = "
                + DatabaseHelper.TEST_RESULT_TABLE + "." + DatabaseHelper.PATIENT_TEST_ID
                + " WHERE " + DatabaseHelper.PATIENTS_TABLE + ".ROWID = " + patientID;

        Cursor cursor = super.getDatabase().rawQuery(selectQuery, null);
        List<PatientTest> patientTests = null;
        Patient patient = null;

        try {
            int getPatientCounter = 0;
            if (cursor.moveToFirst()) {
                patientTests = new ArrayList<>();

                do {
                    if (getPatientCounter == 0) {
                        String patientName = cursor.getString(cursor.getColumnIndex(DatabaseHelper.PATIENT_NAME));
                        String patientFirstName = cursor.getString(cursor.getColumnIndex(DatabaseHelper.PATIENT_FIRST_NAME));
                        int patientGender = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.PATIENT_GENDER));

                        patient = new Patient(patientFirstName, patientName);
                        patient.setGender(patientGender);
                        patient.setPatientID(patientID);

                        getPatientCounter++;
                    }

                    int testDrug = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.TEST_DRUG_RESULT));
                    int testMigraine = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.TEST_MIGRAINE_RESULT));
                    int age = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.PATIENT_TEST_AGE));
                    int gender = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.PATIENT_GENDER));
                    int testResult = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.TEST_RESULT));

                    PatientTest patientTest = new PatientTest(age, testMigraine, gender, testDrug);
                    patientTest.setTestResult(testResult);
                    patientTest.setPatientID(patient.getPatientID());
                    patientTests.add(patientTest);


                } while (cursor.moveToNext());

                if (patient != null)
                    patient.setPatientTest(patientTests);
            }
        } catch (Exception e) {


        } finally {

            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
            super.closeDatabase();
        }

        return patient;
    }

    @Override
    public void unSubscribe() {
        if (mSubscription != null && !mSubscription.isUnsubscribed())
            mSubscription.unsubscribe();
    }
}
