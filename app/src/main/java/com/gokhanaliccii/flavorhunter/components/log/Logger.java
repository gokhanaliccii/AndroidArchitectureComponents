package com.gokhanaliccii.flavorhunter.components.log;

/**
 * Created by gokhan on 28/12/17.
 */

public interface Logger {

    void info(String tag, String log);

    void warning(String tag, String log);

    void error(String tag, String log);
}
