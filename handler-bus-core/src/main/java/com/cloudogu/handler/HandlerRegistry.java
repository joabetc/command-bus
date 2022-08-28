package com.cloudogu.handler;

import java.util.HashMap;
import java.util.Map;

/**
 * Base handler registry.
 *
 * @param <A> type of action to be registered
 * @param <H> type of handler to be returned by the registered provider
 * @param <P> type of provider to be registered
 */
public abstract class HandlerRegistry<A extends CanBeHandled, H extends Handler<?, A>, P extends HandlerProvider<H>> {

  protected final Map<Class<? extends A>, P> providerMap = new HashMap<>();

  /**
   * Retrieves the right handler based on the provided action.
   *
   * @param actionClass the action class
   * @return the corresponding handler
   */
  public H get(Class<? extends A> actionClass) {
    return providerMap.get(actionClass).get();
  }
}
