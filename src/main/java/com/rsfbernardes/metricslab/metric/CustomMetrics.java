package com.rsfbernardes.metricslab.metric;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Component;

@Component
public class CustomMetrics {

    private final Counter counter;

    public CustomMetrics(MeterRegistry meterRegistry) {
        this.counter = Counter.builder("app.hello.requests")
                .description("Number of hello endpoint requests")
                .tag("endpoint", "/hello")
                .register(meterRegistry);
    }

    public void incrementCounter() {
        counter.increment();
    }

}
