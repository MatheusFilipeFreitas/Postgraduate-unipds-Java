package com.mathffreitas.serialization;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

public class Pix implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    // Controls the version of serialization, if the serialized object is in other version
    // this will throw an error! ex: code 2L -> serialized object 1L (incompatible versions)

    private long id;
    private BigDecimal value;
    private String receiver;

    public Pix(long id, BigDecimal value, String receiver) {
        this.id = id;
        this.value = value;
        this.receiver = receiver;
    }

    @Override
    public String toString() {
        return "Pix{" +
                "id=" + id +
                ", value=" + value +
                ", receiver='" + receiver + '\'' +
                '}';
    }
}
