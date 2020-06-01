import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.StreamCorruptedException;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import javax.swing.*;

public class Teacher extends JPanel implements MouseListener, ActionListener {
	BorderLayout bl;

	static JLabel enconterLbl;
	JButton clearBtn, circBtn, lineBtn, rectBtn;
	static JTextField forTimer;
	public static int operation;
	drawArea drawArea1 = new drawArea();
	public static int curX, curY, oldX, oldY;
	static double countForTime = 60.00;

	ActionListener actionListener = new ActionListener() {

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == clearBtn) {
				drawArea1.clear();
				try {

					TeacherMain.sendData(false, 0, 0, 0, 0, 4);

				} catch (StreamCorruptedException f) {
				} catch (IOException f) {
				}
			}
			if (e.getSource() == circBtn) {
				drawArea1.operation = 1;
			} else if (e.getSource() == lineBtn) {
				drawArea1.operation = 2;
			} else if (e.getSource() == rectBtn) {
				drawArea1.operation = 3;
			}
		}
	};

	public Teacher() {

		initGui();

	}

	public void initGui() {
		JFrame frame = new JFrame("Teacher App");
		frame.setLayout(new BorderLayout());

		forTimer = new JTextField();

		java.util.Timer Mytimer = new java.util.Timer();
		TimerTask task = new TimerTask() {

			@Override
			public void run() {
				Long formatT = (new Double(countForTime).longValue());
				String hms = String.format("%02d:%02d",
						TimeUnit.SECONDS.toMinutes(formatT) % TimeUnit.HOURS.toMinutes(1),
						TimeUnit.SECONDS.toSeconds(formatT) % TimeUnit.MINUTES.toSeconds(1));
				forTimer.setText(hms);
				forTimer.setHorizontalAlignment(SwingUtilities.CENTER);
				countForTime--;
				if (countForTime == -1) {
					JOptionPane.showMessageDialog(frame, "Time is over");
				}

			}
		};

		Mytimer.schedule(task, 0, 1000);

		JPanel controls = new JPanel();
		enconterLbl = new JLabel("Count Shapes " + "0");

		frame.getContentPane().add(controls, BorderLayout.NORTH);

		clearBtn = new JButton("Clear");
		circBtn = new JButton("Circle");
		lineBtn = new JButton("Line");
		rectBtn = new JButton("Rectangle");

		clearBtn.addActionListener(actionListener);
		circBtn.addActionListener(actionListener);
		lineBtn.addActionListener(actionListener);
		rectBtn.addActionListener(actionListener);

		controls.add(clearBtn);
		controls.add(circBtn);
		controls.add(lineBtn);
		controls.add(rectBtn);
		controls.add(enconterLbl);
		controls.add(forTimer);

		frame.add(controls, BorderLayout.NORTH);
		frame.add(drawArea1, BorderLayout.CENTER);

		frame.setSize(500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		oldX = e.getX();
		oldY = e.getY();

	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	}
}
