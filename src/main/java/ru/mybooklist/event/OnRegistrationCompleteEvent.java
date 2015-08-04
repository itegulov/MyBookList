package ru.mybooklist.event;

import org.springframework.context.ApplicationEvent;
import ru.mybooklist.model.User;

import java.util.Locale;

/**
 * @author Daniyar Itegulov
 */
public class OnRegistrationCompleteEvent extends ApplicationEvent {
    private final User user;
    private final Locale locale;
    private final String appUrl;

    public OnRegistrationCompleteEvent(User user, Locale locale, String appUrl) {
        super(user);

        this.user = user;
        this.locale = locale;
        this.appUrl = appUrl;
    }

    public User getUser() {
        return user;
    }

    public Locale getLocale() {
        return locale;
    }

    public String getAppUrl() {
        return appUrl;
    }
}
