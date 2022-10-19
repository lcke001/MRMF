package com.bs.mrmf.pojo;

import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Data
public class customer {
    private String  id;
    private String name;
    private String gender;
    private String phone;
    private String address;
    private BigDecimal money;
    private String level;
    private BigDecimal expenditure;
    private String birthday;
    private String log;
    private String createdDate;
    private String state;
    private String root;
    private String email;
}
