13. Suppose that the absolute path */weather/winter/snow.dat* represents a file that exists within a file system.
    Which of the following lines of code creates an object that represents the file? (Choose all that apply.)

A. new File("/weather", "winter", "snow.dat")
B. new File("/weather/winter/snow.dat")
C. new File("/weather", "/winter/snow.dat")
D. new File("weather", "/winter/snow.dat")
E. new File(new File("/weather/winter"), "snow.dat")
F. None of the above

Correct Answer: B; E

- Option A does not compile, as there is no File constructor that takes three parameters.
- Option B is correct and is the proper way to create a File instance with a single *String* parameter.
- Option C is incorrect, as there is no constructor that takes a *String* followed by a *File*.
- There is a constructor that takes a File followed by a *String*, making option E correct.
- Option D is incorrect because the first parameter is missing a slash(/) to indicate it is an absolute path.
- Since it's a relative path, it is correct only when the user's current directory is the root directory.

