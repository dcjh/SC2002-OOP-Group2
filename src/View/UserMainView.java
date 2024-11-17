package View;

/**
 * Interface representing a general user view with a menu.
 */
public interface UserMainView {

    /**
     * Displays the menu options available to a user.
     * 
     * @param userID The ID of the user for which the menu will be displayed.
     */
    void menu(String userID);
}
