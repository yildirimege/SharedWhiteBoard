import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;
import java.awt.RenderingHints;
import java.awt.Image;

public class StudentDraw extends JComponent {

	public int oldX, oldY, curX, curY, operation;
	public static Graphics2D g2;
	private Image image;

	public StudentDraw() {

		setDoubleBuffered(true);

	}

	public void drawShape(int oldX, int oldY, int curX, int curY, int operation, Graphics2D g2) {

		this.g2 = g2;
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
			this.repaint();

		}

		else if (operation == 2) {

			g2.drawLine(oldX, oldY, curX, curY);

		}

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (image == null) {
			image = createImage(getSize().width, getSize().height);
			g2 = (Graphics2D) image.getGraphics();
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			clear();

		}
		g.drawImage(image, 0, 0, this);
	}

	public void clear() {

		g2.setPaint(Color.white);
		g2.fillRect(0, 0, getSize().width, getSize().height);
		g2.setPaint(Color.black);
		repaint();

	}

}
