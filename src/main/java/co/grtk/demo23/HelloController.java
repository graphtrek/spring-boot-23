package co.grtk.demo23;

import org.springframework.boot.availability.AvailabilityChangeEvent;
import org.springframework.boot.availability.ReadinessState;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    private final ApplicationEventPublisher publisher;

    public HelloController(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    @GetMapping("/hello")
    public String hello() throws Exception {
        Thread.sleep(5000);
        return "Hello World";
    }

    @GetMapping("/down")
    public String down() {
        AvailabilityChangeEvent.publish(publisher,this, ReadinessState.REFUSING_TRAFFIC);
        return "down";
    }

    @RequestMapping("/up")
    public String up() {
        AvailabilityChangeEvent.publish(publisher, this, ReadinessState.ACCEPTING_TRAFFIC);
        return "up";
    }
}