package view;

import controller.GameController;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import model.Convertable;
import model.MapObject;
import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
//import model.TerrainTile;

/**
 * Created by RuYiMarone on 11/11/2016.
 */
public class WorkerMenu extends AbstractMenu {
    /**
    * There should be a convert and move button
    * as well as the functions associated with those
    * buttons
    */
    private Button convertButton;
    private Button moveButton;
    public WorkerMenu() {
        //TODO
        convertButton = new Button("Convert");
        moveButton = new Button("Move");
        convertButton.setOnMousePressed(e -> {
                if (((Convertable) GameController
                    .getLastClicked().getTile()
                    .getOccupant()).canConvert(GameController
                    .getLastClicked().getTile().getType())) {
                    MapObject mapO = ((Convertable) GameController
                        .getLastClicked().getTile()
                        .getOccupant()).convert();
                    GameController.getLastClicked()
                        .getTile().setOccupant(mapO);
                    GameController.getLastClicked()
                        .updateTileView();
                    GameController.updateResourcesBar();
                    String path1 = "src/main/java/view/sounds/boing.mp3";
                    Media song1 = new Media(new File(path1).toURI().toString());
                    MediaPlayer player1 = new MediaPlayer(song1);
                    player1.play();
                } else {
                    Alert newAlert = new Alert(Alert.AlertType.ERROR);
                    newAlert.setHeaderText(
                        "Occupant is not Convertable!");
                    newAlert.setTitle("Cannot Convert Occupant!");
                    String path = "src/main/java/view/sounds/beep.mp3";
                    Media song = new Media(new File(path).toURI().toString());
                    MediaPlayer player = new MediaPlayer(song);
                    player.play();
                    newAlert.showAndWait();
                    //System.exit(0);
                }
            });
        moveButton.setOnMousePressed(e -> {
                GameController.moving();
                GameController.updateResourcesBar();
            });
        addMenuItem(convertButton);
        addMenuItem(moveButton);
    }
}