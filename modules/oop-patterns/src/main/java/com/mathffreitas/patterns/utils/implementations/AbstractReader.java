package com.mathffreitas.patterns.utils.implementations;

import com.mathffreitas.patterns.utils.IReader;
import com.mathffreitas.patterns.utils.mapper.IMapper;

import java.lang.IO;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import java.util.stream.Stream;
public abstract class AbstractReader<T> implements IReader<T> {
    @Override
    public final List<T> read(IMapper<T> mapper) throws IOException {
        String path = IO.readln("Enter file path: ");
        InputStream input = getClass().getClassLoader()
                .getResourceAsStream(path);

        String content = new String(input.readAllBytes(), StandardCharsets.UTF_8);
        String[] lines = content.split("\n");
        return parse(lines, mapper);
    }

    protected abstract List<T> parse(String[] lines, IMapper<T> mapper);
}