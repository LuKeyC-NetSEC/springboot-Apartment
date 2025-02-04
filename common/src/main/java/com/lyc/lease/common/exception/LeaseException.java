package com.lyc.lease.common.exception;

import com.lyc.lease.common.result.ResultCodeEnum;
import lombok.Data;

/**
 * @ClassName LeaseException
 * @Description TODO 自定义异常类
 * @Author LuKey_C
 * @Date 2025/2/4 9:22
 * @Version 1.0
 */
@Data
public class LeaseException extends RuntimeException{

    private Integer code;

    public LeaseException(Integer code,String msg){
        super(msg);
        this.code = code;
    }

    public LeaseException(ResultCodeEnum resultCodeEnum){
        super(resultCodeEnum.getMessage());
        this.code= resultCodeEnum.getCode();
    }
}
