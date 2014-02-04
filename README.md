java-maybe
==========

Maybe (some/none) in java.

API
---

Available in the docs folder,
or online [here](http://duta.github.io/java-maybe/).

Basic usage
-----------

```java
Maybe<Integer> three = Maybe.some(3);
// Prints "3"
print(three.isSome() ? three.get() : "None");
// Prints "Some(3)"
print(three);

Maybe<Integer> none = Maybe.none();
// Prints "None"
print(none.isSome() ? none.get() : "None");
// Prints "None"
print(none);
```

Example function
----------------

Non-maybe definition:

```java
public Vec3 add(Vec3 a, Vec3 b) {
    if(a == null) {
        throw new IllegalArgumentException(
            "a cannot be null");
    }
    if(b == null) {
        throw new IllegalArgumentException(
            "a cannot be null");
    }
    return new Vec3(
        a.getX() + b.getX(),
        a.getY() + b.getY(),
        a.getZ() + b.getZ());
}
```

Maybe definition:

```java
public Maybe<Vec3> add(Maybe<Vec3> a, Maybe<Vec3> b) {
    if(a == null || a.isNone()
    || b == null || b.isNone()) {
        return Maybe.none();
    }
    Vec3 aRaw = a.get();
    Vec3 bRaw = b.get();
    return Maybe.some(new Vec3(
        aRaw.getX() + bRaw.getX(),
        aRaw.getY() + bRaw.getY(),
        aRaw.getZ() + bRaw.getZ()));
}
```

* * *

Non-maybe safe usage:

```java
Vec3 a = ...;
Vec3 b = ...;
Vec3 result;
try {
    result = add(a, b);
} catch(IllegalArgumentException ex) {
    // Take appropriate action
}
System.out.println(result);
```

Maybe safe usage:

```java
Maybe<Vec3> a = ...;
Maybe<Vec3> b = ...;
Maybe<Vec3> result = add(a, b);
if(result.isNone()) {
    // Take appropriate action
} else {
    System.out.println(result.get());
}
```

* * *

Non-maybe unsafe usage:

```java
Vec3 a = ...;
Vec3 b = ...;
Vec3 result = add(a, b);
System.out.println(result);
```

Maybe unsafe usage:

```java
Maybe<Vec3> a = ...;
Maybe<Vec3> b = ...;
Maybe<Vec3> result = add(a, b);
System.out.println(result.get());
```

Notes
-----

If the compiler can't figure out the type of
`Maybe` you want when doing something like:

```java
Maybe.none()
```

Write the following instead (replacing String
with whatever type you actually want it to be):

```java
Maybe.<String>none()
```
