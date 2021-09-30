import java.io.*;

public class JExplorer{
    public static void main(String[] args){
        Keyboard kboard = new Keyboard();
        String fileURI = "/home/" + System.getProperty("user.name");
        FileManager fileManager = new FileManager();
        boolean execution = true;
        File file = new File(fileURI);
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
            }else if(readedString.equals("+f")){
                System.out.println("Write the name of the file to create");
                String newFileName = kboard.readKeyboard();
                newFileName = fileURI + newFileName;
                File newFile = new File(newFileName);
                if(fileManager.createFile(newFile)){
                    System.out.println("Success creating the file");
                }else{
                    System.out.println("Cannot create the file");
                }
            }else if(readedString.equals(("-f"))){
                System.out.println("Write the name of the file to delete");
                String remFileName = kboard.readKeyboard();
                remFileName = fileURI + remFileName;
                File remFile = new File(remFileName);
                if(fileManager.removeFile(remFile)){
                    System.out.println("Success deleting the file");
                }else{
                    System.out.println("Cannot delete the file");
                }
            }else if(readedString.equals("cf")){
                System.out.println("Write the origin file");
                String origin = kboard.readKeyboard();
                origin = fileURI + origin;
                File originFile = new File(origin);
                System.out.println("Write the destination file");
                String dest = kboard.readKeyboard();
                dest = fileURI + dest;
                File destFile = new File(dest);
                fileManager.copyFileUnbuffered(originFile, destFile);
            }else if(readedString.equals("*e")){
                System.out.println("Write the key");
                int key = kboard.readInt();
                System.out.println("Write the file to encrypt");
                String fileString = kboard.readKeyboard();
                System.out.println("Write the destination file");
                String destString = kboard.readKeyboard();
                File encFile = new File(fileURI + fileString);
                File dest = new File(fileURI + destString);
                fileManager.encryptFile(encFile, dest, key);
            }else if(readedString.equals("*d")){
                System.out.println("Write the key");
                int key = kboard.readInt();
                System.out.println("Write the file to decrypt");
                String fileString = kboard.readKeyboard();
                System.out.println("Write the destination file");
                String fileDest = kboard.readKeyboard();
                File encFile = new File(fileURI + fileString);
                File dest = new File(fileURI + fileDest);
                fileManager.decryptFile(encFile, dest, key);
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
                System.out.println("Type '+f' to create a file");
                System.out.println("Type '-f' to delete a file");
                System.out.println("Type 'cf' to copy file content to another file");
                System.out.println("Type '*e' to encrypt a file (WIP)");
                System.out.println("Type '*d' to decrypt a file (WIP)");
                System.out.println("Type '!' to exit");
                readedString = kboard.readKeyboard();
            }else{
                execution = false;
            }


        }
        
    }







}