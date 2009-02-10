package com.googlecode.hdbc.dbmigrate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.googlecode.hdbc.dbmigrate.processor.IInputProcessor;
import com.googlecode.hdbc.dbmigrate.validator.IInputValidator;

public final class Menu {
    private final List<MenuItem> menuItems = new ArrayList<MenuItem>();
    private IInputProcessor inputProcessor;
    private IInputValidator inputValidator;

    private Menu(final IInputProcessor prc, final IInputValidator vld) {
        this.inputProcessor = prc;
        this.inputValidator = vld;
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

    public Menu add(final MenuItem item) {
        menuItems.add(item);
        return this;
    }

    private void printMenu() {
        for (MenuItem item : this.menuItems) {
            System.out.println(item.toString());
        }
    }

    public void run() throws IOException {
        printMenu();
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String response = in.readLine();
        if (this.inputValidator.validate(response)) {
            this.inputProcessor.process(response, menuItems);
        } else {
            System.out.println("invalid response");
            run();
        }
    }
}
