package org.example.blanks;


import org.example.exceptions.IncorrectInputInScriptException;
import org.example.exceptions.InvalidFormException;

/**
 * Абстрактный класс формы для ввода пользовательских данных.
 * @param <T> создаваемый объект
 * @author ilhom_soliev
 */
public abstract class Form<T> {
  public abstract T build() throws IncorrectInputInScriptException, InvalidFormException;
}
