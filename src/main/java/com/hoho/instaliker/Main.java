package com.hoho.instaliker;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class Main {

    public static void main(String[] args) {
        Injector injector = Guice.createInjector();
        Instaliker instaliker = injector.getInstance(Instaliker.class);
        instaliker.run();
    }
}
