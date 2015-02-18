package server;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResponseController 
{
    private final AtomicLong counter = new AtomicLong();
	private final String noResponse = "Sorry! We were unable to match your query with a response.";
	
    @RequestMapping("/response")
    public Response response(@RequestParam(value="data", defaultValue=noResponse) String data) 
	{	   
        return new Response(counter.incrementAndGet(), data);		
    }
}