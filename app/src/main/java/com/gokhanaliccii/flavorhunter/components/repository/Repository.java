package com.gokhanaliccii.flavorhunter.components.repository;

import com.gokhanaliccii.flavorhunter.components.repository.callback.DataLoadListener;
import com.gokhanaliccii.flavorhunter.components.repository.callback.TaskCompleteListener;
import com.gokhanaliccii.flavorhunter.components.repository.criteria.SearchCriteria;

/**
 * Created by gokhan on 28/12/17.
 */

public interface Repository<Model> {

    void get(DataLoadListener listener, SearchCriteria... searchCriteria);

    void remove(TaskCompleteListener listener, SearchCriteria... searchCriteria);
}