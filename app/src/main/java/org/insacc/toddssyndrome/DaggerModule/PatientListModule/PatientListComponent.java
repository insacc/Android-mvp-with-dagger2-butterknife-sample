package org.insacc.toddssyndrome.DaggerModule.PatientListModule;

import org.insacc.toddssyndrome.DaggerModule.AppComponent;
import org.insacc.toddssyndrome.DaggerModule.CustomScope;
import org.insacc.toddssyndrome.DaggerModule.DatabaseHelperModule;
import org.insacc.toddssyndrome.PatientList.PatientListActivity;

import dagger.Component;

/**
 * Created by can on 18.02.2017.
 */
@CustomScope
@Component(dependencies = AppComponent.class, modules = {PatientListModule.class, DatabaseHelperModule.class})
public interface PatientListComponent {

    void inject(PatientListActivity activity);


}
