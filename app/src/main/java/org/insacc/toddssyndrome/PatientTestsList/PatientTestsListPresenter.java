package org.insacc.toddssyndrome.PatientTestsList;

import org.insacc.toddssyndrome.Database.GetPatientTestsService.GetPatientTestsService;
import org.insacc.toddssyndrome.Model.Patient;

/**
 * Created by can on 18.02.2017.
 */

public class PatientTestsListPresenter implements PatientTestsListContract.Presenter,
        GetPatientTestsService.GetPatientTestsCallback {

    private PatientTestsListContract.View mView;

    private GetPatientTestsService mGetPatientTests;

    public PatientTestsListPresenter(PatientTestsListContract.View view, GetPatientTestsService getPatientTestsService) {
        this.mView = view;
        this.mGetPatientTests = getPatientTestsService;
    }


    @Override
    public void loadPatient(long patientID) {
        mGetPatientTests.getPatientsTests(patientID, this);
    }

    @Override
    public void callPatientListUi() {
        mView.openPatientListUi();
    }

    @Override
    public void onPatientTestsLoaded(Patient patient) {
        mView.populatePatient(patient);
    }

    @Override
    public void onPatientTestLoadFail() {

    }

    /**
     * Unsubscribes the service.
     */
    @Override
    public void unSubscribe() {
        if (mGetPatientTests != null)
            mGetPatientTests.unSubscribe();
    }
}
