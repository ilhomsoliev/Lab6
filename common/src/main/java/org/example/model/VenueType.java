package org.example.model;

/**
 * Enum class given in task
 * @author: ilhom_soliev
 */
public enum VenueType {
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
