package org.insacc.toddssyndrome;

import android.app.Application;

import org.insacc.toddssyndrome.DaggerModule.AppComponent;
import org.insacc.toddssyndrome.DaggerModule.AppModule;
import org.insacc.toddssyndrome.DaggerModule.DaggerAppComponent;

/**
 * Created by can on 18.02.2017.
 */

public class MyApp extends Application {


    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();


        this.mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }
}
