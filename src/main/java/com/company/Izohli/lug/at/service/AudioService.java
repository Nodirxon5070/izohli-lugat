package com.company.Izohli.lug.at.service;

import com.company.Izohli.lug.at.dto.ErrorDto;
import com.company.Izohli.lug.at.dto.ResponseDto;
import com.company.Izohli.lug.at.dto.requestDto.RequestAudioDto;
import com.company.Izohli.lug.at.dto.responseDto.AudioDto;
import com.company.Izohli.lug.at.mapper.AudioMapper;
import com.company.Izohli.lug.at.repository.AudioRepository;
import com.company.Izohli.lug.at.utill.SimpleCrud;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AudioService implements SimpleCrud<Integer, RequestAudioDto, AudioDto> {
    private final AudioMapper audioMapper;
    private final AudioRepository audioRepository;

    @Override
    public ResponseDto<AudioDto> createEntity(RequestAudioDto dto) {
        return null;
    }

    @Override
    public ResponseDto<AudioDto> getEntity(Integer entityId) {
        return null;
    }

    @Override
    public ResponseDto<AudioDto> updateEntity(Integer entityId, RequestAudioDto entity) {
        return null;
    }

    @Override
    public ResponseDto<AudioDto> deleteEntity(Integer entityId) {
        return null;
    }
}
