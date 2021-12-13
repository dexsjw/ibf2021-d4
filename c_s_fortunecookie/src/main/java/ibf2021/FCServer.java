package ibf2021;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class FCServer {
    public static void main(String[] args) throws IOException {

        System.out.println("--- Server listening at port 12345 ---");
        ServerSocket server = new ServerSocket(12345);
        Socket socket = server.accept();
        
        BufferedInputStream bis = new BufferedInputStream(socket.getInputStream());
        BufferedReader in = new BufferedReader(new InputStreamReader(bis));
        BufferedOutputStream bos = new BufferedOutputStream(socket.getOutputStream());
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(bos));

        //C:\Users\dexte\Documents\NUS-ISS (IBF_TFIP)\Module 1\codes\gitDay4\ibf2021-d4\c_s_fortunecookie - current directory
        String cookieFilePath = "cookie_file.txt";
        String clientLine = in.readLine();

        while (!clientLine.equals("close") && clientLine != null) {

            System.out.println("Client: " + clientLine);

            try {
                
                if (clientLine.equals("get-cookie")) {
                    Cookie cookie = new Cookie();
                    out.write("cookie-text " + cookie.getCookie(cookieFilePath));
                    out.newLine();
                    out.flush();
                
                } else {
                    out.write("Invalid request, please use \"get-cookie\" to request cookies");
                    out.newLine();
                    out.flush();
                }
                clientLine = in.readLine();
    
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        socket.close();
        server.close();

    }
}
