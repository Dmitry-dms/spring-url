package ru.urlshortener.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.urlshortener.repository.KVRepository;
import java.net.URI;
import java.util.Base64;
import java.util.List;



@RestController
public class WebController {

    @Autowired
    private KVRepository kvRepository;

    @Value("${spring.redis.ttl_seconds}")
    private int ttl;

    @GetMapping("/{shortUrl}")
    public ResponseEntity<Void> setUrl(@PathVariable String shortUrl) {
        String r = kvRepository.get(shortUrl);
        if (r == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(r)).build();
    }

    @GetMapping("/")
    public String index(@RequestParam(name = "url") String url) {

        String encodeBytes = Base64.getEncoder().encodeToString(url.getBytes());
        List<Character> li = encodeBytes.chars().mapToObj(i -> (char) i).toList();

        int N = li.size();
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < 7; i++) {
            int r = (int) (Math.random() * (N - 1));
            str.append(li.get(r));
        }
        String shortUrl = str.toString();

        kvRepository.set(shortUrl, url, ttl);

        return shortUrl;
    }
}
