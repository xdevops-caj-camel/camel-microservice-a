package cn.xdevops.camelmicroservicea.routes;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

// uncomment to test
//@Component
public class MyFirstTimerRouter extends RouteBuilder {

    // recommend to use constructor injection
    @Autowired
    private GetCurrentTimeBean getCurrentTimeBean;
    @Autowired
    private SimpleLoggingProcessingComponent loggingProcessingComponent;

    @Override
    public void configure() throws Exception {
        // timer
        // transformation
        // log

        // message: Exchange[ExchangePattern: InOnly, BodyType: null, Body: [Body is null]]
        // generate message every second
        // print logs at different stages
        from("timer:first-timer") // simulate from queue
                .log("${body}")
                .transform().constant("My Constant Message") // message content
                .log("${body}")
//                .transform().constant("Time now is " + LocalDateTime.now()) // constant time
//                .bean("getCurrentTimeBean") // use spring bean to do transformation

                // processing
                // transformation

                .bean(getCurrentTimeBean) // the bean only has one method at this moment
                .log("${body}")
                .bean(loggingProcessingComponent)
                .log("${body}")
                .process(new SimpleLoggingProcessor()) // use camel processor to process
                .to("log:first-timer"); // simulate database
    }


}

@Component
class GetCurrentTimeBean {
    public String getCurrentTime() {
        return "Time now is " + LocalDateTime.now();
    }
}

@Slf4j
@Component
class SimpleLoggingProcessingComponent {
    public void process(String message) {
        log.info("SimpleLoggingProcessingComponent {}", message);
    }
}

@Slf4j
class SimpleLoggingProcessor implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        log.info("SimpleLoggingProcessor {}", exchange.getMessage().getBody());
    }
}
