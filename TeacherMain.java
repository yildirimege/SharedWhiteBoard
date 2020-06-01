import java.net.*;
import javax.swing.*;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.StreamCorruptedException;
import java.net.Socket;

public class TeacherMain implements Serializable {
	private static Socket socket = null;
	private static DataInputStream input = null;
	public static ObjectOutputStream out = null;
	public static ObjectInputStream in = null;

	public TeacherMain(String address, int port) {

		try {
			socket = new Socket(address, port);
			System.out.println("Connected");

			// sends output to the socket

		} catch (UnknownHostException u) {
			System.out.println(u);
		} catch (IOException i) {
			System.out.println(i);
		}

	}

	public static void sendData(Boolean isFirst, int curX, int curY, int oldX, int oldY, int operation)
			throws IOException, StreamCorruptedException {

		if (isFirst == true) {
			isFirst = false;
			out = new ObjectOutputStream(socket.getOutputStream());
			out.writeObject(operation);
			out.writeObject(curX);
			out.writeObject(curY);
			out.writeObject(oldX);
			out.writeObject(oldY);

			out.flush();
		} else {
			out.writeObject(operation);
			out.writeObject(curX);
			out.writeObject(curY);
			out.writeObject(oldX);
			out.writeObject(oldY);

			out.flush();
		}

	}

	public static void closeconn() {
		try {
			input.close();
			socket.close();
		} catch (IOException i) {
			System.out.println(i);
		}
	}

	public static void main(String[] args) throws IOException, ClassNotFoundException {

		Teacher frame = new Teacher();
		TeacherMain Client = new TeacherMain("127.0.0.1", 50006);

		in = new ObjectInputStream(socket.getInputStream());

		int input = (int) in.readObject();

		if (input == 1) {
			JOptionPane.showMessageDialog(frame, "Student raised hand");

		}

	}
}
