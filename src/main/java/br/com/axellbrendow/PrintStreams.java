package br.com.axellbrendow;

import java.util.stream.IntStream;

public class PrintStreams
{
    public static void run()
    {
        System.out.println("\n# intStream");
        IntStream.of(1, 2, 3).forEach(System.out::println);
    }
}
