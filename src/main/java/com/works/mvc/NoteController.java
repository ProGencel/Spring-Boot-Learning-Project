package com.works.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("mvc")
public class NoteController {

    @GetMapping("note")
    public String note()
    {
        return "note";
    }

}
