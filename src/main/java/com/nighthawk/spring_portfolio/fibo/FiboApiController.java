package com.nighthawk.spring_portfolio.fibo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/fibo")
public class FiboApiController {

    @Autowired
    private FiboJpaRepository repository;

    @GetMapping("/")
    public ResponseEntity<List<Jokes>> getJokes() {
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }

    @PostMapping("/like/{id}")
    public ResponseEntity<Jokes> setLike(@PathVariable long id) {
        Optional<Jokes> optional = repository.findById(id);
        if (optional.isPresent()) {
            Jokes joke = optional.get();
            joke.setHaha(joke.getHaha() + 1);
            repository.save(joke);
            return new ResponseEntity<>(joke, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/jeer/{id}")
    public ResponseEntity<Jokes> setJeer(@PathVariable long id) {
        Optional<Jokes> optional = repository.findById(id);
        if (optional.isPresent()) {
            Jokes joke = optional.get();
            joke.setBoohoo(joke.getBoohoo() + 1);
            repository.save(joke);
            return new ResponseEntity<>(joke, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    // Add visualization endpoints here

}
