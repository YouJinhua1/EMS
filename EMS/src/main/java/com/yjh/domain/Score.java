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
public class Score {
    private Integer id;

    private Integer courseId;

    private Integer studentId;

    private Integer grade;

    private Date updatetime;

    private Integer teacherId;

    
}