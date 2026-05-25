package com.mathffreitas.patterns.utils.factory;

import com.mathffreitas.patterns.models.types.FileType;
import com.mathffreitas.patterns.utils.IReader;
import com.mathffreitas.patterns.utils.ReaderCSV;
import com.mathffreitas.patterns.utils.ReaderJSON;

public class ReaderFactory<T> {
    public IReader<T> create(FileType type) throws IllegalArgumentException {
        return switch(type) {
            case CSV -> new ReaderCSV<T>();
            case JSON -> new ReaderJSON<T>();
            default -> throw new IllegalArgumentException("Invalid file type");
        };
    }
}

