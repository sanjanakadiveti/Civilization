package view;

import controller.GameController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import model.TerrainTile;
import model.Unit;
import javafx.scene.control.TextInputDialog;
import java.util.Optional;
import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
/**
 * Created by RuYiMarone on 11/11/2016.
 */
public class RecruitMenu extends AbstractMenu {
    /**
    * recuit menu should have a list of worker/units
    * to choose from. There should also be a select button
    * and the function of the button should be implemented
    *here
    */
    private ObservableList<String> workerList;
    private Button selectButton;
    private String path = "src/main/java/view/sounds/newSettlement.mp3";
    private Media song = new Media(new File(path).toURI().toString());
    private MediaPlayer player = new MediaPlayer(song);
    public RecruitMenu() {
        //TODO
        workerList = FXCollections.observableArrayList(
            "Melee Unit", "Ranged Unit", "Hybrid Unit",
            "Siege Unit", "Settlers", "Farmers", "Coal Miners",
            "Anglers", "Master Builders");
        ListView<String> listViewWorkers = new ListView<String>(workerList);
        listViewWorkers.setPrefWidth(PREFWIDTH);
        listViewWorkers.setPrefHeight(240);
        addMenuItem(listViewWorkers);
        selectButton = new Button("Select");
        selectButton.setOnMousePressed(e -> {
                String selectedItem =
                    listViewWorkers.getSelectionModel()
                    .getSelectedItem();
                TerrainTile tileCurrent = GameController
                    .getLastClicked().getTile();
                Unit unit;
                switch (selectedItem) {
                case "Melee Unit": unit = GameController
                .getCivilization().getMeleeUnit();
                    player.play();
                                   break;
                case "Ranged Unit": unit = GameController
                .getCivilization().getRangedUnit();
                    player.play();
                                    break;
                case "Hybrid Unit": unit = GameController
                .getCivilization().getHybridUnit();
                    player.play();
                                    break;
                case "Siege Unit": unit = GameController
                .getCivilization().getSiegeUnit();
                    player.play();
                                   break;
                case "Settlers": TextInputDialog dialog =
                                     new TextInputDialog("New Settler Unit");
                                 dialog.setTitle(
                                     "A New Settler Unit");
                                 dialog.setHeaderText(
                                     "You have built a Settler Unit!");
                                 dialog.setContentText(
                                     "Enter the name of your"
                                     + " new settler unit:");
                    Optional<String> result = dialog.showAndWait();
                    if (result.isPresent()) {
                        String settlement = result.get();
                        unit = GameController
                                    .getCivilization()
                                    .getSettlerUnit(settlement);
                        player.play();
                    } else {
                        unit = GameController
                                    .getCivilization()
                                    .getSettlerUnit("Settlement");
                    }
                                 break;
                case "Farmers": unit = GameController
                .getCivilization().getFarmerUnit();
                    player.play();
                                break;
                case "Coal Miners": unit = GameController
                .getCivilization().getCoalMinerUnit();
                    player.play();
                                    break;
                case "Anglers": unit = GameController
                .getCivilization().getAnglerUnit();
                    player.play();
                                break;
                case "Master Builders": unit = GameController
                .getCivilization().getMasterBuilderUnit();
                    player.play();
                                         break;
                default: unit = null;
                         break;
                }
                if (unit.isAffordable()) {
                    unit.applyInitialCosts();
                    tileCurrent.setOccupant(unit);
                } else {
                    Alert newAlert = new Alert(Alert.AlertType.ERROR);
                    newAlert.setHeaderText("You can't afford it!");
                    newAlert.setTitle(
                        "Cannot Place the Unit onto the Tile!");
                    String path1 = "src/main/java/view/sounds/beep.mp3";
                    Media song1 = new Media(new File(path1).toURI().toString());
                    MediaPlayer player1 = new MediaPlayer(song1);
                    player1.play();
                    newAlert.showAndWait();
                    //System.exit(0);
                }
                GameController.getLastClicked().updateTileView();
                GameController.updateResourcesBar();
            });
        addMenuItem(selectButton);
    }
}