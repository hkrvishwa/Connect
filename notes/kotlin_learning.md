#KOTLIN

   






10. Collections
    Collection
    Iterable
    List
    MutableList
    Set
    MutableSet
    Map
    MutableMap
    ArrayList
    HashSet
    HashMap
    Read-Only Collections
    Mutable Collections
11. Collection Operations
    forEach
    map
    mapNotNull
    filter
    filterNot
    filterNotNull
    find
    first
    firstOrNull
    last
    lastOrNull
    any
    all
    none
    count
    sum
    sumOf
    minOrNull
    maxOrNull
    reduce
    fold
    groupBy
    associate
    associateBy
    sorted
    sortedBy
    sortedDescending
    sortedByDescending
    distinct
    distinctBy
    flatten
    flatMap
    partition
    chunked
    windowed
    zip
    take
    drop
12. Classes and Objects
    Classes
    Objects
    Properties
    Methods
    Primary Constructor
    Secondary Constructor
    init Block
    Getters
    Setters
    Visibility Modifiers
    public
    private
    protected
    internal
13. Object-Oriented Programming
    Encapsulation
    Inheritance
    Polymorphism
    Abstraction
    open Classes
    final Classes
    Method Overriding
    Property Overriding
    Abstract Classes
    Abstract Functions
    Interfaces
    Multiple Interfaces
14. Special Classes
    Data Classes
    Enum Classes
    Sealed Classes
    Sealed Interfaces
    Nested Classes
    Inner Classes
    Anonymous Classes
    Value Classes
    Object Declarations
    Companion Objects
15. Data Classes
    Primary Constructor Properties
    equals
    hashCode
    toString
    copy
    componentN
    Destructuring
16. Enum Classes
    Enum Constants
    Enum Properties
    Enum Functions
    Enum Constructors
    Enum Interfaces
    entries
17. Sealed Classes and Interfaces
    Sealed Classes
    Sealed Interfaces
    Restricted Hierarchies
    Exhaustive when
    State Modeling
    Result Modeling
18. Interfaces
    Interface Declaration
    Abstract Members
    Default Implementations
    Interface Properties
    Multiple Interface Implementation
    Interface Inheritance
    Functional Interfaces
    SAM Interfaces
19. Inheritance
    Base Classes
    Derived Classes
    open
    override
    final
    super
    Constructor Inheritance
    Property Overriding
    Method Overriding
20. Lambdas
    Lambda Expressions
    Lambda Parameters
    Lambda Return Values
    it Parameter
    Trailing Lambda
    Multiple Parameters
    Lambda Type Inference
    Closures
21. Higher-Order Functions
    Functions as Parameters
    Functions as Return Values
    Function Types
    Function References
    Bound Function References
    Unbound Function References
    Anonymous Functions
    Closures
22. Scope Functions
    let
    run
    with
    apply
    also
    this
    it
    Scope Function Return Values
    Scope Function Chaining
23. Extension Functions
    Extension Functions
    Extension Properties
    Nullable Extensions
    Generic Extensions
    Member Extensions
    Extension Resolution
    Extension Scope
24. Type Checking and Casting
    is
    !is
    as
    as?
    Smart Cast
    Unsafe Cast
    Safe Cast
    Type Inference

26. Generics
    Generic Classes
    Generic Functions
    Generic Interfaces
    Generic Constraints
    Multiple Constraints
    where
    Type Parameters
    Type Erasure
    Variance
    Covariance
    Contravariance
    in
    out
    Star Projection
27. Delegation
    Class Delegation
    Interface Delegation
    Property Delegation
    by
    lazy Delegation
    Observable Properties
    Vetoable Properties
    Map Delegation
    Custom Delegates
    ReadOnlyProperty
    ReadWriteProperty
28. Destructuring
    Destructuring Declarations
    componentN
    Data Class Destructuring
    Pair Destructuring
    Map Destructuring
    Lambda Destructuring
29. Pairs and Triples
    Pair
    Triple
    to
    Destructuring
30. Sequence
    Sequence
    Iterable vs Sequence
    Lazy Evaluation
    Sequence Operations
    asSequence
    generateSequence
    Sequence Pipelines
31. Functional Programming
    Pure Functions
    Immutability
    First-Class Functions
    Higher-Order Functions
    Function Composition
    Lambdas
    Closures
    Functional Collection Operations
32. Equality and Comparison
    Structural Equality
    Referential Equality
    equals
    hashCode
    Comparable
    Comparator
    compareTo
    compareBy
    compareByDescending
33. Operator Overloading
    operator
    plus
    minus
    times
    div
    rem
    compareTo
    contains
    get
    set
    invoke
    unaryPlus
    unaryMinus
    inc
    dec
34. Infix Functions
    infix
    Member Infix Functions
    Extension Infix Functions
    Infix Function Restrictions
35. Inline Functions
    inline
    noinline
    crossinline
    Inline Lambdas
    Non-Local Returns
    Inline Properties
    Performance Considerations
36. Reified Type Parameters
    reified
    Runtime Type Access
    Generic Type Checks
    Reified Extensions
    Reified with Inline Functions
37. Type Aliases
    typealias
    Function Type Aliases
    Generic Type Aliases
    Nested Type Aliases
38. Annotations
    Annotation Classes
    Annotation Targets
    Annotation Retention
    Annotation Parameters
    Use-Site Targets
    Custom Annotations
    Repeatable Annotations
39. Reflection
    KClass
    Class References
    Function References
    Property References
    Callable References
    Constructors
    Members
    Runtime Type Inspection


45. Flow
    Flow
    Flow Builder
    emit
    collect
    Cold Flow
    Flow Cancellation
    Flow Context
    Flow Exceptions
    Flow Completion
46. Flow Operators
    map
    filter
    transform
    take
    drop
    reduce
    fold
    debounce
    sample
    distinctUntilChanged
    combine
    zip
    merge
    flatMapConcat
    flatMapMerge
    flatMapLatest
    catch
    retry
    retryWhen
    onStart
    onEach
    onCompletion
    buffer
    conflate
    collectLatest
47. StateFlow
    StateFlow
    MutableStateFlow
    State Value
    State Updates
    Hot Flow
    stateIn
    SharingStarted
    StateFlow Collection
48. SharedFlow
    SharedFlow
    MutableSharedFlow
    emit
    tryEmit
    Replay
    replayCache
    Buffer Capacity
    Buffer Overflow
    shareIn
    Hot Flow
    Event Streams
49. Channels
    Channel
    SendChannel
    ReceiveChannel
    send
    receive
    trySend
    tryReceive
    Channel Capacity
    Buffered Channel
    Rendezvous Channel
    Conflated Channel
    Channel Closing
50. Advanced Flow
    callbackFlow
    channelFlow
    flowOn
    buffer
    conflate
    collectLatest
    Hot Streams
    Cold Streams
    Backpressure
    State Sharing
    Flow Sharing
    Flow Exception Transparency
    Context Preservation
51. Kotlin DSL
    DSL Basics
    Lambda with Receiver
    Function Types with Receiver
    Type-Safe Builders
    Builder Pattern
    Nested DSL
    DSL Scope
    DslMarker
    Custom DSL
52. Contracts
    Kotlin Contracts
    contract
    CallsInPlace
    Returns
    ReturnsNotNull
    Smart Cast Contracts
    InvocationKind
53. Java Interoperability
    Calling Java from Kotlin
    Calling Kotlin from Java
    Platform Types
    Java Getters and Setters
    Java Collections
    Checked Exceptions
    SAM Conversion
    @JvmStatic
    @JvmField
    @JvmOverloads
    @JvmName
    @Throws
    JVM Wildcards
54. Kotlin JVM Internals
    Kotlin Compilation
    JVM Bytecode
    Primitive Types
    Boxing
    Unboxing
    Nullable Primitive Types
    Lambda Compilation
    Object Compilation
    Companion Object Compilation
    Data Class Generated Members
    Default Argument Generation
    Synthetic Methods
    Type Erasure
55. Memory and Performance
    Stack and Heap
    Object Allocation
    Garbage Collection
    Boxing
    Unboxing
    String Allocation
    Collection Allocation
    Sequence Performance
    Inline Functions
    Lambda Allocation
    Memory Leaks
    Performance Optimization




