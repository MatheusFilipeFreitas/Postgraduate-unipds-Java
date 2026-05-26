package com.mathffreitas.integration.models;

import java.math.BigDecimal;

public class Pix {
    private Long id;
    private BigDecimal price;
    private String destinyKey;

    public Pix(Long id, BigDecimal price, String destinyKey) {
        this.id = id;
        this.price = price;
        this.destinyKey = destinyKey;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDestinyKey() {
        return destinyKey;
    }

    public void setDestinyKey(String destinyKey) {
        this.destinyKey = destinyKey;
    }
}
