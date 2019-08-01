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
public class Course {
    private Integer id;

    private String courseno;

    private Integer majorId;

    private String name;

    private Integer credit;

    private String period;

    private Date starttime;

    private Date endtime;

    private String classroom;

    private String arrange;

    private Integer teacherId;

}