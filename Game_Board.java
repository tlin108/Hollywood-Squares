import javax.swing.JButton;
import javax.swing.JPanel;


public class Game_Board extends JPanel {
	private JButton[][] tile;
	private DataEventController dataControl;
	
	public Game_Board(DataEventController dc){
		super();
		dataControl = dc; 
		tile = new JButton[3][3];
		this.setSize(500,500);
		this.setLocation(550, 50);
		for(int i=0;i<9;i++){
			tile[i/3][i%3] = new JButton(Integer.toString(i+1));
			tile[i/3][i%3].addActionListener(dataControl);
			this.add(tile[i/3][i%3]);
		}
		this.setVisible(true);
	}
	/*
	public void refreshBoard(){ //this is pretty much impossible since there's no path to let us fetch board_data contents from Data_Event_Controller
		String bd[][] = dataControl.getBoard().getBoardData(); //we need the getBoard() method in DEH class
		for(int i=0;i<9;i++){
			tile[i/3][i%3].setName(bd[i/3][i%3]);
		}
		}
	*/
	}
