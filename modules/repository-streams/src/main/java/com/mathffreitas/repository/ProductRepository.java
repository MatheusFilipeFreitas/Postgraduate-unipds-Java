package com.mathffreitas.repository;

import java.util.Optional;

public interface ProductRepository {
    void init();
    Optional<Product> findById(int id);
}
