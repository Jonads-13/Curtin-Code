package edu.curtin.saed_assignment2.api;

import java.util.Locale;

public interface LocaleHandler {
    
    void start(API api);
    void notifyLocaleChanged(Locale locale);
}
