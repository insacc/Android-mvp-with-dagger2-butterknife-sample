package org.insacc.toddssyndrome.DaggerModule.PatientTestModule;

import org.insacc.toddssyndrome.DaggerModule.CustomScope;
import org.insacc.toddssyndrome.Database.AddPatientTestService.AddPatientTestService;
import org.insacc.toddssyndrome.Logic.SyndromeProbabilityLogic;
import org.insacc.toddssyndrome.Logic.SyndromeProbabilityLogicImp;
import org.insacc.toddssyndrome.AddPatientTest.PatientTestContract;
import org.insacc.toddssyndrome.AddPatientTest.PatientTestPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by can on 18.02.2017.
 */
@Module
public class PatientTestModule {


    private PatientTestContract.View mView;

    public PatientTestModule(PatientTestContract.View view) {
        this.mView = view;
    }

    @Provides
    @CustomScope
    public PatientTestContract.Presenter providesPresenter(SyndromeProbabilityLogic syndromeProbabilityLogic,
                                                           AddPatientTestService addPatientTestService) {
        return new PatientTestPresenter(mView, syndromeProbabilityLogic, addPatientTestService);
    }

    @Provides
    @CustomScope
    public SyndromeProbabilityLogic providesLogic() {
        return new SyndromeProbabilityLogicImp();
    }
}
