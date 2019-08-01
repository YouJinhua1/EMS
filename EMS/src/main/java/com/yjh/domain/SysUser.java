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
@ToString
public class SysUser {
    private Integer id;

    private String number;

    private String password;

    private String salt;

    private String name;

    private String gender;

    private Integer age;

    private String nation;

    private Date entime;

    private String politicsStatus;

    private String idNumber;

    private Integer sdeptId;

    private Integer majorId;
    
    private Integer teamId;

    public SysUser() {
    	
    }
    
}