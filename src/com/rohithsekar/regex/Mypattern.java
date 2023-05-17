package com.rohithsekar.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Mypattern
{
    public static void main(String[] args)
    {
        //the compile method takes in a regex and compiles it as a Pattern object and returns that Pattern object
        Pattern pattern = Pattern.compile("Hel*o world");
        Pattern pattern1 = Pattern.compile("how are",Pattern.CASE_INSENSITIVE); //overrided compile method

        //The pattern objects are used with Match class methods to achieve functionality
        //The matcher method can be invoked on any Pattern object to test for match in a character sequence
        Matcher m = pattern.matcher("Hello world!, How are you?");
        Matcher m1 = pattern1.matcher("Hello world!, How are you?");
        //Above, we test if the Pattern object is present in the Charsequence

        while(m.find())
        {
            System.out.println("Pattern found from index " + m.start() + " to index " + (m.end()-1));
        }
        while(m1.find())
        {
            System.out.println("Pattern found from index " + m1.start() + " to index " + (m1.end()-1));
        }



        /*
        Q. How does the loop works?

        A.This loop iterates as long as the Matcher object m finds a match in the input. The find() method
        searches for the next occurrence of the pattern specified in the Matcher object within the input.
        When a match is found, the loop body is executed. It prints the message "Pattern found from
        index [start] to index [end]" where [start] represents the starting index of the match and [end]
        represents the ending index of the match.

        The start() method returns the index of the start of the match, and the end() method returns the
        index of the character immediately following the end of the match. By subtracting 1 from m.end(),
        we get the ending index of the match itself.
        The loop continues until find() no longer finds any more matches, at which point it terminates.

       Q. So, the find method actually searches for match from the start to end of the input string?

       A.Yes, that's correct. The find() method of the Matcher object searches for matches within the input
        string from the current position of the Matcher object to the end of the string. It does not search from
        the start of the string every time(Only for the first time, it starts from beginning of the input string)
        The Matcher object keeps track of its current position in the input string,
        and each time find() is called, it starts searching for the pattern from that position onward.
        This allows the loop to find multiple matches in the input string.

         */

        String input = "Make2the3best4of5what6you7got";
        String delimiter = "\\d"; //storing the special pattern \d in the string
        Pattern pattern2 = Pattern.compile(delimiter,Pattern.CASE_INSENSITIVE);
        String[] result = pattern2.split(input);
        for(String i : result)
        {
            System.out.println(i);
        }

        //Besides the Pattern and Matcher class, there is the PatternSyntaxException Class
        /*
        Class 3: PatternSyntaxException Class
        This is an object of Regex which is used to indicate a syntax error in a regular expression pattern
        and is an unchecked exception. Following are the methods been there up in the PatternSyntaxException
        class as provided below in tabular format as follows:
        1.	getDescription()	It is used to retrieve the description of the error.
        2.	getIndex()	        It is used to retrieve the error index.
        3.	getMessage()	    It is used to return a multi-line string containing the description of the
                                syntax error and its index, the erroneous regular-expression pattern, and a
                                visual indication of the error index within the pattern.
        4.	getPattern()	    It is used to retrieve the erroneous regular-expression pattern.
       */

        /*
        Other than the three classes, we have an interface.
        Interface 1: MatchResult Interface

    This interface is used to determine the result of a match operation for a regular expression. It must be noted that
    although the match boundaries, groups, and group boundaries can be seen, the modification is not allowed through a
    MatchResult. Following are the methods been there up here in this interface as provided below in tabular format as
    follows:
    S.No.	Method	           Description
    1.	    end()	           It is used to return the offset after the last character is matched.
    2.	    end(int group)	   It is used to return the offset after the last character of the subsequence captured by
                               the given group during this match.
    3.	    group()	           It is used to return the input subsequence matched by the previous match.
    4.	    group(int group)   It is used to return the input subsequence captured by the given group during the
                               previous   match operation.
    5.	    groupCount()	   It is used to return the number of capturing groups in this match result’s pattern.
    6.	    start()	           It is used to return the start index of the match.
    7.	    start(int group)   It is used to return the start index of the subsequence captured by the given group
                               during this match.
         */

    }
}
