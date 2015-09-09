
public class PlayerData {

	private char symbol;
	private int score;
	
    PlayerData(){

        score = 0;

	}
    
	public char getPlayerSymbol (){

			return symbol;

	}

	public int getPlayerScore(){

			return score;

	}
	
	public void setPlayerScore(int score){

        this.score = score + this.score;

	}
	
	public void setSymbol(char symbol){

        this.symbol = symbol;

	}
	
	public boolean checkGameWin(){

        if (score >= 500){
            return true;
        }
        return false;

	}
	
}
