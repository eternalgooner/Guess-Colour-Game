import javax.swing.JApplet;


public class PracGuessColourApplet extends JApplet {

	/**
	 * Create the applet.
	 */
	public PracGuessColourApplet() {
		PracGuessColourApp panel = new PracGuessColourApp();
		setContentPane(panel);
	}
	public void init(){
		setSize(600, 400);
	}
	

}
