package view;

import controller.GameController;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import model.Civilization;

public class ResourcesMenu {
    private HBox hbox;
    /**
    * creates a resource bar and display the current state of
    * your civilization's resouces
    */
    public ResourcesMenu() {
        hbox = new HBox(15);
        update();
    }
    /**
    * should update all the resouce values to the current
    * state of your resource values
    */
    public void update() {
        hbox.getChildren().clear();
        Civilization civ = GameController.getCivilization();
        hbox.getChildren().add(new Label("Strat Level: "
            + civ.getStrategy().getStrategyLevel()));
        hbox.getChildren().add(new Label("Resources: "
            + civ.getResources()));
        hbox.getChildren().add(new Label("Settlements: "
            + civ.getNumSettlements()));
        hbox.getChildren().add(new Label("Money: "
            + civ.getTreasury().getCoins()));
        hbox.getChildren().add(new Label("Food: "
            + civ.getFood()));
        hbox.getChildren().add(new Label("Happiness: "
            + civ.getHappiness()));
    }
    /**
    * updates the resource bar and returns the resource bar
    * @return a hbox representation of the resource bar
    */
    public HBox getRootNode() {
        //TODO
        update();
        return hbox;
    }
}