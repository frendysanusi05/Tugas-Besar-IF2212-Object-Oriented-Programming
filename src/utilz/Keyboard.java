package utilz;

import java.util.Scanner;

import exceptions.WrongInputsException;

public class Keyboard {
    private static final Scanner scan = new Scanner(System.in);

    public static int getScan() throws WrongInputsException {
        int input = 0;
        try {
            input = scan.nextInt();
        }
        catch (WrongInputsException e) {
            System.out.print("\u001B[0m");
            System.out.println("Masukan harus bernilai integer!\n");
        }
        return input;
    }
}
