import java.util.Random;

public class QuestionData {

	private String[][] question;
	private int questionNo;

	public QuestionData(){
		int row = 41;
		int col = 2;
		
		question = new String[row][col];
		question[0][0] = "Pluto is the largest object in the Kuiper belt.";
		question[0][1] = "true";
		question[1][0] = "Eris is the most massive dwarf planet known in our Solar System.";
		question[1][1] = "true";
		question[2][0] = "The programming language C++ was designed by Dennis Ritchie.";
		question[2][1] = "false";//Bjarne Stroustrup
		question[3][0] = "A dog's nose print is as unique as a human fingerprint.";
		question[3][1] = "true";
		question[4][0] = "Does image acquisition include scanner video and camera? ";
		question[4][1] = "true";
		question[5][0] = "Seven algorithm for grayscale image include averaging, luminance,"
				+ " desaturation, decomposition min/max, single color channeling, custom color of gray"
				+ "and custom color of gray with dithering.";
		question[5][1] = "true";
		question[6][0] = "Desaturation equation is [max(R,G,B) - min(R,G,B)]/2.";
		question[6][1] = "false";//plus
		question[7][0] = "Saturation is how much green (how much wavelength) you are interested in.";
		question[7][1] = "false";//red
		question[8][0] = "When passing by reference you are sending the address that contains the value"
				+ " that allows value to change. ";
		question[8][1] = "true";
		question[9][0] = "In a BiModal histogram, there are 3 clear peaks.";
		question[9][1] = "false";//2 clear peaks
		question[10][0] = "i rows, j cols --> row major = (i*c) + (j+1) and "
				+ "col major = (j*c)-(i+1)";
		question[10][1] = "false";//plus
		question[11][0] = "GMM stands for Gaussian Mathematical Models";
		question[11][1] = "false";//Mixture Model 
		question[12][0] = "The probability of a color in background is histogram(color)/sum of all histogram(colors).";
		question[12][1] = "true";
		question[13][0] = "In the convex hull algorithm, you run through each point clockwise";
		question[13][1] = "false";//counterclockwise
		question[14][0] = "Problems in image acquisition is noise and variance";
		question[14][1] = "true";
		question[15][0] = "Thresholding would enable area and perimeter calculations directly "
				+ "from a grayscale image?";
		question[15][1] = "true";
		question[16][0] = "Central moments is about the mean and raw moments is about zero.";
		question[16][1] = "true";
		question[17][0] = "Standard deviation is the square root of mean";
		question[17][1] = "false";//variance
		question[18][0] = "Leukocytes is also commonly known as red blood cells.";
		question[18][1] = "false";//erythrocytes
		question[19][0] ="Gorillas are able to lift an estimate of over 2,000kg.";
		question[19][1] = "true";
		question[20][0] = "The reverse side of the Nobel Peace Prize shows three naked men standing with their hands on"
				+ "each other's shoulders.";
		question[20][1] = "true";
		question[21][0] = "The space between your eyebrows is called the 'rasceta'?";
		question[21][1] = "false";//glabella
		question[22][0] = "Olympus Mons, located on planet Mars, is nearly at a max height of no more than 12.5km(8mi)?";
		question[22][1] = "false";//nearly 25km(15mi)
		question[23][0] = "Thomas Jefferson was the 4th president of the United States?";
		question[23][1] = "false";//James Madison
		question[24][0] = "The Treaty of Paris that ended the American Revolutionary War was singed in 1812?";
		question[24][1] = "false";//1783
		question[25][0] = "The shortest possible sentence contains a subject, a verb, and an object?";
		question[25][1] = "false";//Think!
		question[26][0] = "A preposition is usually followed by a noun?";
		question[26][1] = "true";
		question[27][0] = "An ostrich's eye is bigger than its brain?";
		question[27][1] = "true";
		question[28][0] = "The Declaration of independence was signed on July 4, 1776?";
		question[28][1] = "false";//August
		question[29][0] = "Bill Gates, co-founder of Microsoft, was arrested in New Mexico in 1977 for "
				+ "jumping a red light and driving without a licence?";
		question[29][1] = "true";
		question[30][0] = "In a model view controller, the view broadcasts a signal to the user?";
		question[30][1] = "false";//model
		question[31][0] = "CMYK refer 4 ink colors --> Cyan, Maroon, Yellow, Kispen";
		question[31][1] = "false";
		question[32][0] = "Alpha channel is the process of combining an image with a background to create the "
				+ "appearance of partial or full transparency";
		question[32][1] = "true";
		question[33][0] = "The author of the novel 'Of Mice and Men' is J.D. Salinger.";
		question[33][1] = "false";//John Steinbeck
		question[34][0] = "All motion stops at terminal velocity.";
		question[34][1] = "false";
		question[35][0] = "The earth's gravity is accelerating all satellites in orbit towards its center.";
		question[35][1] = "true";
		question[36][0] = "Light is a wave of electromagnetic energy.";
		question[36][1] = "true";
		question[37][0] = "Earth's atmosphere is mostly Nitrogen";
		question[37][1] = "true";
		question[38][0] = "Newton's First Law of Motion states 'For every action there is an equal and opposite reaction.'";
		question[38][1] = "false";//Newton's Third Law of Motion
		question[39][0] = "Water has a higher refractive index than glass.";
		question[39][1] = "false";//water is 1.33, glass is 1.5
		question[40][0] = "The longest bone in the human body is the femur.";
		question[40][1] = "true";
    }

	public String getQuestion(){

		int random = (int) (Math.random()*question.length);
		questionNo = random;
		return question[random][0];

	}

	public boolean checkAnswer(boolean answer, boolean fakeAnswer){

		if(answer == true && fakeAnswer == true && question[questionNo][1].equals(Boolean.toString(true))) return true;

		if(answer == false && fakeAnswer == false && question[questionNo][1].equals(Boolean.toString(true))) return true;

		if(answer == true && fakeAnswer == false && question[questionNo][1].equals(Boolean.toString(false))) return true;

		if(answer == false && fakeAnswer == true && question[questionNo][1].equals(Boolean.toString(false))) return true;

		if(answer == false && fakeAnswer == true && question[questionNo][1].equals(Boolean.toString(true))) return false;

		if(answer == true && fakeAnswer == false && question[questionNo][1].equals(Boolean.toString(true))) return false;

		if(answer == false && fakeAnswer == false && question[questionNo][1].equals(Boolean.toString(false))) return false;

		if(answer == true && fakeAnswer == true && question[questionNo][1].equals(Boolean.toString(false))) return false;

		else
			return false;

	}

	public String generateFakeAnswer() {

		String GeneratedAnswer = "";
		int answer = (int) (Math.random()*2);

		if (answer == 0) {
			return GeneratedAnswer = "true";
		}
		else {
			return GeneratedAnswer = "false";
		}

	}


}
