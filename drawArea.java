import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JComponent;

public class drawArea extends JComponent {

	private static Socket socket = null;
	private Image image;
	private Graphics2D g2;
	public static int operation, encounter = 0;
	boolean isFirst = true;
	public static boolean isEnCounterIncreasing = false;
	public static int curX, curY, oldX, oldY;

	public drawArea() {

		setDoubleBuffered(false);
		addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				oldX = e.getX();
				oldY = e.getY();
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				curX = e.getX();
				curY = e.getY();
				
				try {
					TeacherMain.sendData(isFirst, curX, curY, oldX, oldY, operation);
					isFirst = false;

				} catch (IOException e1) {

					e1.printStackTrace();
				}

				if (g2 != null) {
					if (operation == 3) {
						if (curX < oldX && curY > oldY) {
							g2.drawRect(curX, oldY, Math.abs(curX - oldX), Math.abs(curY - oldY));

						} else if (curX > oldX && curY > oldY) {
							g2.drawRect(oldX, oldY, Math.abs(curX - oldX), Math.abs(curY - oldY));

						} else if (curX < oldX && curY < oldY) {
							g2.drawRect(curX, curY, Math.abs(curX - oldX), Math.abs(curY - oldY));
						} else if (curX > oldX && curY < oldY) {

							g2.drawRect(oldX, curY, Math.abs(curX - oldX), Math.abs(curY - oldY));
						}

						encounter++;
						Teacher.enconterLbl.setText("Count Shapes " + String.valueOf(drawArea.encounter));
						isEnCounterIncreasing = true;
						repaint();
						isEnCounterIncreasing = false;

					} else if (operation == 1) {

						if (curX < oldX && curY > oldY) {
							g2.drawOval(curX, oldY, Math.abs(curX - oldX), Math.abs(curY - oldY));

						} else if (curX > oldX && curY > oldY) {
							g2.drawOval(oldX, oldY, Math.abs(curX - oldX), Math.abs(curY - oldY));

						} else if (curX < oldX && curY < oldY) {
							g2.drawOval(curX, curY, Math.abs(curX - oldX), Math.abs(curY - oldY));
						} else if (curX > oldX && curY < oldY) {

							g2.drawOval(oldX, curY, Math.abs(curX - oldX), Math.abs(curY - oldY));
						}
						encounter++;
						Teacher.enconterLbl.setText("Count Shapes " + String.valueOf(drawArea.encounter));
						repaint();
					}

					else if (operation == 2) {
						g2.drawLine(oldX, oldY, curX, curY);
						encounter++;
						Teacher.enconterLbl.setText("Count Shapes " + String.valueOf(drawArea.encounter));
						repaint();
					}
				}

			}

		});

	}

	@Override
	protected void paintComponent(Graphics g) {
		if (image == null) {
			image = createImage(getSize().width, getSize().height);
			g2 = (Graphics2D) image.getGraphics();
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			clear();

		}
		g.drawImage(image, 0, 0, null);
	}

	public void clear() {

		g2.setPaint(Color.white);
		g2.fillRect(0, 0, getSize().width, getSize().height);
		g2.setPaint(Color.black);
		repaint();

	}

}