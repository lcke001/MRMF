package com.bs.mrmf.pojo;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Data
public class administrator {
    private Integer id;
    private String account;
    private String password;
    private String name;
    private String rootTag;
}
