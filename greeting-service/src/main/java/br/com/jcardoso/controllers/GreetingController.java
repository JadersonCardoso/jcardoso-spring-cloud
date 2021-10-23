package br.com.jcardoso.controllers;

import br.com.jcardoso.configuration.GreetingConfiguration;
import br.com.jcardoso.model.Greeting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {

    private static final String template = "%s, %s";
    private final AtomicLong couter = new AtomicLong();

    @Autowired
    private GreetingConfiguration configuration;

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "") String name ) {
        if (name.isEmpty()) name = configuration.getDefaultValue();
        return new Greeting(couter.incrementAndGet(), String.format(template, configuration.getGreeting(), name));
    }
}
