package com.kulagin.books.socialmultiplication.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kulagin.books.socialmultiplication.services.SocialMultiplicationService;
import com.kulagin.books.socialmultiplication.services.dto.Multiplication;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/multiplications", produces = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class MultiplicationController {
    private final SocialMultiplicationService multiplicationService;
    
    @GetMapping("/random")
    Multiplication getRandomMultiplication() {
        return multiplicationService.getRandomMultiplication();
    }
}
