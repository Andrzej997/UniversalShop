package com.dummy.universalshop.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 * Created by Mateusz on 16.05.2017.
 */
@Component
public class LocalizedMessageFacade {

    @Autowired
    private MessageSource messageSource;

    public String getValue(String key, Locale locale) {
        return messageSource.getMessage(key, new Object[0], locale);
    }

    public String getDefaultValueFromContextLocale(String key) {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(key, new Object[0], locale);
    }

    public String getDefaultValue(String key) {
        return messageSource.getMessage(key, new Object[0], Locale.ENGLISH);
    }

    public String getValue(String key, Locale locale, Object[] params) {
        if (params == null) {
            return getValue(key, locale);
        }
        return messageSource.getMessage(key, params, locale);
    }

    public String getDefaultValueFromContextLocale(String key, Object[] params) {
        if (params == null) {
            return getDefaultValueFromContextLocale(key);
        }
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(key, params, locale);
    }

    public String getDefaultValue(String key, Object[] params) {
        return messageSource.getMessage(key, params, Locale.ENGLISH);
    }
}
