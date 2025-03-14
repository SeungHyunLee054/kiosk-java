package module.cart.service;

import module.cart.domain.model.Cart;
import module.cart.exception.CartException;
import module.cart.type.CartExceptionCode;
import module.io.input.Input;
import module.menu.domain.model.MenuItem;


public class CartService {
    private final Input input;
    private final Cart cart;

    public CartService(Input input, Cart cart) {
        this.input = input;
        this.cart = cart;
    }

    private static final int ONE = 1;
    private static final int TWO = 2;

    /**
     * 장바구니에 메뉴를 추가하는 메서드
     *
     * @param menuItem 내가 고른 메뉴
     */
    public void addCart(MenuItem menuItem) {
        System.out.println(menuItem.getName() + "     | W "
                + menuItem.getPrice() + "       | " + menuItem.getDescription());
        System.out.println("위 메뉴를 장바구니에 추가하시겠습니까?");
        System.out.println("1. 확인     2. 취소");

        int input = this.input.inputInt();

        if (input == ONE) {
            cart.addMenuToCart(menuItem);
            System.out.println(menuItem.getName() + " 이 장바구니에 추가되었습니다.");
        } else if (input == TWO) {
            throw new CartException(CartExceptionCode.CANCEL_ADD_CART);
        } else {
            throw new CartException(CartExceptionCode.INPUT_WRONG);
        }
    }

    /**
     * 장바구니를 가져오는 메서드
     *
     * @return Cart
     */
    public Cart getCart() {
        return cart;
    }

    /**
     * 장바구니를 삭제하는 메서드
     */
    public void removeCart() {
        cart.removeCart();
    }

    /**
     * 장바구니에서 입력받은 메뉴명을 조회해 삭제하는 메서드
     * @param name 메뉴명
     */
    public void removeMenuItemInCart(String name) {
        MenuItem menuItem = cart.getCartItems().entrySet()
                .stream()
                .filter(entry -> entry.getKey().getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new CartException(CartExceptionCode.NOT_EXIST_MENU))
                .getKey();

        cart.getCartItems().remove(menuItem);
        System.out.println(menuItem.getName() + " 을(를) 취소하였습니다.");
    }

}