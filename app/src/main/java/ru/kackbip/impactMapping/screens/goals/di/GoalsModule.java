package ru.kackbip.impactMapping.screens.goals.di;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.kackbip.impactMapping.api.IApi;
import ru.kackbip.impactMapping.application.CurrentActivityProvider;
import ru.kackbip.impactMapping.screens.goals.presenter.GoalsPresenter;
import ru.kackbip.impactMapping.screens.goals.router.GoalsRouter;
import ru.kackbip.impactMapping.screens.goals.router.IGoalsRouter;
import ru.kackbip.impactMapping.screens.goals.interactor.IGoalsInteractor;
import ru.kackbip.impactMapping.screens.goals.interactor.GoalsInteractor;

/**
 * Created by ryashentsev on 18.10.2016.
 */

@Module
public class GoalsModule {

    @Provides
    @Singleton
    public IGoalsInteractor provideGoalsInteractor(IApi api){
        return new GoalsInteractor(api);
    }

    @Provides
    @Singleton
    public GoalsPresenter provideGoalsPresentor(IGoalsInteractor interactor, IGoalsRouter router){
        return new GoalsPresenter(interactor, router);
    }

    @Provides
    @Singleton
    public IGoalsRouter provideGoalsRouter(CurrentActivityProvider currentActivityProvider){
        return new GoalsRouter(currentActivityProvider);
    }

}
