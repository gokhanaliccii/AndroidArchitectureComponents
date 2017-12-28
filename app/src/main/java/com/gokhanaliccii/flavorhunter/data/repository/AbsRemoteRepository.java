package com.gokhanaliccii.flavorhunter.data.repository;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by gokhan on 28/12/17.
 */

public abstract class AbsRemoteRepository {

    protected CompositeSubscription mCompositeSubscription;

    public AbsRemoteRepository() {
        this.mCompositeSubscription = mCompositeSubscription;
    }

    void addSubscription(Subscription... subscriptions) {
        mCompositeSubscription.addAll(subscriptions);
    }

    protected void clear() {
        mCompositeSubscription.clear();
    }

}