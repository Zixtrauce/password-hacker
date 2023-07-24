package hacker;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Main {
    public static void main(String[] args) {

        //initialize required variables
        String ip_address;
        int port;
        String message_for_sending;

        //process arguments
        ip_address = args[0];
        port = Integer.parseInt(args[1]);
        message_for_sending = args[2];

        try (

            //create new socket and datastream to recieve input
            Socket socket = new Socket(ip_address, port);
            DataInputStream input = new DataInputStream(socket.getInputStream());
            DataOutputStream output = new DataOutputStream(socket.getOutputStream())

          ) {
            //send message from commandline argument to server
            output.writeUTF(message_for_sending);
            //read reply from server
            String receivedMsg = input.readUTF();

            //output received message
            System.out.println(receivedMsg);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
