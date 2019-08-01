package com.yjh.domain;

import java.util.Date;


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
public class SysRolePerm {
    private Integer id;

    private Integer roleId;

    private Integer permId;

    private String operator;

    private Date operateTime;

    private String operateIp;

}