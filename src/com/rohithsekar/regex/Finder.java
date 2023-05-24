package com.rohithsekar.regex;
import java.util.regex.Pattern;

/*
Java Regex Finder Example

Regex                             Description

.                                 Any character except a new-line
\d                                Any digits, [0-9]
\D                                Any non-digit, [^0-9]
\s                                Whitespace character, [\t\n\x0B\f\r]
\S                                Non-whitespace character, [^\s]
\w                                Word character, [a-zA-Z_0-9] (Alpha-numeric)
\W                                Non-word character, [^\w]
\b                                Word boundary
\B                                Non -Word boundary
^                                 Matches the beginning of a line/string
$                                 Matches the end of a line/string


 */
public class Finder
{
    public static void main(String[] args)
    {

            // Check if all elements are numbers
            System.out.println(Pattern.matches("\\d+", "1234"));

            // Check if all elements are non-numbers
            System.out.println(Pattern.matches("\\D+", "1234"));

            // Check if all the elements are non-numbers
            System.out.println(Pattern.matches("\\D+", "Gfg"));

            // Check if all the elements are non-spaces
            System.out.println(Pattern.matches("\\S+", "gfg"));


        }
}

