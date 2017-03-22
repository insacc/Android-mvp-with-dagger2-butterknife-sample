package org.insacc.toddssyndrome.DaggerModule.PatientTestResultModule;

import org.insacc.toddssyndrome.DaggerModule.CustomScope;
import org.insacc.toddssyndrome.PatientTestResult.PatientTestResultActivity;

import dagger.Component;

/**
 * Created by can on 18.02.2017.
 */
@CustomScope
@Component(modules = PatientTestResultModule.class)
public interface PatientTestResultComponent {

    void inject(PatientTestResultActivity activity);
}
