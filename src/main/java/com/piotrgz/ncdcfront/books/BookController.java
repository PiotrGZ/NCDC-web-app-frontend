package com.piotrgz.ncdcfront.books;

import com.piotrgz.ncdcfront.configuration.Config;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/")
public class BookController {
    private final Config config;
    private final RestTemplate restTemplate = new RestTemplate();

    public BookController(Config config) {
        this.config = config;
    }

    @GetMapping()
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView("home");
        ResponseEntity<List<Book>> response = restTemplate.exchange(config.getBaseUrl() + "books", HttpMethod.GET, null, new ParameterizedTypeReference<List<Book>>() {
        });
        List<Book> employees = response.getBody();
        if(!employees.isEmpty()) {
            modelAndView.addObject("books", employees);
            return modelAndView;
        }else return new ModelAndView("homeEmptyList");
    }

    @GetMapping("/add")
    public ModelAndView addBook() {
        return new ModelAndView("add").addObject("book", new Book());
    }

    @PostMapping("/post")
    public ModelAndView postBook(@ModelAttribute Book book) {
        restTemplate.postForLocation(config.getBaseUrl() + "books", book);
        ModelAndView modelAndView = new ModelAndView("home");
        ResponseEntity<List<Book>> response = restTemplate.exchange(config.getBaseUrl() + "books", HttpMethod.GET, null, new ParameterizedTypeReference<List<Book>>() {
        });
        List<Book> employees = response.getBody();
        if(!employees.isEmpty()) {
            modelAndView.addObject("books", employees);
            return modelAndView;
        }else return new ModelAndView("homeEmptyList");
    }
}

