import java.awt.*;
import javax.swing.*;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.StreamCorruptedException;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.FileWriter;

public class StudentMain extends JComponent implements Serializable {

	private static final long serialVersionUID = 1L;
	private static Socket socket = null;
	private static ServerSocket server = null;
	private static DataInputStream in = null;
	private static Graphics2D g2;
	private static StudentDraw studentDraw = new StudentDraw();
	static ObjectInputStream inn;
	static ObjectOutputStream out;

	public StudentMain(int port) throws IOException {
		// starts server and waits for a connection
		try {
			server = new ServerSocket(port);
			System.out.println("Server started");

			System.out.println("Waiting for a client ...");

			socket = server.accept();
			System.out.println("Client accepted");

		} catch (IOException i) {
			System.out.println(i);
		}
	}

	public static void sendData(int isRaiseHand) throws IOException, StreamCorruptedException {

		out = new ObjectOutputStream(socket.getOutputStream());
		out.writeObject(isRaiseHand);

		out.flush();
	}

	public static void closeconn() {
		System.out.println("Closing connection");
		try {
			socket.close();
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void forAttendance(Student obj) throws IOException {

		try {
			FileWriter myObj = new FileWriter("Attendance.txt");
			myObj.write(obj.name + " is present.");
			myObj.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static int arr[];

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		System.out.println("Student App Started.");
		arr = new int[100];
		int i = 0;
		Student frame = new Student("Ahmet");
		forAttendance(frame);
		StudentMain server = new StudentMain(50006);
		g2 = studentDraw.g2;

		inn = new ObjectInputStream(socket.getInputStream());
		while (true) {
			if (i == 5) {
				i = 0;
			}
			int input = (Integer) inn.readObject();
			arr[i] = input;
			i++;
			if (arr[0] == 4) {
				studentDraw.clear();
				return;
			}
			if (i == 5) {
				studentDraw.drawShape(arr[3], arr[4], arr[1], arr[2], arr[0], g2);
			}
			frame.setPreferredSize(new Dimension(500, 500));
			frame.repaint();
			System.out.println(input);

		}

	}

}
