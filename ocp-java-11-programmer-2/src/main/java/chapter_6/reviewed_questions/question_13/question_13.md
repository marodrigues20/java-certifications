13. Suppose you have separate modules for a service provider interface, service provider, service locator, and consumer.
    Which are true about the directives you need to specify? (Choose all that apply.)

A. The service provider interface must use the *exports* directive. <br>
B. The service provider interface must use the *provides* directive. <br>
C. The service provider interface must use the *requires* directive. <br>
D. The service provider must use the *export* directive. <br>
E. The service provider must use the *provides* directive. <br>
F. The service provider must use the *requires* directive. <br>


---
### Explanation ###

- Correct Answer: A; E; F

- Option A is correct because the service provider interfaces must specify *exports* for any other modules to reference it.
- Option F is correct because the service provider needs access to the service provider interface.
- Option E is also correct because provider needs to declare that it provides the service.
---