import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class DataEventController implements ActionListener{

	// Databases for the game.
	private static PlayerData   player[];
	private static BoardData    board;
	private static QuestionData question;
	private static ComputerAI   ai;
	private static int[] 		markedTile;

	private static boolean      checkAnswer;
	private static boolean      aiMode;
	private static boolean      playerTurn;

	DataEventController(boolean aiMode){

		// checks to see if ai is present or no;
		this.aiMode = aiMode;

		// Instantiate class objects
		question      = new QuestionData();
		board         = new BoardData();
		ai            = new ComputerAI();
		player        = new PlayerData[2];
		markedTile 	  = new int [10];

		for(int i = 0; i < 2; i++ ){
			player[i] = new PlayerData();
		}

		// To randomize what symbol each player get.
		// Random approach since our design doesn't
		// Let the player choose his own symbol.
		int random = (int) (Math.random()*2);
		if( random == 0) {
			player[0].setSymbol('X');
			player[1].setSymbol('O');
		}
		else{
			player[0].setSymbol('O');
			player[1].setSymbol('X');
		}

	}// constructor

	private void initializeGame(String savedFile) throws IOException{

		Scanner sc = new Scanner(System.in);
		int boxNumber;
		boolean answer;
		String gameFunction;

		//will skip this part if its from a loaded game
		if(markedTile[9]==0){
			int random = (int) (Math.random()*2);
			// Determines by random who takes the first move
			if( random == 0 )
				playerTurn = true;
			else
				playerTurn = false;
		}

		System.out.println();
		System.out.println("---------------------------------------------");

		while( !player[0].checkGameWin() && !player[1].checkGameWin() ){
			while(!board.checkRoundWin(player[0].getPlayerSymbol())&&!board.checkRoundWin(player[1].getPlayerSymbol())){

				int currentTurn;
				if( playerTurn ){
					currentTurn = 1;
				}
				else{
					currentTurn = 2;
				}


				if(aiMode) {

					System.out.println();
					System.out.println("Type any of the following character for their function before picking a box.");
					System.out.println("\t\tQ (Quit) S (Save) E (Exit)");
					System.out.println();

					System.out.println();
					if( currentTurn == 2){
						System.out.println("Turn: Player " + currentTurn + " (Computer)");
					}
					else{
						System.out.println("Turn: Player " + currentTurn + " (Human)");
					}
					System.out.println("Score:");
					System.out.println("Player 1 ( " + player[0].getPlayerSymbol() + " ) : " + player[0].getPlayerScore());
					System.out.println("Computer ( " + player[1].getPlayerSymbol() + " ) : " + player[1].getPlayerScore());
					System.out.println();

					try {
						Thread.sleep(1500);
					}
					catch (InterruptedException e) { }

					System.out.println("| " + board.getBoardData()[0][0] + " | " + board.getBoardData()[0][1] + " | " + board.getBoardData()[0][2] + " |");
					System.out.println("-------------");
					System.out.println("| " + board.getBoardData()[1][0] + " | " + board.getBoardData()[1][1] + " | " + board.getBoardData()[1][2] + " |");
					System.out.println("-------------");
					System.out.println("| " + board.getBoardData()[2][0] + " | " + board.getBoardData()[2][1] + " | " + board.getBoardData()[2][2] + " |\n");

					if( currentTurn == 2 )
						System.out.println("Host: Computer please pick a box. ");
					else
						System.out.println("Host: Please pick a box. ");

					if( currentTurn ==2 ){
						boxNumber = ai.generateMove(board.getBoardData(), player[1].getPlayerSymbol());
						markedTile[boxNumber]=1;
						System.out.println("Computer picked box " + (boxNumber + 1) + "." );
					}
					else {
						// Forces player to type an int with values 1 to 9 or one of the game functions
						while(true) {
							while (!sc.hasNextInt()) {
								if(sc.hasNext()){
									gameFunction=sc.next();
									if(gameFunction.equalsIgnoreCase("Q")){
										System.out.println("Returning to Main Menu...");
										return;
									}
									else if(gameFunction.equalsIgnoreCase("S")){
										File outFile = new File(savedFile);
										FileWriter fWriter = new FileWriter (outFile);
										PrintWriter pWriter = new PrintWriter (fWriter);
										//ai_Mode on or not
										pWriter.println(aiMode);
										//Player1 data
										pWriter.println(player[0].getPlayerSymbol() + " " + player[0].getPlayerScore());
										//Player2 data
										pWriter.println(player[1].getPlayerSymbol() + " " + player[1].getPlayerScore());
										//board data
										for(int i=0;i<3;i++){
											pWriter.println();
											for(int j=0;j<3;j++){
												if(board.getBoardData()[i][j].equals("O")||board.getBoardData()[i][j].equals("X")){
													if(i==0)
														pWriter.println(j+" "+board.getBoardData()[i][j]);
													else if(i==1)
														pWriter.println((3+j)+" "+board.getBoardData()[i][j]);
													else if(i==2)
														pWriter.println((6+j)+" "+board.getBoardData()[i][j]);
												}
											}
										}
										pWriter.println();
										//player turn
										pWriter.println(playerTurn);
										//Marked tile
										pWriter.println();
										for(int i=0;i<9;i++){
											pWriter.print(markedTile[i]+" ");
										}
										pWriter.print("1"); // mark for load game
										fWriter.close();
										pWriter.close();
										System.out.println("file successfully saved.");

									}
									else if(gameFunction.equalsIgnoreCase("E")){
										System.out.println("Thank you for playing! See you next time!");
										System.exit(0);
									}
								}
								System.out.println("Host: Please enter between 1-9 or choose one of the game functions: ");
							}

							boxNumber = sc.nextInt() - 1;
							if(boxNumber > 8 || boxNumber < 0 ) {
								System.out.println("Host: Please enter between 1-9: ");
							}
							else if(markedTile[boxNumber]==1){
								System.out.println("Host: The tile has already been marked, please pick another tile: ");
							}
							else{
								markedTile[boxNumber]=1;
								break;
							}
						}
					}

					System.out.println("Host: Your question is,");
					System.out.println("      " + question.getQuestion());
					boolean fakeAnswer = Boolean.parseBoolean(question.generateFakeAnswer());

					try {
						Thread.sleep(3500);
					}
					catch (InterruptedException e) { }


					System.out.println("Celebrity: The answer is " + fakeAnswer + "!");

					try {
						Thread.sleep(1000);
					}
					catch (InterruptedException e) { }

					System.out.println("Host: Do you agree or disagree with the Celebrity? true(agree) false(disagree)");

					if( currentTurn ==2 ){
						answer = ai.generateAIanswer();

						System.out.println("Computer answered " + answer +"." );
					}
					else {
						// forces a player to type true or false answer.
						while(!sc.hasNextBoolean()) {
							System.out.println("Host: Please enter true or false (case sensitive):");
							sc.next();
						}
						answer = sc.nextBoolean();
					}

					if (question.checkAnswer(answer, fakeAnswer)) {
						System.out.println("Host: You are correct! You are awarded the square.");
						board.setBoard(boxNumber, player[currentTurn - 1].getPlayerSymbol());
					} else {
						System.out.println("Host: I'm sorry, you're wrong. The opponent is awarded the square.");
						board.setBoard(boxNumber, player[currentTurn % 2].getPlayerSymbol());
					}

				}

				// TWO PLAYER MODE ---------------------------
				else{

					System.out.println("Type any of the following character for their function before picking a box.");
					System.out.println("Q (Quit) S (Save) E (Exit)");
					System.out.println();
					System.out.println("Turn: Player " + currentTurn);
					System.out.println("Score:");
					System.out.println("Player 1 ( " + player[0].getPlayerSymbol() + " ) : " + player[0].getPlayerScore());
					System.out.println("Player 2 ( " + player[1].getPlayerSymbol() + " ) : " + player[1].getPlayerScore());
					System.out.println();

					try {
						Thread.sleep(1500);
					}
					catch (InterruptedException e) { }

					System.out.println("| " + board.getBoardData()[0][0] + " | " + board.getBoardData()[0][1] + " | " + board.getBoardData()[0][2] + " |");
					System.out.println("-------------");
					System.out.println("| " + board.getBoardData()[1][0] + " | " + board.getBoardData()[1][1] + " | " + board.getBoardData()[1][2] + " |");
					System.out.println("-------------");
					System.out.println("| " + board.getBoardData()[2][0] + " | " + board.getBoardData()[2][1] + " | " + board.getBoardData()[2][2] + " |\n");

					if( currentTurn == 2 )
						System.out.println("Host: Player 2 please pick a box. ");
					else
						System.out.println("Host: Player 1 please pick a box. ");

					// Forces player to type an int with values 1 to 9 or one of the game functions
					while(true) {
						while (!sc.hasNextInt()) {
							if(sc.hasNext()){
								gameFunction=sc.next();
								if(gameFunction.equalsIgnoreCase("Q")){
									System.out.println("Returning to Main Menu...");
									return;
								}
								else if(gameFunction.equalsIgnoreCase("S")){
									File outFile = new File(savedFile);
									FileWriter fWriter = new FileWriter (outFile);
									PrintWriter pWriter = new PrintWriter (fWriter);
									//ai_Mode on or not
									pWriter.println(aiMode);
									//Player1 data
									pWriter.println(player[0].getPlayerSymbol() + " " + player[0].getPlayerScore());
									//Player2 data
									pWriter.println(player[1].getPlayerSymbol() + " " + player[1].getPlayerScore());
									//board data
									for(int i=0;i<3;i++){
										pWriter.println();
										for(int j=0;j<3;j++){
											if(board.getBoardData()[i][j].equals("O")||board.getBoardData()[i][j].equals("X")){
												if(i==0)
													pWriter.println(j+" "+board.getBoardData()[i][j]);
												else if(i==1)
													pWriter.println((3+j)+" "+board.getBoardData()[i][j]);
												else if(i==2)
													pWriter.println((6+j)+" "+board.getBoardData()[i][j]);
											}
										}
									}
									pWriter.println();
									//Player Turn
									pWriter.println(playerTurn);
									//Marked tiles
									for(int i=0;i<9;i++){
										pWriter.print(markedTile[i]+" ");
									}
									pWriter.print("1"); // mark for load game
									fWriter.close();
									pWriter.close();
									System.out.println("file successfully saved.");

								}
								else if(gameFunction.equalsIgnoreCase("E")){
									System.out.println("Thank you for playing! See you next time!");
									System.exit(0);
								}
							}
							System.out.println("Host: Please enter between 1-9 or choose one of the game functions: ");
						}
						boxNumber = sc.nextInt() - 1;
						if(boxNumber > 8 || boxNumber < 0 ) {
							System.out.println("Host: Please enter between 1-9: ");
						}
						else if(markedTile[boxNumber]==1){
							System.out.println("Host: The tile has already been marked, please pick another tile: ");
						}
						else{
							markedTile[boxNumber]=1;
							break;
						}
					}

					System.out.println("Host: Your question is,");
					System.out.println("      " + question.getQuestion());
					boolean fakeAnswer = Boolean.parseBoolean(question.generateFakeAnswer());

					try {
						Thread.sleep(3500);
					}
					catch (InterruptedException e) { }

					System.out.println("Celebrity: The answer is " + fakeAnswer + "!");

					try {
						Thread.sleep(1000);
					}
					catch (InterruptedException e) { }

					System.out.println("Host: Do you agree or disagree with the Celebrity? true(agree) false(disagree)");

					// forces a player to type true or false answer.
					while(!sc.hasNextBoolean()) {
						System.out.println("Host: Please enter true or false (case sensitive):");
						sc.next();
					}
					answer = sc.nextBoolean();

					if (question.checkAnswer(answer, fakeAnswer)) {
						System.out.println("Host: You are correct! You are awarded the square.");
						board.setBoard(boxNumber, player[currentTurn - 1].getPlayerSymbol());
					} else {
						System.out.println("Host: I'm sorry, you're wrong. The opponent is awarded the square.");
						board.setBoard(boxNumber, player[currentTurn % 2].getPlayerSymbol());
					}

				}

				// Switching done here
				if(playerTurn){
					playerTurn = false;
				}
				else if(!playerTurn)
					playerTurn = true;

				System.out.println();
				System.out.println();
				System.out.println("---------------------------------------------");

				try {
					Thread.sleep(3000);
				}
				catch (InterruptedException e) { }
			}

			if(board.checkRoundWin(player[0].getPlayerSymbol())){
				System.out.println("The Winner of this round is Player 1 ( "+player[0].getPlayerSymbol()+" )!");
				System.out.println("Player 1 has earned "+board.calculateScore(player[0].getPlayerSymbol())+" points!");
				player[0].setPlayerScore(board.calculateScore(player[0].getPlayerSymbol()));

			} else if(board.checkRoundWin(player[1].getPlayerSymbol())){
				System.out.println("The Winner of this round is Player 2 ( "+player[1].getPlayerSymbol()+" )!");
				System.out.println("Player 2 has earned "+board.calculateScore(player[1].getPlayerSymbol())+" points!");
				player[1].setPlayerScore(board.calculateScore(player[1].getPlayerSymbol()));
			}

			for(int i=0;i<9;i++){
				markedTile[i]=0;
			}

			System.out.println();
			if(!player[0].checkGameWin()&&!player[1].checkGameWin())
				System.out.println("Starting a new round...");

			try {
				Thread.sleep(4000);
			}
			catch (InterruptedException e) { }

			//to refresh the board for next round
			board.setBoard(null,'R');
		}
		if(player[0].checkGameWin()){
			System.out.println("The Winner of this game is Player 1 ( "+player[0].getPlayerSymbol()+" ) Scored: "+ player[0].getPlayerScore()+"!");
		}
		else{
			System.out.println("The Winner of this game is Player 2 ( "+player[1].getPlayerSymbol()+" ) Scored: "+ player[1].getPlayerScore()+"!");
		}
		System.out.println();
		System.out.println("Would you like to play again?");
		System.out.println("	Y (Yes) or N (No)");
		while(true){
			if(sc.hasNext()){
				gameFunction=sc.next();
				if(gameFunction.equalsIgnoreCase("Y")){
					System.out.println("Returning to Main Menu...");
					return;
				}
				else if(gameFunction.equalsIgnoreCase("N")){
					System.out.println("Thank you for playing! See you next time!");
					sc.close();
					System.exit(0);
				}
				else
					System.out.println("Please enter Y for yes, and N for No");
			}
		}
	}




	@Override
	public void actionPerformed(ActionEvent e) {






	}

	public static void main(String[] args){

			String savedFile;
			if(args.length < 1){
				savedFile = "save.txt";
			}
			else{
				savedFile = args[0];
			}

			while(true){
				DataEventController dec;
				Scanner sc = new Scanner(System.in);
				int playerAmount; String playMode;
				System.out.println("Welcome to HollyWood Square! (a tic-tac-toe with a twist!)");
				System.out.println();
				System.out.println("Please choose one of the following options by typing in the corresponding character");
				System.out.println();
				System.out.println("\t New Game \t Load Game \t Exit Game");
				System.out.println("\t    N     \t     L     \t     E");

				while(true) {
					playMode = sc.next();
					if(playMode.equalsIgnoreCase("N")) {
						System.out.println("Please choose which player mode you would like! (1 for single player, 2 for two players)");
						while(true) {
							while (!sc.hasNextInt()) {
								System.out.println("Please choose between 1 (Single Player) or 2 (Two Players)");
								sc.next();
							}
							playerAmount = sc.nextInt();
							if(playerAmount > 2 || playerAmount < 1 ) {
								System.out.println("Please choose between 1 (Single Player) or 2 (Two Players)");
							}
							else
								break;
						}
						if(playerAmount==1)
							dec = new DataEventController(true);
						else
							dec = new DataEventController(false);

						try {
							dec.initializeGame(savedFile);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						break;
					}
					else if(playMode.equalsIgnoreCase("L")){
						Scanner inFile = null;
						try{
							inFile = new Scanner(new FileReader(savedFile));
						}catch(FileNotFoundException e){
							System.out.println("File not found, returning to main menu");
							break;
						}

							//Initializing load game data
							dec = new DataEventController(Boolean.parseBoolean(inFile.nextLine()));
							dec.player[0].setSymbol(inFile.next().charAt(0));
							dec.player[0].setPlayerScore(inFile.nextInt());
							dec.player[1].setSymbol(inFile.next().charAt(0));
							dec.player[1].setPlayerScore(inFile.nextInt());
							while(inFile.hasNextInt())
								dec.board.setBoard(inFile.nextInt(),inFile.next().charAt(0));
							dec.playerTurn = Boolean.parseBoolean(inFile.next());
							for(int j=0;j<10;j++){
								dec.markedTile[j]=Integer.parseInt(inFile.next());
							}

							inFile.close();
							try {
								dec.initializeGame(savedFile);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}	
						break;
					}
					else if(playMode.equalsIgnoreCase("E")){
						System.out.println("Thank you for playing! See you next time!");
						sc.close();
						return;
					}
					else
						System.out.println("Please choose one of the options by their corresponding keyword");
				}
			}
		}
}
