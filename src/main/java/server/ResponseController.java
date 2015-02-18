package server;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResponseController 
{
    private final AtomicLong counter = new AtomicLong();
	
    @RequestMapping("/response")
    public Response response(@RequestParam(value="leaf", defaultValue="0") String leaf, 
							 @RequestParam(value="fruit", defaultValue="0") String fruit,
							 @RequestParam(value="bugs", defaultValue="0") String bugs,
							 @RequestParam(value="branches", defaultValue="0") String branches) 
	{	   
		System.out.println(leaf);
        Request req = new Request(counter.incrementAndGet(), leaf, fruit, bugs, branches);		
		System.out.println(req.generateName());
		System.out.println(req.generateContent());
		Response resp = new Response(req);
		System.out.println(resp.getName());
		System.out.println(resp.getContent());
		return resp;
    }
}