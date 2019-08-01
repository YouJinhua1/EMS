package com.yjh.domain;

import java.util.Date;

import com.yjh.domain.Sdept.SdeptBuilder;

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
public class SysPerm {
    private Integer id;

    private String name;

    private Integer parentId;

    private String level;

    private Integer type;

    private String code="#";

    private String url="#";

    private Integer status=1;

    private Integer seq;

    private String remark;

    private String operator;

    private Date operateTime;

    private String operateIp;
}