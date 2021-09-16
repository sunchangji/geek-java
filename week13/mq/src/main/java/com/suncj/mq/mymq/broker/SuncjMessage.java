package com.suncj.mq.mymq.broker;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;

/**
 * @Classname SunCjMessage
 * @Description TODO
 * @Date 2021/9/16 11:49 上午
 * @Created by sunchangji
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SuncjMessage {

    private HashMap<String,Object> headers;

    private Object body;
}
