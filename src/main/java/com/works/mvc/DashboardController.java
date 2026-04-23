package com.works.mvc;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("mvc")
public class DashboardController {

    @GetMapping("dashboard")
    public String dashboard()
    {
        return "dashboard";
    }

}
