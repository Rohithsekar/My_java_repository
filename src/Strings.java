import java.util.Locale;

public class Strings
{
    public static void main(String[] args)
    {
        String name = "john";
        //use soup, short for System.out.print
        //use soul, short for System.out.println
        //use souf, short for System.out.printf
        //You can define your own custom live template by adding it in live templates option in settings
        System.out.print("Hi\n"); //no new line is added
        System.out.println(name); // new line added
        System.out.printf("The String name's value is %s and its length is %d\n",name,name.length()); //formatted output

        //String methods
        System.out.printf("upper cased string is %s\n",name.toUpperCase());
        System.out.printf("lower cased string is %s\n",name.toLowerCase());
        String txt = "Find out where I am";
        System.out.printf("The first occurence of string, \"find\" in the text is index %d\n",txt.indexOf("out"));
        String first = "William";
        String second = " Harvey";
        System.out.println(first + "" + second);
        System.out.println(first.concat(second));
        System.out.print("Hey");
        

    }
}
