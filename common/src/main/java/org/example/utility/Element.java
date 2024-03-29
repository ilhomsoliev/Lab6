package org.example.utility;

import java.io.Serializable;

public abstract class Element implements Comparable<Element>, Validatable, Serializable {

  abstract public int getId();
}
