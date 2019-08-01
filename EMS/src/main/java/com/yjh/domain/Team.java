package com.yjh.domain;

import java.util.Date;

import com.yjh.domain.SysRole.SysRoleBuilder;

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
public class Team {
    private Integer id;

    private String name;

    private Integer number=0;

    private Integer majorId;

    private Integer teacherId;

    private String grade;

    
}