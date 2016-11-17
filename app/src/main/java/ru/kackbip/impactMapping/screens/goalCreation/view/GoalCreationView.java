package ru.kackbip.impactMapping.screens.goalCreation.view;

import android.support.design.widget.Snackbar;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.widget.RxTextView;

import java.util.Date;

import ru.kackbip.impactMapping.R;
import ru.kackbip.impactMapping.screens.base.view.BaseActivityView;
import ru.kackbip.impactMapping.screens.goalCreation.presenter.GoalCreationPresenter;
import rx.Observable;

public class GoalCreationView extends BaseActivityView<GoalCreationPresenter> implements IGoalCreationView {

    @Override
    public int getLayoutResourceId() {
        return R.layout.goal_creation;
    }

    @Override
    public boolean needFab() {
        return false;
    }

    @Override
    public void showError() {
        Snackbar.make(getWindow().getDecorView(), R.string.error, Snackbar.LENGTH_LONG)
                .setAction("Ага", null).show();
    }

    @Override
    public Observable<Void> waitForCreateClick() {
        return RxView.clicks(findViewById(R.id.create));
    }

    @Override
    public Observable<String> waitForTitleChanged() {
        return RxTextView.textChanges((TextView) findViewById(R.id.title))
                .filter(charSequence -> charSequence.length()>0)
                .map(CharSequence::toString);
    }

    @Override
    public Observable<Date> waitForDateChanged() {
        return ((EditDate)findViewById(R.id.date)).observeDate();
    }
}
