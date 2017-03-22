package org.insacc.toddssyndrome.Database.GetPatientListService;

import org.insacc.toddssyndrome.BaseService;
import org.insacc.toddssyndrome.Model.Patient;

import java.util.List;

/**
 * Created by can on 18.02.2017.
 */

public interface GetPatientListService extends BaseService {

    void getPatientList(GetPatientListCallback callback);

    interface GetPatientListCallback {

        void onPatientListLoaded(List<Patient> patientList);

        void onPatientListLoadFail();
    }
}
