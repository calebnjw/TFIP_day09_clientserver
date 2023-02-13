package main.java.app.client;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Console;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientApp {
  public static void main(String[] args) throws UnknownHostException, IOException {
    String command = ""; // to store commands
    String response; // to store response

    Socket s = new Socket("localhost", 3000); // open a socket

    Console console = System.console();

    try (OutputStream os = s.getOutputStream()) {
      BufferedOutputStream bos = new BufferedOutputStream(os);
      DataOutputStream dos = new DataOutputStream(bos);

      try (InputStream is = s.getInputStream()) {
        BufferedInputStream bis = new BufferedInputStream(is);
        DataInputStream dis = new DataInputStream(bis);

        while (!command.equalsIgnoreCase("quit")) {
          command = console.readLine("Enter \"guess xx\": ");
          dos.writeUTF(command);
          dos.flush();

          response = dis.readUTF();
          System.out.println(response);
        }

        dis.close();
        bis.close();
        is.close();
      } catch (EOFException e) {
        e.printStackTrace();
        s.close();
      }
      dos.close();
      bos.close();
      os.close();
    } catch (EOFException e) {
      e.printStackTrace();
      s.close();
    }

    while (!command.equalsIgnoreCase("quit")) {
    }
  }

}