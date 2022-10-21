package com.ZenPack.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MenuDto {

    private String menuName;
    private Date createdTime;
    private String createdBy;
    private Integer parentMenuId;
    private List<FeatureDto> features;

}
