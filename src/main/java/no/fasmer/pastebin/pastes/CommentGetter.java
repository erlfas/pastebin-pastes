package no.fasmer.pastebin.pastes;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import java.util.Collections;
import java.util.List;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CommentGetter {

    private final RestTemplate restTemplate;

    public CommentGetter(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @HystrixCommand(fallbackMethod = "defaultComments")
    public List<Comment> getComments(Paste paste) {
        return restTemplate.exchange(
                "http://PASTEBINCOMMENTS/comments/{pasteId}", 
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Comment>>() {},
                paste.getId()).getBody();
    }
    
    public List<Comment> defaultComments(Paste paste) {
        return Collections.emptyList();
    }
    
}
