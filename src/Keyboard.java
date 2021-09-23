import java.io.*;

public class Keyboard {
    public String readKeyboard(){
        BufferedReader kboard = new BufferedReader(new InputStreamReader(System.in));
        String value = "";
        do{
            try{
                System.out.print("> ");
                value = kboard.readLine();
            }catch(Exception e){
                value = "";
            }
        }while(value.length() < 1);
        return value;
    }
}
