17. Which of the following statement about resource bundles are correct?
    (Choose all that apply.)

Answers: D and F

- Option A is incorrect because Java will look at parent bundles if a key is not found in a specified resource bundle.
- Option B is incorrect because resource bundles are loaded from static factory method.
- In fact, ResourceBundle is an abstract class, so calling that constructor is not even possible.
- Option C is incorrect, as resource bundle values are read from the ResourceBundle object directly.
- Option D is correct because the locale is changed only in memory.
- Option E is incorrect, as the resource bundle for the default locale may be used if there is no resource bundle for 
  the specified locale (or its locale without a country code).
- Finally, option F is correct. The JVM will set a default locale automatically, making it possible to use a resource 
  bundle for a locale, even if a locale was not explicitly set.