package com.gokhanaliccii.flavorhunter.components.repository.callback;

/**
 * Created by gokhan on 28/12/17.
 */

public interface DataLoadListener<T> {

    void onDataLoaded(T t);

    void onDataLoadFailed(String reason);
}
