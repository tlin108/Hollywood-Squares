
public class BoardData {

    private String[][] board;

    public BoardData(){

        board = new String[][]  { {"1", "2", "3"},
                                  {"4", "5", "6"},
                                  {"7", "8", "9"} };

    } // default constructor

    public BoardData(String board[][]) {

        // Overloading of class to load saved games
        this.board = board;

    } // constructor overload to be used in loading.

    public String[][] getBoardData() {

        return board;

    }

    /**
     * Baord is numbered 0 1 2
     *                   3 4 5
     *                   6 7 8
     * and is marked depending on the spot
     * @param spot - the spot of the board
     * @param Symbol_ - the symbol to mark
     */
    public void setBoard(Integer spot, char Symbol_) {

        // Symbol for players are "X", "O" and "R". "R" is for refresh board
        String symbol = Character.toString(Symbol_);
        symbol = symbol.toUpperCase();
        // Refreshes the board if symbol is "R" and spot is null
        if( spot == null && symbol.equals("R") ){

        	board = new String[][]  { {"1", "2", "3"},
        							  {"4", "5", "6"},
        							  {"7", "8", "9"} };
            return;
        }

        // Just for debugging cases, might come in handy
        if( spot == null || ( spot > 8 || spot < 0 ) ){

            throw new IllegalArgumentException("Spot values over what is allowed or can't be null at this point");

        }

        if( !symbol.equals("X") ){

            if( !symbol.equals("O")){
                throw new IllegalArgumentException("Symbols are only X or O. Can't be R or any letter at this point");
            }

        }

        if( !symbol.equals("O")){

            if( !symbol.equals("X")){
                throw new IllegalArgumentException("Symbols are only X or O. Can't be R or any letter at this point");
            }

        }

        // Marks board here
        if( spot == 0){

            board[0][0] = symbol;

        }
        if (spot == 1){

            board[0][1] = symbol;

        }
        if (spot == 2){

            board[0][2] = symbol;

        }
        if (spot == 3){

            board[1][0] = symbol;

        }
        if (spot == 4){

            board[1][1] = symbol;

        }
        if (spot == 5){

            board[1][2] = symbol;

        }
        if (spot == 6){

            board[2][0] = symbol;

        }
        if (spot == 7){

            board[2][1] = symbol;

        }
        if (spot == 8){

            board[2][2] = symbol;

        }

    }

    public boolean checkRoundWin(char Symbol_){
    		String Symbol = Character.toString(Symbol_);
        	int count=0;
        	for(int i=0;i<3;i++){
        		
        		//check vertical
        		if(board[i][0].equals((board[i][1])) && board[i][1].equals((board[i][2])) && board[i][2].equals(Symbol)){
        			return true;
        		}
        		
        		//check horizontal
        		if(board[0][i].equals((board[1][i])) && board[1][i].equals((board[2][i])) && board[2][i].equals(Symbol)){
        			return true;
        		}
       		
        		//check if player has 5 squares
        		for(int j=0;j<3;j++){
        			if(board[i][j].equals(Symbol))
        				count++;
        		}
        		
        	}
 
        	//check diagonals
    		if(board[0][0].equals((board[1][1])) && board[1][1].equals((board[2][2])) && board[2][2].equals(Symbol))
    			return true;
    		
    		
    		if(board[0][2].equals((board[1][1])) && board[1][1].equals((board[2][0])) && board[2][0].equals(Symbol))
    			return true;
    		
    		
        	if(count>=5){
        		return true;
        	}
        	else
        		return false;
    }
    
    public int calculateScore(char Symbol_){
    	String Symbol = Character.toString(Symbol_);
    	int count=0;
    	for(int i=0;i<3;i++){
    		for(int j=0;j<3;j++){
    			if(board[i][j].equals(Symbol))
    				count++;
    		}
    	}
    	
    	return (count*50);
    }
}
