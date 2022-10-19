import chn.util.*;
import apcslib.*;

public class Tester
{
    public static void main (String[] args)
    {
        FileInput inputTest = new FileInput("lifeNums.txt");
        GameOfLife test = new GameOfLife(inputTest);
        test.toArray();
        test.print();//0
        test.countRow(10);
        test.countColumn(10);
        test.countTotal();
        test.generate(); //1
        test.generate(); //2
        test.generate(); //3
        test.generate(); //4
        test.generate();        
    }
}
