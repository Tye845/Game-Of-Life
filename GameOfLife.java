import chn.util.*;
import apcslib.*;
/**
 * Write a description of class GameOfLife here.
 *
 * @Tye Williams
 * @version 1.1
 */
public class GameOfLife
{
    // instance variables - replace the example below with your own
    final int ROWS_NUM = 22;
    final int COLS_NUM = 22;
    private FileInput input;
    int [][] petriDish = new int [ROWS_NUM] [COLS_NUM];
    int [][] updatedPetriDish = new int [ROWS_NUM] [COLS_NUM];
    int [][] neighborCount = new int [ROWS_NUM] [COLS_NUM];
    private int generationCtr = 0;
    /**
     * Constructor for objects of class GameOfLife
     */
    public GameOfLife(FileInput in)
    {
        input = in;
    }

    public int [] [] toArray()
    {
        int ctr = 0;
        int row = 0;
        int col = 0;
        for(int i = 0; i < 101; i++)
        {
            if(ctr == 0)
            {
                ctr++;
                row = input.readInt();
                continue;                
            }
            row = input.readInt();
            col = input.readInt();
            petriDish[row][col] = 1;
            ctr++;
        }       
        return petriDish;
    }

    public void print()
    {
        System.out.println("Generation " + generationCtr);

        for(int i = 0; i < ROWS_NUM; i++)
        {
            System.out.print(Format.left(i, 3) + "  ");
            for(int j = 0; j < COLS_NUM; j++)
            {
                System.out.print(petriDish[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    public int countTotal()
    {
        int ctr = 0;
        for(int i = 0; i < ROWS_NUM; i++)
        {
            for(int j = 0; j < COLS_NUM; j++)
            {
                if(petriDish[i][j] == 1)
                {
                    ctr++;
                }
            }
        }
        System.out.println("Number of living organisms is: " + ctr);
        System.out.println();
        return ctr;
    }

    public int countRow(int row)
    {
        int ctr = 0;
        for(int i = 0; i < COLS_NUM; i++)
        {
            if(petriDish[row][i] == 1)
            {
                ctr++;
            }
        }
        System.out.println("Number of living organisms in Row " + row + " is: " + ctr);
        return ctr;
    }

    public int countColumn(int col)
    {
        int ctr = 0;
        for(int i = 0; i < COLS_NUM; i++)
        {
            if(petriDish[i][col] == 1)
            {
                ctr++;
            }
        }
        System.out.println("Number of living organisms in Column " + col + " is: " + ctr);
        return ctr;
    }

    public int [][] checkLocalArea(int row, int col)
    {
        int ctr = 0;
        int neighborCtr = 0;
        //i controls looping through the rows
        for(int miniRow = row - 1; miniRow <= row + 1; miniRow++)
        {
            for(int miniCol = col - 1; miniCol <= col + 1; miniCol++)
            {
                int temp = petriDish[miniRow][miniCol];
                if(temp == 1)
                {
                    neighborCtr++;
                }

            }
        }
        if(petriDish[row][col] == 1)
        {
            neighborCtr--;
        }
        neighborCount[row][col] = neighborCtr;
        return neighborCount;
    }

    public int [][] generate()
    {        
        //i controls looping through the rows
        generationCtr++;
        for(int i = 1; i < 21; i++)
        {
            //j controls looping through the cols
            for(int j = 1; j < 21; j++)
            {
                checkLocalArea(i, j);
            }

        }

        for(int i = 1; i < 21; i++)
        {
            for(int j = 1; j < 21; j++)
            {
                //Every empty cell with three living neighbors will come to life in the next generation (a "birth")
                if(petriDish[i][j] == 0 && neighborCount[i][j] == 3)
                {
                    updatedPetriDish[i][j] = 1;
                }
                //Any cell with one or zero neighbors will die of loneliness
                else if(petriDish[i][j] == 1 && neighborCount[i][j] <= 1)
                {
                    updatedPetriDish[i][j] = 0;
                }
                //any cell with four or more neighbors will die from overcrowding (a "death")
                else if(petriDish[i][j] == 1 && neighborCount[i][j] >= 4)
                {
                    updatedPetriDish[i][j] = 0;
                }
                //Any cell with two or three neighbors will live into the next generation (no change)
                else if(petriDish[i][j] == 1 && (neighborCount[i][j] == 2 || neighborCount[i][j] == 3 ))
                {
                    updatedPetriDish[i][j] = 1;
                }
            }
        }
        for(int i = 0; i < 22; i++)
        {
            //j controls looping through the cols
            for(int j = 0; j < 22; j++)
            {
                petriDish [i][j] = updatedPetriDish [i][j];
            }

        }
        print();
        countRow(10);
        countColumn(10);
        countTotal();
        return neighborCount;
    }
}
