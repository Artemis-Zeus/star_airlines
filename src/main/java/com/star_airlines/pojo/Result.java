package com.star_airlines.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Result {
    private Integer code;
    private String msg;
    private Object data;

    public static Result success(String msg,Object data){
        return new Result(1,msg,data);
    }
    public static Result success(Object data){
        return new Result(1,"success",data);
    }
    public static Result error(String msg){
        return new Result(0,msg,null);
    }
}
