package com.ZenPack.Dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MenuDto {

    private String menuName;
    private Long createdTime;
    private String createdBy;
    private Integer parentMenuId;
    private List<FeatureDto> features;

}
