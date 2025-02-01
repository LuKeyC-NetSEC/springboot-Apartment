package com.lyc.lease.model.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;


public enum ItemType implements BaseEnum {

    APARTMENT(1, "公寓"),

    ROOM(2, "房间");

    //MybatisPlus TypeHandler 使code和枚举实例完成映射 1 <-> APARTMENT
    //http参数和枚举类型之间的转换
    @EnumValue
    //Spring Mvc HttpMessageConverter 使code和枚举实例完成映射 1 <-> APARTMENT
    @JsonValue
    private Integer code;
    private String name;

    @Override
    public Integer getCode() {
        return this.code;
    }


    @Override
    public String getName() {
        return name;
    }

    ItemType(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

}
