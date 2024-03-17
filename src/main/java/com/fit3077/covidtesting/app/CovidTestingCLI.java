package com.fit3077.covidtesting.app;

import com.fit3077.covidtesting.app.Console;
import com.fit3077.covidtesting.app.common.DependencyContainer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CovidTestingCLI implements CommandLineRunner {

    @Override
    public void run (String... args) throws Exception {
        main(args);
    }

    public static void main(String[] args) throws Exception {
        DependencyContainer dependencyContainer = new DependencyContainer();
        Console console = new Console(dependencyContainer);
        console.run();
    }
}
