package com.server.DesignPattern.StrategyPattern.example02;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 同步数据到 ES 引擎
 */
@Component
public class AsyncDataToEsTaskHandler extends BaseHandler {
    @Override
    public void execute() {
        System.out.println("执行同步数据到ES的异步任务！");
    }

    @Override
    public List<AsyncTaskTypeEnum> supportTaskType() {
        return Lists.newArrayList(AsyncTaskTypeEnum.ASYNC_DATA_TO_ES);
    }
}
