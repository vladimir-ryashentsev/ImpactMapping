package ru.kackbip.impactMapping.screens.goalCreation.di;

import javax.inject.Singleton;

import dagger.Subcomponent;
import ru.kackbip.impactMapping.application.di.BaseComponent;
import ru.kackbip.impactMapping.screens.goalCreation.view.GoalCreationView;

/**
 * Created by ryashentsev on 18.10.2016.
 */

@Singleton
@Subcomponent(modules = {GoalCreationModule.class})
public interface GoalCreationComponent extends BaseComponent<GoalCreationView>{
}
