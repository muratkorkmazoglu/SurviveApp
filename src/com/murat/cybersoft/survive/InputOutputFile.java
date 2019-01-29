package com.murat.cybersoft.survive;
import java.io.*;

public class InputOutputFile {

    public String readInput(String fileName) {

        StringBuilder buf = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {

            String currentLine;
            while ((currentLine = br.readLine()) != null) {
                buf.append(currentLine);
                System.out.println(currentLine);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return buf.toString();
    }

    public void writeOutput(String message,String filename) {

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            bw.write(message);
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }
}
