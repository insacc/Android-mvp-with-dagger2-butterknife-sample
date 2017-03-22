package org.insacc.toddssyndrome.DaggerModule.PatientTestResultModule;

import org.insacc.toddssyndrome.DaggerModule.CustomScope;
import org.insacc.toddssyndrome.PatientTestResult.PatientTestResultContract;
import org.insacc.toddssyndrome.PatientTestResult.PatientTestResultPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by can on 18.02.2017.
 */
@Module
public class PatientTestResultModule {

    private PatientTestResultContract.View mView;

    public PatientTestResultModule(PatientTestResultContract.View view) {
        this.mView = view;
    }

    @CustomScope
    @Provides
    public PatientTestResultContract.Presenter providesPresenter() {
        return new PatientTestResultPresenter(mView);
    }
}
