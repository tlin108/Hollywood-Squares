import java.util.Random;

public class ComputerAI {

	private int makeSmartMove;
	private int timeDelay;

	public ComputerAI(){

		timeDelay = 3; // default value

	}

	public ComputerAI(int timeDelay){

		this.timeDelay = timeDelay;

	} // constructor if time dealy wants to be changed


    /**
     * Method to compute best move for computer ai.
     * @param board - most recent/updated copy of the board
     * @param symbol - symbol used by computer ai
     * @return - returns the best move
     */
	public int generateMove(String[][] board, char symbol) {

		Random randomMove = new Random(); // local variable to generate random numbersd

		String aiSymbol = Character.toString(symbol);
        aiSymbol = aiSymbol.toUpperCase();

		String playerSymbol;
		if (aiSymbol.equalsIgnoreCase("O") ){
            playerSymbol = "X";
        }
		else{
            playerSymbol = "O";
        }

		// Simuation of thinkTime is done here using timeDelay
		int delay = randomMove.nextInt(timeDelay) + 1;
        try {
			Thread.sleep(delay*1000);
		}
		catch (InterruptedException e) { }

		// Check Horizontal
		for (int i = 0; i < 3; i++) {

			if ((board[0][i].equals(board[1][i]) && board[0][i].equals(aiSymbol))) {
				if (!board[2][i].equals(playerSymbol)) {
                    makeSmartMove = 6+i;
					return makeSmartMove;
				
				}
			}
		}

		for (int i = 0; i < 3; i++) {

			if ((board[2][i].equals(board[1][i]))
					&& board[2][i].equals(aiSymbol)) {
				if (!board[0][i].equals(playerSymbol)) {
                    makeSmartMove = 0+i;
					return makeSmartMove;
				}
			}
		}

		for (int i = 0; i < 3; i++) {

			if ((board[2][i].equals(board[0][i]))
					&& board[2][i].equals(aiSymbol)) {
				if (!board[1][i].equals(playerSymbol)){
                    makeSmartMove = 3+i;
					return makeSmartMove;
				}
			}
		}

		// Check Vertical
		for (int i = 0; i < 3; i++) {

			if (board[i][0].equals((board[i][1]))
					&& board[i][0].equals(aiSymbol)) {
				if (!board[i][2].equals(playerSymbol)) {
                    makeSmartMove = 2+(3*i);
					return makeSmartMove;
				}
			}
		}

		for (int i = 0; i < 3; i++) {

			if ((board[i][2].equals(board[i][1]))
					&& board[i][2].equals(aiSymbol)) {
				if (!board[i][0].equals(playerSymbol)) {
                    makeSmartMove = 0+(3*i);
					return makeSmartMove;
					
				}
			}
		}

		for (int i = 0; i < 3; i++) {

			if ((board[i][2].equals(board[i][0]))
					&& board[i][2].equals(aiSymbol)) {
				if (!board[i][1].equals(playerSymbol)) {
                    makeSmartMove = 1+(3*i);
					return makeSmartMove;
					
				}
			}
		}

		// CHECK DIAGONALLY

		if ((board[0][0].equals(board[1][1])) && board[0][0].equals(aiSymbol)) {
			if (!board[2][2].equals(playerSymbol)) {
				return 8;
			}
		}

		if ((board[0][0].equals(board[2][2])) && board[0][0].equals(aiSymbol)) {
			if (!board[1][1].equals(playerSymbol)) {
				return 4;
			}
		}

		if ((board[2][2].equals(board[1][1])) && board[2][2].equals(aiSymbol)) {
			if (!board[0][0].equals(playerSymbol)) {
				return 0;
			}
		}

		if ((board[0][2].equals(board[1][1])) && board[0][2].equals(aiSymbol)) {
			if (!board[2][0].equals(playerSymbol)) {
				return 6;
			}
		}

		if ((board[2][0].equals(board[1][1])) && board[2][0].equals(aiSymbol)) {
			if (!board[0][2].equals(playerSymbol)) {
				return 2;
			}
		}

		if ((board[2][0].equals(board[0][2])) && board[0][2].equals(aiSymbol)) {
			if (!board[1][1].equals(playerSymbol)) {
				return 4;
			}
        }

		// Blocking move algorithms starts here
		
		// check Horizontal for block
            for (int i = 0; i < 3; i++) {

                if ((board[0][i].equals(board[1][i])) && board[0][i].equals(playerSymbol)) {
                    if (!board[2][i].equals(aiSymbol)) {
                            makeSmartMove = 6+i;
							return makeSmartMove;
                    }
                }
            }

        for (int i = 0; i < 3; i++) {

            if ((board[2][i].equals(board[1][i])) && board[2][i].equals(playerSymbol)) {
                if (!board[0][i].equals(aiSymbol)) {
                    makeSmartMove = 0+i;
                    return makeSmartMove;
                }
            }
        }

        for (int i = 0; i < 3; i++) {

            if ((board[2][i].equals(board[0][i])) && board[2][i].equals(playerSymbol)) {
                if (!board[1][i].equals(aiSymbol)){
                    makeSmartMove = 3+i;
                    return makeSmartMove;
                }
            }
        }

        // check Vertical for block
        for (int i = 0; i < 3; i++) {

            if (board[i][0].equals((board[i][1])) && board[i][0].equals(playerSymbol)) {
                if (!board[i][2].equals(aiSymbol)) {
                    makeSmartMove = 2+(3*i);
                    return makeSmartMove;
                }
            }
        }

        for (int i = 0; i < 3; i++) {

            if ((board[i][2].equals(board[i][1])) && board[i][2].equals(playerSymbol)) {
                if (!board[i][0].equals(aiSymbol)) {
                    makeSmartMove = 0+(3*i);
                    return makeSmartMove;
                }
            }
        }

        for (int i = 0; i < 3; i++) {

            if ((board[i][2].equals(board[i][0])) && board[i][2].equals(playerSymbol)) {
                if (!board[i][1].equals(aiSymbol)) {
                    makeSmartMove = 1+(3*i);
                    return makeSmartMove;
                }
            }
        }

        // CHECK DIAGONALLY for block

        if ((board[0][0].equals(board[1][1])) && board[0][0].equals(playerSymbol)) {
            if (!board[2][2].equals(aiSymbol)) {
                return 8;
            }
        }

        if ((board[0][0].equals(board[2][2])) && board[0][0].equals(playerSymbol)) {
            if (!board[1][1].equals(aiSymbol)) {
                return 4;
            }
        }

        if ((board[2][2].equals(board[1][1])) && board[2][2].equals(playerSymbol)) {
            if (!board[0][0].equals(aiSymbol)) {
                return 0;
            }
        }

        if ((board[0][2].equals(board[1][1])) && board[0][2].equals(playerSymbol)) {
            if (!board[2][0].equals(aiSymbol)) {
                return 6;
            }
        }

        if ((board[2][0].equals(board[1][1])) && board[2][0].equals(playerSymbol)) {
            if (!board[0][2].equals(aiSymbol)) {
                return 2;
            }
        }

        if ((board[2][0].equals(board[0][2])) && board[0][2].equals(playerSymbol)) {
            if (!board[1][1].equals(aiSymbol)) {
                return 4;
            }
        }

		// Take mid if available and no win or block.
		if ((!board[1][1].equals(playerSymbol)) && (!board[1][1].equals(aiSymbol))) {
            board[1][1] = aiSymbol;
            return 4;
        }
		

		// Make random move if mid not available

		while (true) {

			randomMove = new Random();
			int row = randomMove.nextInt(3);
			int col = randomMove.nextInt(3);

			if ((!board[row][col].equals(playerSymbol)) && (!board[row][col].equals(aiSymbol))) {
				if (row == 0 && col==0) return 0;
				if (row == 0 && col==1) return 1;
				if (row == 0 && col==2) return 2;
				if (row == 1 && col==0) return 3;
				if (row == 1 && col==1) return 4;
				if (row == 1 && col==2) return 5;
				if (row == 2 && col==0) return 6;
				if (row == 2 && col==1) return 7;
				if (row == 2 && col==2) return 8;

			}
		}
	}

    /**
     * Random generator of true/false to simulate ai answering.
     * @return - return ai decision
     */
	public boolean generateAIanswer() {

		Random randomMove = new Random();

        // Think time
        int delay = randomMove.nextInt(timeDelay) + 1;
        try {
            Thread.sleep(delay*1000);
        }
        catch (InterruptedException e) { }

        int answer = randomMove.nextInt(2);

		if (answer == 0)
			return false;
		else
			return true;

	}

}
