public class PuzzlePegs 
{
    public static boolean MakeMove(Board userBoard, List moves, List moveStates, int [] [] pegs)
    {
        //Assign new peg values
        userBoard.pegGrid[pegs[0][0]][pegs[0][1]] = 0;
        userBoard.pegGrid[pegs[1][0]][pegs[1][1]] = 0;
        userBoard.pegGrid[pegs[2][0]][pegs[2][1]] = 1;

        //Document the move made and new board state
        String moveMade = new String();
        moveMade = "Move " + userBoard.GetPegNumber(pegs[0][0], pegs[0][1]) + " to " + userBoard.GetPegNumber(pegs[2][0], pegs[2][1]);
        moves.enqueue(moveMade);
        moveStates.enqueue(userBoard.GetState());

        //Check if the new board state is a solution and return true if so
        if (userBoard.IsSolution())
        {
            if (userBoard.HasEndPos())
            {
                if (userBoard.GetEndPos() == userBoard.GetFirstPegNumber()) return true;
            }
            else return true;
        }

        //Call recursively with updated board, if recursive call is false revert the move and pop from the lists
        if (SolveBoard(userBoard, moves, moveStates)) return true;
        else
        {
            userBoard.pegGrid[pegs[0][0]][pegs[0][1]] = 1;
            userBoard.pegGrid[pegs[1][0]][pegs[1][1]] = 1;
            userBoard.pegGrid[pegs[2][0]][pegs[2][1]] = 0;

            moves.pop();
            moveStates.pop();

            return false;
        }

    }
    public static boolean SolveBoard(Board userBoard, List moves, List moveStates)
    {
        //Find and make first available move (if applicable)
        int i;
        int j;
        int [] [] pegs = new int [3] [2];

        for (i = 0; i < 5; i++)
        {
            for (j = 0; j <= i; j++)
            {
                //Check for moves where pegs exist
                if (userBoard.pegGrid[i][j] == 1)
                {
                    //Check N (up-right)
                    if (i-2 >= j)
                    {
                        if (userBoard.pegGrid[i-1][j] == 1)
                        {
                            if (userBoard.pegGrid[i-2][j] == 0)
                            {
                                //Store the peg locations for restoration
                                pegs[0][0] = i;
                                pegs[0][1] = j;
                                pegs[1][0] = i-1;
                                pegs[1][1] = j;
                                pegs[2][0] = i-2;
                                pegs[2][1] = j;

                                if (MakeMove(userBoard, moves, moveStates, pegs)) return true;
                            }
                        }
                    }
                    //Check S (down-left)
                    if (i+2 <= 4)
                    {
                        if (userBoard.pegGrid[i+1][j] == 1)
                        {
                            if (userBoard.pegGrid[i+2][j] == 0)
                            {
                                //Store the peg locations for restoration
                                pegs[0][0] = i;
                                pegs[0][1] = j;
                                pegs[1][0] = i+1;
                                pegs[1][1] = j;
                                pegs[2][0] = i+2;
                                pegs[2][1] = j;

                                if (MakeMove(userBoard, moves, moveStates, pegs)) return true;
                            }
                        }
                    }
                    //Check E (right)
                    if (j+2 <= i)
                    {
                        if (userBoard.pegGrid[i][j+1] == 1)
                        {
                            if (userBoard.pegGrid[i][j+2] == 0)
                            {
                                //Store the peg locations for restoration
                                pegs[0][0] = i;
                                pegs[0][1] = j;
                                pegs[1][0] = i;
                                pegs[1][1] = j+1;
                                pegs[2][0] = i;
                                pegs[2][1] = j+2;

                                if (MakeMove(userBoard, moves, moveStates, pegs)) return true;
                            }
                        }
                    }
                    //Check W (left)
                    if (j-2 >= 0)
                    {
                        if (userBoard.pegGrid[i][j-1] == 1)
                        {
                            if (userBoard.pegGrid[i][j-2] == 0)
                            {
                                //Store the peg locations for restoration
                                pegs[0][0] = i;
                                pegs[0][1] = j;
                                pegs[1][0] = i;
                                pegs[1][1] = j-1;
                                pegs[2][0] = i;
                                pegs[2][1] = j-2;

                                if (MakeMove(userBoard, moves, moveStates, pegs)) return true;
                            }
                        }
                    }
                    //Check NW (up-left)
                    if (i-2 >= 0 && j-2 >= 0)
                    {
                        if (userBoard.pegGrid[i-1][j-1] == 1)
                        {
                            if (userBoard.pegGrid[i-2][j-2] == 0)
                            {
                                //Store the peg locations for restoration
                                pegs[0][0] = i;
                                pegs[0][1] = j;
                                pegs[1][0] = i-1;
                                pegs[1][1] = j-1;
                                pegs[2][0] = i-2;
                                pegs[2][1] = j-2;

                                if (MakeMove(userBoard, moves, moveStates, pegs)) return true;
                            }
                        }
                    }
                    //Check SE (down-right)
                    if (i+2 <= 4 && j+2 <= 4)
                    {
                        if (userBoard.pegGrid[i+1][j+1] == 1)
                        {
                            if (userBoard.pegGrid[i+2][j+2] == 0)
                            {
                                //Store the peg locations for restoration
                                pegs[0][0] = i;
                                pegs[0][1] = j;
                                pegs[1][0] = i+1;
                                pegs[1][1] = j+1;
                                pegs[2][0] = i+2;
                                pegs[2][1] = j+2;

                                if (MakeMove(userBoard, moves, moveStates, pegs)) return true;
                            }
                        }
                    }
                }
            }
        }

        return false;

    }

    public static void main(String [] args)
    {
        //////////////////////////////////////////////////////////////////////////////////////////
        // Inputs
        //////////////////////////////////////////////////////////////////////////////////////////

        //Initial conditions (assuming no user-specification)
        int startPos, endPos;
        startPos = 13;
        endPos = -1;

        //Assign first arg if it is present
        if (args.length > 0)
        {
            startPos = Integer.parseInt(args[0]);
            
            //Check the range
            if (startPos < 1)
            {
                startPos = 1;
                System.err.println("\tERR: input \"" + args[0] + "\" out of range, replaced with \"1\"");
            }
            if (startPos > 15)
            {
                startPos = 15;
                System.err.println("\tERR: input \"" + args[0] + "\" out of range, replaced with \"15\"");
            }
        }

        //Assign second arg if it is present
        if (args.length > 1)
        {
            endPos = Integer.parseInt(args[1]);

            //Check the range
            if (endPos < 1)
            {
                endPos = 1;
                System.err.println("\tERR: input \"" + args[1] + "\" out of range, replaced with \"1\"");
            }
            if (endPos > 15)
            {
                endPos = 15;
                System.err.println("\tERR: input \"" + args[1] + "\" out of range, replaced with \"15\"");
            }
        }

        //////////////////////////////////////////////////////////////////////////////////////////
        // Solving
        //////////////////////////////////////////////////////////////////////////////////////////

        Board userBoard = new Board(startPos, endPos);
        List moves = new List();
        List moveStates = new List();

        moves.enqueue("Initial Board");
        moveStates.enqueue(userBoard.GetState());

        double deltaTime = System.currentTimeMillis();

        if (SolveBoard(userBoard, moves, moveStates))
        {
            while (!moves.isEmpty())
            {
                System.out.println(moves.dequeue());
                System.out.println(moveStates.dequeue());
            }
            deltaTime = System.currentTimeMillis() - deltaTime;
            System.out.println("Finished. (" + deltaTime/1000 + "s)");
        }
        else
        {
            deltaTime = System.currentTimeMillis() - deltaTime;
            System.out.println("No single-peg solution found. (" + deltaTime/1000 + "s)"); 
        }
    }
}