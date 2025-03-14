package module.io.output;

import module.cart.domain.model.Cart;
import module.kiosk.type.Discount;
import module.menu.domain.model.MenuItem;
import module.menu.type.Category;

import java.util.List;
import java.util.Map;

public class Output {
    public void printCategory() {
        System.out.println("[ MENU ]");
        for (Category category : Category.values()) {
            System.out.println(category.getValue() + ". " + category.name());
        }
        System.out.println("0. 종료");
    }

    public void printCheckOrderMenu() {
        System.out.println("[ Order Menu ]");
        System.out.println("4. Orders");
        System.out.println("5. Cancel");
    }

    public int printConfirmOrderMenu(Cart cart) {
        System.out.println("아래와 같이 주문하시겠습니까?");
        System.out.println("[ Orders ]");

        int sum = 0;
        for (Map.Entry<MenuItem, Integer> entry : cart.getCartItems().entrySet()) {
            System.out.println(entry.getKey().getName() + "     | W " + entry.getKey().getPrice()
                    + "       | " + entry.getKey().getDescription() + "   | " + entry.getValue() + "개");
            sum += entry.getKey().getPrice() * entry.getValue();
        }

        System.out.println("[ Total Price ]");
        System.out.println("W " + sum);

        System.out.println("1. 주문   2. 메뉴판  3. 부분취소");

        return sum;
    }

    public void printDiscountOrder() {
        System.out.println("할인 정보를 입력해주세요.");
        for (Discount discount : Discount.values()) {
            System.out.println(discount.getValue() + ". " + discount.getName() + " : "
                    + discount.getDiscountPercent() + "%");
        }
    }

    public void printMessage(String message) {
        System.out.println(message);
    }

    public void printMenuItemList(Category category, List<MenuItem> menuItemList) {
        System.out.println("[ " + category.name() + " MENU ]");
        for (MenuItem menuItem : menuItemList) {
            System.out.println(menuItem.getId() + ". " + menuItem.getName() + "     | W " + menuItem.getPrice()
                    + "       | " + menuItem.getDescription());
        }
        System.out.println("0. 뒤로가기");
    }

    public void printRemoveMenuItemInCart() {
        System.out.println("삭제할 메뉴명을 입력해 주세요.");
    }

}
