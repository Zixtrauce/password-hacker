package hacker;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ConnectionLink {
    String ip_address;
    int port;
    Socket socket;
    DataInputStream input;
    DataOutputStream output;
    String last_message_in = "";
    public ConnectionLink(String ip_address, int port){
        this.ip_address = ip_address;
        this.port = port;

        //create new socket and datastream to recieve input

        try {
            socket = new Socket(ip_address, port);
            input = new DataInputStream(socket.getInputStream());
            output = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String SendAndReceive(String to_send) {

        try {
            //send message from input to server
            output.writeUTF(to_send);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            //receive message from server
            last_message_in = input.readUTF();
            //return received message
            return last_message_in;
        } catch (IOException e) {
            throw new RuntimeException(e + last_message_in);
        }

    }

}

