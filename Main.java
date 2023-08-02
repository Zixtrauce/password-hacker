package hacker;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayDeque;

public class Main {
    public static void main(String[] args) {

        //initialize required variables
        String ip_address;
        int port;

        //process arguments
        ip_address = args[0];
        port = Integer.parseInt(args[1]);



        //establish ConnectionLink & create initial deque
        ConnectionLink activeConnection = new ConnectionLink(ip_address, port);
        ArrayDeque<String> testingDeque = new ArrayDeque<String>();
        testingDeque.add("");

        //run the bruteforcer & grab the password returned
        SimpleBruteForcer attack = new SimpleBruteForcer(testingDeque, activeConnection);
        String password = attack.BruteForce();

        //print the password
        System.out.println(password);


        }


}

