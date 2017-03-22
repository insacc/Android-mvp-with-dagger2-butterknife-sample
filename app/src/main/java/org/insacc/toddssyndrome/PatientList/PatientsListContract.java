package org.insacc.toddssyndrome.PatientList;

import org.insacc.toddssyndrome.BasePresenter;
import org.insacc.toddssyndrome.Model.Patient;

import java.util.List;

/**
 * Created by can on 18.02.2017.
 * Declares interfaces for the activity and the presenter.
 */

public interface PatientsListContract {

    interface View {

        void populatePatientList(List<Patient> patients);

        void openAddTestUi();

        void onPatientClicked(long patientID);

        void openPatientTests(long patientID);
    }

    interface Presenter extends BasePresenter {

        void loadPatientList();

        void callAddTestView();

        void callPatientTestDetail(long patientID);
    }
}
