package hacker;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Iterator;


public class SimpleBruteForcer {
    ArrayDeque<String> workingDeque;
    ConnectionLink workingConnection;

    public SimpleBruteForcer(ArrayDeque<String> workingDeque, ConnectionLink workingConnection) {
        this.workingDeque = workingDeque;
        this.workingConnection = workingConnection;
    }

    public String BruteForce() {

        Iterator<String> it = workingDeque.iterator();
        String failOutput = " ";
        ArrayDeque<String> nextDeque = new ArrayDeque<String>();

        while (it.hasNext()) {
            String bigTest = it.next();

            //try adding all numbers to end of first queue item
            for (int i = 48; i <= 57; i++) {
                String testString = (bigTest + (char)i);
                String result;

                result = workingConnection.SendAndReceive(testString);

                if (result.equals("Connection success!")) {
                    return testString;
                } else if (result.equals("Wrong password!")) {
                    nextDeque.add(testString);
                } else if (result.equals("Too many attempts.")) {
                    return result;
                }
            }

            //try adding all letters to end of first queue item
            for (int i = 97; i <= 122; i++) {
                String testString = (bigTest + (char)i);
                String result;

                result = workingConnection.SendAndReceive(testString);

                if (result.equals("Connection success!")) {
                    return testString;
                } else if (result.equals("Wrong password!")) {
                    nextDeque.add(testString);
                } else if (result.equals("Too many attempts.")) {
                    return result;
                }
            }
        }

        //if this failed, all tried items have been added to end of queue so restart
        //but on those items, effectively adding a new character
        SimpleBruteForcer nextBruteForce = new SimpleBruteForcer(nextDeque, workingConnection);
        return nextBruteForce.BruteForce();
    }



}
