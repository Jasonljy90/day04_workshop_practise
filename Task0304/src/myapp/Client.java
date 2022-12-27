import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws Exception {

        // Get the host
        String input = args[0];
        String[] hostPort = input.split(":");
        String host = hostPort[0];
        Integer port = Integer.parseInt(hostPort[1]);
        
        // Create the socket to the server
        Socket socket = new Socket(host, port);

        System.out.printf("Connected to %s:%d\n", host, port);

        OutputStream os = socket.getOutputStream();
        BufferedOutputStream bos = new BufferedOutputStream(os);
        DataOutputStream dos = new DataOutputStream(bos);
        
        InputStream is = socket.getInputStream();
        BufferedInputStream bis = new BufferedInputStream(is);
        DataInputStream dis = new DataInputStream(bis);

        Scanner scan = new Scanner(System.in);

        while(true) {
            System.out.println("Please enter command: ");
            String command = scan.nextLine();
            dos.writeUTF(command);
            dos.flush();
            String message = dis.readUTF();

            if (message.contains("close")) {
                message = message.replace("close", "");
                System.out.println(message);
                break;
            }else if (message.contains("cookie-text")) {
                message = message.replace("cookie-text", "");
                System.out.println("Fortune cookie message: " + message);
            } else{
                System.out.println(message);
            }
        }
        scan.close();
        socket.close();
    }
}
