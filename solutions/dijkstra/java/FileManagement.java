import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManagement {

    /**
     * Saves a text file in the folder output inside the source folder of this project
     * @param data the text to be writted in the file
     * @param fileName the path (including the name) for the file to be created
     */
    public static void saveFile(String data, String fileName){
        File savedFile = new File(fileName);
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(savedFile));
            PrintWriter printer = new PrintWriter(bw);
            printer.print(data);
            printer.close();
            bw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Read the content on a text file
     * @param root the path for start looking for the file, if "" starts on the users default root folder
     * @return a string list with the lines of the file
     */
    public static List<String> readFile(String root){
        File file = selectFile(root);
        ArrayList<String> lines = new ArrayList<>();
        try{
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while((line=reader.readLine()) != null){
                lines.add(line);
            }
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
        return lines;
    }

    /**
     * Let the user pick a file from his file system
     * @param root the path for the FileChooser to start, if "" starts on the users default root folder
     * @return the file selected by the user
     */
    private static File selectFile(String root){
        JFileChooser chooser = new JFileChooser(root);
        int returnVal = chooser.showOpenDialog(new Panel());
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            return chooser.getSelectedFile();
        }
        return null;
    }
}
