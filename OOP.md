# Object-Oriented Programming

Main idea, avoid duplicating work.

https://refactoring.guru/design-patterns
https://sourcemaking.com/design_patterns

Design patterns as blueprints for recurring designs
	you can see what the result and its features are, but the exact order of implementation is up to you.
https://refactoring.guru/design-patterns/what-is-pattern
The concept of patterns was first described by Christopher Alexander in A Pattern Language: Towns, Buildings, Construction.  The book describes a “language” for designing the urban environment.  The units of this language are patterns. They may describe how high  windows should be, how many levels a building should have, how large  green areas in a neighborhood are supposed to be, and so on.
The idea was picked up by four authors: Erich Gamma, John Vlissides, Ralph Johnson, and Richard Helm. In 1994, they published Design Patterns: Elements of Reusable Object-Oriented Software,  in which they applied the concept of design patterns to programming.  The book featured 23 patterns solving various problems of  object-oriented design and became a best-seller very quickly. Due to its  lengthy name, people started to call it “the book by the gang of four”  which was soon shortened to simply “the GoF book”.

## Pitfalls
"Usually the need for patterns arises when people choose a programming language or a technology that lacks the necessary level of abstraction. In this case, patterns become a kludge that gives the language much-needed super-abilities."
May cause you to see every problem as a nail (when you wield the Design Pattern hammer) when simpler solutions exist

- Creational patterns provide object creation mechanisms that increase flexibility and reuse of existing code.
- Structural patterns explain how to assemble objects and classes into larger structures, while keeping the structures flexible and efficient.
- Behavioral patterns take care of effective communication and the assignment of responsibilities between objects.
Creational Patterns

Factories produce products,
Factory Method/ Virtual Constructor

Interface for creating objects in a superclass, but allow subclasses to alter the type of object that will be created
Abstract superclass or interface or parent may have methods or default return types, but more so promises that each concrete implementation in the form of a subclass will have these methods.
Products that inherit from the superclass have a common base class or interface (same methods)
Client that uses the factory doesn't see a difference between subclasses - treats everything as their abstract base class.
Abstract Factory

"All you factories look the same to me"

Produce families of related objects without specifying their concrete classes
Abstract Factory creating abstract products, concrete versions create concrete versions that inherit from the abstract products.
Client works with abstract types - doesn't distinguish based on the actual concrete type that is returned - doesn't depend upon the concrete class.
Create objects from each class of a product family.
Art Deco chairs, couches, and tables vs. Victorian chairs, couches, and tables. Treat them all the same way. Two separate families of related objects
Builder

Construct complex objects step by step, allowing you to produce different types and representations with the same initialization code without breeding subclasses.
Can do:
```
new House(windows, doors, rooms, hasGarage, hasSwimmingPool, hasStatues, hasGarden all as number | null);
```
Telescopic constructor
"Say you have a constructor with ten optional parameters. Calling such a beast is very inconvenient; therefore, you overload the constructor and create several shorter versions with fewer parameters. These constructors still refer to the main one, passing some default values into any omitted parameters."
Or can extract object construction code into its own class, separate objects called builders.
```
HouseBuilder {
    buildWalls();
    buildDoors();
    buildWindows();
    buildRoof();
    buildGarage();
    getResult(): House;
}
```
Execute builder functions on the builder object - call only the steps you need 
Different builders can produce different types of objects
Castle vs. Log Cabin

Can extract a series of calls to the builder into a separate class called a Director. The director defines the order to execute the building steps, the builder provides the implementation.
Shortcut to make a common product.
How to build a car

The resultant products don't have to belong to the same class hierarchy or interface.

Doesn't expose the unfinished product while running construction steps, so the client code won't be able to fetch the incomplete result.
Prototype

Copy existing objects without making your code dependent on their classes.

Can't always copy from the outside
"Say you have an object, and you want to create an exact copy of it.  How would you do it? First, you have to create a new object of the same  class. Then you have to go through all the fields of the original object  and copy their values over to the new object.
Nice! But there’s a catch. Not all objects can be copied that way  because some of the object’s fields may be private and not visible from  outside of the object itself."
Plus, if you have to know the object's class to create a duplicate, you become dependent on that class. 

Alternative to subclassing
Objects that support cloning are prototypes
"The implementation of the clone method is very similar  in all classes. The method creates an object of the current class and  carries over all of the field values of the old object into the new one.  You can even copy private fields because most programming languages let  objects access private fields of other objects that belong to the same  class."
If can clone, don't need to construct a new object from scratch.

```Java
interface Prototype {
    clone();
}
class ConcretePrototype implements Prototype {
    field: type;
    constructor() {
        this.field = intialValue;
    }
    /* 
    A prototype class must define the alternative constructor that accepts an object of that class as an argument. The constructor must copy the values of all fields defined in the class from the passed object into the newly created instance. If you’re changing a subclass, you must call the parent constructor to let the superclass handle the cloning of its private fields. 
    */
    constructor (source: Type) {
        this.field = source.field;
    }
    /* 
    The cloning method usually consists of just one line: running a new operator with the prototypical version of the constructor. Note, that every class must explicitly override the cloning method and use its own class name along with the new operator. Otherwise, the cloning method may produce an object of a parent class.
    */
    clone(): Type {
        return new ConcretePrototype(this);
    }
}
```

Prototypes are objects that can clone themselves.

```Java
ConcretePrototype newProto = oldProto.clone();
```
as opposed to
```Java
Prototype newProto = new ConcretePrototype(oldProto);
```

A Prototype Registry provides an easy way to access frequently-used prototypes
Stores a set of pre-built objects ready to be copied.
Simplest form: name to prototype hash map.

### Singleton

"The only God you need!"
Ensure a class has only one instance, and provide global access to it.
Control access to some shared resource, like a database or a file.

Global variables are unsafe as any code could potential overwrite the contents. A singleton allows global access but adds protection.
- Make the default constructor private, to prevent other objects from using the new operator with the Singleton class.
	- Only the Singleton itself can replace the cached instance.
- Create a static creation method that acts as a constructor. Under the hood, this method calls the private constructor to create an object and saves it in a static field. All following calls to this method return the cached object.
	- If your code has access to the Singleton class, then it’s able to call the Singleton’s static method. So whenever that method is called, the same object is always returned.
```
class Singleton {
    // Store the instance
    private static instance: Singleton;
    
    // Prevent direct construction calls with the "new" operator
    private constructor () {
        initializingFunctions();
    }
    
    // Control access to the instance
    public static getInstance() {
    
    if (instance == null) then
            acquireThreadLock() and then
                // Ensure that the instance hasn't yet been
                // initialized by another thread while this one
                // has been waiting for the lock's release.
                if (instance == null)
                    instance = new Singleton()
        return Singleton.instance
    }
    
    // Business logic so you can use this instance for something
    public query(command) {
    }
}
```

It's like the government

Pitfalls
1. Violates the Single Responsibility Principle. The pattern solves two problems at the time.
2. The Singleton pattern can mask bad design, for instance, when the components of the program know too much about each other.
3. The pattern requires special treatment in a multithreaded environment so that multiple threads won’t create a singleton object several times.
4. It may be difficult to unit test the client code of the Singleton because many test frameworks rely on inheritance when producing mock objects. Since the constructor of the singleton class is private and overriding static methods is impossible in most languages, you will need to think of a creative way to mock the singleton. Or just don’t write the tests. Or don’t use the Singleton pattern.
Structural Patterns

Adapter

Allow objects with incompatible interfaces to collaborate by converting one object into something the other object can understand - change the interface into a different one.

Wrapper that converts
The Adapter inherits from the client, which is trying to communicate with the service, and the service, which has an incompatible interface. Implements a compatible interface and translates into calls to the wrapped service object.

Often used to convert 3rd party or legacy services that cannot be changed.
Bridge

Split a large class or set of closely related classes into two separate hierarchies - Abstraction and Implementation - which can be developed independently of each other.
Bridge between screens and remotes.

Circle and Square are subclasses of Shape.
Want Red and Blue subclasses as well.
Could create RedCircle, BlueCirlce, RedSquare, and BlueSquare subclasses, but the hierarchy is now growing exponentially.
Trying to extend the Shape class in multiple independent dimensions (form and color)

Extract one of the dimensions into a separate class hierarchy
Shape (Circle, Square) contains Color (Blue, Red)
Shape has a reference field pointing to a color object and delegates any color related work to it.
The reference in one class to the other is the Bridge.

"Abstraction (also called interface) is a high-level control layer for some entity. This layer isn’t supposed to do any real work on its own. It should delegate the work to the implementation layer (also called platform)."

Modules rather than a monolith.

Abstraction - GUI, delegates actual work to the linked implementation object
Refined abstractions provide variants of control logic
Implementation - APIs
Concrete Implementations contain platform-specific code
Client generally only works with the abstraction, but it still links the abstraction with one of the implementation objects


- device: Device is a reference field linking the two
Can switch up implementations at runtime.
Composite

Compose objects into tree structures and then work with these structures as if they were individual objects. 
Containers or composites of leaves and more containers. All elements share a common interface that can be used to pass down order calls.
If the core model of your app can be represented as a tree.

Ex: packages of packages and products. Have all return the price of what they contain.
Military
Hierarchies that can be broken down, pass down orders
Decorator/ Wrapper

Change the skin
Like a Russian Nesting Doll!
Or wearing a sweater and a raincoat to protect yourself from the cold and the rain.
Enhance the object without changing the interface. Attach new behaviors to objects by placing them inside special wrapper objects that contain these behaviors.

Extending a class is the first thing that comes to mind when you need  to alter an object’s behavior. However, inheritance has several serious  caveats that you need to be aware of.
- Inheritance is static. You can’t alter the behavior of an existing object at runtime. You can only replace the whole object with another one that’s created from a different subclass.
- Subclasses can have just one parent class. In most languages, inheritance doesn’t let a class inherit behaviors of multiple classes at the same time.
One of the ways to overcome these caveats is by using Aggregation or Composition 
 instead of Inheritance. Both of the alternatives work almost the same way: one object has a reference to another and delegates it some work, whereas with inheritance, the object itself is able to do that work, inheriting the behavior from its superclass.
With this new approach you can easily substitute the linked “helper” object with another, changing the behavior of the container at runtime. An object can use the behavior of various classes, having references to multiple objects and delegating them all kinds of work.

In Aggregation, the Client calls a Service.
Wrapper is the alternative nickname for the Decorator  pattern that clearly expresses the main idea of the pattern. A “wrapper”  is an object that can be linked with some “target” object. The wrapper  contains the same set of methods as the target and delegates to it all  requests it receives. However, the wrapper may alter the result by doing  something either before or after it passes the request to the target.
When does a simple wrapper become the real decorator? As I mentioned,  the wrapper implements the same interface as the wrapped object. That’s  why from the client’s perspective these objects are identical. Make the  wrapper’s reference field accept any object that follows that  interface. This will let you cover an object in multiple wrappers,  adding the combined behavior of all the wrappers to it.

Stack the wrappers on top of each other to get a lot more functionality.
Stack of notifiers that each "send()" something to different places, like email and social networks. The client only actually works with the last or top decorator.
new Notifier(otherNotifier)
DataSource Decorator, Encryption Decorator, and CompressionDecorator all write and read around a FileDataSource. All implement the write and read methods from interface DataSource.

When you can't use inheritance: Many programming languages have the final keyword that can be used to prevent further extension of a class. For a final class, the only way to reuse the existing behavior would be to wrap the class with your own wrapper, using the Decorator pattern.
Facade

Make a single object to represent a whole subsystem!
Define a new interface for a subsystem of objects, like a library, a framework, or any other complex set of classes. May provide simplified or more limited functionality compared to working directly with the subsystem, but really only limiting yourself to what the client actually uses in a more straightforward way.
An Additional Facade class can be created to prevent polluting a single facade with unrelated features that might make it yet another complex structure. Additional facades can be used by both clients and other facades.
Instead of making your code work with dozens of the framework classes directly, you create a facade class which encapsulates that functionality and hides it from the rest of the code. This structure also helps you to minimize the effort of upgrading to future versions of the framework or replacing it with another one. The only thing you’d need to change in your app would be the implementation of the facade’s methods.
Beware it becoming a God object, an object that knows too much or does too much. The God object is an example of an anti-pattern.
Flyweight (Optimization)

Make lots of littel objects!
lets you fit more objects into the available amount of RAM by sharing common parts of state between multiple objects instead of keeping all of the data in each object.
Ex: in a game, assets might share the same colors, sprites, or modifiers, but have unique coordinates, movement vectors and speed.
The constants are its intrinsic state that other objects can only read, not modify.
The rest of the object's state, often altered “from the outside” by other objects, is called the extrinsic state.
The Flyweight pattern suggests that you stop storing the extrinsic state inside the object. Instead, you should pass this state to specific methods which rely on it. Only the intrinsic state stays within the object, letting you reuse it in different contexts. As a result, you’d need fewer of these objects since they only differ in the intrinsic state, which has much fewer variations than the extrinsic.
an object that only stores the intrinsic state is called a flyweight. Immutable after initialization.
For more convenient access to various  flyweights, you can create a factory method that manages a pool of  existing flyweight objects. The method accepts the intrinsic state of  the desired flyweight from a client, looks for an existing flyweight  object matching this state, and returns it if it was found. If not, it  creates a new flyweight and adds it to the pool.
There are several options where this method could be placed. The most  obvious place is a flyweight container. Alternatively, you could create  a new factory class. Or you could make the factory method static and  put it inside an actual flyweight class.
The state stored inside a flyweight is called “intrinsic.” The state passed to the flyweight’s methods is called “extrinsic.”


Where does the extrinsic state move to? Some class should still store  it, right? In most cases, it gets moved to the container object, which  aggregates objects before we apply the pattern.
In our case, that’s the main Game object that stores all particles in the particles  field. To move the extrinsic state into this class, you need to create  several array fields for storing coordinates, vectors, and speed of each  individual particle. But that’s not all. You need another array for  storing references to a specific flyweight that represents a particle.  These arrays must be in sync so that you can access all data of a  particle using the same index.
A more elegant solution is to create a separate context class that  would store the extrinsic state along with reference to the flyweight  object. This approach would require having just a single array in the  container class.
Wait a second! Won’t we need to have as many of these contextual  objects as we had at the very beginning? Technically, yes. But the thing  is, these objects are much smaller than before. The most  memory-consuming fields have been moved to just a few flyweight objects.  Now, a thousand small contextual objects can reuse a single heavy  flyweight object instead of storing a thousand copies of its data.
only add a particle if there's none with the given color and sprite



You might be trading RAM over CPU cycles when some of the context data needs to be recalculated each time somebody calls a flyweight method.
Proxy

Provide with the same interface
provide a substitute or placeholder for another object. A proxy controls access to the original object, allowing you to perform something either before or after the request gets through to the original object.
Ex: A massive object that consumes a vast amount of system resources you need from time to time - like a Database with slow queries
You could implement lazy initialization: create this object only when  it’s actually needed. All of the object’s clients would need to execute  some deferred initialization code. Unfortunately, this would probably  cause a lot of code duplication.
In an ideal world, we’d want to put this code directly into our  object’s class, but that isn’t always possible. For instance, the class  may be part of a closed 3rd-party library.
The proxy disguises itself as a database object. It can handle lazy  initialization and result caching without the client or the real  database object even knowing.
But what’s the benefit? If you need to execute something either  before or after the primary logic of the class, the proxy lets you do  this without changing that class. Since the proxy implements the same  interface as the original class, it can be passed to any client that  expects a real service object.

Credit cards are proxies for bank accounts, which are proxies for a bundle of cash.

There are dozens of ways to utilize the Proxy pattern. Let’s go over the most popular uses.
 Lazy  initialization (virtual proxy). This is when you have a heavyweight  service object that wastes system resources by being always up, even  though you only need it from time to time.
 Instead of  creating the object when the app launches, you can delay the object’s  initialization to a time when it’s really needed.
 Access control  (protection proxy). This is when you want only specific clients to be  able to use the service object; for instance, when your objects are  crucial parts of an operating system and clients are various launched  applications (including malicious ones).
 The proxy can pass the request to the service object only if the client’s credentials match some criteria.
 Local execution of a remote service (remote proxy). This is when the service object is located on a remote server.
 In this case, the  proxy passes the client request over the network, handling all of the  nasty details of working with the network.
 Logging requests (logging proxy). This is when you want to keep a history of requests to the service object.
 The proxy can log each request before passing it to the service.
 Caching request results (caching proxy). This is when you need to cache results of  client requests and manage the life cycle of this cache, especially if  results are quite large.
 The proxy can  implement caching for recurring requests that always yield the same results. The proxy may use the parameters of requests as the cache keys.
 Smart reference. This is when you need to be able to dismiss a heavyweight object once there are no clients that use it.
 The proxy can  keep track of clients that obtained a reference to the service object or  its results. From time to time, the proxy may go over the clients and  check whether they are still active. If the client list gets empty, the  proxy might dismiss the service object and free the underlying system  resources.
The proxy can also track whether the client had modified the service  object. Then the unchanged objects may be reused by other clients.

1. You can control the service object without clients knowing about it.
2. You can manage the lifecycle of the service object when clients don’t care about it.
3. The proxy works even if the service object isn’t ready or is not available.
Behavioral Patterns

Algorithms and assigning responsibility
Chain of responsibility

Pass a request along a chain of handlers. Upon receiving a request, each handler decides to process the request or pass it on.
passes a request sequentially along a dynamic chain of potential receivers until one of them handles it.
Ex: Authenticate and, if true, go along with next request. Authorize, check for repeated failed attempts from the same IP attempt, validate/ sanitize the data, cache.
Extract each check into its own class with a single method that performs the check. In addition to processing a request, handlers can choose to pass the request further along the chain to its reference to the next handler in the chain.
A chain can be formed from a branch of an object tree.
Usually self-contained and immutable.
 Use the Chain of Responsibility  pattern when your program is expected to process different kinds of  requests in various ways, but the exact types of requests and their  sequences are unknown beforehand.
 The pattern lets  you link several handlers into one chain and, upon receiving a request,  “ask” each handler whether it can process it. This way all handlers get a  chance to process the request.
 Use the pattern when it’s essential to execute several handlers in a particular order.
 Since you can link the handlers in the chain in any order, all requests will get through the chain exactly as you planned.
 Use the CoR pattern when the set of handlers and their order are supposed to change at runtime.
 If you provide  setters for a reference field inside the handler classes, you’ll be able  to insert, remove or reorder handlers dynamically.
Command

turns a request into a stand-alone object that contains all information about the request. This transformation lets you parameterize methods with different requests, delay or queue a request’s execution, and support undoable operations. 
establishes unidirectional connections between senders and receivers
Turn specific method into a stand-alone object - can now pass as an argument, store, switch up. Parameterize objects with operations. Queue operations, schedule execution, or execute remotely (as objects, can be serialized and logged). Can implement reversible operations (undo/ redo) by storing a history stack with backups of state or just perform the inverse of an operation.
Separation of concerns - break into layers, like GUI and business logic being separate. Separate them with a Command class that will take and send requests, all of which implement the same interface, as simple as just an execute() method.
Rather than a copy, save, open button subclasses, just pass in a copy, save, or open command object reference upon construction that will be executed on click, making it easy to apply the same copy behavior to something that's not a button, like hotkeys.
Like a restaurant. Waiter takes your order on a piece of paper and delivers it to the cook's queue.
Iterator

Traverse a collection without exposing its underlying representation (list, stack, tree, set, etc.)
Interface example: with methods getNext(), hasMore() for some type, then actually implement it in classes which can be then used by different collections
Different implementations for depth-first vs. breadth-first for trees. Iterator is returned by the collection instance, generally
Iterate a variety of ways over attractions and sights in a city - map, random walking, guided tour
Mediator

eliminates direct connections between senders and receivers, forcing them to communicate indirectly via a mediator object. Reduces chaotic dependencies
Think of forms where elements appear and disappear based on the status of other elements (dependent, validation). Don't couple them directly, but have an overarching controller, like a dialog box 
Components aren't aware of other components - they only notify the mediator. This way they can be reused in other contexts
They have a reference to the mediator object for when they want to use it to send a message
Can also have mediator responsible for creation or destruction of components, resembling a factory or a facade.
Can implement with Observer, if the mediator acts as publisher
Memento

Save and restore previous state without revealing implementation details - snapshots
hippie objects - open relations, keep state public. Most objects aren't like this - have private fields or open to change
So have the originator object create memento snapshots rather than some outsider making copies. Snapshot metadata, like creation time, operation name may be available to outside caretakers through an interface, without allowing for modification. Commands make snapshots, use history caretaker to decide when to revert or redo by having popping a (usually immutable) memento from the caretaker's history and passing it to the originator through a restore(memento) method. Or have the undo operation call a memento.restore() so the  caretaker is independent from the originator. Do need to link the memento to the originator along with its state values at some point in time.
Use a command object as a caretaker. Can have memento be a nested class of the Originator.
Observer

lets receivers dynamically subscribe to and unsubscribe from receiving requests from some publisher that can notify multiple objects about events that happen to some subject object they're observing. The subject and publisher may be the same object.
Publisher maintain a list of subscribers and ways to modify the list. Subscribers each have some update or notify method through which the publisher can pass contextual parameters.
Good for when changes in one object's state affect some group of others, the makeup of which is unknown beforehand or changes dynamically.
State

Behavior alters when internal state changes, appearing as if the object changed its class.
Think finite-state machines, where the state can be switched (transition) between a range of options (may be some ordering to which can be switched to that depends on the current state).
Use lots of conditional operators (if, switch) depending on the value of its state, as stored in some field.
With the State pattern, create new classes for all possible states (can nest them to access private members of the original object) and extract state-specific behavior here, as defined by some interface. The original object stores a context - a reference to one of the state objects that represents its current state and delegates all state-related work to that object.
States may be aware of each other and initiate transitions. Compare to strategies, which almost never know about each other.
Strategy

Change the guts of an object by defining a family of algorithms, each in a separate class, as interchangeable algorithms.
Golf clubs
The original class, the context, has a field storing a reference to the present strategy and delegates work to it.
Can also do with functional interfaces - implement different versions of an algorithm inside a set of anonymous functions
Compare to Template method: object (can switch at runtime) vs. class-level (static); composition vs. inheritance
Template Method

Define the skeleton of an algorithm in a super class and in subclasses override specific steps (methods, like parse(data)) without changing its structure.
Think supporting data mining of different file types
Abstract step
Optional step - default implementation
Hook - optional step with an empty body

Factory Method is a specialization of Template Method, and can serve as a step
Visitor

Separate algorithms from the objects on which they operate.
Have some Visitor class that you can pass the original object to and do some operation with
Rather than an object having an exportToXML() method, have some Visitor with exportTypeToXML() for each type.
then have some overarching export method that runs a method based on the type of each object -  can do with if else or Double Dispatch: give each object some obj.accept(visitor) method that then calls visitor.appropriateExportMethod() or visitor.visit(this) where this is some known class, with visit(type) defined and overloaded for each type. This is dynamic binding alongside overloaded methods.

Travelling salesman offering specialized insurance policies based on the type of building