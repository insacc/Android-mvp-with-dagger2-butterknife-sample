package org.insacc.toddssyndrome;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import org.insacc.toddssyndrome.Database.AddPatientTestService.AddPatientTestService;
import org.insacc.toddssyndrome.Database.AddPatientTestService.AddPatientTestServiceImp;
import org.insacc.toddssyndrome.Database.DatabaseHelper;
import org.insacc.toddssyndrome.Database.GetPatientListService.GetPatientListService;
import org.insacc.toddssyndrome.Database.GetPatientListService.GetPatientListServiceImp;
import org.insacc.toddssyndrome.Database.GetPatientTestsService.GetPatientTestsService;
import org.insacc.toddssyndrome.Database.GetPatientTestsService.GetPatientTestsServiceImp;
import org.insacc.toddssyndrome.Model.Patient;
import org.insacc.toddssyndrome.Model.PatientTest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Created by can on 19.02.2017.
 */
@RunWith(AndroidJUnit4.class)
public class PatientListServiceTest {

    private AddPatientTestService mAddTestService;

    private GetPatientListService mGetPatientsService;

    private GetPatientTestsService mGetTestsService;

    private DatabaseHelper mDbHelper;

    @Before
    public void setup() {
        mDbHelper = new DatabaseHelper(InstrumentationRegistry.getTargetContext());
        mGetPatientsService = new GetPatientListServiceImp(mDbHelper);
        mAddTestService = new AddPatientTestServiceImp(mDbHelper);
        mGetTestsService = new GetPatientTestsServiceImp(mDbHelper);

        PatientTest patientTest = new PatientTest(10, 1, 1, 1);
        Patient patient = new Patient("Can", "Undeger");
        patient.setGender(0);
        mAddTestService.addPatientTest(patient, patientTest, new AddPatientTestService.AddPatientTestCallback() {
            @Override
            public void onPatientTestAdded() {

            }

            @Override
            public void onPatientTestAddFail() {
                fail("Failed");
            }
        });
    }

    @Test
    public void testPatientList() {


        mGetPatientsService.getPatientList(new GetPatientListService.GetPatientListCallback() {
            @Override
            public void onPatientListLoaded(List<Patient> patientList) {
                assertThat(patientList.size(), greaterThan(0));

            }

            @Override
            public void onPatientListLoadFail() {
                fail("Failed");
            }
        });


    }

    @Test
    public void testPatientTests() {
        mGetTestsService.getPatientsTests(0, new GetPatientTestsService.GetPatientTestsCallback() {
            @Override
            public void onPatientTestsLoaded(Patient patient) {
                Log.i("patientName", patient.getFirstName());
                assertEquals(patient.getFirstName(), "Can");
                assertEquals(patient.getLastName(), "Undeger");
            }

            @Override
            public void onPatientTestLoadFail() {

            }
        });
    }
}
