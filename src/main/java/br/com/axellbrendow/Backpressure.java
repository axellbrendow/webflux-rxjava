package br.com.axellbrendow;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import io.reactivex.rxjava3.core.BackpressureStrategy;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableEmitter;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class Backpressure
{
    private static void emit(FlowableEmitter<Integer> emitter)
    {
        IntStream.rangeClosed(1, 10)
            .forEach(n -> {
                System.out.println("Emitting" + n);
                emitter.onNext(n);
                sleep(500);
            });
        emitter.onComplete();
    }

    private static void process(Integer number)
    {
        System.out.println("Processing" + number);
        sleep(1000);
    }

    private static void sleep(Integer millis)
    {
        try {
            TimeUnit.MILLISECONDS.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void run()
    {
        System.out.println("\n# backpressure");
        Flowable.create(
            Backpressure::emit,
            BackpressureStrategy.BUFFER // Stores the messages when the processor is busy
            // BackpressureStrategy.DROP // Discard last message if the processor is busy
            // BackpressureStrategy.ERROR // Throw an exception when the processor is busy
            // BackpressureStrategy.LATEST // Keep only the last message in the buffer
            // BackpressureStrategy.MISSING // Messages are passed without buffering or dropping, you handle it!
        )
        .observeOn(Schedulers.computation(), true, 2)
        .subscribe(Backpressure::process);
        sleep(10000);
    }
}
