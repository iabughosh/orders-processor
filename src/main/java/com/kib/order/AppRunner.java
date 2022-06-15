package com.kib.order;

import org.apache.camel.main.Main;

public class AppRunner {

    private AppRunner() {}

    public static void main(String[] args) throws Exception {
        // use Camels Main class
        Main main = new Main(AppRunner.class);
        // now keep the application running until the JVM is terminated (ctrl + c or sigterm)
        main.run(args);
    }
}
