package org.insacc.toddssyndrome.Database.GetPatientListService;

import android.database.Cursor;
import android.util.Log;

import org.insacc.toddssyndrome.Database.BaseDatabaseService;
import org.insacc.toddssyndrome.Database.DatabaseHelper;
import org.insacc.toddssyndrome.Model.Patient;

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
 * Service class to retrieve the list of the patients from database.
 */

public class GetPatientListServiceImp extends BaseDatabaseService implements GetPatientListService {

    private Subscription mSubscription;

    public GetPatientListServiceImp(DatabaseHelper databaseHelper) {
        super(databaseHelper);

    }


    @Override
    public void getPatientList(final GetPatientListCallback callback) {


        Single<List<Patient>> getPatientList = Single.fromCallable(new Callable<List<Patient>>() {
            @Override
            public List<Patient> call() throws Exception {
                return getPatientListHelper();
            }
        }).subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread());

        mSubscription = getPatientList.subscribe(new Action1<List<Patient>>() {
            @Override
            public void call(List<Patient> patients) {
                if (patients != null) {
                    callback.onPatientListLoaded(patients);
                } else {
                    callback.onPatientListLoadFail();
                }
            }
        });
    }

    private List<Patient> getPatientListHelper() {

        String selectQuery = "SELECT rowid," + DatabaseHelper.PATIENT_NAME + ", "
                + DatabaseHelper.PATIENT_FIRST_NAME + ", " + DatabaseHelper.PATIENT_GENDER
                + " FROM " + DatabaseHelper.PATIENTS_TABLE;
        super.openDatabase();
        Cursor cursor = super.getDatabase().rawQuery(selectQuery, null);
        List<Patient> patients = null;

        try {
            if (cursor.moveToFirst()) {
                patients = new ArrayList<>();
                do {

                    Patient patient;

                    String patientName = cursor.getString(cursor.getColumnIndex(DatabaseHelper.PATIENT_NAME));
                    String patientFirstName = cursor.getString(cursor.getColumnIndex(DatabaseHelper.PATIENT_FIRST_NAME));
                    int patientGender = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.PATIENT_GENDER));
                    Log.i("patientFound", "called");
                    long patientID = cursor.getLong(cursor.getColumnIndex("rowid"));
                    Log.i("patientID", String.valueOf(patientID));
                    patient = new Patient(patientFirstName, patientName);
                    patient.setGender(patientGender);
                    patient.setPatientID(patientID);

                    patients.add(patient);

                } while (cursor.moveToNext());
            }
        } catch (Exception e) {


        } finally {

            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
            super.closeDatabase();
        }
        return patients;

    }

    @Override
    public void unSubscribe() {
        if (mSubscription != null && !mSubscription.isUnsubscribed())
            mSubscription.unsubscribe();
    }
}
