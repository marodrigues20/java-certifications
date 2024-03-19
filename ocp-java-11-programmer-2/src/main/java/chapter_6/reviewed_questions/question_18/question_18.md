18. Suppose you have separate modules for a service provider interface, service provider, service locator, and 
    consumer. Which statement are true about the directives you need to specify? (Choose all that apply.)

A. The consumer must use the requires directive.
B. The consumer must use the uses directive.
C. The service locator must use the requires directive.
D. The service locator must use the uses directive.

---
### Explanation ###

- Correct Answer: A; C; D

- Option A and C are correct because both consumer and the service locator depend on the service provider interface.
- Additionally, option D is correct because the service locator must specify that it uses the service provider interface
  to look it up.
---