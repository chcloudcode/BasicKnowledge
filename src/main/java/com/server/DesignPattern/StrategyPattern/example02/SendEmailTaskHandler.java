package com.server.DesignPattern.StrategyPattern.example02;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 发送邮件的异步任务
 */
@Component
public class SendEmailTaskHandler extends BaseHandler {

        @Override
        public void execute() {
            System.out.println("执行发送邮件的异步任务！");
        }

    @Override
    public List<AsyncTaskTypeEnum> supportTaskType() {
        return Lists.newArrayList(AsyncTaskTypeEnum.SEND_EMAIL);
    }
}
