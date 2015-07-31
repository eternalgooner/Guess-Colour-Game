//DAVID MACKESSY 205-A

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import java.awt.SystemColor;


public class DavidMackessyGuessWordApp extends JPanel {
	private JTextField txtDisplayWord;
	private JTextField txtLives;	
	private String originalWord;
	private char[] displayNewWord;
	private char[] displayAsterisks;
	private int wordCounter = 0;
	private int lives = 8;
	private JPanel panel = this;
	
	public DavidMackessyGuessWordApp() {
		setBackground(SystemColor.control);
		setLayout(null);
		initButtons();
		
		JLabel lblGuessTheWord = new JLabel("GUESS THE WORD");
		lblGuessTheWord.setHorizontalAlignment(SwingConstants.CENTER);
		lblGuessTheWord.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblGuessTheWord.setForeground(Color.RED);
		lblGuessTheWord.setBounds(288, 32, 193, 20);
		add(lblGuessTheWord);
		
		txtDisplayWord = new JTextField();
		txtDisplayWord.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtDisplayWord.setHorizontalAlignment(SwingConstants.RIGHT);
		txtDisplayWord.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtDisplayWord.setEditable(false);
		txtDisplayWord.setBounds(288, 63, 193, 20);
		add(txtDisplayWord);
		//set characters wide to 24
		txtDisplayWord.setColumns(24);
		
		JLabel lblGuessesRemaining = new JLabel("GUESSES REMAINING");
		lblGuessesRemaining.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblGuessesRemaining.setForeground(Color.RED);
		lblGuessesRemaining.setBounds(288, 113, 125, 14);
		add(lblGuessesRemaining);
		
		txtLives = new JTextField();
		txtLives.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtLives.setHorizontalAlignment(SwingConstants.RIGHT);
		txtLives.setEditable(false);
		txtLives.setBounds(433, 110, 48, 20);
		add(txtLives);
		//set characters wide to 3
		txtLives.setColumns(3);
	}
	//create method to generate buttons needed & fill with letters/words from letters array
	public void initButtons(){
		JButton[] buttons = new JButton[28];
		String[] letters = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "START", "ANSWER"};
		int x = 10;
		int y = 230;
		int width = 85;
		int height = 25;
		for(int i = 0; i < buttons.length; ++i){
			if(i % 7 == 0){
				x = 10;
				y = y + height;
			}else{
				x = x + width;
			}
			buttons[i] = new JButton();
			buttons[i].setBounds(x, y, width, height);
			add(buttons[i]);
			buttons[i].setText(letters[i]);
			buttons[i].addActionListener(myActionListener);
			buttons[i].setBackground(SystemColor.controlHighlight);
			buttons[i].setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		}
	}
	//create actionlistener to add to every button
	ActionListener myActionListener = new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent event) {
			String buttonPressed = event.getActionCommand();		//grab whatever text is on the button pressed
			if(buttonPressed.equalsIgnoreCase("Start")){			//if Start pressed execute the following code
				
				//clear both text fields
				txtDisplayWord.setText("");
				txtLives.setText("");
				
				//reset word counter to 1st word after cycling through all 5
				if(wordCounter == 5){
					wordCounter = 0;
				}
				//capture the current word being used
				String[] words = {"MEMORY", "COMPUTER", "PRINTER", "TROUSERS", "BUTTERCUP"};
				originalWord = words[wordCounter];
				displayNewWord = originalWord.toCharArray();
				displayAsterisks = originalWord.toCharArray();
				
				//fill asterisks array with * for each element
				for(int i = 0; i < originalWord.length(); ++i){
					displayAsterisks[i] = '*';
				}
				String display = new String(displayAsterisks);
				txtDisplayWord.setText(display);
				
				//set lives to 8 when new game starts & display to text field
				lives = 8;
				txtLives.setText("" + lives);
				
				//increment word counter to use the next word in the array
				++wordCounter;
			}else if(buttonPressed.equalsIgnoreCase("Answer")){			//if Answer pressed, execute the following code	
				txtDisplayWord.setText(originalWord);
				
				//set lives to -1 so when answer is pressed & displayed, this disables all buttons except for Start. This makes it feel more user-friendly
				lives = -1;
				//clear guesses text field
				txtLives.setText("");
			}else{														//if letter chosen, execute the following code
				if(lives != -1){					
				
					//create char for letter chosen by user
					char charChosen = buttonPressed.charAt(0);
					
					//check to see if the letter guessed is in the guess word
					int index = originalWord.indexOf(charChosen);
					
					//if letter not found in word
					if(index == -1){
						--lives;
						txtLives.setText("" + lives);				
					}
					//if letter is found in word
					else{
						for(int i = 0; i < originalWord.length(); ++i){
							if(displayNewWord[i] == charChosen){
								displayAsterisks[i] = charChosen;
							}
						}
						String display = new String (displayAsterisks);
						txtDisplayWord.setText(display);
					}
				}
			}
			//check to see if the user has won & display appropriate message to screen
			//if user has won
			if(lives != -1){
				if(txtDisplayWord.getText().equals(originalWord) && !buttonPressed.equalsIgnoreCase("Answer")){	
					JLabel lblWin = new JLabel("            You Win");
					lblWin.setFont(new Font("Tahoma", Font.BOLD, 14));
					lblWin.setForeground(Color.BLUE);
					JOptionPane.showMessageDialog(panel, lblWin);
				}
				//if user has lost
				else if(!txtDisplayWord.getText().equals(originalWord) && lives == 0){							
					JLabel lblLose = new JLabel("           You Lose");
					lblLose.setFont(new Font("Tahoma", Font.BOLD, 14));
					lblLose.setForeground(Color.RED);				
					JOptionPane.showMessageDialog(panel, lblLose);
				}
			}
		}		
	};	
}
