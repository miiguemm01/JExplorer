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

    public int readInt(){
        BufferedReader kboard = new BufferedReader(new InputStreamReader(System.in));
        boolean works = false;
        int value;
        do{
            try{
                System.out.print("> ");
                value = Integer.parseInt(kboard.readLine());
                works = true;
            }catch(Exception e){
                works = false;
                value = 0;
            }
        }while(!works);

        return value;
    }
}
