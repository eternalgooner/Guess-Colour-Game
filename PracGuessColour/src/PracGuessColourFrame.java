import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


public class PracGuessColourFrame extends JFrame {

	private JPanel contentPane;
	private static PracGuessColourFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new PracGuessColourFrame();
					frame.setContentPane(new PracGuessColourApp(frame));
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PracGuessColourFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}
	
	public void restartGame(){
		frame.getContentPane().removeAll();
		frame.getContentPane().invalidate();
		repaint();
		frame.getContentPane().add(new PracGuessColourApp(frame));
		frame.getContentPane().revalidate();
		//repaint();
	}

}
