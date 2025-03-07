package module.io.input;

import module.io.input.exception.InputException;

import java.util.Scanner;

import static module.io.input.type.InputExceptionCode.INPUT_WRONG;

public class Input {
    private final Scanner scanner = new Scanner(System.in);

    public int inputInt() {
        int input;
        try {
            input = scanner.nextInt();
        } catch (NumberFormatException e) {
            throw new InputException(INPUT_WRONG);
        }
        return input;
    }

    public void closeScanner() {
        scanner.close();
    }
}
