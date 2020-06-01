import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.IOException;
import java.io.StreamCorruptedException;

public class Student extends JPanel {
	BorderLayout bl;

	JButton handBtn;
	public static String operation;
	StudentDraw sDraw = new StudentDraw();
	protected static int curX, curY, oldX, oldY;
	int isRaiseHand = 0;
	String name;

	ActionListener actionListener = new ActionListener() {

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == handBtn) {
				isRaiseHand = 1;
				try {
					StudentMain.sendData(isRaiseHand);
				} catch (StreamCorruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		}
	};

	public Student(String inputname) {
		this.name = inputname;
		initGui();
	}

	public void initGui() {
		JFrame frame = new JFrame("Student App");

		frame.setLayout(new BorderLayout());

		JPanel controls = new JPanel();
		handBtn = new JButton("Raise Hand");

		handBtn.addActionListener(actionListener);

		controls.add(handBtn);
		frame.add(controls, BorderLayout.NORTH);

		frame.getContentPane().add(sDraw, BorderLayout.CENTER);
		frame.getContentPane().validate();

		frame.setPreferredSize(new Dimension(500, 500));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.pack();
	}

}
