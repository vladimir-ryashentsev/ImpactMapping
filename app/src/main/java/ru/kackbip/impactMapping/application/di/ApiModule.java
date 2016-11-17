package ru.kackbip.impactMapping.application.di;

import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.kackbip.impactMapping.api.Api;
import ru.kackbip.impactMapping.api.IApi;
import ru.kackbip.impactMapping.api.commands.CreateGoalCommand;
import ru.kackbip.impactMapping.api.commands.executors.CreateGoalCommandExecutor;
import ru.kackbip.impactMapping.api.commands.executors.ICommandExecutor;
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
    public IApi provideApi(IProjectionRepository projectionRepository, Map<Class, ICommandExecutor> commandExecutors){
        return new Api(projectionRepository, commandExecutors);
    }

    @Provides
    @Singleton
    public Map<Class, ICommandExecutor> provideCommandExecutors(IProjectionRepository projectionRepository){
        Map<Class, ICommandExecutor> executors = new HashMap<>();
        executors.put(CreateGoalCommand.class, new CreateGoalCommandExecutor(projectionRepository));
        return executors;
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
