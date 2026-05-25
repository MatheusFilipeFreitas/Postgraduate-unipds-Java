package com.mathffreitas.patterns.utils.mapper;

public interface IMapper<T> {
    T parse(String[] values);
}
