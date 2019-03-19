package com.fredoliveira.util;

import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class Messages {

    private static final String BUNDLE_NAME = "messages";

    private static final ResourceBundle RESOURCE_BUNDLE = Utf8ResourceBundle.getBundle(BUNDLE_NAME);

    public static String getString(MessageKey key, Object... params) {
        try {
            return MessageFormat.format(RESOURCE_BUNDLE.getString(key.toString()), params);
        } catch (MissingResourceException e) {
            return '!' + key.toString() + '!';
        }
    }

}
