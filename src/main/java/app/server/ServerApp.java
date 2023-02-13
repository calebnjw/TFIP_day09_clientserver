package main.java.app.server;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class ServerApp {
  public static Integer answer;
  public static Integer guess;

  public static void main(String[] args) throws EOFException, IOException {
    ServerSocket ss = new ServerSocket(3000);
    Socket s = ss.accept();

    InputStream is = s.getInputStream();
    BufferedInputStream bis = new BufferedInputStream(is);
    DataInputStream dis = new DataInputStream(bis);

    OutputStream os = s.getOutputStream();
    BufferedOutputStream bos = new BufferedOutputStream(os);
    DataOutputStream dos = new DataOutputStream(bos);

    Random random = new Random();
    String received = "";
    answer = random.nextInt(100);

    try {
      while (!received.equals("quit")) {
        received = dis.readUTF();

        if (received.contains("guess")) {
          guess = Integer.parseInt(received.substring(6));
        }

        if (guess < answer) {
          dos.writeUTF("Guess a bigger numnber.");
        } else if (guess > answer) {
          dos.writeUTF("Guess a smaller numnber.");
        } else {
          dos.writeUTF("You got it!");
        }
        dos.flush();
      }
    } catch (EOFException e) {
      e.printStackTrace();
    }

    dos.close();
    bos.close();
    os.close();

    dis.close();
    bis.close();
    is.close();
  }
}
