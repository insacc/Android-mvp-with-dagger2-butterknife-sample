package org.insacc.toddssyndrome.AddPatientTest;

import android.util.Log;

import org.insacc.toddssyndrome.Database.AddPatientTestService.AddPatientTestService;
import org.insacc.toddssyndrome.Logic.SyndromeProbabilityLogic;
import org.insacc.toddssyndrome.Model.Patient;
import org.insacc.toddssyndrome.Model.PatientTest;

/**
 * Created by can on 18.02.2017.
 * The presenter which controls the patientTestActivity view
 */

public class PatientTestPresenter implements PatientTestContract.Presenter, AddPatientTestService.AddPatientTestCallback {

    private PatientTestContract.View mView;

    private SyndromeProbabilityLogic mSyndromeLogic;

    private AddPatientTestService mAddPatientTestService;

    private int mTestResult;

    public PatientTestPresenter(PatientTestContract.View view, SyndromeProbabilityLogic syndromeProbabilityLogic,
                                AddPatientTestService addPatientTestService) {
        this.mView = view;
        this.mSyndromeLogic = syndromeProbabilityLogic;
        this.mAddPatientTestService = addPatientTestService;
    }

    @Override
    public void evaluateAndSavePatientTest(Patient patient, PatientTest patientTest) {

        if (patient == null) {
            mView.displayFillAllFieldsMessage();
        } else {

            mTestResult = mSyndromeLogic.getToddsProbabilityForTest(patientTest);
            patientTest.setTestResult(mTestResult);

            mAddPatientTestService.addPatientTest(patient, patientTest, this);

        }

    }

    @Override
    public void onPatientTestAdded() {
        mView.openTestResultUi(mTestResult);
    }

    @Override
    public void onPatientTestAddFail() {

    }

    @Override
    public void unSubscribe() {
        if (mAddPatientTestService != null)
            mAddPatientTestService.unSubscribe();
    }
}
