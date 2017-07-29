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
      case "Monday" => "Lundi" // expressions so these are returned if match
      case "Tuesday" => "Mardi" // Return value is on right of arrow symbol
      // if no match, scala match error is raised, to prevent this use a case with catch
  }
  println(dayInFrench);
  // Pattern guards and OR-ed expressions Allows a way to add conditions to clauses
  // OR-ed expressions
  //Pattern guards - adds an if onto a case
  val dayStart = dayOfWeek match{
      //case "Monday" => "Monday: 9AM"
      case "Saturday"|"Sunday" => s"$dayOfWeek: 11AM" // Allows two or more values in a match using Or-ed
      case "Tuesday"|"Wednesday"|"Thursday"|"Friday" => s"$dayOfWeek: 10AM" // compound OR condition inside a match
      case bankHoliday if bankHoliday == "Monday" => s"$bankHoliday: 1PM" // Pattern guard starts with placeholder var called value binding
      // assigns match var and then if uses arrow decimeter
  }
  println(dayStart);
















}
