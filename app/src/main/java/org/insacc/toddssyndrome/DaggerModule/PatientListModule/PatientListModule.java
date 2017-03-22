package org.insacc.toddssyndrome.DaggerModule.PatientListModule;

import org.insacc.toddssyndrome.DaggerModule.CustomScope;
import org.insacc.toddssyndrome.Database.GetPatientListService.GetPatientListService;
import org.insacc.toddssyndrome.PatientList.PatientListPresenter;
import org.insacc.toddssyndrome.PatientList.PatientsListContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by can on 18.02.2017.
 */
@Module
public class PatientListModule {

    private PatientsListContract.View mView;

    public PatientListModule(PatientsListContract.View view) {
        this.mView = view;
    }

    @Provides
    @CustomScope
    public PatientsListContract.Presenter providesPresenter(GetPatientListService getPatientListService) {
        return new PatientListPresenter(mView, getPatientListService);
    }


}
