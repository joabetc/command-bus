package com.cloudogu.handler;

/**
 * Contract for handlers
 *
 * @param <R> type of returned value
 * @param <A> type of action to be handled
 */
public interface Handler<R, A extends CanBeHandled> {

  /**
   * Handles an action.
   *
   * @param action action to be handled
   * @return optional return value
   */
  R handle(A action);
}
