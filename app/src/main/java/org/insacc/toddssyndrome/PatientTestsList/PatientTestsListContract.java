package org.insacc.toddssyndrome.PatientTestsList;

import org.insacc.toddssyndrome.BasePresenter;
import org.insacc.toddssyndrome.Model.Patient;

/**
 * Created by can on 18.02.2017.
 */

public interface PatientTestsListContract {

    interface View {
        void populatePatient(Patient patient);

        void openPatientListUi();


    }

    interface Presenter extends BasePresenter {
        void loadPatient(long patientID);

        void callPatientListUi();


    }
}
