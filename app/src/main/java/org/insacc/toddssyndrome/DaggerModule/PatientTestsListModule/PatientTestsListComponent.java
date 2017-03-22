package org.insacc.toddssyndrome.DaggerModule.PatientTestsListModule;

import org.insacc.toddssyndrome.DaggerModule.AppComponent;
import org.insacc.toddssyndrome.DaggerModule.CustomScope;
import org.insacc.toddssyndrome.DaggerModule.DatabaseHelperModule;
import org.insacc.toddssyndrome.PatientTestsList.PatientTestsListActivity;

import dagger.Component;

/**
 * Created by can on 18.02.2017.
 */
@CustomScope
@Component(dependencies = AppComponent.class, modules = {PatientTestsListModule.class, DatabaseHelperModule.class})
public interface PatientTestsListComponent {

    void inject(PatientTestsListActivity activity);
}
