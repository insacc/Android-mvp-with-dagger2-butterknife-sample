package org.insacc.toddssyndrome.Database.GetPatientTestsService;

import org.insacc.toddssyndrome.BaseService;
import org.insacc.toddssyndrome.Model.Patient;
import org.insacc.toddssyndrome.Model.PatientTest;

import java.util.List;

/**
 * Created by can on 18.02.2017.
 */

public interface GetPatientTestsService extends BaseService {

    void getPatientsTests(long patientId, GetPatientTestsCallback callback);

    interface GetPatientTestsCallback {

        void onPatientTestsLoaded(Patient patient);

        void onPatientTestLoadFail();
    }
}
