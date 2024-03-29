package org.example.model;


import org.example.utility.Validatable;

import java.util.Objects;
/**
 * Class given in task
 * @author: ilhom_soliev
 */
public class Coordinates implements Validatable {
    private Long x; //Поле не может быть null
    private Double y; //Поле не может быть null

    public Coordinates(Long x, Double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Валидирует правильность полей.
     *
     * @return true, если все верно, иначе false
     */
    @Override
    public boolean validate() {
        if (x == null) return false;
        return y != null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return Objects.equals(x, that.x) && Objects.equals(y, that.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
