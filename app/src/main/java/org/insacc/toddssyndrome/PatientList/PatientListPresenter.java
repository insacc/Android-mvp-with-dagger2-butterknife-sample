package org.insacc.toddssyndrome.PatientList;

import org.insacc.toddssyndrome.Database.GetPatientListService.GetPatientListService;
import org.insacc.toddssyndrome.Model.Patient;

import java.util.List;

/**
 * Created by can on 18.02.2017.
 * Presenter class which controls the @class PatientListActivity.java
 */

public class PatientListPresenter implements PatientsListContract.Presenter, GetPatientListService.GetPatientListCallback {

    private PatientsListContract.View mView;

    private GetPatientListService mPatientListService;

    public PatientListPresenter(PatientsListContract.View view, GetPatientListService getPatientListService) {
        this.mView = view;
        this.mPatientListService = getPatientListService;
    }

    @Override
    public void loadPatientList() {
        mPatientListService.getPatientList(this);
    }

    @Override
    public void callAddTestView() {
        mView.openAddTestUi();
    }

    @Override
    public void callPatientTestDetail(long patientID) {
        mView.openPatientTests(patientID);
    }

    @Override
    public void onPatientListLoaded(List<Patient> patientList) {
        mView.populatePatientList(patientList);
    }

    @Override
    public void onPatientListLoadFail() {
        //TODO display error message
    }

    @Override
    public void unSubscribe() {
        if (mPatientListService != null)
            mPatientListService.unSubscribe();
    }
}
