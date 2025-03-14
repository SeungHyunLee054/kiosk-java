package module.io.input;

import module.io.input.exception.InputException;
import module.io.input.type.InputExceptionCode;

import java.util.InputMismatchException;
import java.util.Scanner;


public class Input implements AutoCloseable {
    private static final Input instance = new Input();
    private final Scanner scanner;

    private Input() {
        scanner = new Scanner(System.in);
    }

    public static Input getInstance() {
        return instance;
    }

    public int inputInt() {
        try {
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            scanner.nextLine();
            throw new InputException(InputExceptionCode.INPUT_WRONG);
        }
    }

    public String inputString() {
        return scanner.next();
    }

    @Override
    public void close() {
        scanner.close();
    }
}
