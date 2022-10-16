package com.ZenPack.Dto;

import lombok.Getter;
import lombok.Setter;
import springfox.documentation.spring.web.json.Json;

import java.util.List;


@Getter
@Setter
public class ZenPackDto {

    private Long zenPackId;
    private String name;
    private String createdBy;
    private Long createdDate;
    private String updatedBy;
    private Long updatedTime;
    private List<MenuDto> menus;

}