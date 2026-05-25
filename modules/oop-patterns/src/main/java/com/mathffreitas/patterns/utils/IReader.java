package com.mathffreitas.patterns.utils;

import com.mathffreitas.patterns.utils.mapper.IMapper;

import java.io.IOException;
import java.util.List;

public interface IReader<T> {
    List<T> read(IMapper<T> mapper) throws IOException;
}
