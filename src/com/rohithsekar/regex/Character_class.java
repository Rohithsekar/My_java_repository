package com.rohithsekar.regex;
import java.util.regex.Pattern;
/*
Regex Character classes
^ - NOT , - (in the range)

Character Class                              Description


[xyz]                                        x,y or z
[^xyz]                                       Any characters other than x,y or z
[a-zA-Z]                                     characters from range a to z or A to Z. (only a single character)
[a-f[m-t]]                                   Union of a to f and m to t.
[a-z && p-y]                                 All the range of elements intersection between two ranges
[a-z && [^bc]]                               a to z union, except b and c
[a-z && [^m-p]]                              a to z union with except range m to p


 */
public class Character_class
{
    // Main function
    public static void main(String[] args)
    {
        // Checks if the string matches with the regex
        // Should be single character a to z
        System.out.println(Pattern.matches("[a-z]", "g"));

        // Check if the element is range a to z or A to Z. It takes only a single char argument.
        //If a string longer than length of 1 is passed, it returns false.
        //takes only a single character in the regex range to return true, else false
        System.out.println(Pattern.matches("[a-zA-Z]", "Gfg"));

        //takes only a single character in the regex to return true, else false
        System.out.println(Pattern.matches("[ab]", "ab"));
        //takes only a single character not from the regex to return true, else false
        System.out.println(Pattern.matches("[^ab]","c"));

        //The regular expression "[a-f[m-t]]" matches any single character that is either in the range "a" to "f"
        // (inclusive) or in the range "m" to "t" (inclusive).
        System.out.println(Pattern.matches("[a-f[m-t]]","D"));

        System.out.println(Pattern.matches("a-z && p-y","r")); //Don't know why it returns false for all inputs




    }
}
