package com.mathffreitas.patterns.utils;

import com.mathffreitas.patterns.utils.implementations.AbstractReader;
import com.mathffreitas.patterns.utils.mapper.IMapper;
import java.util.ArrayList;
import java.util.List;

public class ReaderCSV<T> extends AbstractReader<T> {

    @Override
    protected List<T> parse(String[] lines, IMapper<T> mapper) {
        List<T> list = new ArrayList<>();
        for (String line : lines) {
            String[] values = line.split("\\s*[,;]\\s*");
            list.add(mapper.parse(values));
        }
        return list;
    }
}