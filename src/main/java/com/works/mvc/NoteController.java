package com.works.mvc;

import com.works.entity.Note;
import com.works.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("mvc")
@RequiredArgsConstructor
public class NoteController {

    final NoteService noteService;

    @GetMapping("note")
    public String note(Model model, @RequestParam(defaultValue = "1") int page )
    {
        Page<Note> notePage = noteService.noteList(page - 1);
        model.addAttribute("notePage",notePage);
        return "note";
    }

    @GetMapping("note/delete/{id}")
    public String delete(@PathVariable int id)
    {
        noteService.delete(id);
        return "redirect:/mvc/note";
    }

}
