package ru.kackbip.impactMapping.screens.goals.view;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.jakewharton.rxbinding.view.RxView;

import ru.kackbip.impactMapping.R;
import ru.kackbip.impactMapping.api.projections.Goals;
import ru.kackbip.impactMapping.screens.base.view.BaseActivityView;
import ru.kackbip.impactMapping.screens.goals.presenter.GoalsPresenter;
import rx.Observable;

/**
 * Created by ryashentsev on 08.10.2016.
 */

public class GoalsView extends BaseActivityView<GoalsPresenter> implements IGoalsView {

    private RecyclerView goals;
    private GoalsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        goals = (RecyclerView) findViewById(R.id.goals);
        goals.setHasFixedSize(true);
        goals.setLayoutManager(new LinearLayoutManager(this));
        goals.setAdapter(adapter = new GoalsAdapter());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setImageResource(R.drawable.ic_add_white_24dp);
    }

    @Override
    public int getLayoutResourceId() {
        return R.layout.goals;
    }

    @Override
    public boolean needFab() {
        return true;
    }

    @Override
    public void showGoals(Goals goals) {
        Thread t = Thread.currentThread();
        adapter.setGoals(goals.getGoals());
    }

    @Override
    public Observable<Void> waitForCreateClick() {
        return RxView.clicks(findViewById(R.id.fab));
    }
}
