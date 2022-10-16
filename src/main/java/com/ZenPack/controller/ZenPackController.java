package com.ZenPack.controller;

import com.ZenPack.Dto.SpecificationDto;
import com.ZenPack.Dto.ZenPackDto;
import com.ZenPack.model.ZenPack;
import com.ZenPack.repository.ZenPackRepository;
import com.ZenPack.service.Impl.ZenPackServiceImpl;
import com.ZenPack.service.Services.SpecificationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class ZenPackController {

    @Autowired
    private ZenPackServiceImpl service;

    @Autowired
    private ZenPackRepository zenPackRepository;

    @Autowired
    private SpecificationService specificationService;


    @PostMapping("/save")
    public ResponseEntity<ZenPack> saveZenPack(@RequestBody ZenPack zenPack) {
        return service.saveZenPack(zenPack);
    }

    @PostMapping("/create")
    public ResponseEntity<ZenPackDto> createZenPack(@RequestBody ZenPackDto zenPackDto) throws JsonProcessingException {
        return service.createZenPack(zenPackDto);
    }
    @GetMapping(value = "get_all",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ZenPackDto> getAllZenPack() throws JsonProcessingException {
        return service.getAllZenPack();
    }
    @DeleteMapping("/delete/{zenPackId}")
    public String deleteByZenPackId(@PathVariable Long zenPackId){
        return service.deleteByzenPackId(zenPackId);
    }
    @GetMapping("/getByZenPackId/{zenPackId}")
    public ZenPackDto getByZenPackId(@PathVariable Long zenPackId){
        return service.getByZenPackId(zenPackId);
    }

    @PostMapping("/search")
    public ResponseEntity<Page<ZenPack>> getBySpecification(@RequestBody SpecificationDto specificationDto){
        ResponseEntity<Page<ZenPack>> response = specificationService.getBySpecification(specificationDto);
        return new ResponseEntity<>(response.getBody(),response.getStatusCode());
    }

}