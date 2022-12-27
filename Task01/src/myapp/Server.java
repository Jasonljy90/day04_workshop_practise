
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) throws Exception {
        //java -cp fortunecookie.jar fc.Server 12345 cookie_file.txt
        Integer port = Integer.parseInt(args[0]);
        String fileName = args[1];
        ArrayList<String> messages = new ArrayList<>();
        
        File myObj = new File(fileName);
        Scanner myReader = new Scanner(myObj);
        int countMessages = 0;
        
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            messages.add(data);
            countMessages++;
        }
        myReader.close();
        
        ServerSocket server = new ServerSocket(port);
        Socket socket = server.accept();
        Random rnd = new Random();
        String payload = messages.get(rnd.nextInt(countMessages)); 
        
        OutputStream os = socket.getOutputStream();
        BufferedOutputStream bos = new BufferedOutputStream(os);
        DataOutputStream dos = new DataOutputStream(bos);
        dos.writeUTF(payload);
        dos.flush();
        
        os.close();
        server.close();
    }
}
