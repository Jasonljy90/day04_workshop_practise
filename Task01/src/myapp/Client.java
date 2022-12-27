import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.InputStream;
import java.net.Socket;

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

        InputStream is = socket.getInputStream();
        BufferedInputStream bis = new BufferedInputStream(is);
        DataInputStream dis = new DataInputStream(bis);
        String message = dis.readUTF();
        System.out.println("Fortune cookie message: " + message);
        is.close();
        socket.close();
    }
}
