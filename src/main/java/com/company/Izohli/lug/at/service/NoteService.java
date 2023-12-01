package com.company.Izohli.lug.at.service;

import com.company.Izohli.lug.at.dto.ErrorDto;
import com.company.Izohli.lug.at.dto.ResponseDto;
import com.company.Izohli.lug.at.dto.requestDto.RequestNoteDto;
import com.company.Izohli.lug.at.dto.responseDto.NoteDto;
import com.company.Izohli.lug.at.module.Note;
import com.company.Izohli.lug.at.repository.NoteRepository;
import com.company.Izohli.lug.at.repository.WordRepository;
import com.company.Izohli.lug.at.service.mapper.NoteMapper;
import com.company.Izohli.lug.at.service.mapper.WordMapper;
import com.company.Izohli.lug.at.util.SimpleCrud;
import com.company.Izohli.lug.at.validation.NoteValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteService implements SimpleCrud<Integer, RequestNoteDto, NoteDto> {
    private final NoteRepository noteRepository;
    private final NoteValidation noteValidation;
    private final NoteMapper noteMapper;
    private final WordMapper wordMapper;
    private final WordRepository wordRepository;

    @Override
    public ResponseDto<NoteDto> createEntity(RequestNoteDto dto) {
        List<ErrorDto> errors;
        errors = this.noteValidation.noteValid(dto);
        if (!errors.isEmpty()) {
            return ResponseDto.<NoteDto>builder()
                    .code(-3)
                    .message("Validation error")
                    .errorList(errors)
                    .build();
        }
        try {
            Note note = this.noteMapper.toEntity(dto);
            return ResponseDto.<NoteDto>builder()
                    .success(true)
                    .message("Ok")
                    .data(this.noteMapper.toDtoWithWord(
                            this.noteRepository.save(note)
                    ))
                    .build();

        } catch (Exception e) {
            return ResponseDto.<NoteDto>builder()
                    .code(-2)
                    .message(String.format("Note while saving error %s", e.getMessage()))
                    .build();
        }
    }

    @Override
    public ResponseDto<NoteDto> getEntity(Integer entityId) {
        return this.noteRepository.findByNoteIdAndDeletedAtIsNull(entityId)
                .map(note -> {
                    NoteDto noteDto = this.noteMapper.toDtoWithWord(note);
                    noteDto.setWord(this.wordMapper.toDto(this.wordRepository.findWordByWordId(note.getWordId())));
                    return ResponseDto.<NoteDto>builder()
                            .success(true)
                            .message("Ok")
                            .data(noteDto)
                            .build();
                })
                .orElse(ResponseDto.<NoteDto>builder()
                        .code(-1)
                        .message(String.format("Note with %d id is not found", entityId))
                        .build());
    }

    @Override
    public ResponseDto<NoteDto> updateEntity(Integer entityId, RequestNoteDto dto) {
        try {
            return this.noteRepository.findByNoteIdAndDeletedAtIsNull(entityId)
                    .map(note -> ResponseDto.<NoteDto>builder()
                            .success(true)
                            .message("OK")
                            .data(this.noteMapper.toDto(
                                    noteRepository.save(
                                            noteMapper.updateNote(dto, note))))
                            .build()
                    ).orElse(ResponseDto.<NoteDto>builder()
                            .code(-1)
                            .message(String.format("Note with %d id is not found", entityId))
                            .build());
        } catch (Exception e) {
            return ResponseDto.<NoteDto>builder()
                    .code(-2)
                    .message(String.format("Note while saving error %s", e.getMessage()))
                    .build();
        }
    }

    @Override
    public ResponseDto<NoteDto> deleteEntity(Integer entityId) {
        return this.noteRepository.findByNoteIdAndDeletedAtIsNull(entityId)
                .map(note -> {
                    note.setDeletedAt(LocalDateTime.now());
                    return ResponseDto.<NoteDto>builder()
                            .success(true)
                            .message("OK")
                            .data(this.noteMapper.toDto(
                                    this.noteRepository.save(note)
                            ))
                            .build();
                }).orElse(ResponseDto.<NoteDto>builder()
                        .code(-1)
                        .message(String.format("Note with %d id is not found", entityId))
                        .build());
    }
}
