package com.server.DesignPattern.StrategyPattern.example02;

import java.util.List;

/**
 * 封装 不同异步任务  相同的行为 execute
 */

public abstract class BaseHandler {

    public abstract void execute();

    public abstract List<AsyncTaskTypeEnum> supportTaskType();

}
