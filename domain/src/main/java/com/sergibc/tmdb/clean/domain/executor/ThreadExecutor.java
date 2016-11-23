package com.sergibc.tmdb.clean.domain.executor;

import com.sergibc.tmdb.clean.domain.interactor.UseCase;

import java.util.concurrent.Executor;

/**
 * Executor implementation can be based on different frameworks or techniques of asynchronous execution, but every implementation
 * will execute the TODO modify link {@link UseCase} out of the UI thread.
 */
public interface ThreadExecutor extends Executor {
}