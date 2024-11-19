## jexpression
A simple Java custom expression evaluator.

[中文](../README.md) | [英文](./readme_en.md)

### Description

A lightweight expression evaluator, for example: `1+1`.

### Syntax

- **Operators**: `+`, `-`, `*`, `/`
- **Logical operators**: `<`, `>`, `==`, `<=`, `>=`, `!=`, `&&`, `||`
- **Precedence**: Supports parentheses `(` and `)` with nested expressions, e.g., `((1+2)-3)*4`.
- **Variable support**: Variables can be used in expressions if they are predefined in the context. Variable names must consist of Unicode characters, underscores, and digits, but cannot start with a digit.
- **Custom functions**: You can implement custom functions by extending the `Func` interface and adding them to the context.
- **Supported types**: Method return values, parameters, or variables must be of the following types:
    - `int`
    - `float`
    - `double`
    - `string`
    - `boolean`
    - `BigDecimal`

### Notes

- During evaluation, both literal numbers and calculated numeric results are internally represented as `BigDecimal`.

---

### Examples

#### Simple expression

```java
String exp = "1+1";
var runtime = new Runtime();
var result = runtime.executeObject(exp);
assertEquals(new BigDecimal(2), result);
```

#### Convert result to other types

```java
String exp = "1+1";
var runtime = new Runtime();
var result = runtime.execute(exp);
assertEquals(2, result.toInt());
```

#### Comparison

```java
String exp = "1 > 2";
var runtime = new Runtime();
var result = runtime.execute(exp);
assertFalse(result.toBoolean());
```

#### Calling custom methods

```java
String exp = "'abc' + stringRepeat('1', 3)";
var runtime = new Runtime();
runtime.addFunc(new StringRepeat());

var result = runtime.execute(exp);
assertEquals("abc111", result.toString());
```

#### Using variables

```java
// Commission = order amount * 20% + transport fee
String exp = "订单金额*0.2 + 路费";
var runtime = new Runtime();
runtime.addFunc(new StringRepeat());

var ctx = new Context();
ctx.setVar("订单金额", 200.1);
ctx.setVar("路费", 123.2);

var result = runtime.execute(ctx, exp);
assertEquals(200.1*0.2+123.2, result.toDouble());
```