package com.sergibc.tmdb.clean.domain.interactor;

import com.sergibc.tmdb.clean.domain.executor.PostExecutionThread;
import com.sergibc.tmdb.clean.domain.executor.ThreadExecutor;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;

/**
 * Abstract class for a Use Case (Interactor in terms of Clean Architecture).
 * This interface represents a execution unit for different use cases (this means any use case in the application should
 * implement
 * this contract).
 * <p/>
 * By convention each UseCase implementation will return the result using a {@link Subscriber} that will execute its job in a
 * background thread and will post the result in the UI thread.
 */
public abstract class UseCase {

    private final ThreadExecutor threadExecutor;

    private final PostExecutionThread postExecutionThread;

    private Subscription subscription = Subscriptions.empty();

    protected UseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        this.threadExecutor = threadExecutor;
        this.postExecutionThread = postExecutionThread;
    }

    /**
     * Builds an {@link Observable} which will be used when executing the current {@link UseCase}.
     */
    protected abstract Observable buildUseCaseObservable();

    /**
     * Executes the current use case.
     *
     * @param useCaseSubscriber The guy who will be listen to the observable build with {@link #buildUseCaseObservable()}.
     */
    @SuppressWarnings("unchecked")
    public void execute(Subscriber useCaseSubscriber) {
        this.subscription = this.buildUseCaseObservable()
                .subscribeOn(Schedulers.from(threadExecutor))   // executes the task in a new
                        // Scheduler. In this case from a
                        // ThreadExecutor.
                .observeOn(postExecutionThread.getScheduler())  // method will provide the result
                        // on a specific Scheduler: the
                        // postExecutionThread in our example.
                .subscribe(useCaseSubscriber);
    }

    /**
     * Unsubscribes from current {@link Subscription}.
     */
    public void unsubscribe() {
        if (!subscription.isUnsubscribed()) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        subscription.unsubscribe();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            thread.start();
        }
    }
}