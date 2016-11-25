package ru.kackbip.impactMapping.application.di;

import android.content.SharedPreferences;

import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.kackbip.impactMapping.api.Api;
import ru.kackbip.impactMapping.api.IApi;
import ru.kackbip.impactMapping.api.commands.executors.CommandExecutorsProvider;
import ru.kackbip.impactMapping.api.commands.executors.local.LocalCommandExecutorsProvider;
import ru.kackbip.impactMapping.api.projections.repository.IProjectionRepository;
import ru.kackbip.impactMapping.api.projections.repository.ProjectionRepository;
import ru.kackbip.infrastructure.storage.pojo.GsonPojoStorage;
import ru.kackbip.infrastructure.storage.pojo.GsonStringifier;
import ru.kackbip.infrastructure.storage.pojo.IPojoStorage;
import ru.kackbip.infrastructure.storage.pojo.IStringifier;
import ru.kackbip.infrastructure.storage.string.IStringStorage;
import ru.kackbip.infrastructure.storage.string.local.SharedPreferencesStringStorage;

/**
 * Created by ryashentsev on 18.10.2016.
 */

@Module
public class ApiModule {

    @Provides
    @Singleton
    public IApi provideApi(IProjectionRepository projectionRepository, CommandExecutorsProvider commandExecutorsProvider){
        return new Api(projectionRepository, commandExecutorsProvider);
    }

    @Provides
    @Singleton
    public CommandExecutorsProvider provideCommandExecutorsProvider(IProjectionRepository projectionRepository){
        return new LocalCommandExecutorsProvider(projectionRepository);
    }

    @Provides
    @Singleton
    public IProjectionRepository provideProjectionRepository(IPojoStorage pojoStorage){
        return new ProjectionRepository(pojoStorage);
    }

    @Provides
    @Singleton
    public IPojoStorage providePojoStorage(IStringStorage stringStorage, IStringifier stringifier){
        return new GsonPojoStorage(stringStorage, stringifier);
    }

    @Provides
    @Singleton
    public IStringStorage provideStringStorage(SharedPreferences sharedPreferences){
        return new SharedPreferencesStringStorage(sharedPreferences);
    }

    @Provides
    @Singleton
    public IStringifier provideStringifier(Gson gson){
        return new GsonStringifier(gson);
    }
}
