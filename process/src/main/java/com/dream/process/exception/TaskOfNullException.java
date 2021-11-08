package com.dream.process.exception;

public class TaskOfNullException extends  Exception {
    public TaskOfNullException(String message) {
        super(message);
    }
    public TaskOfNullException() {
        super("任务已完成或者不存在");
    }
}
