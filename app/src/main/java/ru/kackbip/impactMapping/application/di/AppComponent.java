package ru.kackbip.impactMapping.application.di;

import javax.inject.Singleton;

import dagger.Component;
import ru.kackbip.impactMapping.screens.goalCreation.di.GoalCreationComponent;
import ru.kackbip.impactMapping.screens.goalCreation.di.GoalCreationModule;
import ru.kackbip.impactMapping.screens.goals.di.GoalsComponent;
import ru.kackbip.impactMapping.screens.goals.di.GoalsModule;

/**
 * Created by ryashentsev on 18.10.2016.
 */

@Singleton
@Component(modules = {AppModule.class, GsonModule.class, ApiModule.class})
public interface AppComponent {
    GoalsComponent plusGoalsComponent(GoalsModule goalsModule);
    GoalCreationComponent plusGoalCreationComponent(GoalCreationModule goalsModule);
}
