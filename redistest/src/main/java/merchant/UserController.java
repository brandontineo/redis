package merchant;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private static final String template = "Favorite phrase, %s :D";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public User greeting(@RequestParam(value="phrase", defaultValue="[Silence]") String phrase) {
        return new User(counter.incrementAndGet(), String.format(template, phrase));
    }
}
