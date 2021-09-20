package br.com.axellbrendow;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Flowable;

public class PrintReactive
{
    public static void run() throws InterruptedException
    {
        System.out.println("\n# flowable");
        Flowable.interval(1, 1, TimeUnit.SECONDS)
            .map(n -> n * 2)
            .subscribe(System.out::println);
        TimeUnit.SECONDS.sleep(3);
    }
}
