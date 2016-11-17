package ru.kackbip.impactMapping.screens.goalCreation.di;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.kackbip.impactMapping.api.IApi;
import ru.kackbip.impactMapping.application.CurrentActivityProvider;
import ru.kackbip.impactMapping.screens.goalCreation.interactor.GoalCreationInteractor;
import ru.kackbip.impactMapping.screens.goalCreation.interactor.IGoalCreationInteractor;
import ru.kackbip.impactMapping.screens.goalCreation.presenter.GoalCreationPresenter;
import ru.kackbip.impactMapping.screens.goalCreation.router.GoalCreationRouter;
import ru.kackbip.impactMapping.screens.goalCreation.router.IGoalCreationRouter;

/**
 * Created by ryashentsev on 18.10.2016.
 */

@Module
public class GoalCreationModule {

    @Provides
    @Singleton
    public IGoalCreationInteractor provideInteractor(IApi api){
        return new GoalCreationInteractor(api);
    }

    @Provides
    @Singleton
    public GoalCreationPresenter providePresentor(IGoalCreationInteractor interactor, IGoalCreationRouter router){
        return new GoalCreationPresenter(interactor, router);
    }

    @Provides
    @Singleton
    public IGoalCreationRouter provideRouter(CurrentActivityProvider currentActivityProvider){
        return new GoalCreationRouter(currentActivityProvider);
    }

}
