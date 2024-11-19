# jexpression
一个简易的表达式执行器

[中文](./README.md) | [英文](./readme/readme_en.md)

## 说明

一个简易的表达式，例如：1+1

### 语法：
* 操作符：+，-， *， /
* 逻辑操作符：<, >, ==, <=, >=, !=, &&, ||
* 优先级：（，），支持嵌套，eg.((1+2)-3)*4
* 支持读取变量，需要提前加入到 context 里。unicode 字符+下划线+数字，不能以数字开头
* 支持自定义方法，实现 Func 接口，添加到 context 使用
* 方法返回值，参数或变量的类型仅支持基本类型和 BigDecimal
  * int
  * float
  * double
  * string
  * boolean

### 注意事项
* 计算过程中表达式中识别为数字和公式计算结果是数字的均使用 BigDecimal 进行包装

### 举多个例子

* 简单表达式


```java

String exp = "1+1";
var runtime = new Runtime();
var result = runtime.executeObject(exp);
assertEquals(new BigDecimal(2), result);

```

* 结果转其他类型
```java

String exp = "1+1";
var runtime = new Runtime();
var result = runtime.execute(exp);
assertEquals(2, result.toInt());
        
```

* 比大小
```java

String exp = "1 > 2";
var runtime = new Runtime();
var result = runtime.execute(exp);
assertFalse(result.toBoolean());

```

* 调用方法
```java

String exp = "'abc' + stringRepeat('1', 3)";
var runtime = new Runtime();
runtime.addFunc(new StringRepeat());

var result = runtime.execute(exp);
assertEquals("abc111", result.toString());

```


* 变量的使用
```java

 // 佣金=订单金额*20%+路费
String exp = "订单金额*0.2 + 路费";
var runtime = new Runtime();
runtime.addFunc(new StringRepeat());

var ctx = new Context();
ctx.setVar("订单金额", 200.1);
ctx.setVar("路费", 123.2);

var result = runtime.execute(ctx, exp);
assertEquals(200.1*0.2+123.2, result.toDouble());

```