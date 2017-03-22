package org.insacc.toddssyndrome.Database.AddPatientTestService;

import org.insacc.toddssyndrome.BaseService;
import org.insacc.toddssyndrome.Model.Patient;
import org.insacc.toddssyndrome.Model.PatientTest;

/**
 * Created by can on 18.02.2017.
 *
 */

public interface AddPatientTestService extends BaseService {

    void addPatientTest(Patient patient, PatientTest patientTest, AddPatientTestCallback callback);

    interface AddPatientTestCallback {

        void onPatientTestAdded();

        void onPatientTestAddFail();
    }
}
