package br.com.axellbrendow;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Flowable;

public class PrintReactiveErrorFlow
{
    private static void process(Long number)
    {
        System.out.println("Received number " + number);
    }

    private static Long transform(Long number)
    {
        if (new Random().nextDouble() < 0.3) throw new RuntimeException("Ops!");
        return number * 2;
    }

    private static void dealWithError(Throwable throwable)
    {
        throwable.printStackTrace();
    }

    public static void run() throws InterruptedException
    {
        System.out.println("\n# errorFlow");
        Flowable.interval(1, 1, TimeUnit.SECONDS)
            .map(PrintReactiveErrorFlow::transform)
            .subscribe(PrintReactiveErrorFlow::process, PrintReactiveErrorFlow::dealWithError);
        TimeUnit.SECONDS.sleep(10);
    }
}
