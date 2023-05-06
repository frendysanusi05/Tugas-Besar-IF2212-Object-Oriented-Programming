package utilz;

import java.util.InputMismatchException;
import java.util.Scanner;

import exceptions.WrongInputsException;

public class Keyboard {
    private Scanner scan = new Scanner(System.in);
    private static Keyboard instance = new Keyboard();

    public static Keyboard getInstance() {
        return instance;
    }

    public int nextInt() {
        int input = -999;
        try {
            input = scan.nextInt();
        } catch (InputMismatchException e) {
            System.out.print("\u001B[0m");
            System.out.println("\nMasukan harus bernilai integer!\n");
        }
        scan.nextLine();
        return input;
    }

    public double nextDouble() {
        double input = -999d;
        try {
            input = scan.nextDouble();
        } catch (InputMismatchException e) {
            System.out.print("\u001B[0m");
            System.out.println("\nMasukan harus bernilai double!\n");
        }
        scan.nextLine();
        return input;
    }

    public String nextLine() {
        return scan.nextLine();
    }

    public String next() {
        return scan.next();
    }

    public String getKeyboard(String message) throws WrongInputsException {
        String input = nextLine();
        if (input.equals("")) {
            throw new WrongInputsException(message);
        }
        else return input;
    }
}
