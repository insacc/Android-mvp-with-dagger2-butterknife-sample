package org.insacc.toddssyndrome.DaggerModule;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by can on 18.02.2017.
 */
@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    Context provideContext();

}
