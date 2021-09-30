import java.util.*;
import java.io.*;
import java.text.DecimalFormat;

public class FileManager {

    public void read(File file){
        if(file.exists()){
            if(file.isDirectory()){
                this.directoryRead(file);
            }else{
                this.fileRead(file);
            }
        }else{
            System.out.println("This file doesn't exist");
        }
    }

    public void directoryRead(File file){
        File[] content = file.listFiles();
        List<File> contentFiles = new ArrayList<File>();
        List<File> contentDirs = new ArrayList<File>();
        if(file.exists()){
            try{
                for(int i = 0; i < content.length; i++){
                    if(content[i].isDirectory()){
                        contentDirs.add(content[i]);
                    }else{
                        contentFiles.add(content[i]);
                    }
                }
    
                for(int i = 0; i < contentDirs.size(); i++){
                    System.out.println("D    " + contentDirs.get(i).getName());
                }
        
                for(int i = 0; i < contentFiles.size(); i++){
                    String format = "B";
                    double size = contentFiles.get(i).length();
                    DecimalFormat dFormat = new DecimalFormat();
                    if(size > 999 && size < 1000000){
                        format = "KB";
                        size = contentFiles.get(i).length() / 1000f;
                    }else if(size > 999999 && size < 1000000000){
                        format = "MB";
                        size = contentFiles.get(i).length() / 1000000f;
                    }else if(size > 999999999){
                        format = "GB";
                        size = contentFiles.get(i).length() / 1000000000f; 
                    }
                    dFormat.setMaximumFractionDigits(2);
                    System.out.println("F    " + dFormat.format(size) + " " + format + "    " + checkPermissions(contentFiles.get(i)) + "    " + contentFiles.get(i).getName());
                }
            }catch(Exception e){
                System.out.println("This folder has no content");
            }
        }else{
            System.out.println("This file doesn't exist");
        }
        
    }

    public boolean createFile(File file){
        boolean success = false;
        if(!file.exists()){
            try {
                file.createNewFile();
                success = true;
            } catch (IOException e) {
                //Cannot create file
            }
        }

        return success;
    }

    public boolean removeFile(File file){
        boolean success = false;
        if(file.exists()){
            file.delete();
            success = true;
        }
        return success;
    }

    public void copyFile(File origin, File dest){
        if(!origin.isDirectory() && !dest.isDirectory()){
            try {
                BufferedReader fileReader = new BufferedReader(new FileReader(origin));
                FileWriter fileWriter = new FileWriter(dest);
                String line = fileReader.readLine();
                while(line != null){
                    fileWriter.write(line + "\n");
                    line = fileReader.readLine();
                }
                fileWriter.close();
                fileReader.close();
            } catch (FileNotFoundException e) {
                System.out.println("File does not exist");
            } catch (IOException e) {
                //NOTHING TO DO
            }
        }
    }

    public void copyFileUnbuffered(File origin, File dest){
        if(!origin.isDirectory() && !dest.isDirectory()){
            try{
                FileReader fileReader = new FileReader(origin);
                FileWriter fileWriter = new FileWriter(dest);
                int asciiChar = fileReader.read();
                while(asciiChar != -1){
                    fileWriter.write(asciiChar);
                    asciiChar = fileReader.read();
                }
                fileWriter.close();
                fileReader.close();
            }catch(FileNotFoundException e){
                System.out.println("This file does not exist");
            }catch(IOException e){
                //NOTHING TO DO
            }
        }
    }

    public void encryptFile(File file, File dest, int key){ //WIP
        try{
            FileReader fileReader = new FileReader(file);
            FileWriter fileWriter = new FileWriter(dest);
            int asciiChar = fileReader.read();
            while(asciiChar != -1){
                asciiChar += key;
                fileWriter.write(asciiChar);
            }
            fileReader.close();
            fileWriter.close();
        }catch(Exception e){
        }
    }

    public void decryptFile(File file, File dest, int key){ //WIP
        try{
            FileReader fileReader = new FileReader(file);
            FileWriter fileWriter = new FileWriter(dest);
            int asciiChar = fileReader.read();
            while(asciiChar != -1){
                asciiChar -= key;
                fileWriter.write(asciiChar);
            }
            fileReader.close();
            fileWriter.close();
        }catch(Exception e){
            //NADA
        }
    }


    public void fileRead(File file){
        int lineCounter = 1;
        if(file.canRead()){
            try{
                BufferedReader bReader = new BufferedReader(new FileReader(file));
                String line = bReader.readLine();
                System.out.println(file.getName());
            System.out.println("---------------------------------------------------------");
            while(line != null){
                System.out.println(lineCounter + "  " + line);
                lineCounter++;
                line = bReader.readLine();
            }
            bReader.close();
            }catch(IOException ioException){
                ioException.printStackTrace();
                System.out.println("Can't read from file");
            }
        }else{
            String permissions = checkPermissions(file);
            System.out.println("You don't have permission to read this file.");
            if(permissions.equals("!")){
                permissions = "none";
            }
            System.out.println("Your permissions are: " + checkPermissions(file));
        }
        
        
    }

    private String checkPermissions(File file){
        String p = "";
        if(file.canRead()){
            p += "r";
        }
        if(file.canWrite()){
            p += "w";
        }
        if(file.canExecute()){
            p += "x";
        }
        if(p.equals("")){
            p += "!";
        }
        return p;
    }
}
