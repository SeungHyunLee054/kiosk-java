package module.io.output;

import module.cart.domain.model.Cart;
import module.io.input.type.Discount;
import module.menu.type.Category;

import java.util.List;

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

    public int printConfirmOrderMenu(List<Cart> cartList) {
        System.out.println("아래와 같이 주문하시겠습니까?");
        System.out.println("[ Orders ]");

        int sum = 0;
        for (Cart cart : cartList) {
            System.out.println(cart.getMenu().getName() + "     | W " + cart.getMenu().getPrice()
                    + "       | " + cart.getMenu().getDescription());
            sum += cart.getMenu().getPrice() * cart.getQuantity();
        }

        System.out.println("[ Total Price ]");
        System.out.println("W " + sum);

        System.out.println("1. 주문   2. 메뉴판");

        return sum;
    }

    public void printDiscountOrder() {
        System.out.println("할인 정보를 입력해주세요.");
        for (Discount discount : Discount.values()) {
            System.out.println(discount.getValue() + ". " + discount.getName() + " : "
                    + discount.getDiscountPercent() + "%");
        }
    }

}
