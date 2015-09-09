import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;


public class Game_Display extends JFrame{
	private JTextArea scoreDisplay, questionDisplay;
	private Game_Board boardDisplay;
	private JButton Accept, Reject;
	private DataEventController dataControl; 
	
	public Game_Display(DataEventController dc){
		super();
		dataControl = dc; 
		scoreDisplay = new JTextArea();
		questionDisplay = new JTextArea();
		boardDisplay = new Game_Board(dc);
		Accept = new JButton("Accept");
		Reject = new JButton("Reject");
		Accept.addActionListener(dataControl);
		Reject.addActionListener(dataControl);
		this.add(scoreDisplay);
		this.add(questionDisplay);
		this.add(boardDisplay);
		this.add(Accept);
		this.add(Reject);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(500,500);
		this.setLocation(50,50);
		this.setVisible(true);
		
		
	}
}
