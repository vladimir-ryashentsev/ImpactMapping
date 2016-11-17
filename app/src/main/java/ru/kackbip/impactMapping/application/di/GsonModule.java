package ru.kackbip.impactMapping.application.di;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ryashentsev on 18.10.2016.
 */

@Module
public class GsonModule {

    @Provides
    @Singleton
    public Gson provideGson(){
        return new GsonBuilder().create();
    }

}
