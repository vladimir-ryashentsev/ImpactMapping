package ru.kackbip.impactMapping.screens.goals.di;

import javax.inject.Singleton;

import dagger.Subcomponent;
import ru.kackbip.impactMapping.application.di.BaseComponent;
import ru.kackbip.impactMapping.screens.goals.view.GoalsView;

/**
 * Created by ryashentsev on 18.10.2016.
 */

@Singleton
@Subcomponent(modules = {GoalsModule.class})
public interface GoalsComponent extends BaseComponent<GoalsView>{
}
