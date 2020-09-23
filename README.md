# JACK - Java Aist Class Kit

JACK is a set of different utility and helper classes with the focus to reduce code duplication by providing useful 
additions to the java core classes.

## Introduction

JACK provides base functionality that we think should be included in vanilla Java itself. This allows you to reduce some 
common code classes and focus on the more interesting code bits.  

For more information on what jack is capable of, please check out our [examples section](https://fhooeaist.github.io/jack/examples.html) 
and take a look at the unit tests.

## Getting Started

JACK is a single module java maven project based on Java 11. If you want to use code of this project we recommend adding 
a dependency based on your dependency management tooling. For further help please check out the following page: 
[Dependency Information](https://fhooeaist.github.io/jack/dependency-info.html)

Example for Apache Maven:

```xml
<dependency>
  <groupId>science.aist</groupId>
  <artifactId>jack</artifactId>
  <version>2.0.0</version>
</dependency>
```

If you want to build the project yourself, just checkout this git repo, make sure you have jdk 11 or above installed as
well as maven 3.6.0 or above and build the project by running the maven command: `mvn package`. This results in a 
jar-file inside the target folder, which can be used as a dependency in other projects.

## FAQ

If you have any questions, please checkout our [FAQ](https://fhooeaist.github.io/jack/faq.html) section.

## Contributing

**First make sure to read our [general contribution guidelines](https://fhooeaist.github.io/CONTRIBUTING.html).**

In addition to that, the following applies to this repository:
 - If you want to check in your code, first ensure if there is a package, that fits the logic of your code. Only if this 
 does not apply, create a new one.
 - Do not add dependencies to other frameworks like Spring (Test-Dependencies are ok though). 

## Contact

If you have any further requests please do contact us via email: [contact@aist.science](mailto:contact@aist.science).

We ask you not to contact any author directly, as we are delegating maintenance of this repository internally.

Please be aware of the fact, that we are a non-profit research organisation and do not have the resources to answer
your questions right away. Nevertheless, we are committed **to processes your request within a few workdays**. 

## Licence

Copyright (c) 2020 the original author or authors.
DO NOT ALTER OR REMOVE COPYRIGHT NOTICES.

This Source Code Form is subject to the terms of the Mozilla Public
License, v. 2.0. If a copy of the MPL was not distributed with this
file, You can obtain one at https://mozilla.org/MPL/2.0/.

## Research

If you are going to use this project as part of a research paper, we would ask you to reference this project by citing
it. DOI: TBD