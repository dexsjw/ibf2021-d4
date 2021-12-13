package ibf2021;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class FCClient {
    public static void main(String[] args) throws UnknownHostException, IOException {
        
        System.out.println("--- Creating client socket ---");
        Socket socket = new Socket("localhost", 12345);

        BufferedInputStream bis = new BufferedInputStream(socket.getInputStream());
        BufferedReader in = new BufferedReader(new InputStreamReader(bis));
        BufferedOutputStream bos = new BufferedOutputStream(socket.getOutputStream());
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(bos));

        System.out.println("Connected to localhost:12345");
        System.out.println("Use \"get-cookie\" to request cookies");
        Scanner scan = new Scanner(System.in);
        String scanLine = scan.nextLine();
        String serverMsg = "";
        
        while (!scanLine.equals("close")) {
            out.write(scanLine);
            out.newLine();
            out.flush();
            serverMsg = in.readLine();

            if (serverMsg.contains("cookie-text")) {
                System.out.println(serverMsg.substring(12));
            } else {
                System.out.println(serverMsg);
            }

            scanLine = scan.nextLine();
        }

        socket.close();
        scan.close();

    }
}
