package utilz;

import java.util.Scanner;

import exceptions.WrongInputsException;

public class Keyboard {
    private static Scanner scan = new Scanner(System.in);

    public static Scanner getInstance() {
        return scan;
    }

    public int nextInt() {
        return scan.nextInt();
    }

    public String nextLine() {
        return scan.nextLine();
    }

    public String getKeyboard(String message) throws WrongInputsException {
        String input = nextLine();
        if (input.equals("")) {
            throw new WrongInputsException(message);
        }
        else return input;
    }
}
