package utilz;

import java.util.Scanner;

import exceptions.WrongInputsException;

public class Keyboard {
    private Scanner scan = new Scanner(System.in);
    private static Keyboard instance = new Keyboard();

    public static Keyboard getInstance() {
        return instance;
    }

    private int nextInt() {
        String input;
        int num = -999;
        try {
            input = scan.nextLine();
            num = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new WrongInputsException("Masukan harus bernilai integer!\n");
        }
        return num;
    }

    private double nextDouble() {
        String input;
        double num = -999d;
        try {
            input = scan.nextLine();
            num = Double.parseDouble(input);
        } catch (NumberFormatException e) {
            throw new WrongInputsException("Masukan harus bernilai double!\n");
        }
        return num;
    }

    public String nextLine() {
        return scan.nextLine();
    }

    public String next() {
        return scan.next();
    }

    public int getIntKeyboard() throws WrongInputsException {
        int input = -999;
        try {
            input = nextInt();
        } catch (WrongInputsException e) {
            System.out.println("\u001B[0m");
            System.out.println(e.getMessage());
        }
        return input;
    }

    public double getDoubleKeyboard() throws WrongInputsException {
        double input = -999;
        try {
            input = nextDouble();
        } catch (WrongInputsException e) {
            System.out.println("\u001B[0m");
            System.out.println(e.getMessage());
        }
        return input;
    }
}
