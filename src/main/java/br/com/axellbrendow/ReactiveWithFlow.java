package br.com.axellbrendow;

import java.util.concurrent.Flow;
import java.util.concurrent.Flow.Subscription;
import java.util.concurrent.SubmissionPublisher;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import java.util.stream.IntStream;

public class ReactiveWithFlow
{
    private static Logger logger = Logger.getAnonymousLogger();

    public static void run()
    {
        System.out.println("\n# reactive with flow");

        CustomSubscriber subscriber = new CustomSubscriber();

        try (var publisher = new SubmissionPublisher<Integer>())
        {
            publisher.subscribe(subscriber);
            IntStream.range(1, 10)
                .forEach(i -> {
                    logger.info("Emitting " + i);
                    publisher.submit(i);
                });
            sleep();
        }
    }

    private static void sleep()
    {
        try
        {
            TimeUnit.SECONDS.sleep(10);
        }
        catch (InterruptedException e)
        {
            logger.severe(e.getMessage());
            e.printStackTrace();
        }
    }
}

class CustomSubscriber implements Flow.Subscriber<Integer> {
    private final Logger logger = Logger.getAnonymousLogger();
    private Flow.Subscription subscription;

    @Override
    public void onComplete() {
        logger.info("Done");
    }

    @Override
    public void onError(Throwable throwable) {
        throwable.printStackTrace();
    }

    @Override
    public void onNext(Integer item) {
        logger.info("Receiving " + item);
        this.subscription.request(1);
    }

    @Override
    public void onSubscribe(Subscription subscription) {
        this.subscription = subscription;
        this.subscription.request(5);
    }
}
