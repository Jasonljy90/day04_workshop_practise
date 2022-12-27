
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.InputStream;
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
        String command = "";

        // get command from client
        InputStream is = socket.getInputStream();
        BufferedInputStream bis = new BufferedInputStream(is);
        DataInputStream dis = new DataInputStream(bis);

        OutputStream os = socket.getOutputStream();
        BufferedOutputStream bos = new BufferedOutputStream(os);
        DataOutputStream dos = new DataOutputStream(bos);

        while (!(command = dis.readUTF()).toLowerCase().equals("close")) {
            String payload = messages.get(rnd.nextInt(countMessages));

            if (command.toLowerCase().equals("get-cookie")) {
                    os = socket.getOutputStream();
                    bos = new BufferedOutputStream(os);
                    dos = new DataOutputStream(bos);
                    dos.writeUTF("cookie-text " + payload);
                    dos.flush();
            }else{   
                dos.writeUTF("Invalid command: " + command);
                dos.flush();
            }
        }
        dos.writeUTF("close Good Bye, Have a great Day!");
        dos.flush();
        is.close();
        os.close();
        server.close();
    }
}
