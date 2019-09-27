package com.server.DesignPattern.StrategyPattern.example02;

/**
 * 异步任务枚举
 */
public enum AsyncTaskTypeEnum {

    SEND_EMAIL("send_email","发送邮件"),
    SEND_MESSAGE("send_message","推送消息"),
    ASYNC_DATA_TO_ES("async_data_to_es","同步数据到es");

    private String code;

    private String name;

     AsyncTaskTypeEnum(String code,String name ){
        this.code=code;
        this.name=name;
    }

    public static AsyncTaskTypeEnum getByCode(String code){
         for(AsyncTaskTypeEnum taskTypeEnum : AsyncTaskTypeEnum.values()){
             if(taskTypeEnum.code.equals(code)){
                 return taskTypeEnum;
             }
         }
         return null;
    }

}
