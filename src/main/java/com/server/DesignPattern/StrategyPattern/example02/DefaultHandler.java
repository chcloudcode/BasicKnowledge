package com.server.DesignPattern.StrategyPattern.example02;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DefaultHandler extends BaseHandler {
    @Override
    public void execute() {

    }

    @Override
    public List<AsyncTaskTypeEnum> supportTaskType() {
        return Lists.newArrayList();
    }
}
