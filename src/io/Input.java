package io;

import exception.CustomException;
import menu.MenuItem;
import type.Category;

import java.util.Scanner;

import static type.CustomExceptionCode.INPUT_ZERO_BACK;
import static type.CustomExceptionCode.INPUT_ZERO_EXIT;

public class Input {
    private final Scanner scanner = new Scanner(System.in);
    private final int ZERO = 0;

    public MenuItem inputMenu() {
        System.out.print("id를 입력해 주세요 : ");
        String strId = scanner.nextLine();
        if (isZero(strId)) {
            throw new CustomException(INPUT_ZERO_EXIT);
        }
        long id = Long.parseLong(strId);

        System.out.print("종류를 입력해 주세요 : ");
        String strCategory = scanner.nextLine();
        if (isZero(strCategory)) {
            throw new CustomException(INPUT_ZERO_EXIT);
        }
        Category category = Category.fromCategoryVal(Integer.parseInt(strCategory));

        System.out.print("메뉴 명을 입력해 주세요 : ");
        String name = scanner.nextLine();
        if (isZero(name)) {
            throw new CustomException(INPUT_ZERO_EXIT);
        }

        System.out.print("가격을 입력해 주세요(원) : ");
        String strPrice = scanner.nextLine();
        if (isZero(strPrice)) {
            throw new CustomException(INPUT_ZERO_EXIT);
        }
        int price = Integer.parseInt(strPrice);

        System.out.print("설명을 입력해주세요 : ");
        String description = scanner.nextLine();
        if (isZero(description)) {
            throw new CustomException(INPUT_ZERO_EXIT);
        }

        return new MenuItem(id, category, name, price, description);
    }

    public Category selectCategory() {
        System.out.print("종류를 선택해 주세요 : ");
        String strCategory = scanner.nextLine();
        if (isZero(strCategory)) {
            throw new CustomException(INPUT_ZERO_EXIT);
        }

        return Category.fromCategoryVal(Integer.parseInt(strCategory));
    }

    public int selectMenu() {
        System.out.print("메뉴를 선택해 주세요 : ");
        String strMenu = scanner.nextLine();
        if (isZero(strMenu)) {
            throw new CustomException(INPUT_ZERO_BACK);
        }

        return Integer.parseInt(strMenu);
    }

    public void closeScanner() {
        scanner.close();
    }


    private boolean isZero(String str) {
        int intStr;
        try {
            intStr = Integer.parseInt(str);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("잘못된 값을 입력하였습니다.");
        }

        return ZERO == intStr;
    }
}
