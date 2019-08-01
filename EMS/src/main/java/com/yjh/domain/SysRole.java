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
public class SysRole {
    private Integer id;

    private String name;

    private Integer status;

    private String remark;

    private String operator;

    private Date operateTime;

    private String operateIp;

}