package server;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.google.gson.Gson;

@RestController
public class ResponseController 
{

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/response")
    public Response response(@RequestParam(value="name", defaultValue="World") String name) 
	{
        return new Response(counter.incrementAndGet(),
                            String.format(template, name));
    }
}