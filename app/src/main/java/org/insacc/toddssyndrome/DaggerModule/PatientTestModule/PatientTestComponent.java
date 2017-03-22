package org.insacc.toddssyndrome.DaggerModule.PatientTestModule;

import org.insacc.toddssyndrome.DaggerModule.AppComponent;
import org.insacc.toddssyndrome.DaggerModule.CustomScope;
import org.insacc.toddssyndrome.AddPatientTest.PatientTestActivity;
import org.insacc.toddssyndrome.DaggerModule.DatabaseHelperModule;
import org.insacc.toddssyndrome.Database.DatabaseHelper;

import dagger.Component;

/**
 * Created by can on 18.02.2017.
 */
@CustomScope
@Component(dependencies = AppComponent.class, modules = {PatientTestModule.class, DatabaseHelperModule.class})
public interface PatientTestComponent {

    void inject(PatientTestActivity activity);

}
