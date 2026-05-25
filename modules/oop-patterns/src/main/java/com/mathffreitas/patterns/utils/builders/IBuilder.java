package com.mathffreitas.patterns.utils.builders;

public interface IBuilder<T> {
    void validate();
    T build();
}
