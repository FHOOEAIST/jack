# Java AIST Class Kit (JACK)

JACK is our internal multi purpose Java framework, which consists of multiple, reusable utility classes.
The main goal of this framework is to avoid code duplication in all our project. Therefore, JACK provides a simple way to have a code basis across project boundaries.

## Content

Currently, JACK consists of the following packages:

- **Data:** The data package consists of data wrappers like pair or a LambdaContainer (for manipulating non-final objects in a lambda).
- **Exception:** Package containing different exception utility functionalty e.g. methods for avoiding checked exceptions checking
- **General:** The general package consists of miscellaneous classes and interfaces. Amongst other things it contains the `Transformer<FROM, TO>` interfaces, the `PropertyMapperCreator` and a `CopyUtil` for deep object copies.
- **Math:** The math package consists of different extensions to `java.lang.Math` as double comparision or other mathematical/number stuff. 
- **Persistence:** The persistence package consists of different persistence functionality.
  - **Core:** The persistence core package consists of interfaces/classes which are reused in the different persistence implementation packages.
  - **Filesystem:** The filesystem package consists of `FileStorage` classes which persist any serializable class into the TEMP folder of your local OS. And also a `FileUtils` class which provides functionality to write an InputStream to the TEMP folder of your local OS (this is useful if you have to make Resource files accessible for e.g. wrapped C++ libraries as TensorFlow or OpenCV). In addition to that you can also find `CSVProcessor` and `ReflectionCSVProcessor`, that allow you to read and write domain classes to CSV file.
- **Random:** The random package consists of extensions for `java.util.Random` like `inRange()` number generation. 
- **Reflection:** Contains different utility functionality for reflection based code like extracting all fields of a class containing also the super classes' fields.
- **Stream:** The stream package consists of extensions for `java.lang.Stream` like different mapping methods.
- **String:** Contains useful utility classes for operating with strings

## Contribution
If you want to add your own stuff, first check if there is already a package in which it fits in. If not, then create a new package,
and make sure, that you add it as dependency to the all package. Never add code to the all package, this should only have a single pom file,
and nothing else. Do not add dependencies to other frameworks like Spring. Also make sure, when you add a new package
that the order in the base package fits (e.g. all package has always to be the last package to be built).

If you add a new class in an already existing package make sure, you add that class to the corresponding package-info.java file. If you create a new package, make sure to create a new package-info.java file and add a short description for the package + for every class in that package.