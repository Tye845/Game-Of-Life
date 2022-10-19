import chn.util.*;
import apcslib.*;
/**
 * Write a description of class Tester here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Tester
{
    // instance variables - replace the example below with your own

    /**
     * Constructor for objects of class Tester
     */
    public static void main (String[] args)
    {
        FileInput inputTest = new FileInput("lifeNums.txt");
        GameOfLife test = new GameOfLife(inputTest);
        test.toArray();
        test.print();//0
        test.countRow(10);
        test.countColumn(10);
        test.countTotal();
        test.generate();//1
        test.generate();//2
        test.generate();//3
        test.generate();//4
        test.generate();
        
    }
}
