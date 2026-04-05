package com.works.controller;

import com.works.dto.NoteSaveRequestDto;
import com.works.dto.NoteUpdateRequestDto;
import com.works.entity.Note;
import com.works.repository.NoteRepository;
import com.works.service.NoteService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("note")
@RequiredArgsConstructor
public class NoteRestController {

    final NoteService noteService;
    private final NoteRepository noteRepository;

    @PostMapping("save")
    public Note save(@Valid @RequestBody NoteSaveRequestDto noteSaveRequestDto)
    {
        return noteService.save(noteSaveRequestDto);
    }

    @PostMapping("saveAll")
    public List<Note> saveAll(@Valid @RequestBody List<NoteSaveRequestDto> noteSaveRequestDtos)
    {
        return noteService.saveAll(noteSaveRequestDtos);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity delete(@PathVariable long id)
    {
        return noteService.delete(id);
    }

    @DeleteMapping("deleteBetween")
    @Transactional
    public ResponseEntity deleteBetween(@RequestParam long firstId,
                                        @RequestParam long lastId)
    {
        return noteService.deleteBetween(firstId,lastId);
    }

    @PutMapping("update")
    public ResponseEntity update(@Valid @RequestBody NoteUpdateRequestDto noteUpdateRequestDto)
    {
        return noteService.update(noteUpdateRequestDto);
    }

    @PostMapping("noteList")
    public Page<Note> noteList(@RequestParam(defaultValue = "0") int page)
    {
        return noteService.noteList(page);
    }

    @PostMapping("search")
    public Page<Note> search(@RequestParam String q,
                             @RequestParam (defaultValue = "0") int page,
                             @RequestParam String noteDay)
    {
        return noteService.search(q,page,noteDay);
    }

}
