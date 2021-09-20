package br.com.axellbrendow;

public class App 
{
    public static void main( String[] args ) throws InterruptedException
    {
        PrintReactive.run();
        PrintStreams.run();
        PrintReactiveErrorFlow.run();
        Backpressure.run();
    }
}
