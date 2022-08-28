package com.cloudogu.handler;

import javax.inject.Provider;

/**
 * Base class for handler providers
 *
 * @param <H> type of handler
 */
public abstract class HandlerProvider<H extends Handler<?, ?>> implements Provider<H> {

  protected final Class<H> type;

  protected HandlerProvider(Class<H> type) {
    this.type = type;
  }
}
