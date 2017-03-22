package org.insacc.toddssyndrome;

import org.insacc.toddssyndrome.Logic.SyndromeProbabilityLogic;
import org.insacc.toddssyndrome.Logic.SyndromeProbabilityLogicImp;
import org.insacc.toddssyndrome.Model.PatientTest;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    private SyndromeProbabilityLogic mLogic;

    @Before
    public void setup() {
        mLogic = new SyndromeProbabilityLogicImp();
    }

    @Test
    public void calculationIsCorrect() {

        int testAge1 = -1;
        int testDrug = 1;
        int testMigraine = 0;
        int testGender1 = 1;

        PatientTest test = new PatientTest(testAge1, testMigraine, testGender1, testDrug);
        PatientTest patientTest = null;
        PatientTest test2 = new PatientTest(16, 1, 0, 1);

        assertEquals(mLogic.getToddsProbabilityForTest(patientTest), 0);
        assertEquals(mLogic.getToddsProbabilityForTest(test), 25);
        assertEquals(mLogic.getToddsProbabilityForTest(test2), 100);
    }

}