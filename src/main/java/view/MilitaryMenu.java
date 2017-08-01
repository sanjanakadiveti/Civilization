package view;

import controller.GameController;
import javafx.scene.control.Button;

/**
 * Created by William on 11/11/2016.
 */

public class MilitaryMenu extends AbstractMenu {
    /**
    * Implement the buttons and actions associated with
    * the buttons for the military menu
    */
    private Button attackButton;
    private Button moveButton;
    public MilitaryMenu() {
        //TODO
        attackButton = new Button("Attack");
        moveButton = new Button("Move");
        attackButton.setOnMousePressed(e -> {
                GameController.attacking();
                GameController.updateResourcesBar();
            });
        moveButton.setOnMousePressed(e -> {
                GameController.moving();
                GameController.updateResourcesBar();
            });
        addMenuItem(attackButton);
        addMenuItem(moveButton);
    }
}