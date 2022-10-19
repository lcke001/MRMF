package com.bs.mrmf.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Data
public class item {
    @TableId(value="id",type= IdType.AUTO)
    private Integer id;
    private String name;
    private BigDecimal price;
    private String state;
    private String root;
    private String createdate;
}
