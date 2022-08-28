package com.cloudogu.handler;

public interface Bus<A extends CanBeHandled> {
  /**
   * Executes an action.
   *
   * @param action the action to be executed
   * @return return value
   * @param <R> type of the return value
   */
  <R> R execute(A action);
}
