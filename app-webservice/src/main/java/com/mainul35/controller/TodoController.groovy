package com.mainul35.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@RestController
class TodoController {
    @GetMapping("/")
    def index () {
        return ["status": 200, "message": "App is up to date"]
    }
}
