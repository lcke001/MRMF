package com.bs.mrmf.pojo;

import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Data
public class orders {
    private String orderId;
    private String item;
    private String customer;
    private Double money;
    private String date;
    private String root;
}
