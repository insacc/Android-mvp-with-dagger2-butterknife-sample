package org.insacc.toddssyndrome;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.insacc.toddssyndrome.Database.AddPatientTestService.AddPatientTestService;
import org.insacc.toddssyndrome.Database.AddPatientTestService.AddPatientTestServiceImp;
import org.insacc.toddssyndrome.Database.DatabaseHelper;
import org.insacc.toddssyndrome.Model.Patient;
import org.insacc.toddssyndrome.Model.PatientTest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class AddPatientTestUnitTest {

    private DatabaseHelper mDbHelper;

    private AddPatientTestService mAddPatientTest;

    @Before
    public void setup() {
        mDbHelper = new DatabaseHelper(InstrumentationRegistry.getTargetContext());
        mAddPatientTest = new AddPatientTestServiceImp(mDbHelper);
    }
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        PatientTest patientTest = new PatientTest(10, 1, 1, 1);
        PatientTest patientTest1 = null;
        Patient patient = new Patient("Can", "Undeger");
        patient.setGender(0);
        mAddPatientTest.addPatientTest(patient, patientTest, new AddPatientTestService.AddPatientTestCallback() {
            @Override
            public void onPatientTestAdded() {

            }

            @Override
            public void onPatientTestAddFail() {
                fail("Failed");
            }
        });

        mAddPatientTest.addPatientTest(patient, patientTest1, new AddPatientTestService.AddPatientTestCallback() {
            @Override
            public void onPatientTestAdded() {

            }

            @Override
            public void onPatientTestAddFail() {
                fail("Failed");
            }
        });



    }
}
