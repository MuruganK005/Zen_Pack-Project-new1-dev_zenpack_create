package com.ZenPack.Dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FeatureDto {

    private Integer id;
    private String featureId;
    private String text;
    private String icon;
    private Boolean isSettingMenu;
    private String featureUrl;

}
