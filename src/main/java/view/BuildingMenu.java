package view;

import controller.GameController;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import model.Building;
import model.MapObject;
import model.Settlement;
import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * This class should represents the bulding menu
 */
public class BuildingMenu extends AbstractMenu {
    /**
     * there should be an invest and demolish button for this menu as well as
     * functions associated with the buttons
     */
    private Button investButton = new Button("Invest");
    private Button demolishButton = new Button("Demolish");
    public BuildingMenu() {
        // TODO
        super();
        addMenuItem(investButton);
        addMenuItem(demolishButton);
        investButton.setOnMousePressed(e -> {
                MapObject occupant = GameController
                    .getLastClicked().getTile().getOccupant();
                if (GameController.getCivilization()
                    .getTreasury().getCoins() >= 25) {
                    GameController.getCivilization().getTreasury().spend(25);
                    ((Building) occupant).invest();
                    GameController.updateResourcesBar();
                    GameController.getLastClicked().updateTileView();
                    String pathI = "src/main/java/view/sounds/invest.mp3";
                    Media songI = new Media(new File(pathI).toURI().toString());
                    MediaPlayer playerI = new MediaPlayer(songI);
                    playerI.play();
                } else {
                    Alert newAlert = new Alert(Alert.AlertType.ERROR);
                    newAlert.setHeaderText("You don't have enough gold.");
                    newAlert.setTitle(
                        "Civilization Cannot Invest in the Occupant!");
                    String path1 = "src/main/java/view/sounds/beep.mp3";
                    Media song1 = new Media(new File(path1).toURI().toString());
                    MediaPlayer player1 = new MediaPlayer(song1);
                    player1.play();
                    newAlert.showAndWait();
                    // System.exit(0);
                }
            });
        demolishButton.setOnMousePressed(e -> {
                if (GameController.getCivilization().getNumSettlements() > 1) {
                    if (GameController.getLastClicked()
                        .getTile().getOccupant() instanceof Settlement) {
                        ((Settlement) GameController.getLastClicked()
                            .getTile().getOccupant()).demolish();
                        GameController.getLastClicked()
                        .getTile().setOccupant(null);
                        GameController.updateResourcesBar();
                        GameController.getLastClicked().updateTileView();
                        String pathD = "src/main/java/view/sounds/demolish.mp3";
                        Media demolishSong = new Media(new File(pathD)
                            .toURI().toString());
                        MediaPlayer playerD = new MediaPlayer(demolishSong);
                        playerD.play();
                    }
                } else {
                    Alert newAlert = new Alert(Alert.AlertType.ERROR);
                    newAlert.setHeaderText(
                        "You don't have more than one Settlement");
                    newAlert.setTitle("Occupant Cannot Be Demolished!");
                    String path = "src/main/java/view/sounds/beep.mp3";
                    Media song = new Media(new File(path).toURI().toString());
                    MediaPlayer player = new MediaPlayer(song);
                    player.play();
                    newAlert.showAndWait();
                    // System.exit(0);
                }
            });
    }
}