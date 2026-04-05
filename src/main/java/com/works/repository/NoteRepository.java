package com.works.repository;

import com.works.entity.Note;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note,Long> {

    long deleteByIdBetween(long idStart, long idEnd);

    Page<Note> findByTitleContainsOrDetailContains(String title, String detail, Pageable pageable);
}
