package com.googlecode.hdbc.dbmigrate;

import java.util.ArrayList;

import com.googlecode.hdbc.dbmigrate.processor.IInputProcessor;
import com.googlecode.hdbc.dbmigrate.validator.IInputValidator;

public final class Menu {
    private final ArrayList<MenuItem> menuItems = new ArrayList<MenuItem>();
    private final IInputProcessor inputProcessor = null;
    private final IInputValidator inputValidator = null;

    private Menu(final IInputProcessor prc, final IInputValidator vld) {
        this.inputProcessor = prc;
        this.inputValidator = vad;
    }

    public static Menu menu(final IInputProcessor processor, final IInputValidator validator) {
        return new Menu(processor, validator);
    }

    private static <T> T instantiate(final Class<T> t) {
        T instance = null;
        try {
            instance = t.newInstance();
        } catch (InstantiationException e) {
            new IllegalArgumentException(e);
        } catch (IllegalAccessException e) {
            new IllegalArgumentException(e);
        }
        return instance;
    }

    public static IInputProcessor processWith(final Class<? extends IInputProcessor> cls) {
        return instantiate(cls);
    }

    public static IInputValidator validateWith(final Class<? extends IInputValidator> cls) {
        return instantiate(cls);
    }

    public Menu item(final MenuItem item) {
        menuItems.add(item);
        return this;
    }

    public void run() {

    }
}
