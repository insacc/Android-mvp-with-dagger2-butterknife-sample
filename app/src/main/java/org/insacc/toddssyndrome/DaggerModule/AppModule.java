package org.insacc.toddssyndrome.DaggerModule;

import android.content.Context;

import org.insacc.toddssyndrome.MyApp;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by can on 18.02.2017.
 */
@Module
public class AppModule {

    private MyApp mApplication;

    public AppModule(MyApp application) {

        this.mApplication = application;
    }

    @Provides
    @Singleton
    public Context provideApplication() {
        return mApplication;
    }
}
