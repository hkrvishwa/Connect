
# Kotlin 

• Concise: Reduces boilerplate code by roughly 40% compared to Java.
    using data clasess,setter,getter
• Null Safety: Prevents NullPointerException errors by making ?.
• Interoperable: Has 100% bidirectional compatibility with Java.
• Multi-Paradigm: Combines Object-Oriented and Functional programming styles


## Kotlin Variables & Types
• val: Declares a read-only (immutable) local variable that cannot be reassigned after initialization.
• var: Declares a mutable variable that can be reassigned with new values later in the code.
• Type Inference: Automatically determines a variable's data type at compile-time based on its assigned value.
• Explicit Types: Manually defining the variable's type using a colon and type name during declaration.
• Int: A 32-bit signed integer used for whole numbers between -2 billion and 2 billion.
• Long: A 64-bit signed integer used for very large whole numbers exceeding Int limits.
• Short: A 16-bit signed integer used to save memory in large arrays of small numbers.
• Byte: An 8-bit signed integer used for raw binary data and small integer values.
• Float: A 32-bit single-precision floating-point number used for decimals requiring 6-7 digits of precision.
• Double: A 64-bit double-precision floating-point number used for highly precise decimals with 15-16 digits.
• Boolean: A logical data type that holds only one of two possible values: true or false.
• Char: Represents a single 16-bit Unicode character enclosed in single quotation marks.
• String: An immutable sequence of characters enclosed in double quotation marks used to store text.
• Any: The root of the Kotlin class hierarchy, meaning every Kotlin class inherits from it.
• Unit: Represents a type that returns no meaningful value, equivalent to void in Java.
• Nothing: A special type representing a value that never exists, used for functions that always throw exceptions.
• Type Conversion: The process of explicitly converting a number from one type to another using helper functions like toInt().
• Constants: Compile-time values defined with const val that are hardcoded and cannot change at runtime.


## Kotlin Operators
• Arithmetic Operators: Performs math calculations like addition. 1 + 2
• Assignment Operators: Assigns values to a variable. x = 5
• Comparison Operators: Checks if a value is greater or less. x > y
• Logical Operators: Combines conditions with AND/OR. a && b
• Unary Operators: Flips a number's positive or negative sign. -x
• Increment and Decrement: Adds or subtracts one from a variable. x++
• Range Operators: Creates a bounded sequence of values. 1..10
• Membership Operators: Checks if a value is inside a collection. x in list
• Equality Operators: Checks if two values are structurally equal. a == b
• Referential Equality: Checks if two references point to the same object. a === b


## Kotlin Strings
• String Basics: Immutable sequence of characters stored in a single text object. "text"
• String Templates: Immutable evaluation of variables directly inside a string using a dollar sign. "$name"
• String Interpolation: Immutable calculation of expressions inside a string using curly braces. "${x + y}"
• Multiline Strings: Immutable raw text block enclosed in triple quotes that preserves newlines. """text"""
• String Functions: Read-only operations on immutable strings that always return a new string. "a".uppercase()
• String Comparison: Value-based matching of immutable strings using structural equality. "a" == "b"
• StringBuilder: A mutable object used to efficiently modify, append, and build text without creating new string instances. append("a")
• length: Returns the total count of characters in an immutable string. "hi".length
• trim(): Returns a copy of the immutable string with leading and trailing whitespace removed. " a ".trim()
• trimIndent(): Returns a copy of the immutable multiline string with a common minimal indent removed.(beginning of lines) """ x """.trimIndent()
• indexOf(): Returns the zero-based index of the first occurrence of a character. "abc".indexOf('b')
• substring(): Extracts a specific part of an immutable string , "abcd".substring(1, 3) results in "bc"
• "abcd".slice(1..3) results in "bcd", here 1 and 3 index in inclusive
• buildString { }:  mutable StringBuilder internally to construct an immutable string. buildString { append("a") }
• replace(): Returns a string where all occurrences of a target substring are replaced with a replacement. "aba".replace("a", "x")
• split(): Splits the string into a list of substrings around occurrences of the specified delimiter. "a,b".split(",")
• startsWith(): Returns true if the string starts with the specified prefix prefix. "abc".startsWith("ab")
• endsWith(): Returns true if the string ends with the specified suffix suffix. "abc".endsWith("bc")
• contains(): Returns true if the string contains the specified character sequence. "abc".contains("b")
• reversed(): Returns a string with the characters in reverse order. "abc".reversed()
• first(): Returns the very first character of the string. "abc".first()
• last(): Returns the very last character of the string. "abc".last()
• dropLast(): Returns a string with the last N characters removed. "abcd".dropLast(1)
• take(): Returns a string containing the first N characters. "abcd".take(2)
• takeLast(): Returns a string containing the last N characters. "abcd".takeLast(2)
• repeat(): Returns a string containing the original text repeated the specified number of times. "Hi".repeat(3)
• padStart(3, '0'): Places characters at the beginning. "5" becomes "005" (total length 3).
• padEnd(3, '0'): Places characters at the end. "5" becomes "500" (total length 3).


## Kotlin Conditional Statements

• if: Executes a block of code only if the specified boolean condition is true. if (x > 0) { }
• if-else: Executes one block if the condition is true, and an alternative block if it is false. if (x > 0) a else b
• if Expression: Evaluates to a value from the chosen block, replacing the traditional ternary operator. val max = if (a > b) a else b
• Nested if: An if statement placed inside another if block to check sequential, dependent conditions. if (a) { if (b) { } }
• when: Evaluates a variable against multiple branches sequentially, acting as a powerful replacement for the switch statement. when(x) { }
• when Expression: Returns a value from the matching branch, requiring an else branch to guarantee a result. val res = when(x) { 1 -> "one" else -> "other" }
• when with Ranges: Checks whether a value falls inside or outside a specific interval using the in or !in keywords. in 1..10 -> println("match")
• when with Types: Checks or smart-casts the data type of an object using the is or !is keywords. is String -> println("text")

Inside the when Condition (Branch Types)

The when construct can match conditions using:
• Constants / Literals: Exact values like 1, "hello", or true.
• Multiple Values: Grouped matching separated by commas like 1, 2, 3 ->.
• Expressions: Dynamic runtime results like getLowLimit() ->.
• Ranges: Intervals checked via in 1..10 ->.
• Types: Class hierarchy matches checked via is String -> (which also smart-casts the variable).
• No Argument: Evaluates branches as raw boolean expressions like an if-else if chain when written as when { x == y -> ... }


## Kotlin Loops & Control Transfer

• for: Iterates over any item that provides an iterator, such as a collection, array, or range. for (i in 1..5)
• while: Repeatedly executes a block of code as long as its boolean condition remains true. while (x > 0)
• do-while: Executes the body block once first, then repeatedly checks the condition before looping again. do { } while (x > 0)
• Ranges: Creates an inclusive sequence of numbers from start to finish using the double-dot operator. 1..5 (1, 2, 3, 4, 5)
• until: Creates a half-open range of numbers where the final upper bound number is excluded. 1 until 5 (1, 2, 3, 4)
• downTo: Creates a range that counts backward down to a smaller target number. 5 downTo 1 (5, 4, 3, 2, 1)
• step: Changes the iteration interval or gap size between consecutive numbers in a range loop. 1..5 step 2 (1, 3, 5)
• break: Terminates the nearest enclosing loop instantly and moves execution to the line right after it. break
• continue: Skips the remaining lines in the current loop iteration and moves directly to the next cycle. continue
• Labels: An identifier suffix using the @ symbol to mark a specific outer loop for tracking. loop@ for (...)
• Labeled Break: Jumps completely out of an outer loop that has been marked with a corresponding label. break@loop
• Labeled Continue: Skips the current cycle of a marked outer loop and proceeds to its next iteration. continue@loop



## Kotlin Functions

• Function Declaration: Defines an executable block of code using the fun keyword followed by the function name. fun greet() {}
• Function Parameters: Inputs defined with explicit types using Pascal notation name: Type inside the function signature. (x: Int, y: Int)
• Return Types: Decided at the end of the function signature after a colon to declare the output data type. fun sum(): Int
• Unit Functions: Functions that return no meaningful value, where the Unit return type declaration is completely optional. fun log(): Unit
• Single-Expression Functions: Shorthand syntax omitting braces and return when a function body consists of a single statement. fun double(x: Int) = x * 2
• Default Arguments: Pre-assigned values for parameters that allow omitting them when calling the function. fun build(id: Int = 0)
• Named Arguments: Specifying the parameter name explicitly during invocation to pass arguments in any order or skip defaults. build(id = 5)
• vararg: Allows passing a variable, comma-separated number of arguments of the same type into a single parameter array. fun printAll(vararg strings: String)
• Function Overloading: Creating multiple functions with the exact same name but differing parameter numbers, types, or arrangements. fun draw(x: Int) / fun draw(s: String)
• Local Functions: A nested function declared inside the body of another function to encapsulate and hide helper logic. fun outer() { fun inner() {} }
• Recursive Functions: A function that solves a problem by continually invoking calls to itself with modified arguments. fun steps(n: Int) { steps(n-1) }
• Tail Recursive Functions: A performance-optimized recursion using tailrec where the compiler optimizes the self-call into a fast, safe loop. tailrec fun search()

#### tailrec fun
In regular recursion, each self-call adds a new frame to the call stack. If the recursion goes too deep, the application crashes with a StackOverflowError.
When you prefix a function with the tailrec modifier, the Kotlin compiler automatically optimizes the code behind the scenes. 
It rewrites your recursive function into a standard, fast while loop, which uses zero extra stack memory and prevents crashes.


#### Lambdas

• Lambda Expressions: Function literals that are not declared but passed immediately as an expression.
```kotlin
val sum = { a: Int, b: Int -> a + b }
```

Lambda Type Inference: The compiler deduces lambda input and output types from the context variable declaration.
```kotlin
val greet: () -> String = { "Hi" }
```

Trailing Lambda
```kotlin
fun runTask(action: () -> Unit) {
action()
}

//we can call using trailing lambda
runTask {
println("Hello")
}
```

#### closure
A closure is a lambda/function that captures and accesses variables from its surrounding scope, even though those variables are declared outside the lambda
```kotlin
var c = 0
val increase = {
    c++
}
```

#### Higher-Order Function

A higher-order function is a function that does at least one of these:
Takes another function/lambda as a parameter
Returns another function

fun trace(block: () -> Unit)
fun pick(): () -> Int = { 42 }

#### Function Reference
A function reference lets you refer to an existing function without calling it.
Use the :: operator before the function name.
It is useful when passing an existing function to another function.

```kotlin
fun printName(name: String) {
println(name)
}

val names = listOf("John", "Alex")
names.forEach(::printName)
```

#### Bound Function References
Bound function reference = function reference tied to one particular object
```kotlin
class User(val name: String) {
fun printName() {
println(name)
}
}

val user = User("Vishwanath")
val print = user::printName
print() // Vishwanath
```
#### UnBound Function References
Unbound Function References: Referencing a class member function where the object instance must be passed as the first parameter. String::uppercase

#### inline
inline: Tells the compiler to copy the function's bytecode directly into the call site to eliminate lambda allocation overhead. inline fun run(block: () -> Unit)

#### infix
infix: Allows calling a single-parameter member or extension function without using parentheses or dots. 
```kotlin 
infix fun Int.add(x: Int) = this + x (used as 1 add 2)
```

#### inline infix combine
```kotlin
inline infix fun Int.add(other: Int): Int {
    return this + other
}
val result = 10 add 20
println(result) // 30
```
#### Extension
Adds new functionality to an existing class without inheriting from it or modifying its source code. 
fun String.shout() = this + "!"

### Operator fun
An operator function lets you define how operators like +, -, *, [], ==, etc. behave for your own classes.
You use the operator keyword with predefined function names such as plus, minus, or times.
Kotlin automatically converts an operator expression such as a + b into the corresponding function call a.plus(b)

```kotlin
data class Money(val amount: Int) {

    operator fun plus(other: Money): Money {
        return Money(amount + other.amount)
    }
}
val m1 = Money(100)
val m2 = Money(200)
val total = m1 + m2
println(total) // Money(amount=300)
```

a + b       → a.plus(b)
a - b       → a.minus(b)
a * b       → a.times(b)
a / b       → a.div(b)
a % b       → a.rem(b)

a[i]        → a.get(i)
a[i] = x    → a.set(i, x)

a++         → a.inc()
a--         → a.dec()

a > b       → a.compareTo(b) > 0
a in b      → b.contains(a)

#### noinline
noinline: Used inside an inline function to prevent a specific lambda parameter from being inlined, allowing it to be stored or passed around.
noinline → only for lambda parameters, not Int, String, objects, or normal variables.
```kotlin
inline fun test(a: () -> Unit, noinline b: () -> Unit)
```

#### crossinline
crossinline = "Inline this lambda, but don't let it return out of my outer function.
inline      → lambda is inlined; non-local return can be allowed
noinline    → lambda is NOT inlined
crossinline → lambda is inlined, but non-local return is NOT allowed

```kotlin
inline fun execute(action: () -> Unit) {
    action()
}

fun test() {
    execute {
        println("Hello")
        return          // exits test()
    }

    println("Done")     // not executed
}

//crossinline
inline fun execute(crossinline action: () -> Unit) {
    action()
}

fun test() {
    execute {
        println("Hello")
        return          // Compile-time error
    }
    println("Done")
}
//crossinline keeps the lambda eligible for inlining, but prevents it from doing a non-local return.
```
