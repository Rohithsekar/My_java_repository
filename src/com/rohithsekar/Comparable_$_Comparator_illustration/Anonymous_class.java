package com.rohithsekar.Comparable_$_Comparator_illustration;

//This class illustrates anonymous classes that will be useful to create comparator objects

public class Anonymous_class
{
        interface HelloWorld
        {
            public void greet();
            public void greetSomeone(String someone);
        }

        public void sayHello()   //method
        {
            /*
            Local inner class:
            The term "local" is used to indicate that the inner class is defined within a method of an outer class.
            A local inner class is declared and defined within the body of a method, and its scope is limited to that
            specific method. A local inner class has access to the fields and methods of the outer class within which
            it is contained. It can access both the instance variables and methods of the outer class directly, without
            needing any special syntax or qualifiers. This includes accessing private members of the outer class.

            A local inner class does not have access to the following:

            1. Local variables and parameters of the method in which it is defined, unless they are declared as final
            or effectively final. This means that the local inner class cannot directly access or modify non-final
            local variables of the enclosing method.

            2. Other local inner classes or methods within the same method block.

            3. Local variables and parameters of enclosing methods or blocks other than the one in which it is defined.

            4. Static members (fields or methods) of the enclosing class, unless they are explicitly accessed through
            the class name.

            5. Instance variables or methods of the enclosing class's instance, if it is a static inner class.

        It is important to note that local inner classes have access to instance variables and methods of the enclosing
        class (including private members) if they are non-static and defined within a non-static context.
             */
            class EnglishGreeting implements HelloWorld //local inner class (non-static)
            {
                String name = "world";
                public void greet()
                {
                    greetSomeone("world");
                }
                public void greetSomeone(String someone)
                {
                    name = someone;
                    System.out.println("Hello " + name);
                }
            }

            //creating an EnglishGreeting class object and assigning its reference to an interface variable.
            /*
            In essence, by assigning an object of a class to an interface variable, you are leveraging the fact that the
            class implements the interface and therefore fulfills the contract defined by the interface. The interface
            variable provides a way to interact with the object based on the common behavior defined by the interface.
            While it cannot directly access class-specific methods or attributes, it can still invoke the methods
            defined in the interface, and the appropriate implementation in the class will be executed.
            This approach allows for polymorphism, abstraction, and flexibility in working with different
            implementations of the interface while decoupling the code from specific implementation details.
             */
            HelloWorld englishGreeting = new EnglishGreeting();

            /*
            Anonymous classes enable you to make your code more concise. They enable you to declare and instantiate a
            class at the same time. They are like local classes except that they DO NOT HAVE A NAME. Use them if you
            need to use a local class only once.

            While local classes are class declarations, ANONYMOUS CLASSES ARE EXPRESSIONS, which means that you define
            the class in another expression. In the following example, anonymous classes are in the initialization
            statements of the local variables "frenchGreeting" and "spanishGreeting".

            The syntax of an anonymous class expression is like the invocation of a constructor, except that there is a
            class definition contained in a block of code.

            The anonymous class expression consists of the following:

            * The new operator

            * The name of an interface to implement or a class to extend. In this example, the anonymous class is
             implementing the interface HelloWorld.

            * Parentheses that contain the arguments to a constructor, just like a normal class instance(object) creation
              expression. Note: When you implement an interface, there is no constructor, so you use an empty pair of
              parentheses, as in this example.

            * A body, which is a class declaration body. More specifically, in the body, method declarations are allowed
             but statements are not.

             Because an anonymous class definition is an expression, it must be part of a statement. In this example,the
             anonymous class expression is part of the statement that instantiates the frenchGreeting object.
             (This explains why there is a semicolon after the closing brace.)


             */

            HelloWorld frenchGreeting = new HelloWorld()  //Anonymous class. In this example, the anonymous class expression is part of the statement that instantiates the frenchGreeting object. (This explains why there is a semicolon after the closing brace.)
            {
                String name = "tout le monde";
                public void greet()
                {
                    greetSomeone("tout le monde");
                }
                public void greetSomeone(String someone)
                {
                    name = someone;
                    System.out.println("Salut " + name);
                }
            };

            /*
            In that particular example, HelloWorld is assumed to be an interface, and the code is creating an
            anonymous class that implements the HelloWorld interface. The anonymous class provides its own
            implementation for the greet() and greetSomeone() methods defined in the HelloWorld interface.

            The line new HelloWorld() is instantiating an instance of the anonymous class, which is essentially
            creating an object that implements the HelloWorld interface. This allows you to define the
            implementation of the interface's methods inline without explicitly creating a named class.

            So, to summarize, in the context of the example provided, HelloWorld is an interface, and new
            HelloWorld() creates an instance of an ANONYMOUS CLASS (not interface) implementing that interface.
             */

            /*
         Accessing Local Variables of the Enclosing Scope, and Declaring and Accessing Members of the Anonymous Class:

        Like local classes, anonymous classes can capture variables; they have the same access to local variables of the
        enclosing scope:

        # An anonymous class has access to the members of its enclosing class.

        # An anonymous class cannot access local variables in its enclosing scope that are not declared as final or effectively final.

        # Like a nested class, a declaration of a type (such as a variable) in an anonymous class shadows any other
          declarations in the enclosing scope that have the same name. See Shadowing for more information.

        # Anonymous classes also have the same restrictions as local classes with respect to their members:

        # You cannot declare static initializers or member interfaces in an anonymous class.

        # An anonymous class can have static members provided that they are constant variables.

        # Note that you can declare the following in anonymous classes:

        1.Fields

        2.Extra methods (even if they do not implement any methods of the supertype)

        3.Instance initializers

        4.Local classes

        5.However, you cannot declare constructors in an anonymous class.
             */

            HelloWorld spanishGreeting = new HelloWorld() //anonymous class
            {
                String name = "mundo";
                public void greet() {
                    greetSomeone("mundo");
                }
                public void greetSomeone(String someone) {
                    name = someone;
                    System.out.println("Hola, " + name);
                }
            };
            englishGreeting.greet();
            frenchGreeting.greetSomeone("Fred");
            spanishGreeting.greet();
        }

        public static void main(String... args)
        {
            Anonymous_class myApp =  new Anonymous_class();
            myApp.sayHello();
        }
}

/*
Anonymous inner class is a nameless class that you use to instantiate only one object ever.
So, whenever you use an anonymous class, you will always be defining the class and instantiating the
single object of the class at the same time in a single Java statement. An anonymous class can extend
any other class or implement an interface.
 */
