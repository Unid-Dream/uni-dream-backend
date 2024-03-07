# Enum

## Annotations

### `EnumByValue`
 - default enum in Java will return and resolve by 'name'
 - use this annotation to resolve by value
 - annotation is supported by `EnumUtil`

## Code

### Java's Default. Resolve by Name

```java
public enum DemoEnum {
    ONE,
    TWO;
}
```
- output `DemoEnum.ONE = ONE`

### Resolve By Value

```java
@RequiredArgsConstructor
@Getter
public enum DemoEnum implements NamedEnum {
    ONE(0, "1"),
    TWO(1, "二");

    private final int index;
    private final String value;

    @Override
    public String toNamedString() {
      return String.format("%s_%s", index, value);
    }
}
```
- output 
  - `DemoEnum.ONE = ONE`
  - `DemoEnum.toNamedString(DemoEnum.ONE) = 0_1`
- from input
  - `NamedEnum.get(DemoEnum.class, "0_1") = DemoEnum.ONE`