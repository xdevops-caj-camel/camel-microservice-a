package cn.xdevops.camelmicroservicea.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class ActiveMQSenderRouter extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        // timer
        // error:  No endpoint could be found for: activemq://my-activemq-queue
        from("timer:active-mq-timer?period=10000") // 10 seconds
                .transform().constant("My message for ActiveMQ")
                .log("${body}")
                .to("activemq:my-activemq-queue");

        // queue

    }
}
