package utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {
	private static Connection con;
	private static boolean hasData = false;
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		if(con == null) {
			// sqlite driver
			Class.forName("org.sqlite.JDBC");
			// database path, if it's new database, it will be created in the project folder
			con = DriverManager.getConnection("jdbc:sqlite:SQLiteTest1.db");
		}
		return con;
	}
	 
	public static void initialise() throws SQLException, ClassNotFoundException {
		if( !hasData ) {
			hasData = true;
			// check for database table
			getConnection();
			Statement state = con.createStatement();
			ResultSet res = state.executeQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='question_types'");
			if( !res.next()) {
				Statement state1 = con.createStatement();
				Statement state2 = con.createStatement();
				Statement state3 = con.createStatement();
				Statement state4 = con.createStatement();
				
				state1.executeUpdate("create table question_types(id integer,type varchar(60),primary key (id))");
				state2.executeUpdate("create table topics(id integer, topic varchar(60), primary key (id))");
				state3.executeUpdate("create table mcq_questions(id integer, question varchar(60), options varchar(60), answer varchar(10), diff_level varchar(10), topic_id integer, primary key (id))");
				state4.executeUpdate("create table open_questions(id integer, question varchar(60), diff_level varchar(10), topic_id integer, primary key (id))");
				
				// inserting question types
				PreparedStatement prepType = con.prepareStatement("insert into question_types values(?,?),(?,?);");
				prepType.setString(1, "1");
				prepType.setString(2, "MCQs");
				prepType.setString(3, "2");
				prepType.setString(4, "Open");
				prepType.execute();
				
				// inserting topics
				PreparedStatement prepTopics = con.prepareStatement("insert into topics values(?,?),(?,?),(?,?),(?,?),(?,?),(?,?);");
				prepTopics.setString(1, "1");
				prepTopics.setString(2, "Java Language Basics");

				prepTopics.setString(3, "2");
				prepTopics.setString(4, "Object Oriented Programming");

				prepTopics.setString(5, "3");
				prepTopics.setString(6, "JDK, JRE, JIT & JVM");
				
				prepTopics.setString(7, "4");
				prepTopics.setString(8, "Method Overriding");
				
				prepTopics.setString(9, "5");
				prepTopics.setString(10, "Overloading Methods & Argument Passing");
				
				prepTopics.setString(11, "6");
				prepTopics.setString(12, "Inheritance – Abstract Class and Super");
				prepTopics.execute();
				
				// inserting mcq questions
				// Topic : Java Language Basics
				PreparedStatement prepMCQ = con.prepareStatement("insert into mcq_questions values(?,?,?,?,?,?),(?,?,?,?,?,?),(?,?,?,?,?,?),"
																								+ "(?,?,?,?,?,?),(?,?,?,?,?,?),(?,?,?,?,?,?),"
																								+ "(?,?,?,?,?,?),(?,?,?,?,?,?),(?,?,?,?,?,?);");
				prepMCQ.setString(1, "1");
				prepMCQ.setString(2, "What is the range of short data type in Java?");
				prepMCQ.setString(3, "a) -128 to 127\r\n" + 
						"b) -32768 to 32767\r\n" + 
						"c) -2147483648 to 2147483647\r\n" + 
						"d) None of the mentioned");
				prepMCQ.setString(4, "b");
				prepMCQ.setString(5, "Level1");
				prepMCQ.setString(6, "1");
				
				prepMCQ.setString(7, "2");
				prepMCQ.setString(8, "What is the range of byte data type in Java?");
				prepMCQ.setString(9, "a) -128 to 127\r\n" + 
						"b) -32768 to 32767\r\n" + 
						"c) -2147483648 to 2147483647\r\n" + 
						"d) None of the mentioned");
				prepMCQ.setString(10, "a");
				prepMCQ.setString(11, "Level1");
				prepMCQ.setString(12, "1");
				
				prepMCQ.setString(13, "3");
				prepMCQ.setString(14, "Which of the following are legal lines of Java code?");
				prepMCQ.setString(15, "\t\t1. int w = (int)888.8;\r\n" + 
						"\t\t2. byte x = (byte)100L;\r\n" + 
						"\t\t3. long y = (byte)100;\r\n" + 
						"\t\t4. byte z = (byte)100L;\n\n" +
						"a) 1 and 2\r\n" + 
						"b) 2 and 3\r\n" + 
						"c) 3 and 4\r\n" + 
						"d) All statements are correct");
				prepMCQ.setString(16, "d");
				prepMCQ.setString(17, "Level1");
				prepMCQ.setString(18, "1");
				
				
				prepMCQ.setString(19, "4");
				prepMCQ.setString(20, "Which data type value is returned by all transcendental math functions?");
				prepMCQ.setString(21, "a) int\r\n" + 
						"b) float\r\n" + 
						"c) double\r\n" + 
						"d) long");
				prepMCQ.setString(22, "c");
				prepMCQ.setString(23, "Level2");
				prepMCQ.setString(24, "1");
				
				prepMCQ.setString(25, "5");
				prepMCQ.setString(26, "What is the output of this program?\n"+
						"\tclass increment {\r\n" + 
						"\t\tpublic static void main(String args[]) \r\n" +
						"\t\t{\r\n" + 
						"\t\t\tint g = 3;\r\n" + 
						"\t\t\tSystem.out.print(++g * 8);\r\n" + 
						"\t\t}\r\n" + 
						"\t}");
				prepMCQ.setString(27, "a) 25\r\n" + 
						"b) 24\r\n" + 
						"c) 32\r\n" + 
						"d) 33");
				prepMCQ.setString(28, "c");
				prepMCQ.setString(29, "Level2");
				prepMCQ.setString(30, "1");
				
				prepMCQ.setString(31, "6");
				prepMCQ.setString(32, "What is the output of this program?"
						+ "\tclass area {\r\n" + 
						"        public static void main(String args[]) \r\n" + 
						"        {    \r\n" + 
						"             double r, pi, a;\r\n" + 
						"             r = 9.8;\r\n" + 
						"             pi = 3.14;\r\n" + 
						"             a = pi * r * r;\r\n" + 
						"             System.out.println(a);\r\n" + 
						"        } \r\n" + 
						"    }");
				prepMCQ.setString(33, "a) 301.5656\r\n" + 
						"b) 301\r\n" + 
						"c) 301.56\r\n" + 
						"d) 301.56560000");
				prepMCQ.setString(34, "a");
				prepMCQ.setString(35, "Level2");
				prepMCQ.setString(36, "1");
				
				
				prepMCQ.setString(37, "7");
				prepMCQ.setString(38, "What is Truncation is Java?");
				prepMCQ.setString(39, "a) Floating-point value assigned to an integer type\r\n" + 
						"b) Integer value assigned to floating type\r\n" + 
						"c) Floating-point value assigned to an Floating type\r\n" + 
						"d) Integer value assigned to floating type");
				prepMCQ.setString(40, "a");
				prepMCQ.setString(41, "Level3");
				prepMCQ.setString(42, "1");
				
				prepMCQ.setString(43, "8");
				prepMCQ.setString(44, "Which of these occupy first 0 to 127 in Unicode character set used for characters in Java?");
				prepMCQ.setString(45, "a) ASCII\r\n" + 
						"b) ISO-LATIN-1\r\n" + 
						"c) None of the mentioned\r\n" + 
						"d) ASCII and ISO-LATIN1");
				prepMCQ.setString(46, "d");
				prepMCQ.setString(47, "Level3");
				prepMCQ.setString(48, "1");
				
				prepMCQ.setString(49, "9");
				prepMCQ.setString(50, "Guess the output of the following program -\n" + 
						"\tpublic class NumberSystem{\r\n" + 
						"\t\tpublic static void main(String[] args){\r\n" +  
						"\t\t\tint hexVal = 0x1a;\r\n" + 
						"\t\t\tSystem.out.println(\"Value : \" + hexVal);\r\n" +  
						"\t\t}\r\n" + 
						"}\t");
				prepMCQ.setString(51, "a) 32\r\n" + 
						"b) 24" + 
						"c) 25\r\n" + 
						"d) 26");
				prepMCQ.setString(52, "d");
				prepMCQ.setString(53, "Level3");
				prepMCQ.setString(54, "1");
				
				prepMCQ.execute();
				
				// Topic : Concepts of OOPS
				PreparedStatement prepMCQ_1 = con.prepareStatement("insert into mcq_questions values(?,?,?,?,?,?),(?,?,?,?,?,?),(?,?,?,?,?,?),"
																								+ "(?,?,?,?,?,?),(?,?,?,?,?,?),(?,?,?,?,?,?),"
																								+ "(?,?,?,?,?,?),(?,?,?,?,?,?),(?,?,?,?,?,?);");
				prepMCQ_1.setString(1, "10");
				prepMCQ_1.setString(2, "Which of the following is not OOPS concept in Java?");
				prepMCQ_1.setString(3, "a) Inheritance\r\n" + 
						"b) Encapsulation\r\n" + 
						"c) Polymorphism\r\n" + 
						"d) Compilation");
				prepMCQ_1.setString(4, "d");
				prepMCQ_1.setString(5, "Level1");
				prepMCQ_1.setString(6, "2");
				
				prepMCQ_1.setString(7, "11");
				prepMCQ_1.setString(8, "Which of the following is a type of polymorphism in Java?");
				prepMCQ_1.setString(9, "a) Compile time polymorphism\r\n" + 
						"b) Execution time polymorphism\r\n" + 
						"c) Multiple polymorphism\r\n" + 
						"d) Multilevel polymorphism");
				prepMCQ_1.setString(10, "a");
				prepMCQ_1.setString(11, "Level1");
				prepMCQ_1.setString(12, "2");
				
				prepMCQ_1.setString(13, "12");
				prepMCQ_1.setString(14, "When does method overloading is determined?");
				prepMCQ_1.setString(15, "a) At run time\r\n" + 
						"b) At compile time\r\n" + 
						"c) At coding time\r\n" + 
						"d) At execution time");
				prepMCQ_1.setString(16, "b");
				prepMCQ_1.setString(17, "Level1");
				prepMCQ_1.setString(18, "2");
				
				prepMCQ_1.setString(19, "13");
				prepMCQ_1.setString(20, "When Overloading does not occur?");
				prepMCQ_1.setString(21, "a) More than one method with same name but different method signature and different number or type of parameters\r\n" + 
						"b) More than one method with same name, same signature but different number of signature\r\n" + 
						"c) More than one method with same name, same signature, same number of parameters but different type\r\n" + 
						"d) More than one method with same name, same number of parameters and type but different signature");
				prepMCQ_1.setString(22, "d");
				prepMCQ_1.setString(23, "Level2");
				prepMCQ_1.setString(24, "2");
				
				prepMCQ_1.setString(25, "14");
				prepMCQ_1.setString(26, "Which concept of Java is a way of converting real world objects in terms of class?");
				prepMCQ_1.setString(27, "a) Polymorphism\r\n" + 
						"b) Encapsulation\r\n" + 
						"c) Abstraction\r\n" + 
						"d) Inheritance");
				prepMCQ_1.setString(28, "c");
				prepMCQ_1.setString(29, "Level2");
				prepMCQ_1.setString(30, "2");
				
				prepMCQ_1.setString(31, "15");
				prepMCQ_1.setString(32, "Which concept of Java is achieved by combining methods and attribute into a class?");
				prepMCQ_1.setString(33, "a) Encapsulation\r\n" + 
						"b) Inheritance\r\n" + 
						"c) Polymorphism\r\n" + 
						"d) Abstraction");
				prepMCQ_1.setString(34, "a");
				prepMCQ_1.setString(35, "Level2");
				prepMCQ_1.setString(36, "2");
				
				
				prepMCQ_1.setString(37, "16");
				prepMCQ_1.setString(38, "What is it called if an object has its own lifecycle and there is no owner?");
				prepMCQ_1.setString(39, "a) Aggregation\r\n" + 
						"b) Composition\r\n" + 
						"c) Encapsulation\r\n" + 
						"d) Association");
				prepMCQ_1.setString(40, "d");
				prepMCQ_1.setString(41, "Level3");
				prepMCQ_1.setString(42, "2");
				
				prepMCQ_1.setString(43, "17");
				prepMCQ_1.setString(44, "What is it called where child object gets killed if parent object is killed?");
				prepMCQ_1.setString(45, "a) Aggregation\r\n" + 
						"b) Composition\r\n" + 
						"c) Encapsulation\r\n" + 
						"d) Association");
				prepMCQ_1.setString(46, "b");
				prepMCQ_1.setString(47, "Level3");
				prepMCQ_1.setString(48, "2");
				
				prepMCQ_1.setString(49, "18");
				prepMCQ_1.setString(50, "What is it called where object has its own lifecycle and child object cannot belong to another parent object?");
				prepMCQ_1.setString(51, "a) Aggregation\r\n" + 
						"b) Composition\r\n" + 
						"c) Encapsulation\r\n" + 
						"d) Association");
				prepMCQ_1.setString(52, "a");
				prepMCQ_1.setString(53, "Level3");
				prepMCQ_1.setString(54, "2");
				
				prepMCQ_1.execute();
				
				// Topic : JDK, JRE, JIT & JVM
				PreparedStatement prepMCQ_2 = con.prepareStatement("insert into mcq_questions values(?,?,?,?,?,?),(?,?,?,?,?,?),(?,?,?,?,?,?),"
																								+ "(?,?,?,?,?,?),(?,?,?,?,?,?),(?,?,?,?,?,?),"
																								+ "(?,?,?,?,?,?),(?,?,?,?,?,?),(?,?,?,?,?,?);");
				prepMCQ_2.setString(1, "19");
				prepMCQ_2.setString(2, "Which component is used to compile, debug and execute java program?");
				prepMCQ_2.setString(3, "a) JVM\r\n" + 
						"b) JDK\r\n" + 
						"c) JIT\r\n" + 
						"d) JRE");
				prepMCQ_2.setString(4, "b");
				prepMCQ_2.setString(5, "Level1");
				prepMCQ_2.setString(6, "3");
				
				prepMCQ_2.setString(7, "20");
				prepMCQ_2.setString(8, "Which statement is true about java?");
				prepMCQ_2.setString(9, "a) Platform independent programming language\r\n" + 
						"b) Platform dependent programming language\r\n" + 
						"c) Code dependent programming language\r\n" + 
						"d) Sequence dependent programming language");
				prepMCQ_2.setString(10, "a");
				prepMCQ_2.setString(11, "Level1");
				prepMCQ_2.setString(12, "3");
				
				prepMCQ_2.setString(13, "21");
				prepMCQ_2.setString(14, "Which component is responsible to run java program?");
				prepMCQ_2.setString(15, "a) JVM\r\n" + 
						"b) JDK\r\n" + 
						"c) JIT\r\n" + 
						"d) JRE");
				prepMCQ_2.setString(16, "d");
				prepMCQ_2.setString(17, "Level1");
				prepMCQ_2.setString(18, "3");
				
				prepMCQ_2.setString(19, "22");
				prepMCQ_2.setString(20, "Which component is responsible to optimize bytecode to machine code?");
				prepMCQ_2.setString(21, "a) JVM\r\n" + 
						"b) JDK\r\n" + 
						"c) JIT\r\n" + 
						"d) JRE");
				prepMCQ_2.setString(22, "c");
				prepMCQ_2.setString(23, "Level2");
				prepMCQ_2.setString(24, "3");
				
				prepMCQ_2.setString(25, "23");
				prepMCQ_2.setString(26, "How can we identify whether a compilation unit is class or interface from a .class file?");
				prepMCQ_2.setString(27, "a) Java source file header\r\n" + 
						"b) Extension of compilation unit\r\n" + 
						"c) We cannot differentiate between class and interface\r\n" + 
						"d) The class or interface name should be postfixed with unit type");
				prepMCQ_2.setString(28, "a");
				prepMCQ_2.setString(29, "Level2");
				prepMCQ_2.setString(30, "3");
				
				prepMCQ_2.setString(31, "24");
				prepMCQ_2.setString(32, "What is use of interpreter?");
				prepMCQ_2.setString(33, "a) They convert bytecode to machine language code\r\n" + 
						"b) They read high level code and execute them\r\n" + 
						"c) They are intermediated between JIT and JVM\r\n" + 
						"d) It is a synonym for JIT");
				prepMCQ_2.setString(34, "b");
				prepMCQ_2.setString(35, "Level2");
				prepMCQ_2.setString(36, "3");
				
				prepMCQ_2.setString(37, "25");
				prepMCQ_2.setString(38, "Which component is responsible for converting bytecode into machine specific code?");
				prepMCQ_2.setString(39, "a) JVM\r\n" + 
						"b) JDK\r\n" + 
						"c) JIT\r\n" + 
						"d) JRE");
				prepMCQ_2.setString(40, "a");
				prepMCQ_2.setString(41, "Level3");
				prepMCQ_2.setString(42, "3");
				
				prepMCQ_2.setString(43, "26");
				prepMCQ_2.setString(44, "What does a Java Disassembler do?");
				prepMCQ_2.setString(45, "a) disassembles class files\r\n" + 
						"b) compiles java files\r\n" + 
						"c) interprets java files\r\n" + 
						"d) assembles class files");
				prepMCQ_2.setString(46, "a");
				prepMCQ_2.setString(47, "Level3");
				prepMCQ_2.setString(48, "3");
				
				prepMCQ_2.setString(49, "27");
				prepMCQ_2.setString(50, "Where an object of a class get stored?");
				prepMCQ_2.setString(51, "a) Heap\r\n" + 
						"b) Stack\r\n" + 
						"c) Disk\r\n" + 
						"d) File");
				prepMCQ_2.setString(52, "a");
				prepMCQ_2.setString(53, "Level3");
				prepMCQ_2.setString(54, "3");
				
				prepMCQ_2.execute();
				
				// Topic : Method Overriding
				PreparedStatement prepMCQ_3 = con.prepareStatement("insert into mcq_questions values(?,?,?,?,?,?),(?,?,?,?,?,?),(?,?,?,?,?,?),"
																								+ "(?,?,?,?,?,?),(?,?,?,?,?,?),(?,?,?,?,?,?),"
																								+ "(?,?,?,?,?,?),(?,?,?,?,?,?),(?,?,?,?,?,?);");
				prepMCQ_3.setString(1, "28");
				prepMCQ_3.setString(2, "Which of this keyword can be used in a subclass to call the constructor of superclass?");
				prepMCQ_3.setString(3, "a) super\r\n" + 
						"b) this\r\n" + 
						"c) extent\r\n" + 
						"d) extends");
				prepMCQ_3.setString(4, "a");
				prepMCQ_3.setString(5, "Level1");
				prepMCQ_3.setString(6, "4");
				
				prepMCQ_3.setString(7, "29");
				prepMCQ_3.setString(8, "Multiple inheritance means");
				prepMCQ_3.setString(9, "a) one class inheriting from more super classes\r\n" + 
						"b) more classes inheriting from one super class\r\n" + 
						"c) more classes inheriting from more super classes\r\n" + 
						"d) None of the above\r\n" + 
						"e) (a) and (b) above.");
				prepMCQ_3.setString(10, "a");
				prepMCQ_3.setString(11, "Level1");
				prepMCQ_3.setString(12, "4");
				
				prepMCQ_3.setString(13, "30");
				prepMCQ_3.setString(14, "To prevent any method from overriding, we declare the method as");
				prepMCQ_3.setString(15, "a) static\r\n"
						+ "b) const\r\n"
						+ "c) final\r\n"
						+ "d) abstract\r\n"
						+ "e) none of the above.");
				prepMCQ_3.setString(16, "c");
				prepMCQ_3.setString(17, "Level1");
				prepMCQ_3.setString(18, "4");
				
				prepMCQ_3.setString(19, "31");
				prepMCQ_3.setString(20, "What is the process of defining a method in a subclass having same name & type signature as a method in its superclass?");
				prepMCQ_3.setString(21, "a) Method overloading\r\n" + 
						"b) Method overriding\r\n" + 
						"c) Method hiding\r\n" + 
						"d) None of the mentioned");
				prepMCQ_3.setString(22, "b");
				prepMCQ_3.setString(23, "Level2");
				prepMCQ_3.setString(24, "4");
				
				prepMCQ_3.setString(25, "32");
				prepMCQ_3.setString(26, "Which of these keywords can be used to prevent Method overriding?");
				prepMCQ_3.setString(27, "a) static\r\n" + 
						"b) constant\r\n" + 
						"c) protected\r\n" + 
						"d) final");
				prepMCQ_3.setString(28, "d");
				prepMCQ_3.setString(29, "Level2");
				prepMCQ_3.setString(30, "4");
				
				prepMCQ_3.setString(31, "33");
				prepMCQ_3.setString(32, "Which of these is correct way of calling a constructor having no parameters, of superclass A by subclass B?");
				prepMCQ_3.setString(33, "a) super(void);\r\n" + 
						"b) superclass.();\r\n" + 
						"c) super.A();\r\n" + 
						"d) super();");
				prepMCQ_3.setString(34, "d");
				prepMCQ_3.setString(35, "Level2");
				prepMCQ_3.setString(36, "4");
				
				prepMCQ_3.setString(37, "34");
				prepMCQ_3.setString(38, "At line number 2 below, choose 3 valid data-type attributes/qualifiers among “final, static, native, public, private, abstract, protected”\n"
						+ "public interface Status\r\n" + 
						"   {\r\n" + 
						"        /* insert qualifier here */ int MY_VALUE = 10;\r\n" + 
						"   }");
				prepMCQ_3.setString(39, "a) final, native, private\r\n" + 
						"b) final, static, protected\r\n" + 
						"c) final, private, abstract\r\n" + 
						"d) final, static, public");
				prepMCQ_3.setString(40, "d");
				prepMCQ_3.setString(41, "Level3");
				prepMCQ_3.setString(42, "4");
				
				prepMCQ_3.setString(43, "35");
				prepMCQ_3.setString(44, "Which of these is supported by method overriding in Java?");
				prepMCQ_3.setString(45, "a) Abstraction\r\n" + 
						"b) Encapsulation\r\n" + 
						"c) Polymorphism\r\n" + 
						"d) None of the mentioned");
				prepMCQ_3.setString(46, "c");
				prepMCQ_3.setString(47, "Level3");
				prepMCQ_3.setString(48, "4");
				
				prepMCQ_3.setString(49, "36");
				prepMCQ_3.setString(50, "What is the output of this program?\n"
						+ "class Abc\r\n" + 
						"  {\r\n" + 
						"      public static void main(String[]args)\r\n" + 
						"      {\r\n" + 
						"          String[] elements = { \"for\", \"tea\", \"too\" };\r\n" + 
						"          String first = (elements.length > 0) ? elements[0]: null;\r\n" + 
						"      }\r\n" + 
						"  }");
				prepMCQ_3.setString(51, "a) Compilation error\r\n" + 
						"b) An exception is thrown at run time\r\n" + 
						"c) The variable first is set to null\r\n" + 
						"d) The variable first is set to elements[0].");
				prepMCQ_3.setString(52, "d");
				prepMCQ_3.setString(53, "Level3");
				prepMCQ_3.setString(54, "4");
				
				prepMCQ_3.execute();
				
				// Topic : Overloading Methods & Argument Passing
				PreparedStatement prepMCQ_4 = con.prepareStatement("insert into mcq_questions values(?,?,?,?,?,?),(?,?,?,?,?,?),(?,?,?,?,?,?),"
																+ "(?,?,?,?,?,?),(?,?,?,?,?,?),(?,?,?,?,?,?),"
																+ "(?,?,?,?,?,?),(?,?,?,?,?,?);");
				prepMCQ_4.setString(1, "37");
				prepMCQ_4.setString(2, "What is the process of defining two or more methods within same class that have same name but different parameters declaration?");
				prepMCQ_4.setString(3, "a) method overloading\r\n" + 
						"b) method overriding\r\n" + 
						"c) method hiding\r\n" + 
						"d) none of the mentioned");
				prepMCQ_4.setString(4, "a");
				prepMCQ_4.setString(5, "Level1");
				prepMCQ_4.setString(6, "5");
				
				prepMCQ_4.setString(7, "38");
				prepMCQ_4.setString(8, "Which of these can be overloaded?");
				prepMCQ_4.setString(9, "a) Methods\r\n" + 
						"b) Constructors\r\n" + 
						"c) All of the mentioned\r\n" + 
						"d) None of the mentioned");
				prepMCQ_4.setString(10, "c");
				prepMCQ_4.setString(11, "Level1");
				prepMCQ_4.setString(12, "5");
				
				prepMCQ_4.setString(13, "39");
				prepMCQ_4.setString(14, "Which of these is correct about passing an argument by call-by-value process?");
				prepMCQ_4.setString(15, "a) Copy of argument is made into the formal parameter of the subroutine\r\n" + 
						"b) Reference to original argument is passed to formal parameter of the subroutine\r\n" + 
						"c) Copy of argument is made into the formal parameter of the subroutine and changes made on parameters of subroutine have effect on original argument\r\n" + 
						"d) Reference to original argument is passed to formal parameter of the subroutine and changes made on parameters of subroutine have effect on original argument");
				prepMCQ_4.setString(16, "a");
				prepMCQ_4.setString(17, "Level2");
				prepMCQ_4.setString(18, "5");
				
				prepMCQ_4.setString(19, "40");
				prepMCQ_4.setString(20, "What is the process of defining a method in terms of itself, that is a method that calls itself?");
				prepMCQ_4.setString(21, "a) Polymorphism\r\n" + 
						"b) Abstraction\r\n" + 
						"c) Encapsulation\r\n" + 
						"d) Recursion");
				prepMCQ_4.setString(22, "d");
				prepMCQ_4.setString(23, "Level2");
				prepMCQ_4.setString(24, "5");
				
				prepMCQ_4.setString(25, "41");
				prepMCQ_4.setString(26, "What is the output of the following code?\n"
						+ "class San\r\n" + 
						"{\r\n" + 
						" public void m1 (int i,float f)\r\n" + 
						" {\r\n" + 
						"  System.out.println(\" int float method\");\r\n" + 
						" }\r\n" + 
						" \r\n" + 
						" public void m1(float f,int i);\r\n" + 
						"  {\r\n" + 
						"  System.out.println(\"float int method\");\r\n" + 
						"  }\r\n" + 
						" \r\n" + 
						"  public static void main(String[]args)\r\n" + 
						"  {\r\n" + 
						"    San s=new San();\r\n" + 
						"        s.m1(20,20);\r\n" + 
						"  }\r\n" + 
						"}");
				prepMCQ_4.setString(27, "a) int float method\r\n" + 
						"b) float int method\r\n" + 
						"c) compile time error\r\n" + 
						"d) run time error");
				prepMCQ_4.setString(28, "c");
				prepMCQ_4.setString(29, "Level2");
				prepMCQ_4.setString(30, "5");
				
				prepMCQ_4.setString(31, "42");
				prepMCQ_4.setString(32, "What is the output of this program?\n"
						+ "class overload \r\n" + 
						"    {\r\n" + 
						"        int x;\r\n" + 
						" 	int y;\r\n" + 
						"        void add(int a) \r\n" + 
						"        {\r\n" + 
						"            x =  a + 1;\r\n" + 
						"        }\r\n" + 
						"        void add(int a, int b)\r\n" + 
						"        {\r\n" + 
						"            x =  a + 2;\r\n" + 
						"        }        \r\n" + 
						"    }    \r\n" + 
						"    class Overload_methods \r\n" + 
						"    {\r\n" + 
						"        public static void main(String args[])\r\n" + 
						"        {\r\n" + 
						"            overload obj = new overload();   \r\n" + 
						"            int a = 0;\r\n" + 
						"            obj.add(6);\r\n" + 
						"            System.out.println(obj.x);     \r\n" + 
						"        }\r\n" + 
						"   }");
				prepMCQ_4.setString(33, "a) 5\r\n" + 
						"b) 6\r\n" + 
						"c) 7\r\n" + 
						"d) 8");
				prepMCQ_4.setString(34, "c");
				prepMCQ_4.setString(35, "Level3");
				prepMCQ_4.setString(36, "5");
				
				prepMCQ_4.setString(37, "43");
				prepMCQ_4.setString(38, "What is the output of this program?\n"
						+ "class overload \r\n" + 
						"    {\r\n" + 
						"        int x;\r\n" + 
						" 	int y;\r\n" + 
						"        void add(int a)\r\n" + 
						"        {\r\n" + 
						"            x =  a + 1;\r\n" + 
						"        }\r\n" + 
						"        void add(int a , int b)\r\n" + 
						"        {\r\n" + 
						"            x =  a + 2;\r\n" + 
						"        }        \r\n" + 
						"    }    \r\n" + 
						"    class Overload_methods \r\n" + 
						"    {\r\n" + 
						"        public static void main(String args[])\r\n" + 
						"        {\r\n" + 
						"            overload obj = new overload();   \r\n" + 
						"            int a = 0;\r\n" + 
						"            obj.add(6, 7);\r\n" + 
						"            System.out.println(obj.x);     \r\n" + 
						"        }\r\n" + 
						"    }");
				prepMCQ_4.setString(39, "a) 6\r\n" + 
						"b) 7\r\n" + 
						"c) 8\r\n" + 
						"d) 9");
				prepMCQ_4.setString(40, "c");
				prepMCQ_4.setString(41, "Level3");
				prepMCQ_4.setString(42, "5");
				
				prepMCQ_4.setString(43, "44");
				prepMCQ_4.setString(44, "What is the output of this program?\n"
						+ "class test \r\n" + 
						"    {\r\n" + 
						"        int a;\r\n" + 
						"        int b;\r\n" + 
						"        void meth(int i , int j) \r\n" + 
						"        {\r\n" + 
						"            i *= 2;\r\n" + 
						"            j /= 2;\r\n" + 
						"        }          \r\n" + 
						"    }    \r\n" + 
						"    class Output \r\n" + 
						"    {\r\n" + 
						"        public static void main(String args[])\r\n" + 
						"        {\r\n" + 
						"            test obj = new test();\r\n" + 
						"	    int a = 10;\r\n" + 
						"            int b = 20;             \r\n" + 
						"            obj.meth(a , b);\r\n" + 
						"            System.out.println(a + \" \" + b);        \r\n" + 
						"        } \r\n" + 
						"    }");
				prepMCQ_4.setString(45, "a) 10 20\r\n" + 
						"b) 20 10\r\n" + 
						"c) 20 40\r\n" + 
						"d) 40 20");
				prepMCQ_4.setString(46, "a");
				prepMCQ_4.setString(47, "Level3");
				prepMCQ_4.setString(48, "5");
				
				prepMCQ_4.execute();
				
				// Topic : Inheritance – Abstract Class and Super
				PreparedStatement prepMCQ_5 = con.prepareStatement("insert into mcq_questions values(?,?,?,?,?,?),(?,?,?,?,?,?),(?,?,?,?,?,?),"
																+ "(?,?,?,?,?,?),(?,?,?,?,?,?),(?,?,?,?,?,?),"
																+ "(?,?,?,?,?,?),(?,?,?,?,?,?),(?,?,?,?,?,?);");
				prepMCQ_5.setString(1, "45");
				prepMCQ_5.setString(2, "Which of these keywords are used to define an abstract class?");
				prepMCQ_5.setString(3, "a) abst\r\n" + 
						"b) abstract\r\n" + 
						"c) Abstract\r\n" + 
						"d) abstract class");
				prepMCQ_5.setString(4, "b");
				prepMCQ_5.setString(5, "Level1");
				prepMCQ_5.setString(6, "6");
				
				prepMCQ_5.setString(7, "46");
				prepMCQ_5.setString(8, "If a class inheriting an abstract class does not define all of its function then it will be known as?");
				prepMCQ_5.setString(9, "a) Abstract\r\n" + 
						"b) A simple class\r\n" + 
						"c) Static class\r\n" + 
						"d) None of the mentioned");
				prepMCQ_5.setString(10, "a");
				prepMCQ_5.setString(11, "Level1");
				prepMCQ_5.setString(12, "6");
				
				prepMCQ_5.setString(13, "47");
				prepMCQ_5.setString(14, "Which of these is not a correct statement?");
				prepMCQ_5.setString(15, "a) Every class containing abstract method must be declared abstract\r\n" + 
						"b) Abstract class defines only the structure of the class not its implementation\r\n" + 
						"c) Abstract class can be initiated by new operator\r\n" + 
						"d) Abstract class can be inherited");
				prepMCQ_5.setString(16, "c");
				prepMCQ_5.setString(17, "Level1");
				prepMCQ_5.setString(18, "6");
				
				prepMCQ_5.setString(19, "48");
				prepMCQ_5.setString(20, "Which of these is not abstract?");
				prepMCQ_5.setString(21, "a) Thread\r\n" + 
						"b) AbstractList\r\n" + 
						"c) List\r\n" + 
						"d) None of the Mentioned");
				prepMCQ_5.setString(22, "a");
				prepMCQ_5.setString(23, "Level2");
				prepMCQ_5.setString(24, "6");
				
				prepMCQ_5.setString(25, "49");
				prepMCQ_5.setString(26, "Which of these packages contains abstract keyword?");
				prepMCQ_5.setString(27, "a) java.lang\r\n" + 
						"b) java.util\r\n" + 
						"c) java.io\r\n" + 
						"d) java.system");
				prepMCQ_5.setString(28, "a");
				prepMCQ_5.setString(29, "Level2");
				prepMCQ_5.setString(30, "6");
				
				prepMCQ_5.setString(31, "50");
				prepMCQ_5.setString(32, "What is the output of this program?\n"
						+ "class A \r\n" + 
						"    {\r\n" + 
						"        public int i;\r\n" + 
						"        private int j;\r\n" + 
						"    }    \r\n" + 
						"    class B extends A \r\n" + 
						"    {\r\n" + 
						"        void display() \r\n" + 
						"        {\r\n" + 
						"            super.j = super.i + 1;\r\n" + 
						"            System.out.println(super.i + \" \" + super.j);\r\n" + 
						"        }\r\n" + 
						"    }    \r\n" + 
						"    class inheritance \r\n" + 
						"   {\r\n" + 
						"        public static void main(String args[])\r\n" + 
						"        {\r\n" + 
						"            B obj = new B();\r\n" + 
						"            obj.i=1;\r\n" + 
						"            obj.j=2;   \r\n" + 
						"            obj.display();     \r\n" + 
						"        }\r\n" + 
						"   }");
				prepMCQ_5.setString(33, "a) 2 2\r\n" + 
						"b) 3 3\r\n" + 
						"c) Runtime Error\r\n" + 
						"d) Compilation Error");
				prepMCQ_5.setString(34, "d");
				prepMCQ_5.setString(35, "Level2");
				prepMCQ_5.setString(36, "6");
				
				prepMCQ_5.setString(37, "51");
				prepMCQ_5.setString(38, "What is the output of this program?\n"
						+ "class A \r\n" + 
						"    {\r\n" + 
						"        public int i;\r\n" + 
						"        public int j;\r\n" + 
						"        A() \r\n" + 
						"       {\r\n" + 
						"            i = 1;\r\n" + 
						"            j = 2;\r\n" + 
						"	}\r\n" + 
						"    }    \r\n" + 
						"    class B extends A \r\n" + 
						"    {\r\n" + 
						"        int a;\r\n" + 
						"	B() \r\n" + 
						"        {\r\n" + 
						"            super();\r\n" + 
						"        } \r\n" + 
						"    }    \r\n" + 
						"    class super_use \r\n" + 
						"    {\r\n" + 
						"        public static void main(String args[])\r\n" + 
						"        {\r\n" + 
						"            B obj = new B();\r\n" + 
						"            System.out.println(obj.i + \" \" + obj.j)     \r\n" + 
						"        }\r\n" + 
						"   }");
				prepMCQ_5.setString(39, "a) 1 2\r\n" + 
						"b) 2 1\r\n" + 
						"c) Runtime Error\r\n" + 
						"d) Compilation Error");
				prepMCQ_5.setString(40, "a");
				prepMCQ_5.setString(41, "Level3");
				prepMCQ_5.setString(42, "6");
				
				prepMCQ_5.setString(43, "52");
				prepMCQ_5.setString(44, "What is the output of this program?\n"
						+ "class A \r\n" + 
						"    {\r\n" + 
						"        int i;\r\n" + 
						"        void display() \r\n" + 
						"        {\r\n" + 
						"            System.out.println(i);\r\n" + 
						"        }\r\n" + 
						"    }    \r\n" + 
						"    class B extends A \r\n" + 
						"    {\r\n" + 
						"        int j;\r\n" + 
						"        void display() \r\n" + 
						"        {\r\n" + 
						"            System.out.println(j);\r\n" + 
						"        }\r\n" + 
						"    }    \r\n" + 
						"    class method_overriding \r\n" + 
						"    {\r\n" + 
						"        public static void main(String args[])\r\n" + 
						"        {\r\n" + 
						"            B obj = new B();\r\n" + 
						"            obj.i=1;\r\n" + 
						"            obj.j=2;   \r\n" + 
						"            obj.display();     \r\n" + 
						"        }\r\n" + 
						"   }");
				prepMCQ_5.setString(45, "a) 0\r\n" + 
						"b) 1\r\n" + 
						"c) 2\r\n" + 
						"d) Compilation Error");
				prepMCQ_5.setString(46, "c");
				prepMCQ_5.setString(47, "Level3");
				prepMCQ_5.setString(48, "6");
				
				prepMCQ_5.setString(49, "53");
				prepMCQ_5.setString(50, "What is the output of this program?\n"
						+ " class A \r\n" + 
						"    {\r\n" + 
						"        public int i;\r\n" + 
						"        protected int j;\r\n" + 
						"    }    \r\n" + 
						"    class B extends A \r\n" + 
						"    {\r\n" + 
						"        int j;\r\n" + 
						"        void display() \r\n" + 
						"        {\r\n" + 
						"            super.j = 3;\r\n" + 
						"            System.out.println(i + \" \" + j);\r\n" + 
						"        }\r\n" + 
						"    }    \r\n" + 
						"    class Output \r\n" + 
						"    {\r\n" + 
						"        public static void main(String args[])\r\n" + 
						"        {\r\n" + 
						"            B obj = new B();\r\n" + 
						"            obj.i=1;\r\n" + 
						"            obj.j=2;   \r\n" + 
						"            obj.display();     \r\n" + 
						"        }\r\n" + 
						"   }");
				prepMCQ_5.setString(51, "a) 1 2\r\n" + 
						"b) 2 1\r\n" + 
						"c) 1 3\r\n" + 
						"d) 3 1");
				prepMCQ_5.setString(52, "a");
				prepMCQ_5.setString(53, "Level3");
				prepMCQ_5.setString(54, "6");
				
				prepMCQ_5.execute();
			
				// inserting open questions
				// Topic : Java Language Basics
				PreparedStatement prepOpen = con.prepareStatement("insert into open_questions values(?,?,?,?),(?,?,?,?),(?,?,?,?),(?,?,?,?),(?,?,?,?),(?,?,?,?),"
																								+ "(?,?,?,?),(?,?,?,?),(?,?,?,?),(?,?,?,?),(?,?,?,?),(?,?,?,?),"
																								+ "(?,?,?,?),(?,?,?,?),(?,?,?,?),(?,?,?,?),(?,?,?,?),(?,?,?,?),"
																								+ "(?,?,?,?),(?,?,?,?),(?,?,?,?),(?,?,?,?),(?,?,?,?),(?,?,?,?),"
																								+ "(?,?,?,?),(?,?,?,?),(?,?,?,?),(?,?,?,?),(?,?,?,?),(?,?,?,?),"
																								+ "(?,?,?,?),(?,?,?,?),(?,?,?,?),(?,?,?,?),(?,?,?,?),(?,?,?,?);");
				
				prepOpen.setString(1, "1");
				prepOpen.setString(2, "Why Java is platform independent?");
				prepOpen.setString(3, "Level1");
				prepOpen.setString(4, "1");
				
				prepOpen.setString(5, "2");
				prepOpen.setString(6, "What are wrapper classes?");
				prepOpen.setString(7, "Level1");
				prepOpen.setString(8, "1");
				
				prepOpen.setString(9, "3");
				prepOpen.setString(10, "Explain public static void main(String args[]).");
				prepOpen.setString(11, "Level2");
				prepOpen.setString(12, "1");
				
				prepOpen.setString(13, "4");
				prepOpen.setString(14, "What is multiple inheritance? Is it supported by Java?");
				prepOpen.setString(15, "Level2");
				prepOpen.setString(16, "1");
				
				prepOpen.setString(17, "5");
				prepOpen.setString(18, "Difference between long.Class and Long.TYPE ?");
				prepOpen.setString(19, "Level3");
				prepOpen.setString(20, "1");
				
				prepOpen.setString(21, "6");
				prepOpen.setString(22, "Can you override a private or static method in Java?");
				prepOpen.setString(23, "Level3");
				prepOpen.setString(24, "1");
				
				// Topic : Object Oriented Programming
				prepOpen.setString(25, "7");
				prepOpen.setString(26, "Why java is not 100% Object-oriented?");
				prepOpen.setString(27, "Level1");
				prepOpen.setString(28, "2");
				
				prepOpen.setString(29, "8");
				prepOpen.setString(30, "What is runtime polymorphism or dynamic method dispatch?");
				prepOpen.setString(31, "Level1");
				prepOpen.setString(32, "2");
				
				prepOpen.setString(33, "9");
				prepOpen.setString(34, "What is association?");
				prepOpen.setString(35, "Level2");
				prepOpen.setString(36, "2");
				
				prepOpen.setString(37, "10");
				prepOpen.setString(38, "What do you mean by aggregation?");
				prepOpen.setString(39, "Level2");
				prepOpen.setString(40, "2");
				
				prepOpen.setString(41, "11");
				prepOpen.setString(42, "Why java is not 100% Object-oriented?");
				prepOpen.setString(43, "Level3");
				prepOpen.setString(44, "2");
				
				prepOpen.setString(45, "12");
				prepOpen.setString(46, "What is composition in Java?");
				prepOpen.setString(47, "Level3");
				prepOpen.setString(48, "2");
				
				
				// Topic : JDK, JRE, JIT & JVM
				prepOpen.setString(49, "13");
				prepOpen.setString(50, "Explain JDK, JRE and JVM?");
				prepOpen.setString(51, "Level1");
				prepOpen.setString(52, "3");
				
				prepOpen.setString(53, "14");
				prepOpen.setString(54, "What are the differences between Heap and Stack Memory?");
				prepOpen.setString(55, "Level1");
				prepOpen.setString(56, "3");
				
				prepOpen.setString(57, "15");
				prepOpen.setString(58, "Why Doesn't The Main Method Throw An Error With No Arguments?");
				prepOpen.setString(59, "Level2");
				prepOpen.setString(60, "3");
				
				prepOpen.setString(61, "16");
				prepOpen.setString(62, "Why Do We Only Use The Main Method To Start A Program?");
				prepOpen.setString(63, "Level2");
				prepOpen.setString(64, "3");
				
				prepOpen.setString(65, "17");
				prepOpen.setString(66, "Can The Main Method Be Declared Final?");
				prepOpen.setString(67, "Level3");
				prepOpen.setString(68, "3");
				
				prepOpen.setString(69, "18");
				prepOpen.setString(70, "What does -Xmx and -Xms parameters mean?");
				prepOpen.setString(71, "Level3");
				prepOpen.setString(72, "3");
				
				// Topic : Method Overriding
				prepOpen.setString(73, "19");
				prepOpen.setString(74, "What is method overriding?");
				prepOpen.setString(75, "Level1");
				prepOpen.setString(76, "4");
				
				prepOpen.setString(77, "20");
				prepOpen.setString(78, "Difference between method overloading and overriding?");
				prepOpen.setString(79, "Level1");
				prepOpen.setString(80, "4");
				
				prepOpen.setString(81, "21");
				prepOpen.setString(82, "Why is method overriding known as runtime polymorphism?");
				prepOpen.setString(83, "Level2");
				prepOpen.setString(84, "4");
				
				prepOpen.setString(85, "22");
				prepOpen.setString(86, "Can we override static method in Java?");
				prepOpen.setString(87, "Level2");
				prepOpen.setString(88, "4");
				
				prepOpen.setString(89, "23");
				prepOpen.setString(90, "What will be the outcome of the following program?\n"
						+ "class X\r\n" + 
						"{\r\n" + 
						"	void calculate(int a, int b)\r\n" + 
						"	{\r\n" + 
						"		System.out.println(\"Class X\");\r\n" + 
						"	}\r\n" + 
						"}\r\n" + 
						"\r\n" + 
						"class Y extends X\r\n" + 
						"{\r\n" + 
						"	@Override\r\n" + 
						"	void calculate(int a, int b)\r\n" + 
						"	{\r\n" + 
						"		System.out.println(\"Class Y\");\r\n" + 
						"	}\r\n" + 
						"}\r\n" + 
						"\r\n" + 
						"class Z extends Y\r\n" + 
						"{\r\n" + 
						"	@Override\r\n" + 
						"	void calculate(int a, int b) \r\n" + 
						"	{\r\n" + 
						"		System.out.println(\"Class Z\");\r\n" + 
						"	}\r\n" + 
						"}\r\n" + 
						"\r\n" + 
						"public class MainClass \r\n" + 
						"{	\r\n" + 
						"	public static void main(String[] args)\r\n" + 
						"	{\r\n" + 
						"		X x = new Y();\r\n" + 
						"		\r\n" + 
						"		x.calculate(10, 20);\r\n" + 
						"		\r\n" + 
						"		Y y = (Y) x;\r\n" + 
						"		\r\n" + 
						"		y.calculate(50, 100);\r\n" + 
						"		\r\n" + 
						"		Z z = (Z) y;\r\n" + 
						"		\r\n" + 
						"		z.calculate(100, 200);\r\n" + 
						"	}\r\n" + 
						"}");
				prepOpen.setString(91, "Level3");
				prepOpen.setString(92, "4");
				
				prepOpen.setString(93, "24");
				prepOpen.setString(94, "Can you prevent overriding a method without using final modifier?");
				prepOpen.setString(95, "Level3");
				prepOpen.setString(96, "4");
				
				// Topic : Overloading Methods & Argument Passing
				prepOpen.setString(97, "25");
				prepOpen.setString(98, "What is method overloading?");
				prepOpen.setString(99, "Level1");
				prepOpen.setString(100, "5");
				
				prepOpen.setString(101, "26");
				prepOpen.setString(102, "Can The Main Method Be Overloaded?");
				prepOpen.setString(103, "Level1");
				prepOpen.setString(104, "5");
				
				prepOpen.setString(105, "27");
				prepOpen.setString(106, "What is method signature? What are the things it consist of?");
				prepOpen.setString(107, "Level2");
				prepOpen.setString(108, "5");
				
				prepOpen.setString(109, "28");
				prepOpen.setString(110, "Can we declare one overloaded method as static and another one as non-static?");
				prepOpen.setString(111, "Level2");
				prepOpen.setString(112, "5");
				
				prepOpen.setString(113, "29");
				prepOpen.setString(114, "How do compiler differentiate overloaded methods from duplicate methods?");
				prepOpen.setString(115, "Level3");
				prepOpen.setString(116, "5");
				
				prepOpen.setString(117, "30");
				prepOpen.setString(118, "Can overloaded methods be synchronized?");
				prepOpen.setString(119, "Level3");
				prepOpen.setString(120, "5");
				
				// Topic : Inheritance – Abstract Class and Super
				prepOpen.setString(121, "31");
				prepOpen.setString(122, "What is the difference between abstract classes and interfaces?");
				prepOpen.setString(123, "Level1");
				prepOpen.setString(124, "6");
				
				prepOpen.setString(125, "32");
				prepOpen.setString(126, "Can abstract class have constructors in Java?");
				prepOpen.setString(127, "Level1");
				prepOpen.setString(128, "6");
				
				prepOpen.setString(129, "33");
				prepOpen.setString(130, "Can abstract class implements interface in Java? does they require to implement all methods?");
				prepOpen.setString(131, "Level2");
				prepOpen.setString(132, "6");
				
				prepOpen.setString(133, "34");
				prepOpen.setString(134, "Can abstract class be final in Java?");
				prepOpen.setString(135, "Level2");
				prepOpen.setString(136, "6");
				
				prepOpen.setString(137, "35");
				prepOpen.setString(138, "Is it necessary for abstract class to have abstract method?");
				prepOpen.setString(139, "Level3");
				prepOpen.setString(140, "6");
				
				prepOpen.setString(141, "36");
				prepOpen.setString(142, "When do you favor abstract class over interface?");
				prepOpen.setString(143, "Level3");
				prepOpen.setString(144, "6");
				
				prepOpen.execute();
			} 
		}
	}
}
