package org.insacc.toddssyndrome.DaggerModule.PatientTestsListModule;

import org.insacc.toddssyndrome.DaggerModule.CustomScope;
import org.insacc.toddssyndrome.Database.GetPatientTestsService.GetPatientTestsService;
import org.insacc.toddssyndrome.PatientTestsList.PatientTestsListContract;
import org.insacc.toddssyndrome.PatientTestsList.PatientTestsListPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by can on 18.02.2017.
 */
@Module
public class PatientTestsListModule {

    private PatientTestsListContract.View mView;

    public PatientTestsListModule(PatientTestsListContract.View view) {
        this.mView = view;
    }

    @CustomScope
    @Provides
    public PatientTestsListContract.Presenter providesPresenter(GetPatientTestsService getPatientTestsService) {
        return new PatientTestsListPresenter(mView, getPatientTestsService);
    }
}
