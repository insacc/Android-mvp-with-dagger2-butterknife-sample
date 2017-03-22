package org.insacc.toddssyndrome.AddPatientTest;

import org.insacc.toddssyndrome.BasePresenter;
import org.insacc.toddssyndrome.Model.Patient;
import org.insacc.toddssyndrome.Model.PatientTest;

/**
 * Created by can on 18.02.2017.
 */

public interface PatientTestContract {

    interface View {
        void openTestResultUi(int testResult);

        void displayFillAllFieldsMessage();
    }

    interface Presenter extends BasePresenter {
        void evaluateAndSavePatientTest(Patient patient, PatientTest patientTest);
    }
}
