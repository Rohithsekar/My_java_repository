public class Math
{
    public static void main(String[] args)
    {
        System.out.println(java.lang.Math.max(5,10));
        System.out.println(java.lang.Math.min(9,15));
        System.out.println(java.lang.Math.sqrt(15));
        System.out.println(java.lang.Math.abs(-12.678));
        System.out.println(java.lang.Math.random()); //returns a random floating point value in the range [0,1)
        //To custom generate random numbers within a range, we can do some arithmetic with the output of random()

        //To print values through lower bound until upper bound(not inclusive), multiply it by 10 raised to the power of
        //number of digits you want. If I want a three digit number, I multiply with 10^(3)=1000
        short r = (short) (java.lang.Math.random() * 1000);
        System.out.println(r);
        //To print values in the range of [min,max] both inclusive
        int min = 1;
        int max = 10;
        int randNum =min+ (int)(java.lang.Math.random()*(max-min+1));
        System.out.println(randNum);

    }
}
