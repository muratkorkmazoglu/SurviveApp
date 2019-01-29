package com.murat.cybersoft.survive;
import com.murat.cybersoft.survive.Models.Hero;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        InputParse parser = new InputParse();
        InputOutputFile operation = new InputOutputFile();
        Writer writer = new Writer();

        System.out.println("Lütfen Proje Klasörü İçindeki Okunacak Dosya İsmini Giriniz");
        Scanner user = new Scanner(System.in);
        String fileName = user.nextLine();

        String inputStr = operation.readInput(fileName);
        Hero hero = parser.getHero(inputStr);
        hero.setWriter(writer);
        hero.start(parser.getRoute(inputStr));
    }
}
