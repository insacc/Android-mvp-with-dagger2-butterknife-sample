package org.insacc.toddssyndrome.PatientTestResult;

/**
 * Created by can on 18.02.2017.
 * The presenter class which controls the @class PatientsTestResultActivity.java
 */

public class PatientTestResultPresenter implements PatientTestResultContract.Presenter {

    private PatientTestResultContract.View mView;

    public PatientTestResultPresenter(PatientTestResultContract.View view) {
        this.mView = view;
    }

    @Override
    public void loadTestResult() {
        mView.setTestResult();
    }

    @Override
    public void callPatientListView() {
        mView.openPatientListUi();
    }
}
