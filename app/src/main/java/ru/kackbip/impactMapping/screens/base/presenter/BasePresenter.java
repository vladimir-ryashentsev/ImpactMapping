package ru.kackbip.impactMapping.screens.base.presenter;

import ru.kackbip.impactMapping.screens.base.view.IBaseView;
import rx.Subscription;

/**
 * Created by ryashentsev on 19.10.2016.
 */

public abstract class BasePresenter<T extends IBaseView> {

    protected T view;
    private Subscription destroySubscription;

    public void setView(T view){
        this.view = view;
        subscribeForViewDestroy();
    }

    private void subscribeForViewDestroy(){
        destroySubscription = view.waitForDestroy().subscribe(aVoid -> {
            destroySubscription.unsubscribe();
            onViewDestroy();
        });
    }

    protected abstract void onViewDestroy();
}
