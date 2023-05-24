package com.rohithsekar.regex;
import java.util.regex.Pattern;

/*
Regex Metacharacters

Regex                     	            Description

X?                                      X appears once or not
X+                                      X appears once or more than once
X*                                      X appears zero or not once
X{n}                                    X appears n times
X{n,}                                   X appears n times or more than n
X{n,m}                                  X appears greater than equal to n times and less than m times.

 */

public class Metacharacters
{
    public static void main(String[] args)
    {
        // Checking all the strings using regex
        System.out.println(Pattern.matches("[b-z]?", "a"));

        // Check if all the elements are in range a to z
        // or A to Z
        System.out.println(
                Pattern.matches("[a-zA-Z]+", "GfgTestCase"));

        // Check if elements is not in range a to z
        System.out.println(Pattern.matches("[^a-z]?", "G"));

        // Check if all the elements are either m,a,l,y
        System.out.println(Pattern.matches("[maly]*", "malayalam"));
    }

}
