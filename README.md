# di-from-scratch

Dependency Injection (DI) is a design pattern that implements the Inversion of Control for resolving the dependencies at run-time.

## Advantages of dependency injection

- Loosely coupled, more reusable code
- Code easily testable with mock implementations
- Reduction of boilderplate
- can be used to externalize system's configuration

DI is a core feature of the Spring framework.

## Disadvantages of dependency injection

Some of the disadvantages of DI are listed [here](https://en.wikipedia.org/wiki/Dependency_injection#Disadvantages). Another disadvantage of DI is that it feels like magic when e.g. learning the Spring framework because it is not really clear what's going on under the hood.

## Understanding dependency injection

[This](https://dev.to/martinhaeusler/understanding-dependency-injection-by-writing-a-di-container-from-scratch-part-1-1hdf) article provides an easy to understand introduction to DI by implementing dependency injection from scratch.

The code in this repository is a Kotlin port of the code from the article.
