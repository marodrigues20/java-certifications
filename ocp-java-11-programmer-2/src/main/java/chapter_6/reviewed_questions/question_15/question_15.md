15. Suppose you have separate modules for a service provider interface, service provider, service locator, and 
    consumer. Which module(s) need to specify a *requires* directive on the service provider?

A. Service locator
B. Service provider interface
C. Consumer
D. Consumer and service locator
E. Consumer and service provider
F. Service locator and service provider interface
G. Consumer, service locator, and service provider interface
H. None of the above





---
### Explanation ###

- Correct Answer:  H

- This question is tricky.
- The service provider must have a *uses* directive, but that is on the service provider interface.
- No modules need to specify *requires* on the service provider since that is the implementation.
---