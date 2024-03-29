package org.example.commands;


import org.example.network_models.request.Request;
import org.example.network_models.response.Response;

/**
 * Интерфейс для всех выполняемых команд.
 * @author ilhom
 */
public interface Executable {
  /**
   * Выполнить что-либо.
   * @param request запрос с данными для выполнения команды
   * @return результат выполнения
   */
  Response apply(Request request);
}
