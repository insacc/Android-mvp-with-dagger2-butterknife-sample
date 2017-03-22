package org.insacc.toddssyndrome.Logic;

import org.insacc.toddssyndrome.Model.PatientTest;

/**
 * Created by can on 18.02.2017.
 * Business logic which evaluates the tests.
 */

public class SyndromeProbabilityLogicImp implements SyndromeProbabilityLogic {

    public SyndromeProbabilityLogicImp() {

    }

    @Override
    public int getToddsProbabilityForTest(PatientTest test) {

        if(test == null)
            return 0;

        return calculateAgeRisk(test.getAge()) + calculateDrugRisk(test.isUsedHallucinogenicDrugs())
                + calculateGenderRisk(test.getGender()) + calculateMigraineRisk(test.hasMigraine());
    }

    private int calculateAgeRisk(int age) {
        if (age >= 15) {
            return 25;
        }
        return 0;
    }

    private int calculateGenderRisk(int gender) {
        if (gender == 0)
            return 25;

        return 0;
    }

    private int calculateMigraineRisk(int hasMigraine) {
        if (hasMigraine == 1)
            return 25;

        return 0;
    }

    private int calculateDrugRisk(int hasUsedDrug) {
        if (hasUsedDrug == 1)
            return 25;

        return 0;
    }
}
