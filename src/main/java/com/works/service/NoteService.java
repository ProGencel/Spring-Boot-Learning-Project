package com.works.service;

import com.works.configs.GlobalException;
import com.works.dto.NoteSaveRequestDto;
import com.works.dto.NoteUpdateRequestDto;
import com.works.entity.Note;
import com.works.repository.NoteRepository;
import com.works.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NoteService {

    private final ProductRepository productRepository;
    ModelMapper modelMapper = new ModelMapper();
    final NoteRepository noteRepository;

    public Note save(NoteSaveRequestDto noteSaveRequestDto) {
        Note note = modelMapper.map(noteSaveRequestDto, Note.class);
        return noteRepository.save(note);
    }

    public List<Note> saveAll(List<NoteSaveRequestDto> noteSaveRequestDtos) {
        List<Note> notes = noteSaveRequestDtos.stream().map((element) -> modelMapper.map(element, Note.class)).toList();
        return noteRepository.saveAll(notes);
    }

    public ResponseEntity delete(long id) {
        Optional<Note> noteOptional = noteRepository.findById(id);
        if (noteOptional.isPresent()) {
            noteRepository.deleteById(id);

            Map<String, Object> bm = new HashMap<>();
            bm.put("Success", true);
            bm.put("Message", "Delete process successful");

            return ResponseEntity.ok().body(bm);
        } else {
            Map<String, Object> error = new HashMap<>();
            error.put("success", false);
            error.put("message", "Id cannot found: " + id);
            return ResponseEntity.badRequest().body(error);
        }
    }

    public ResponseEntity deleteBetween(long firstId, long lastId) {
        
        ResponseEntity errorObject = null;

        long tempMin = Math.min(firstId, lastId);
        lastId = (tempMin == lastId) ? firstId : lastId;
        firstId = tempMin;

        if (noteRepository.existsById(firstId) && noteRepository.existsById(lastId)) {

            noteRepository.deleteByIdBetween(firstId, lastId);

            Map<String, Object> bm = new HashMap<>();
            bm.put("success", true);
            bm.put("message", "Elements deleted successfully");

            return ResponseEntity.ok().body(bm);
        } else {
            if (!noteRepository.existsById(firstId)) {
                errorObject = GlobalException.handleInvalidId(GlobalException.ID_POS.FIRST, firstId);
            } else if (!noteRepository.existsById(lastId)) {
                errorObject = GlobalException.handleInvalidId(GlobalException.ID_POS.LAST, lastId);
            }
            
            return errorObject;
        }
    }

    public ResponseEntity update(NoteUpdateRequestDto noteUpdateRequestDto) {
        Optional<Note> noteOptional = noteRepository.findById(noteUpdateRequestDto.getId());

        if(noteOptional.isPresent()) {
            Note note = modelMapper.map(noteUpdateRequestDto, Note.class);
            noteRepository.save(note);

            Map<String, Object> bm = new HashMap<>();
            bm.put("success", true);
            bm.put("message", "Note updated successfully");
            return ResponseEntity.ok().body(bm);

        } else {
            Map<String, Object> error = new HashMap<>();
            error.put("success", false);
            error.put("message", "Note id cannot found: " + noteUpdateRequestDto.getId());
            return ResponseEntity.badRequest().body(error);
        }
    }

    public Page<Note> noteList(int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return noteRepository.findAll(pageable);
    }

    public Page<Note> search(String q, int page, String noteDay)
    {
        Sort sort = Sort.by(noteDay.equals("desc") ? Sort.Direction.DESC : Sort.Direction.ASC, "noteDay");
        Pageable pageable = PageRequest.of(page, 10,sort);
        return noteRepository.findByTitleContainsOrDetailContains(q,q,pageable);
    }

}
