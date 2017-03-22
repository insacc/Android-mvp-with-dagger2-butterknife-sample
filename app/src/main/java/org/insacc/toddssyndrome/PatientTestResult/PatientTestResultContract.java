package org.insacc.toddssyndrome.PatientTestResult;

/**
 * Created by can on 18.02.2017.
 */

public interface PatientTestResultContract {
    interface View {
        void setTestResult();

        void openPatientListUi();
    }

    interface Presenter {
        void loadTestResult();

        void callPatientListView();
    }
}
