import java.util.Scanner;
import java.util.*;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors

public class LP5{
    public static void main(String argv[ ]) throws Exception{

        File fileObj = new File("ALevel.java"); 
        Scanner fileIn = new Scanner(fileObj);
        String wholeProgram = ""; 
    
        while (fileIn.hasNext()){
           wholeProgram = wholeProgram+"\n"+fileIn.nextLine(); 
        }
        System.out.println(wholeProgram);
        fileIn.close(); 

//Matches util statement 
        String utilPattern="import java\\.util\\.\\*\\;";
        String fixedInput=wholeProgram.replaceAll(utilPattern,""); 
        System.out.println("Util fixed:" + fixedInput);
//Matches public class x 
        String classPattern= "(public )?class [A-Z]\\w*";  
        fixedInput=fixedInput.replaceAll(classPattern,"C"); //replaces class def
        System.out.println("class def fix:" + fixedInput); 
//Matches Scanner 
        String scanPattern="Scanner sc = new Scanner\\(System\\.in\\); ";
        fixedInput=fixedInput.replaceAll(scanPattern,"S");
        System.out.println("Scanner fixed:" + fixedInput); 
//Matches Comments 
        String comPattern="(//).*"; 
        fixedInput=fixedInput.replaceAll(comPattern,"");
        System.out.println("comments fixed:" + fixedInput); 
// Matches public static void 
        String defPattern="public static void main\\(String argv\\[ \\]\\) throws Exception"; 
        fixedInput=fixedInput.replaceAll(defPattern,"D"); //replaces public static main 
        System.out.println("public static fixed:" + fixedInput); 

//Matches Print statement 
        String numPattern = "(\\d+\\.*\\d*)";
        //String doublePattern="\\b\\d+\\.\\d+\\b"; 
       // String idPattern = "([^R|X]\\b[a-z]+\\b|[^R|X]\\b[a-z][A-Z]+\\b)";
       String idPattern = "\\b[a-z]\\w*\\b";
       // String p2Pattern ="System\\.out\\.println\\("+idPattern+"\\);"; 
       // String p1Pattern= "System\\.out\\.println\\(\"\\w.*\"\\);";


        //fixedInput=fixedInput.replaceAll(p1Pattern,"S").replaceAll(p2Pattern,"S"); 
        System.out.println("Fixed Printing:" + fixedInput); 
//Reserving Print ln
String printPattern="System\\.out\\.println";
fixedInput=fixedInput.replaceAll(printPattern,"B"); 
System.out.println("reserving println:" + fixedInput); 

String strPattern="(\"(.)*?\")"; 
fixedInput=fixedInput.replaceAll(strPattern,"T");
System.out.println("String:" + fixedInput); 
            

//Matches Numbers and reserve words  
        String rPattern="(int)|(String)|(float)|(char)|(double)|(boolean)";
        fixedInput=fixedInput.replaceAll(rPattern,"R").replaceAll(numPattern,"X"); // repalces reserve words and numbers with ID
        System.out.println("Reserve and Num fixed:" + fixedInput); 


////Matches public static
String funPattern="public static R  ([a-z]\\w*)\\(R \\w*\\)";
fixedInput=fixedInput.replaceAll(funPattern,"F");
System.out.println("public static function fixed:" + fixedInput); 

//Matches Scanner save
        String ssPattern="R \\b[a-z]\\w*\\b = sc\\.next(Double|Int|Line|Float)\\(\\);";
        fixedInput=fixedInput.replaceAll(ssPattern,"S");
        System.out.println("Scanner save:"+ fixedInput); 
     

//Matching if/else
        String ifPattern="(if)";
        String elifPattern="(elif)";
        String elsePattern="(else)"; 
        String whilePattern="(while)"; 
        String forPattern="(for)";
        fixedInput=fixedInput.replaceAll(ifPattern,"Rif").replaceAll(elifPattern,"Relif").replaceAll(elsePattern,"Relse").replaceAll(whilePattern,"Rwhile").replaceAll(forPattern,"Rfor");
        System.out.println("ifelse reserve:" + fixedInput);
//return Pattern
        String rtPattern="return\\(([a-z]\\w*)\\);";
        fixedInput=fixedInput.replaceAll(rtPattern,"S");
        System.out.println("return pattern fixed:" + fixedInput);
 
//Matches ID Pattern              
        fixedInput = fixedInput.replaceAll(idPattern,"ID"); // has to be on the left (need to make sure that stays ID if on left)
        System.out.println("ID fixed: " + fixedInput);
//Condtional Operators 
String coPattern="(<|>|<=|>=|!=|==)";
fixedInput=fixedInput.replaceAll(coPattern,"C"); 
System.out.println("Relational Operators:"+ fixedInput);

//Fix Loop interation
String liPattern="(ID)\\+\\+";
fixedInput=fixedInput.replaceAll(liPattern,"S");
System.out.println("Loop Interation fixed: " + fixedInput);


//Matches Opertors
String opPattern="[+*\\-\\/]";
fixedInput=fixedInput.replaceAll(opPattern,"OP"); // for loop or if else, matching the character
System.out.println("OP fixed: " + fixedInput);

//Print statement 



//Matches system.println
//String s1Pattern="R\\((ID|OP|(\\w|\\s|\\W)*)\\);";
//fixedInput=fixedInput.replaceAll(s1Pattern,"S");
//System.out.println("system fixed:"+ fixedInput); 

//Removes white space
fixedInput=fixedInput.replaceAll("\\s",""); 
System.out.println("Space fixed: " + fixedInput);

//fixed priniting 

String id1Pattern="(OP)*?ID(OP)*?";
String p5Pattern="R\\((D|T|OP)*\\);"; 


fixedInput=fixedInput.replaceAll(id1Pattern,"D");
System.out.println("OVO variable replace:" + fixedInput); 

fixedInput=fixedInput.replaceAll(p5Pattern,"S");  
System.out.println("Final printing fix" + fixedInput); 



//Arethmatic expressions 
String a1Pattern="((ID|X)OP(X|ID))"; 
String a2Pattern="(AOP\\(*A\\)*)";
String aexPattern="R(ID|X)=(A|A1);";

fixedInput=fixedInput.replaceAll(a1Pattern,"A");
System.out.println("AOP1:" + fixedInput);
fixedInput=fixedInput.replaceAll(a2Pattern, "A1"); 
System.out.println("AOP2 :" + fixedInput); 
fixedInput=fixedInput.replaceAll(aexPattern, "S"); 
System.out.println("Araethmatic expression to statemnet:" + fixedInput); 

       
//Legal Condtions 
        String lCpattern="(X|ID|A)C(X|ID|A)*;*"; 
        fixedInput=fixedInput.replaceAll(lCpattern,"S"); 
        System.out.println("fixed Logical Operators Statement:" + fixedInput); 
//Condtional Operators
        String copPattern="(&&)|(\\|\\|)";
        String scopPattern="SCOS"; 
        fixedInput=fixedInput.replaceAll(copPattern,"CO").replaceAll(scopPattern,"S");
        System.out.println("COPs fixed:"+fixedInput); 

//Matches Statment Pattern 
        String sPattern="R??ID\\=[(X)(ID)][(OPX)(OPID)]*;"; 
        fixedInput=fixedInput.replaceAll(sPattern, "S"); 
        System.out.println("Statement fixed:" + fixedInput); 

// Matches if else statemenet and while loops
        String iePattern="Rif\\(S\\)\\{S+\\}(Relif\\(S\\)\\{S+\\})*(Relse\\{S+\\})?";
        String wPattern= "Rwhile\\(S\\)\\{S*\\}";  
        fixedInput=fixedInput.replaceAll(iePattern,"S").replaceAll(wPattern,"S");
        System.out.println("If/else fixed:"+ fixedInput);
//Matching For loops
        String flPattern="Rfor\\(S*\\)\\{S*\\}";
        fixedInput=fixedInput.replaceAll(flPattern,"S");
        System.out.println("For loop to statemnet:" + fixedInput); 
//Matches Functions in Full
        String f2Pattern="F\\{S+\\}";
        fixedInput=fixedInput.replaceAll(f2Pattern,"S");
        System.out.println("Function to statemnet:" + fixedInput); 
//Matches program
        String pPattern="C\\{D\\{S*\\}S*\\}";
        fixedInput=fixedInput.replaceAll(pPattern,"P"); 
        System.out.println("Program fixed:" + fixedInput);

    }
}

