public class Board 
{
    //Constructors
    Board()
    {
        this(13);
    }
    Board(int startPos)
    {
        pegGrid = new int [5] [5];

        int currPos = 1;
        for (int i = 0; i < 5; i++)
        {
            for (int j = 0; j <= i; j++)
            {
                pegGrid[i][j] = 1;
                if (currPos == startPos) pegGrid[i][j] = 0;
                currPos++;
            }
        }
    }
    Board(int startPos, int endPos)
    {
        this(startPos);
        this.endPos = endPos;
    }

    //Member Variables
    public int [] [] pegGrid;
    private int endPos;

    //Accessor Methods
    public String GetState()
    {
        String boardState = new String();
        int spaceScale = 5;
        for (int i = 0; i < 5; i++)
        {
            for (int k = 0; k < spaceScale; k++)
            {
                    boardState += " ";
            }
            for (int j = 0; j <= i; j++)
            {
                boardState += pegGrid[i][j] + " ";
            }
            boardState += "\n";
            spaceScale--;
        }

        return boardState;
    }
    public boolean HasEndPos()
    {
        if (endPos != -1) return true;
        else return false;
    }
    public int GetEndPos()
    {
        return endPos;
    }
    public int GetPegNumber(int i, int j) // Using Gaussian Summation
    {
        return (i*i + i)/2 + j + 1;
    }
    public int GetFirstPegNumber()
    {
        int pegPos = 0;

        boolean found = false;
        for (int i = 0; i < 5; i++)
        {
            for (int j = 0; j <= i && !found; j++)
            {
                if (pegGrid[i][j] == 1)
                {
                    found = true;
                }
                pegPos++;
            }
        }

        return pegPos;
    }
    public boolean IsSolution()
    {
        int pegCount = 0;
        for (int i = 0; i < 5; i++)
        {
            for (int j = 0; j <= i; j++)
            {
                if (pegGrid[i][j] == 1) pegCount++;
            }
        }

        if (pegCount == 1) return true;
        else return false;
    }
}
