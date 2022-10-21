package com.ZenPack.service.Impl;

import com.ZenPack.Dto.MenuDto;
import com.ZenPack.Dto.ZenPackDto;
import com.ZenPack.Exception.ZenPackException;
import com.ZenPack.model.ZenPack;
import com.ZenPack.repository.ZenPackRepository;
import com.ZenPack.service.Services.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import io.swagger.models.Model;
import lombok.extern.slf4j.Slf4j;

import org.json.JSONArray;
import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import springfox.documentation.spring.web.json.Json;

import javax.persistence.EntityManager;
import java.io.StringReader;
import java.lang.reflect.Type;
import java.util.*;

@Service
@Slf4j
public class ZenPackServiceImpl implements ZenPackService {


    @Autowired
    private ZenPackRepository repository;

    @Autowired
    private EntityManager manager;

    private static final Logger logger=LoggerFactory.getLogger(ZenPackServiceImpl.class);

    @Override
    public ZenPackDto createZenPack(ZenPackDto zenPackDto) throws ZenPackException {
        Optional<ZenPack> zenPack1=repository.findByName(zenPackDto.getName());
        if (!zenPack1.isPresent()) {
            ModelMapper mapper = new ModelMapper();
            mapper.getConfiguration().setAmbiguityIgnored(true);
            ZenPack zenPack = mapper.map(zenPackDto, ZenPack.class);
            if (zenPackDto.getFeatures() != null) {
                String menuJson1 = new Gson().toJson(zenPackDto.getFeatures());
                zenPack.setJsonData(menuJson1);
            } else if (zenPackDto.getMenus() != null) {
                String menuJson = new Gson().toJson(zenPackDto.getMenus());
                zenPack.setJsonData(menuJson);
            }
            zenPack.setCreatedDate(new Date());
            zenPack.setUpdatedTime(new Date());
            repository.save(zenPack);
            zenPackDto.setZenPackId(zenPack.getZenPackId());
            zenPackDto.setCreatedDate(zenPack.getCreatedDate());
            zenPackDto.setUpdatedTime(zenPack.getUpdatedTime());
            return zenPackDto;
        }else{
            throw new ZenPackException(HttpStatus.BAD_REQUEST,"ZenPackName Already Exist");
        }
    }
    @Override
    public List<ZenPackDto> getAllZenPack() throws JsonProcessingException {

        List<ZenPack> zenPacks = repository.findAll();
        List<ZenPackDto> zenPackDtos=new ArrayList<>();
        for (ZenPack zenpack:zenPacks) {
            Gson gson=new Gson();
            ModelMapper mapper=new ModelMapper();
            mapper.getConfiguration().setAmbiguityIgnored(true);
            ZenPackDto dto=mapper.map(zenpack,ZenPackDto.class);
            dto.setZenPackId(zenpack.getZenPackId());
            dto.setName(zenpack.getName());
            dto.setZenPackId(zenpack.getZenPackId());
            JsonReader reader = new JsonReader(new StringReader(zenpack.getJsonData()));
            reader.setLenient(true);
            MenuDto[] userinfo1 = gson.fromJson(reader, MenuDto[].class);
            ArrayList<MenuDto> list = new ArrayList(Arrays.asList(userinfo1));
            dto.setMenus(list);
            zenPackDtos.add(dto);
        }
        return zenPackDtos;
    }

    @Override
    public String deleteByzenPackId(Long zenPackId) {
        repository.deleteByZenPackId(zenPackId);
        return  " Id "+zenPackId+" Deleted SuccessFully";
    }

    @Override
    public ZenPackDto getByZenPackId(Long zenPackId) {
        Optional<ZenPack> zenPack= repository.findByZenPackId(zenPackId);
        Gson gson=new Gson();
        ModelMapper mapper=new ModelMapper();
        mapper.getConfiguration().setAmbiguityIgnored(true);
        ZenPackDto dto=mapper.map(zenPack,ZenPackDto.class);
        dto.setZenPackId(zenPack.get().getZenPackId());
        dto.setName(zenPack.get().getName());
        JsonReader reader = new JsonReader(new StringReader(zenPack.get().getJsonData()));
        reader.setLenient(true);
        MenuDto[] userinfo1 = gson.fromJson(reader, MenuDto[].class);
        ArrayList<MenuDto> list = new ArrayList(Arrays.asList(userinfo1));
        dto.setMenus(list);
        return dto;
    }
}