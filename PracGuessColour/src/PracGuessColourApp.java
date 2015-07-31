import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;



public class PracGuessColourApp extends JPanel {
	private JTextField[] textFields = new JTextField[18];
	private int textFieldCounter = 0;
	private String[] userGuess = new String[3];
	private int guessCounter = 1;
	private int colourCounter = 0;
	private String[] colours = {"Red", "Green", "Blue", "Black", "Yellow"};
	private JTextField txtRandomCode1;
	private JTextField txtRandomCode2;
	private JTextField txtRandomCode3;
	private String[] randomColourCode = new String[3];
	private String buttonPressed;
	private Color userChoice;
	private JLabel lbl1;
	private JLabel lbl2;
	private JLabel lbl3;
	private JLabel lbl4;
	private JLabel lbl5;
	private JLabel lbl6;
	private JLabel lblGameOutcome;
	
	private static PracGuessColourFrame parentFrame;
	
	public PracGuessColourApp(PracGuessColourFrame frame){
		this();
		parentFrame = frame;
	}
	/**
	 * Create the panel.
	 */
	public PracGuessColourApp() {
		setLayout(null);
		initTextFields();
		generateRandomColourCode();
		System.out.println(randomColourCode[0]);
		System.out.println(randomColourCode[1]);
		System.out.println(randomColourCode[2]);
		
				
		JLabel lblNewLabel = new JLabel("HITS AND MISSES");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(169, 336, 269, 14);
		add(lblNewLabel);
		
		JLabel lblClickAColour = new JLabel("Click a colour");
		lblClickAColour.setHorizontalAlignment(SwingConstants.CENTER);
		lblClickAColour.setBounds(42, 11, 124, 14);
		add(lblClickAColour);
		
		JButton btnRed = new JButton("Red");
		btnRed.setBounds(11, 27, 63, 30);
		add(btnRed);
		btnRed.addActionListener(myActionListener);
		
		JButton btnGreen = new JButton("Green");
		btnGreen.setBounds(74, 27, 68, 30);
		add(btnGreen);
		btnGreen.addActionListener(myActionListener);
		
		JButton btnBlue = new JButton("Blue");
		btnBlue.setBounds(143, 27, 64, 30);
		add(btnBlue);
		btnBlue.addActionListener(myActionListener);
		
		JButton btnBlack = new JButton("Black");
		btnBlack.setBounds(35, 58, 67, 30);
		add(btnBlack);
		btnBlack.addActionListener(myActionListener);
		
		JButton btnYellow = new JButton("Yellow");
		btnYellow.setBounds(102, 58, 78, 30);
		add(btnYellow);
		btnYellow.addActionListener(myActionListener);
		
		lblGameOutcome = new JLabel("Guess the hidden code");
		lblGameOutcome.setHorizontalAlignment(SwingConstants.CENTER);
		lblGameOutcome.setBounds(125, 312, 158, 14);
		add(lblGameOutcome);
		
		txtRandomCode1 = new JTextField();
		txtRandomCode1.setBackground(Color.WHITE);
		txtRandomCode1.setEditable(false);
		txtRandomCode1.setBounds(347, 295, 30, 30);
		add(txtRandomCode1);
		txtRandomCode1.setColumns(10);
		
		txtRandomCode2 = new JTextField();
		txtRandomCode2.setBackground(Color.WHITE);
		txtRandomCode2.setEditable(false);
		txtRandomCode2.setColumns(10);
		txtRandomCode2.setBounds(387, 295, 30, 30);
		add(txtRandomCode2);
		
		txtRandomCode3 = new JTextField();
		txtRandomCode3.setBackground(Color.WHITE);
		txtRandomCode3.setEditable(false);
		txtRandomCode3.setColumns(10);
		txtRandomCode3.setBounds(427, 295, 30, 30);
		add(txtRandomCode3);
		
		lbl1 = new JLabel("1st guess");
		lbl1.setBounds(502, 54, 60, 14);
		add(lbl1);
		
		lbl2 = new JLabel("2nd guess");
		lbl2.setVisible(false);
		lbl2.setBounds(502, 98, 60, 14);
		add(lbl2);
		
		lbl3 = new JLabel("3rd guess");
		lbl3.setVisible(false);
		lbl3.setBounds(502, 140, 60, 14);
		add(lbl3);
		
		lbl4 = new JLabel("4th guess");
		lbl4.setVisible(false);
		lbl4.setBounds(502, 178, 60, 14);
		add(lbl4);
		
		lbl5 = new JLabel("5th guess");
		lbl5.setVisible(false);
		lbl5.setBounds(502, 215, 60, 14);
		add(lbl5);
		
		lbl6 = new JLabel("6th guess");
		lbl6.setVisible(false);
		lbl6.setBounds(502, 256, 60, 14);
		add(lbl6);
		
		JButton btnStart = new JButton("Start New Game");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				parentFrame.restartGame();
			}
		});
		btnStart.setBounds(24, 211, 142, 30);
		add(btnStart);
		
		
	}
	//create 18 textfields using a loop, stored in an array
	private void initTextFields(){
		int x = 350;
		int y = 10;
		int width = 30;
		int height = 30;
		for(int i = 0; i < textFields.length; ++i){
			if(i % 3 == 0){
				y = y + height + 10;
				x = 350;
			}else{
				x = x + width + 10;
			}
			textFields[i] = new JTextField();
			textFields[i].setBounds(x, y, width, height);
			textFields[i].setEditable(false);
			add(textFields[i]);
			if(i > 2){
				textFields[i].setVisible(false);
			}
			
		}
	}
	//create method to display the next 3 fields when appropriate - use loop & textfield counter
	private void displayNextFields(){
			
			for(int i = textFieldCounter; i < (textFieldCounter + 3); ++i){
				if(i < textFields.length){
					textFields[i].setVisible(true);;
				}
			
			}
	}
	//create method to show next label
	private void nextLabel(){
		switch(guessCounter){
			case 2: lbl2.setVisible(true); break;
			case 3: lbl3.setVisible(true); break;
			case 4: lbl4.setVisible(true); break;
			case 5: lbl5.setVisible(true); break;
			case 6: lbl6.setVisible(true); break;
		}
	}
	//create my actionListener
	ActionListener myActionListener = new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent event) {
			buttonPressed = event.getActionCommand();
			userChoice = colourSelected(buttonPressed);
			//System.out.println(buttonPressed); I used this line to test whether the button is returning the correct colour
			
			//capture user guess
			userGuess[colourCounter] = buttonPressed;
			
			//fill in textFields
			System.out.println(userGuess[colourCounter]);
			if(textFieldCounter < 18){
				textFields[textFieldCounter].setBackground(userChoice);
				++textFieldCounter;
				++colourCounter;
			}
			
		
			// when colourCounter equals 3, check the user guess with the random colour code
			if(colourCounter == 3){
				if(hasWon()){
					lblGameOutcome.setText("You Win!!");
					txtRandomCode1.setBackground(colourSelected(randomColourCode[0]));
					txtRandomCode2.setBackground(colourSelected(randomColourCode[1]));
					txtRandomCode3.setBackground(colourSelected(randomColourCode[2]));
				}else if(guessCounter > 5 && !hasWon()){
					lblGameOutcome.setText("You lose!!");
					txtRandomCode1.setBackground(colourSelected(randomColourCode[0]));
					txtRandomCode2.setBackground(colourSelected(randomColourCode[1]));
					txtRandomCode3.setBackground(colourSelected(randomColourCode[2]));
				}
				++guessCounter;
				colourCounter = 0;
				displayNextFields();
			}
							
			nextLabel();
			
			//display random colour code at end game or if game won
						
		}
		
	};
	// create method to check if the guess is correct
	private boolean hasWon(){
		boolean isWinner = true;
		for(int i = 0; i < randomColourCode.length; ++i){
			if(!userGuess[i].equals(randomColourCode[i])){
				System.out.println(randomColourCode[i]);
				System.out.println(userGuess[i]);
				isWinner = false;
			}
		}
		return isWinner;
	}
	
	//generate our random colour code to be guessed
	private void generateRandomColourCode(){
		Random random = new Random();
		int numColour = random.nextInt(colours.length);
		for(int i = 0; i < randomColourCode.length; ++i){
			numColour = random.nextInt(colours.length);
			randomColourCode[i] = colours[numColour];
		}
		
	}
	//return the colour selected with the actual colour
	private Color colourSelected(String buttonPressed){
		Color colourSelected = null;
		switch(buttonPressed){
			case "Red": colourSelected = Color.RED; break;
			case "Green": colourSelected = Color.GREEN; break;
			case "Blue": colourSelected = Color.BLUE; break;
			case "Black": colourSelected = Color.BLACK; break;
			case "Yellow": colourSelected = Color.YELLOW; break;
		}
		return colourSelected;
	}
}
