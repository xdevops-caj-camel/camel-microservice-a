package cn.xdevops.camelmicroservicea.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class MyFileRouter extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        // move files from files/input to files/output folder
        // keep running
        from("file:files/input")
                .log("${body}") // print file content ?
                .to("file:files/output");
    }
}
