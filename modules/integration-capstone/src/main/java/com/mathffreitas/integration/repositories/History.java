package com.mathffreitas.integration.repositories;

public interface History<T> {
    void registerGetData(T record);
    void vizualizeHistory();
    int getTotalRecords();
}
