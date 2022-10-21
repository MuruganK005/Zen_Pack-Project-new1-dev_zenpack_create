package com.ZenPack.service.Services;

import com.ZenPack.Dto.ZenPackDto;
import com.ZenPack.Exception.ZenPackException;
import com.ZenPack.model.ZenPack;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public interface ZenPackService {
    /*ResponseEntity<ZenPack> saveZenPack(ZenPack zenPack);*/

    ZenPackDto createZenPack(ZenPackDto zenPackDto) throws JsonProcessingException, ZenPackException;

    List<ZenPackDto> getAllZenPack() throws Exception;

    String deleteByzenPackId(Long zenPackId);

    ZenPackDto getByZenPackId(Long zenPackId);
}