package ru.kackbip.impactMapping.screens.base.view;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import ru.kackbip.impactMapping.R;
import ru.kackbip.impactMapping.screens.base.presenter.BasePresenter;
import rx.AsyncEmitter;
import rx.Observable;

/**
 * Created by ryashentsev on 19.10.2016.
 */

public abstract class BaseActivityView<T extends BasePresenter> extends AppCompatActivity implements IBaseView<T> {

    private AsyncEmitter<Void> destroyEmitter;
    private T presenter;

    @Inject
    public void injectPresenter(T presenter) {
        this.presenter = presenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        LayoutInflater layoutInflater = getLayoutInflater();
        layoutInflater.inflate(getLayoutResourceId(), (ViewGroup) findViewById(R.id.screenContent));
        presenter.setView(this);
        if (!needFab()) findViewById(R.id.fab).setVisibility(View.GONE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        destroyEmitter.onNext(null);
        destroyEmitter.onCompleted();
    }

    public abstract int getLayoutResourceId();

    public abstract boolean needFab();

    public Observable<Void> waitForDestroy() {
        return Observable.fromEmitter(emitter -> destroyEmitter = emitter, AsyncEmitter.BackpressureMode.ERROR);
    }

    @Override
    public void showError() {
        Snackbar.make(getWindow().getDecorView(), R.string.error, Snackbar.LENGTH_LONG);
    }
}
