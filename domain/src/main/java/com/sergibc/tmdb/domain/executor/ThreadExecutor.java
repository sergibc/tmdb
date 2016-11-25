package com.sergibc.tmdb.domain.executor;

import com.sergibc.tmdb.domain.interactor.Interactor;

import java.util.concurrent.Executor;

/**
 * Executor implementation can be based on different frameworks or techniques of asynchronous execution, but every implementation
 * will execute the {@link Interactor} out of the UI thread.
 */
public interface ThreadExecutor extends Executor {
}