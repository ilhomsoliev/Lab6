package org.example.model;

import java.io.Serializable;

/**
 * Enum class given in task
 * @author: ilhom_soliev
 */
public enum VenueType implements Serializable {
    PUB,
    OPEN_AREA,
    STADIUM;

    public static String names() {
        StringBuilder nameList = new StringBuilder();
        for (var weaponType : values()) {
            nameList.append(weaponType.name()).append(", ");
        }
        return nameList.substring(0, nameList.length() - 2);
    }
}
