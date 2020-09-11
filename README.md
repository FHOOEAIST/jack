# JACK - Java Aist Class Kit

JACK is a set of different utility and helper classes with the focus to reduce code duplication by providing useful 
additions to the java core classes.

## Introduction

JACK allows you to create projects and reduce some common code classes. It provides base functionality that should
in our opinion be directly included into the java framework itself.  

For more information on what jack is capable of, please check out our [examples section](https://fhooeaist.github.io/jack/examples.html) 
as well as take a look at the unit tests.

## Getting Started

JACK is a single module java maven project based on Java 11. If you want to use code of this project we recommend you 
to add a dependency based on your dependency management tooling. For further help please check out the following page: 
[Dependency Information](https://fhooeaist.github.io/jack/dependency-info.html)

Example for Apache Maven:

```xml
<dependency>
  <groupId>at.fh.hagenberg.aist.jack</groupId>
  <artifactId>jack</artifactId>
  <version>1.1.0</version>
</dependency>
```

If you want to build the project yourself, just checkout this git repo, make sure you have jdk 11 or above installed as
well as maven 3.6.0 or above and build the project by running the maven command: `mvn package`. This results in a 
jar-file inside the target folder, which can be used as a dependency in other projects.

## FAQ

If you have any questions, please checkout our [FAQ](https://fhooeaist.github.io/jack/faq.html) section.

## Contributing

First make sure to read our [general contribution guidelines](https://fhooeaist.github.io/contributing.html).

In addition to that, the following applies to this repository:
 - If you want to add your own stuff, first check if there is already a package in which it fits in. If not, then create a new one.
 - Do not add dependencies to other frameworks like Spring (Test-Dependencies are ok though). 

## Contact

If you have any further requests please do contact us via email: [z10221@fhooe.at](mailto:z10221@fhooe.at).

We ask you not to contact any author directly, we are getting together periodically to maintain your requests.

Please be aware of the fact, that we are a non-profit research organisation and do not have the resources to answer
your questions right away. Nevertheless, we are committed to processes your request within a few workdays. 

## Licence

TBD

## Research

If you are going to use this project as part of a research paper, we would ask you to reference this project by citing
it. DOI: TBD