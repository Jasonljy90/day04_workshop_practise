import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Cookie {
    
    public Cookie() {
    }

    public ArrayList<String> readFile(String fileName) throws Exception {
        
        ArrayList<String> messages = new ArrayList<>();
        
        File myObj = new File(fileName);
        Scanner myReader = new Scanner(myObj);
        Integer countMessages = 0;
        
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            messages.add(data);
            countMessages++;
        }
        myReader.close();
        messages.add(Integer.toString(countMessages));
        return messages;
    }

    public String getMessage(ArrayList<String> messages) {
        String countMessages = messages.get(messages.size() - 1);
        Integer count = Integer.parseInt(countMessages);
        Random rnd = new Random();
        String payload = messages.get(rnd.nextInt(count));
        return payload; 
    }
}
