# dio-webflux-rxjava

## How to run the project ?

```sh
mvn exec:java
```

## Mapping operation

You can transform strings into their lengths before submitting them to an observer.

![Mapping data and sending to an Observer](https://miro.medium.com/max/1200/1*AzZ9kfyC41usaJoWoeWdlQ.gif)

## Catch errors in the flow

You can give a function named `onError` that will handle exceptions.

![](https://raw.githubusercontent.com/wiki/ReactiveX/RxJava/images/rx-operators/blockingSubscribe.o.2.png)

## Backpressure

![Fast producer, slow consumer](https://miro.medium.com/max/1400/1*G-yJQ_ururyvMGkGRA3eAw.png)
