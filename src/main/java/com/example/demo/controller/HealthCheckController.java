package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/healthcheck")
public class HealthCheckController {
    @RequestMapping(value = "/liveness", method = RequestMethod.GET)
    public ResponseEntity<?> liveness() {
        return new ResponseEntity<>("Liveness is oke", HttpStatus.OK);
    }

    @RequestMapping(value = "/readiness", method = RequestMethod.GET)
    public ResponseEntity<?> readiness() {
        return new ResponseEntity<>("Readiness is oke", HttpStatus.OK);
    }
}
