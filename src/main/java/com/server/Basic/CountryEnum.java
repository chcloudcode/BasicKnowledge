package com.server.Basic;

import lombok.Getter;

public enum  CountryEnum {

    ONE(1,"齐"),TWO(2,"楚"),THREE(3,"燕"),FOUR(4,"韩"),FIVE(5,"赵"),SIX(6,"魏"),SEVEN(7,"秦");
    @Getter private Integer code;
    @Getter private String name;

    CountryEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public CountryEnum search(Integer index){
        CountryEnum[] values = CountryEnum.values();
        for(CountryEnum each :values){
            if(each.getCode() == index){
                return each;
            }
        }
        return null;
    }
}
