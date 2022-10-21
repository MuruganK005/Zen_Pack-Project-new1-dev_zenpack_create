package com.ZenPack.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.gson.JsonObject;
import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import springfox.documentation.spring.web.json.Json;

import javax.persistence.*;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "zen_pack")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class ZenPack {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "zen_pack_id")
    private Long zenPackId;

    @Column(name = "zen_pack_name")
    private String name;

    @Column(name = "json_data",length = 50000)
    private String jsonData;

    @Column(name = "updated_time")
    private Date updatedTime= new Date();

    @Column(name = "created_date")
    private Date createdDate =new Date();

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_by")
    private String updatedBy;

}