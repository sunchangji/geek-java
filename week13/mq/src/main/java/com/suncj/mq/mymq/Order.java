package com.suncj.mq.mymq;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Classname Order
 * @Description TODO
 * @Date 2021/9/16 4:53 下午
 * @Created by sunchangji
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Order {
    private Long id;
    private Long ts;
    private String symbol;
    private Double price;
}
