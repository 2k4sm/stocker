package com.sm2k4.stocker;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class DashboardController {
    @GetMapping("/")
    public String showDashboard(){
        return new Date().toString();
    }
}
