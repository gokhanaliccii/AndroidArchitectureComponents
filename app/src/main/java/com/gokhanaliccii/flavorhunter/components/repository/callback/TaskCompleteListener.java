package com.gokhanaliccii.flavorhunter.components.repository.callback;

/**
 * Created by gokhan on 28/12/17.
 */

public interface TaskCompleteListener {

    void onTaskCompleted();

    void onTaskFailed(String reason);
}
