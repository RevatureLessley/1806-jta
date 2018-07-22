package garbage;

import java.io.IOException;
import java.io.StringReader;



public class TestMain {
    public static void main(String[] args){
        String s = "Hello World";
        StringReader reader = new StringReader(s);
        try{
            int b = 0;
            while (( b = reader.read()) != -1){
                System.out.println((char)b);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
