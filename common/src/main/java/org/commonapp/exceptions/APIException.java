package org.commonapp.exceptions;

/**
 * Выбрасывается, если в ответе сервера ошибка
 * @author ilhom
 */
public class APIException extends Exception {
  public APIException(String message) {
    super(message);
  }
}
