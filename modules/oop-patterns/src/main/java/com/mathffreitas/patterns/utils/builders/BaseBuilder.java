package com.mathffreitas.patterns.utils.builders;

public class BaseBuilder<T> implements IBuilder<T> {
    protected T instance;

    @Override
    public void validate() {

    }

    @Override
    public T build() {
        return null;
    }
}
