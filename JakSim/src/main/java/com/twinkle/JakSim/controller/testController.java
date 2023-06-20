package com.twinkle.JakSim.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class testController {
    @GetMapping("/postMethod")
    public SearchParam postMethod(@RequestBody SearchParam searchParam) {
        return searchParam;
    }
}
