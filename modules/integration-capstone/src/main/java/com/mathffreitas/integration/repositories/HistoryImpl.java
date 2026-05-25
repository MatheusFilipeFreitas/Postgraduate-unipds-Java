package com.mathffreitas.integration.repositories;

import com.mathffreitas.integration.models.HistoryContext;
import com.mathffreitas.integration.models.ProductExhibition;
import com.mathffreitas.integration.models.types.HistoryAction;

import java.lang.IO;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.WeakHashMap;

import java.util.HashMap;
import java.time.LocalDate;
public class HistoryImpl<T> implements History<T> {
    private final Map<HistoryContext<T>, LocalDateTime> histories = new WeakHashMap<>();

    public HistoryImpl() {

    }

    @Override
    public void registerGetData(T record) {
        this.registerData(record, HistoryAction.GET);
    }

    @Override
    public void vizualizeHistory() {
        histories.forEach((key, value) -> {
            T data = key.getData();
            ProductExhibition product = (ProductExhibition) data;
            IO.println(product.toString() + " - " + value.toString());
        });
    }

    @Override
    public int getTotalRecords() {
        return histories.size();
    }

    private void registerData(T record, HistoryAction action) {
        histories.put(new HistoryContext<T>(record, action), LocalDateTime.now());
    }

}
