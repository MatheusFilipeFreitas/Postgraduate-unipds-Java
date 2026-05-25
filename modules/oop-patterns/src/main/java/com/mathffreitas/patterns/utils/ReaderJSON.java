package com.mathffreitas.patterns.utils;

import com.mathffreitas.patterns.utils.implementations.AbstractReader;
import com.mathffreitas.patterns.utils.mapper.IMapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReaderJSON<T> extends AbstractReader<T> {

    @Override
    protected List<T> parse(String[] lines, IMapper<T> mapper) {
        List<T> list = new ArrayList<>();
        for (String line : lines) {
            String[] values = line.split("\\s*,\\s*");
            String[] normalizedValues = normalizeJsonValues(values);
            list.add(mapper.parse(normalizedValues));
        }
        return list;
    }

    private String[] normalizeJsonValues(String[] values) {
        return Arrays.stream(values)
                .map(v -> v.replaceAll("[\\[\\]{}\"]", "").trim())
                .map(v -> {
                    int index = v.indexOf(':');
                    return index >= 0 ? v.substring(index + 1).trim() : "";
                })
                .toArray(String[]::new);
    }
}
