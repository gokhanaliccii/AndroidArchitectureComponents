package com.gokhanaliccii.flavorhunter.components.log;

/**
 * Created by gokhan on 28/12/17.
 */

public class ConsoleLogger implements Logger {

    static final String INFO = "I";
    static final String WARNING = "W";
    static final String ERROR = "E";

    final String format = "%s  *%s*  message:%s";

    @Override
    public void info(String tag, String log) {
        printOnConsole(INFO, tag, log);
    }

    @Override
    public void warning(String tag, String log) {
        printOnConsole(WARNING, tag, log);
    }

    @Override
    public void error(String tag, String log) {
        printOnConsole(ERROR, tag, log);
    }

    private void printOnConsole(String indicator, String tag, String message) {
        System.out.println(String.format(format, indicator, tag, message));
    }

}
