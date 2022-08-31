/**
 * MIT License
 *
 * Copyright (c) 2017 Cloudogu GmbH
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.cloudogu.cb.spring;

import com.cloudogu.cb.Command;
import com.cloudogu.cb.CommandHandler;

import com.cloudogu.handler.HandlerRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.GenericTypeResolver;

/**
 * Registry holds the mapping between a command and its handler. The registry should always be injected, by the spring
 * framework.
 */
class Registry extends HandlerRegistry<Command<?>, CommandHandler<?, Command<?>>, CommandProvider<CommandHandler<?, Command<?>>>> {

  @Autowired
  public Registry(ApplicationContext applicationContext) {
    final String[] names = applicationContext.getBeanNamesForType(CommandHandler.class);
    for (final String name : names) {
      register(applicationContext, name);
    }
  }

  @SuppressWarnings("unchecked")
  private void register(ApplicationContext applicationContext, String name){
    final Class<CommandHandler<?,?>> handlerClass = (Class<CommandHandler<?,?>>) applicationContext.getType(name);
    final Class<?>[] generics = GenericTypeResolver.resolveTypeArguments(handlerClass, CommandHandler.class);
    final Class<? extends Command<?>> commandType = (Class<? extends Command<?>>) generics[1];
    providerMap.put(commandType, new CommandProvider(applicationContext, handlerClass));
  }
}
