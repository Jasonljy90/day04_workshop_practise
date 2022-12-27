import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    public static void main(String[] args) throws Exception {
        //java -cp fortunecookie.jar fc.Server 12345 cookie_file.txt
        ArrayList<String> messages = new ArrayList<>();
        Integer port = Integer.parseInt(args[0]);
        String fileName = args[1];
        
        ServerSocket server = new ServerSocket(port);
        Socket socket = server.accept();
        
        // Create an instance of cookie class
        Cookie cookie = new Cookie();

        // Read file and store in arraylist of String
        messages = cookie.readFile(fileName);

        String payload = cookie.getMessage(messages);
        OutputStream os = socket.getOutputStream();
        BufferedOutputStream bos = new BufferedOutputStream(os);
        DataOutputStream dos = new DataOutputStream(bos);
        dos.writeUTF(payload);
        dos.flush();
        
        os.close();
        server.close();
    }
}
