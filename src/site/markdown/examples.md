# Examples

## Overcome java limitations on domain objects

### Pair

```java
// simple pair implementation:
Pair<String, Integer> myPair = Pair.of("a", 1);
String first = myPair.getFirst();
String second = myPair.getSecond();
```

## Handling Exceptions

### Get rid of check exceptions

```java
void someIOFunction() throws IOException {}

void someOtherFunction() {
  try {
    someIOFunction();
  } catch(IOException e) {
    // some handling of the excpetion or throwing of an uncheck exception necessary here.
    // or: use ExceptionUtils
    throw ExceptionUtils.unchecked(e); // throws the IOException without the need to declare it to the function header.
  }
}
```

### Stream with check exception

It can be annoying if you have a stream where you want to call a mapping function that declares a checked exception:

```java
MyTypeA mapping(MyTypeB b) throws IOException {}

void myStreamExecusion() {
  List<MyTypeB> list = ...;
  
  // Does not compile because mapping throws an exception
  list.stream().map(this::mapping).collect(Collectors.toList()); 

  // solution with plain java:
  list.stream().map(x -> {
    try {
      return mapping(x);
    } catch(IOException io) {
      ...
    }
  }).collect(Collectors.toList());

  // solution with jack
  list.stream().map(ExceptionUtils.uncheck(this::mapping)).collect(Collectors.toList());
}
```

Why and how is this working?

We provide a few custom implementation of the java.util.function package, e.g. ThrowingFunction, which has the same
signature as the Function class, with the addition to declare throwing Exception. The ExceptionUtils classes are then
implemented to use these function and return the original corresponding object from the java.util.function package and
converting the checked exception to an unchecked one.

# More Examples

For further examples please checkout our unit-tests, where you can see on how to use the different classes.