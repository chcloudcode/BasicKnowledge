package com.server.DesignPattern.StrategyPattern.example02;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 执行消息推送的异步任务
 */
@Component
public class SendMessageTaskHandler extends BaseHandler {
        @Override
        public void execute() {
            System.out.println("执行消息推送的异步任务！");
        }

    @Override
    public List<AsyncTaskTypeEnum> supportTaskType() {
        return Lists.newArrayList(AsyncTaskTypeEnum.SEND_MESSAGE);
    }
}
