package com.yjh.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Major {
    private Integer id;

    private String name;

    private Integer sdeptId;

    private Integer leaderId;

    private String description;
    
    private List<Team> teamList;

    
}