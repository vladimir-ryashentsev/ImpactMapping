package ru.kackbip.impactMapping.application.di;

/**
 * Created by ryashentsev on 02.11.2016.
 */

public interface BaseComponent<T> {
    void inject(T root);
}
