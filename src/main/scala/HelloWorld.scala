object HelloWorld extends App{
  // changed class to object so it's singleton object
  // object = singleton, not class, main has to be in object (static)
  // App can also be entry point ( extends App, removes need for main method )
//  def main(args: Array[String]): Unit = {
//    // used inteliJ snippet
//    print("Hello World");
//  }
  //=====Intro=====
  println("Extends app!");

  print("Val is immutable\n");
  println("Var is mutable");
  // Who would need to change PI anyway
  val PI : Double = 3.14159;
  // statically typed ^ by can infer type like js below
  var circumfrance = PI *  10;
  println("Hope I can change this: " + circumfrance);
  circumfrance = PI * 5;
  println("Ta da: " + circumfrance);
  println("Immutable storage units are better to use");

  val infered = "I didnt have to say what type this was";
  // it gets infered at run time OR compile time since scala does both
  // Scala is statically typed but it using the inference to guess types
  println(infered);

  //=====String Operations=====
  val baseString =
    """abcdefghijklmnopqrstuvwxyz
     |  this should do
     |  pretty cool how im on 3 lines eh""";
  val string2 = " hi there! ";
  println(baseString + string2);
  // double equals is fine to do in scala since overloading
  println(baseString == string2);
  // s before string to specify placeholders (c# string format) including formulae
  val string3 = s"${string2*2} this is cool eh";
  println(string3);
  //print f notation is also available
  val string4 = f"PI is: $PI%.2f not $PI";
  println(string4);

  //=====Unified types=====
  // in scala ALL values are instances of a class, ALL
  // all types eventually inherit the single superclass, Any <- [AnyRef <- [Collection, Strings, Classes] / AnyVal <- [Char, Boolean, Int, Double]]
  // AnyRef||AnyVal <- Nothing, Null is just for AnyRef

  def printAny(x: Any) = println(x);
  def printAnyVal(y: AnyVal) = println(y);
  def printAnyRef(z: AnyRef) = println(z);
  val iAmAny = "doesnt matter what I am";
  printAny(iAmAny);
  val iAmVal = 1.1;
  printAnyVal(iAmVal);
  val iAmRef = "Yes";
  printAnyRef(iAmRef);

  //=====Emptiness in Scala=====
  // null = any ref type, not value
  // Null, a type or trait, Null is the type of null
  var x:String = null;
  var n:Null = null;
  // Nothing is a type/trait
  val emptyList = List();   // this will be: emptyList:List[Nothing], list of Nothing - it extends everything but can't be instantiated
  // Nil is a singleton instance of List[Nothing]
  // Nil would be end of list signal as Lists == Linked lists
  // None special value assosicated with an Option collection
  var optionList:Option[Double] = None; // allows for presence or absence of a value
  optionList = Option(10/2.2);
  // Unit is like void in java/c#
  def noReturn(x:Any) {println("hello")}; // function will be noReturn: (x: Any)Unit

  //=====Type operations=====
  // asInstanceOf, isInstanceOf, to<Type>, getClass
  println(10.10.asInstanceOf[Long]); // Basically casts, throws class cast ex if cast fails, Integer would throw ex
  println(10.10.toInt); // Uses system.convert utilities and throws NumFormat ex instead if fails
  println(10.10.isInstanceOf[Long]); // Tests variable type, Any or AnyRef works, AnyVal doesnt work neither does null
  println(List(10,2).getClass()); //  Returns class of object

  // Expressions and statements
  // Statements cannot be chained together, expressions can
  // Statements do not return a value (common include assigns or print)
  // Expressions do return a value, "hello" has return value of string: hello
  val rad = 5;
  var expression = {val PI = 3.14; PI * rad * rad}; // entire line is statement, right side is expression block,
  // last expression is return value for entire block, if last expression is statement then entire block is statement block
  var statement = {val PI = 3.14;};
  // if, for and match are statements in java but expressions in scala
  // expressions can be changed to one another, statements can not - composition of functions

  //=====Defining values and variables using expressions=====
  // You can use an expression to define a variable or value since they just return a value
  var radius = 10;
  val area = {
    val PI = 3.14; // Local to scope of expression block
    PI * radius * radius; // Referenced previously defined value or variable
  }
  println("Area before change of rad: " + area);
  radius  = 5;
  println("Area after being changed: " + area)
  // functions are just reusable expression blocks
  // Expressions are "r-values" (can be assigned and chained), statements are not

  //=====Nested scopes=====
  // Scala is statically scoped language
  val area2 = {
    val PI = 3;
    println(s"Inside scope 1, Pi = $PI");
    PI*radius*radius; // this is ignored since it is in outer scope, scala looks for return of inner most scope
    {
      val PI = 3.14159;
      println(s"Inside scope 2, PI = $PI");
      PI*radius*radius;
    }
  }
  println(area2);
  // Functions in scala are named reusuable expression blocks
  // First class functions and closures depend on nested blocks

  //=====If else expression blocks=====
  // Java/C# has statements, scala has expression blocks as they can be used to return value
  // The entire if/else construct is an expression block
  val ifTest =
      if(true){
        "yes, true is true";
      }else{ // if false and no else then return Nothing
        10 / 0.2;
      };
  println(ifTest); // The if returned a value since it is an expression
  // Return value will be treated as Any thanks to type inference
  // Having these as expressions If/Else statements which allows them to be functionally composed ( chained)

  //=====Match expressions=====
  //Similar to switch statements in C#/Java - Scala has match expressions
  // In scala match, only 0 or 1 'case' will evaluate to true, no need for breaks either - still need a catch-all(default)
  // Match expressions used more commonly then if/else
  var dayOfWeek = "Tuesday";
  val dayInFrench = dayOfWeek match{ // variable then declaration match
      case "Monday" => "Lundi"; // expressions so these are returned if match
      case "Tuesday" => "Mardi"; // Return value is on right of arrow symbol
      // if no match, scala match error is raised, to prevent this use a case with catch
  }
  println(dayInFrench);
  // Pattern guards and OR-ed expressions Allows a way to add conditions to clauses
  // OR-ed expressions
  //Pattern guards - adds an if onto a case
  val dayStart = dayOfWeek match{
      //case "Monday" => "Monday: 9AM"
      case "Saturday"|"Sunday" => s"$dayOfWeek: 11AM"; // Allows two or more values in a match using Or-ed
      case "Tuesday"|"Wednesday"|"Thursday"|"Friday" => s"$dayOfWeek: 10AM"; // compound OR condition inside a match
      case bankHoliday if bankHoliday == "Monday" => s"$bankHoliday: 1PM"; // Pattern guard starts with placeholder var called value binding
      // assigns match var and then if uses arrow decimeter
  }
  println(dayStart);

  // catch-all/match-all within a match expression
  val invalidMatch = "Sanday";
  val dayMatch = invalidMatch match{
      case "Monday" => "Monday match!";
      // To prevent scala match error
      // _ Is an unnamed wildcard for inputput valid, it will match anything, must be used on the left side of =>
     case _ => { // Wilcard operator patterns
        println(s"Error! Invalid say $invalidMatch"); // Does not bind the match value to any other values
        invalidMatch; // expression will return this, underscore means any value
      };
     case bankHoliday => { // Value binding patters
        println(s"Hit a bank holiday: $bankHoliday");
        bankHoliday; // This expression will be hit for any value and just return it
      };
  }
  // Underscore is a match all so used in a match it ends up catching all

  // Match expressionss - down casting with pattern variables
  // Unluke java, scala can use match to test type
  val radi:Any = 10.10; // Inference will pick what type it thinks it is
  // If we marked above as String it would through an error checking the :Int
  val testType = radi match{
     case radi:Int => "Integer";
     case radi:String => "String";
     case _:AnyRef => "Some other reference type";// Placehold _ can be used to match type
     case _ => "Any"; // you have to have a match-all catch-all to cover all scenarios (like a default)
  }
  println("Type is: " + testType);
  // Scruntinee (variable whos type is matched) must be a base type
  // This is so you use when wanting to perform a downcast, this is why you must use Any

  //=====For loops=====
  // For loops can be expressions(returns) OR statements(doesnt return)
  // For loops with yield are expressions so that is yields a collection of return values of each iteration
  val dayOfWeekList = List("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun");
  println(dayOfWeekList); // This will be a List[String] - same as List<String> in Java
  for(day <- dayOfWeekList){ // Similar to ForEach in Java, will loop through each item in list assigning to day variable
    day match{
      case "Mon" => println("Monday");
      case _ => print(day + ", ");
    }
  } // Nothing is returned from this for loop so it is a statement (no yield)
  println("-This is a pretty normal java-esque for loop");
  // Vaue binding for loop
  val looper = for(day <- dayOfWeekList) yield { // yield here will make it return values into a collection
    day match{
      case "Mon" => "Manic Monday";
      case otherDay => otherDay;
    }
  }
  println("This has been returned from the for loop: " + looper);
  // This means you dont have to collect results of loop into list with (*.add(item)) loads of times
  // List instead comes with you when used as a yield for

  // for loop different iterator types
  // you can loops through using index or every element of list
  // for above uses value binding method of looping
  val indexLooper = for( i <- 0 until dayOfWeekList.size){ // this uses index, until replaces to and means you dont need to do -1 of size
    dayOfWeekList(i);
  } // to goes up to and onto size value, until goes to the -1 value
  // These functions are aren't best way, foreach, map and flatmap are better ones to use
  // These are functions so they can be chained - still useful to know however

  //for loop with an if statement using pattern guards
  for ( day <- dayOfWeekList if day == "Mon") { // for loop with if condition too
    println("This for if was done in one line!: " + day);
  }

  //Nested for loops
  for{i <- 0 until 3 // notice no comma seperating the 2 ranges
      j <- dayOfWeekList}{ // you can also combine types of iterators, wow!
    print(s"$i, $j, ");
  }
  println("Nested for loop using a single for!");

  //=====While loops/do-while loops=====
  // Purely statements in scala not optional expressions like for
  // while use dont fit with functional so they should be used for writing to DB or something as no returns
  // while loops need mutable variables so it can terminate (var)

  //=====Functions in scala - first class functions=====
  // Partially applied and currying
  // In scala there is a clear different between functions and methods
  //**Methods are used to define object behaviour**
  def max(x:Int, y:Int): Int ={ // Def to specify defining a method, method signature specifies params and return type
    if(x>y) x else y // returns a value based on calc
  } // Curly braces are optional if the body is a single line
  // main is a method too (def main (args: Array[String]) ={ //main code }
  // in order to be static scala 'fakes it and puts it inside singleton object

  //**Functions/functon literals
  // Function literal
  (x:Int)=>x+1; // simple nameless function literal, not stored in variable or given name, but it is an object, type: function1 infered
  // Function trait has different descended types, functionx represents a function with x arguments until function23 (max arg count)
  // First class citizenship for functions:
  // Functions are objects (AnyRef) and can be stored in a variable, methods can return functions and methods can take a function as a param/arg
  // Helps for distributed computing applications

  //Method example, (methods are not objects)
  def getArea2 (radius:Double):Double ={ // Method called getArea2, takes one double param and returns a double
    val PI = 3.14;
    PI * radius * radius;
  } // This would be a method of whatever class contains this definition
  // Calling this would be className.getArea2(10) for examaple
  // Function example
  val getArea = (radius:Double) =>{
    val PI = 3.14;
    PI * radius * radius;
  }:Double //could not do this due to type inference
  // Functions are value types - can be stored in val and var, methods are not but can be converted to function objects
  // Functions have higher overhead and slightly slower, methods are faster and better for performance
  // A function is not associated with a class whereas a method is
  // Functions are basically parametrised expression blocks
  // A defined function would be type Double => Double = <function1>
  // A defined method would be: (radius: Double)Double (in GRPL)

  // Functions are named, reusable expression blocks in any scope
  // Method are named and useable in a certain scope unless converted to a function then above applies






















}
