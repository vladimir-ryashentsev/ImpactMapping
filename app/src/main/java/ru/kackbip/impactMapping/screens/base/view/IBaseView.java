package ru.kackbip.impactMapping.screens.base.view;

import ru.kackbip.impactMapping.screens.base.presenter.BasePresenter;
import rx.Observable;

/**
 * Created by ryashentsev on 19.10.2016.
 */

public interface IBaseView<T extends BasePresenter> {
    Observable<Void> waitForDestroy();
    void showError();
}
