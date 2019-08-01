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
public class Sdept {
    private Integer id;

    private String name;

    private Integer leaderId;

    private String phone;
    
    private List<Major> majorList;

    
}