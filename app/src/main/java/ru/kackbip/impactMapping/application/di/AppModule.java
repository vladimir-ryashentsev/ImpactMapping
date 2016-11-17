package ru.kackbip.impactMapping.application.di;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.kackbip.impactMapping.application.CurrentActivityProvider;

/**
 * Created by ryashentsev on 18.10.2016.
 */

@Module
public class AppModule {

    private Application application;

    public AppModule(Application application){
        this.application = application;
    }

    @Provides
    @Singleton
    public Context provideContext(){
        return application;
    }

    @Provides
    @Singleton
    public Application provideApplication(){
        return application;
    }

    @Provides
    @Singleton
    public CurrentActivityProvider provideCurrentActivityProvider(){
        return new CurrentActivityProvider(application);
    }

    @Provides
    @Singleton
    public SharedPreferences provideSharedPreferences(Context context){
        return application.getSharedPreferences("ImpactMappingSP", 0);
    }

}
