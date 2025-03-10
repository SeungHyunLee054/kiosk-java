package module.io.input;

import module.io.input.exception.InputException;
import module.io.input.type.InputExceptionCode;

import java.util.Scanner;


public class Input {
    private final Scanner scanner = new Scanner(System.in);

    public int inputInt() {
        int input;
        try {
            input = scanner.nextInt();
        } catch (NumberFormatException e) {
            throw new InputException(InputExceptionCode.INPUT_WRONG);
        }
        return input;
    }

    public void closeScanner() {
        scanner.close();
    }
}
