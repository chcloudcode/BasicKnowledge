package com.server.DesignPattern.StrategyPattern.example02;

import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;


/**
 * 异步任务 handler 工厂类
 */
@Component
public class AsyncTaskHandlerFactory implements ApplicationContextAware {

    private Map<AsyncTaskTypeEnum,BaseHandler> baseHandlerMap = Maps.newHashMap();

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    DefaultHandler defaultHandler;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext=applicationContext;
    }

    @PostConstruct
    public void init(){
        //获取 所有 的 异步任务 Handler
        Map<String,BaseHandler> handlerMap = applicationContext.getBeansOfType(BaseHandler.class);
        if(MapUtils.isNotEmpty(handlerMap)){
            for(BaseHandler baseHandler: handlerMap.values()){
                List<AsyncTaskTypeEnum> supportTaskType = baseHandler.supportTaskType();
                if(CollectionUtils.isNotEmpty(supportTaskType)){
                    supportTaskType.stream().forEach(asyncTaskTypeEnum -> baseHandlerMap.put(asyncTaskTypeEnum,baseHandler));
                }
            }
        }
    }

    /**
     * 根据 任务类型代码 获取任务
     */
    public BaseHandler getTaskByCode(String tastTypeCode){
        AsyncTaskTypeEnum taskTypeEnum = AsyncTaskTypeEnum.getByCode(tastTypeCode);
        BaseHandler baseHandler = baseHandlerMap.get(taskTypeEnum);
        if(baseHandler != null){
            return baseHandler;
        }
        return defaultHandler;
    }



}
