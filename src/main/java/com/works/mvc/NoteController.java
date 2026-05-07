package com.works.mvc;

import com.works.dto.NoteSaveRequestDto;
import com.works.entity.Note;
import com.works.service.NoteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("note/create")
    public String create()
    {
        return "noteCreate";
    }

    @PostMapping("note/save")
    public String save(@Valid NoteSaveRequestDto note) {
        noteService.save(note);
        return "redirect:/mvc/note";
    }
}
