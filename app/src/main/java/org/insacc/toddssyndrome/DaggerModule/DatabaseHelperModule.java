package org.insacc.toddssyndrome.DaggerModule;

import android.content.Context;

import org.insacc.toddssyndrome.Database.AddPatientTestService.AddPatientTestService;
import org.insacc.toddssyndrome.Database.AddPatientTestService.AddPatientTestServiceImp;
import org.insacc.toddssyndrome.Database.DatabaseHelper;
import org.insacc.toddssyndrome.Database.GetPatientListService.GetPatientListService;
import org.insacc.toddssyndrome.Database.GetPatientListService.GetPatientListServiceImp;
import org.insacc.toddssyndrome.Database.GetPatientTestsService.GetPatientTestsService;
import org.insacc.toddssyndrome.Database.GetPatientTestsService.GetPatientTestsServiceImp;

import dagger.Component;
import dagger.Module;
import dagger.Provides;

/**
 * Created by can on 18.02.2017.
 */
@Module
public class DatabaseHelperModule {

    @Provides
    @CustomScope
    public DatabaseHelper providesDatabaseHelper(Context context) {
        return new DatabaseHelper(context);
    }


    @Provides
    @CustomScope
    public GetPatientListService providesPatientListService(DatabaseHelper databaseHelper) {
        return new GetPatientListServiceImp(databaseHelper);
    }

    @Provides
    @CustomScope
    public GetPatientTestsService providesPatientTestsService(DatabaseHelper databaseHelper) {
        return new GetPatientTestsServiceImp(databaseHelper);
    }

    @Provides
    @CustomScope
    public AddPatientTestService providesAddPatientTestService(DatabaseHelper databaseHelper) {
        return new AddPatientTestServiceImp(databaseHelper);
    }
}
