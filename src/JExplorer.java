import java.io.*;

public class JExplorer{
    public static void main(String[] args){
        Keyboard kboard = new Keyboard();
        String fileURI = "/home/" + System.getProperty("user.name");
        FileManager fileManager = new FileManager();
        boolean execution = true;
        File file = new File(fileURI);
        //if(fileURI.equals("!")) execution = false;
        String readedString = "";
        String[] URICompose;
        while(execution){
            if(readedString.equals("..")){
                if(!fileURI.equals("/")){
                    URICompose = fileURI.split("/");
                    fileURI = "";
                    for(int i = 0; i < URICompose.length - 1; i++){
                        fileURI += URICompose[i] + "/";
                    }
                }
            }else if(readedString.equals("+")){
                //fileURI no cambia
            }else{
                fileURI += readedString + "/";
            }
            if(!readedString.equals("!")){
                file = new File(fileURI);
                fileManager.read(file);
                if(!file.isDirectory()){
                    URICompose = fileURI.split("/");
                    fileURI = "";
                    for(int i = 0; i < URICompose.length - 1; i++){
                        fileURI += URICompose[i] + "/";
                    }
                }
                file = new File(fileURI);
                System.out.println("Current folder is " + fileURI);
                System.out.println("Type '..' to go back to parent folder");
                System.out.println("Type '+' to list current folder files");
                System.out.println("Type '!' to exit");
                readedString = kboard.readKeyboard();
            }else{
                execution = false;
            }


        }
        
    }







}