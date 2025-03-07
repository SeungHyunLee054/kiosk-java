import exception.CustomException;
import io.Input;
import io.Output;
import menu.Menu;
import menu.MenuItem;
import type.Category;

public class Main {
    public static void main(String[] args) {

        Menu menu = new Menu();
        Input input = new Input();
        Output output = new Output();
        menu.inputTestData();

        do {
//            MenuItem menuItem;
//            try {
//                menuItem = input.inputMenu();
//            } catch (IllegalArgumentException e) {
//                e.getMessage();
//                break;
//            }

//            menu.addMenuItem(menuItem);

            Category category;
            try {
                output.printCategory();
                category = input.selectCategory();
                output.printMenu(menu.getMenuList(category));
            } catch (CustomException e) {
                System.out.println(e.getMessage());
                break;
            }catch (NumberFormatException e){
                System.out.println(e.getMessage());
                continue;
            }

            try {
                int id = input.selectMenu();
                MenuItem selectedMenuItem = menu.getMenuItem(id, category);
                output.printMenuItem(selectedMenuItem);
            } catch (CustomException e) {
                System.out.println(e.getMessage());
                continue;
            }

        } while (true);

        input.closeScanner();
    }
}
