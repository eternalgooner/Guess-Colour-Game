import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import javax.swing.text.Highlighter.HighlightPainter;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.BevelBorder;


public class FindTextApp extends JPanel {
	private JTextField txtEnterWord;
	private JTextField txtStatus;
	private JTextField txtNumPar;
	private JTextField txtNumCharWithoutSpaces;
	private JTextField txtNumCharIncSpaces;
	private JTextField txtNumWords;
	private JTextArea txtArea;

	/**
	 * Create the panel.
	 */
	public FindTextApp() {
		setBackground(Color.LIGHT_GRAY);
		//set layout to null, so I'm not restricted at placing components in the panel
		setLayout(null);
		
		//code for creating textfields, text area, buttons & labels
		txtArea = new JTextArea();
		txtArea.setLineWrap(true);
		txtArea.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtArea.setBounds(48, 40, 289, 145);
		add(txtArea);
		
		JLabel lblNewLabel = new JLabel("Enter text");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(151, 15, 81, 14);
		add(lblNewLabel);
		
		JLabel lblEnterWord = new JLabel("Enter word");
		lblEnterWord.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEnterWord.setBounds(372, 15, 100, 14);
		add(lblEnterWord);
		
		txtEnterWord = new JTextField();
		txtEnterWord.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtEnterWord.setBounds(372, 42, 183, 20);
		add(txtEnterWord);
		txtEnterWord.setColumns(10);
		
		JLabel lblStatus = new JLabel("Status");
		lblStatus.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblStatus.setBounds(372, 73, 46, 14);
		add(lblStatus);
		
		txtStatus = new JTextField();
		txtStatus.setBackground(Color.LIGHT_GRAY);
		txtStatus.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtStatus.setEditable(false);
		txtStatus.setBounds(372, 98, 183, 20);
		add(txtStatus);
		txtStatus.setColumns(10);
		
		JButton btnFindWord = new JButton("Find Word");
		btnFindWord.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		
		//add action listener to the find word button
		btnFindWord.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//code to highlight text found in text area using highlighter
				Highlighter highlighter = txtArea.getHighlighter();
				HighlightPainter painter = new DefaultHighlighter.DefaultHighlightPainter(Color.LIGHT_GRAY);
				highlighter.removeAllHighlights();
				
				//set where to start highlighting & where to stop
				int one = txtArea.getText().indexOf(txtEnterWord.getText());
				int two =  one + txtEnterWord.getText().length();
				
				try{
					highlighter.addHighlight(one, two, painter);
				}catch (BadLocationException e1){
					
				}
				
				//this code will search the text area for the word to be searched and then highlight when found
				//it will also update the text status field with the appropriate message & colour
											
				int index = txtArea.getText().indexOf(txtEnterWord.getText());
				
				//if  text area empty do nothing
				if(txtArea.getText().equals("") || txtEnterWord.getText().equals("")){
					txtStatus.setText("");
					
					//if word found
				}else if(index != -1){
					txtStatus.setText("Word found at: " + (index + 1));
					txtStatus.setForeground(Color.BLUE);
					
					//if word not found
				}else{
					txtStatus.setText("Word not found");
					txtStatus.setForeground(Color.RED);					
				}
				
			}
		});
		btnFindWord.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnFindWord.setBackground(Color.LIGHT_GRAY);
		btnFindWord.setBounds(372, 149, 89, 36);
		add(btnFindWord);
		
		JButton btnClear = new JButton("Clear");
		btnClear.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnClear.setFont(new Font("Tahoma", Font.PLAIN, 10));
		
		//add action listener to Clear button
		btnClear.addActionListener(new ActionListener() {
			
			//this code will clear all fields when the clear button is pressed
			public void actionPerformed(ActionEvent arg0) {
				txtArea.setText("");
				txtEnterWord.setText("");
				txtNumWords.setText("");
				txtNumCharIncSpaces.setText("");
				txtNumCharWithoutSpaces.setText("");
				txtNumPar.setText("");
				txtStatus.setText("");
				
			}
		});
		btnClear.setBackground(Color.LIGHT_GRAY);
		btnClear.setBounds(466, 149, 89, 36);
		add(btnClear);
		
		JButton btnWordCount = new JButton("Word Count");
		btnWordCount.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		
		//add action listener
		btnWordCount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//code to count how many characters are in the text area & update appropriate textfield
				
				//if text area empty, do nothing
				if(txtArea.getText().equals("")){
					txtNumCharIncSpaces.setText("");
				}else{
					//display text length
					txtNumCharIncSpaces.setText("" + txtArea.getText().length());
				}
				
				//code to count the amount of paragraphs in the text area, search using the index of function, 
				//looking for the string "\n". Once found update appropriate textfield
				int paraCounter = 0;				//create counter for number of paragraphs found
				int indexOfPar = 0;					
				
				//if text area empty, do nothing
				if(txtArea.getText().equals("")){
					txtNumPar.setText("");
					
					//if "\n" string found, increment counter & update location of next indexOf check
				}else{
					for(int i = 0; i < txtArea.getText().length(); ++i){
						indexOfPar = txtArea.getText().indexOf("\n", i);
						if(indexOfPar != -1){
							++paraCounter;
							i = indexOfPar;
						}
					}
					txtNumPar.setText("" + (paraCounter + 1));
				}

				//code to count the amount of spaces in the text area so I can deduct from the text length
				//to get the number of characters without spaces & update appropriate textfield
				int spaceCounter = 0;
				int indexOfSpace = 0;
				
				//if text area empty, do nothing
				if(txtArea.getText().equals("")){
					txtNumCharWithoutSpaces.setText("");
					
					//if " " string found, increment counter & update location of next indexOf check
				}else{
					for(int i = 0; i < txtArea.getText().length(); ++i){
						indexOfSpace = txtArea.getText().indexOf(" ", i);
						if(indexOfSpace != -1){
							++spaceCounter;
							i = indexOfSpace;
						}
					}
					txtNumCharWithoutSpaces.setText("" + (txtArea.getText().length() - spaceCounter));
				}
				
				
				//code to count how many words are in the text area using the spaceCounter already setup
				//getting the text length & deducting the number of spaces in the text
				//I added the paragraph counter in here also, as I assume when the carriage return is pressed that a new word is to follow
				if(txtArea.getText().equals("")){
					txtNumWords.setText("");
				}else{
					txtNumWords.setText("" + (spaceCounter + 1 + paraCounter)); // used +1 here if only one word entered, as no spaces or \n will be counted
				}
			}
		});			
		
		btnWordCount.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnWordCount.setBackground(Color.LIGHT_GRAY);
		btnWordCount.setBounds(416, 266, 105, 36);
		add(btnWordCount);
		
		txtNumPar = new JTextField();
		txtNumPar.setBackground(Color.LIGHT_GRAY);
		txtNumPar.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtNumPar.setEditable(false);
		txtNumPar.setBounds(284, 308, 53, 20);
		add(txtNumPar);
		txtNumPar.setColumns(10);
		
		txtNumCharWithoutSpaces = new JTextField();
		txtNumCharWithoutSpaces.setBackground(Color.LIGHT_GRAY);
		txtNumCharWithoutSpaces.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtNumCharWithoutSpaces.setEditable(false);
		txtNumCharWithoutSpaces.setColumns(10);
		txtNumCharWithoutSpaces.setBounds(284, 282, 53, 20);
		add(txtNumCharWithoutSpaces);
		
		txtNumCharIncSpaces = new JTextField();
		txtNumCharIncSpaces.setBackground(Color.LIGHT_GRAY);
		txtNumCharIncSpaces.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtNumCharIncSpaces.setEditable(false);
		txtNumCharIncSpaces.setColumns(10);
		txtNumCharIncSpaces.setBounds(284, 251, 53, 20);
		add(txtNumCharIncSpaces);
		
		txtNumWords = new JTextField();
		txtNumWords.setBackground(Color.LIGHT_GRAY);
		txtNumWords.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtNumWords.setEditable(false);
		txtNumWords.setColumns(10);
		txtNumWords.setBounds(284, 222, 53, 20);
		add(txtNumWords);
		
		JLabel lblNumberOfWords = new JLabel("Number of words");
		lblNumberOfWords.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNumberOfWords.setBounds(48, 225, 226, 14);
		add(lblNumberOfWords);
		
		JLabel lblNumberOfCharacters = new JLabel("Number of characters including spaces");
		lblNumberOfCharacters.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNumberOfCharacters.setBounds(48, 254, 226, 14);
		add(lblNumberOfCharacters);
		
		JLabel lblNumberOfCharacteres = new JLabel("Number of characters without spaces");
		lblNumberOfCharacteres.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNumberOfCharacteres.setBounds(48, 283, 225, 14);
		add(lblNumberOfCharacteres);
		
		JLabel lblNumberOfParagraphs = new JLabel("Number of paragraphs");
		lblNumberOfParagraphs.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNumberOfParagraphs.setBounds(48, 311, 226, 14);
		add(lblNumberOfParagraphs);
		
		JLabel lblCountOfWords = new JLabel("Count of words and charcters");
		lblCountOfWords.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCountOfWords.setHorizontalAlignment(SwingConstants.CENTER);
		lblCountOfWords.setBounds(75, 200, 219, 14);
		add(lblCountOfWords);

	}
}
