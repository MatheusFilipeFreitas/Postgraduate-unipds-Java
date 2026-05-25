package com.mathffreitas.integration.models;

import com.mathffreitas.integration.models.types.HistoryAction;

public class HistoryContext<T> {
    private T data;
    private HistoryAction action;

    public HistoryContext(T data, HistoryAction action) {
        this.data = data;
        this.action = action;
    }

    public T getData() {
        return data;
    }
}
