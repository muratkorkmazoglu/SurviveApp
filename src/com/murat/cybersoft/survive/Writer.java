package com.murat.cybersoft.survive;

import java.util.Scanner;

public class Writer {
    private InputOutputFile fileOperation = new InputOutputFile();

    public void writeMessage(String message) {
        System.out.println("Lütfen Proje Klasörü İçindeki Yazdırılacak Dosya İsmini Giriniz");
        Scanner user = new Scanner(System.in);
        String fileName = user.nextLine();
        fileOperation.writeOutput(message,fileName);
        fileOperation.readInput(fileName);
    }
}
