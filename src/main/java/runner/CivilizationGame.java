package runner;

import java.util.Optional;

import controller.GameController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;
import model.Bandit;
import model.Civilization;
import model.Egypt;
import model.Map;
import model.QinDynasty;
import model.RomanEmpire;
import view.CivEnum;
import view.GameScreen;
import view.GridFX;
import view.StartScreen;
import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
/**
 * Created by Tian-Yo Yang on 11/11/2016.
 */
public class CivilizationGame extends Application {

    /**
     * this method is called upon running/launching the application
     * this method should display a scene on the stage
     */
    private Scene scene;
    private StartScreen layout;
    private ListView<CivEnum> listCivilization;
    public void start(Stage primaryStage) {
        layout = new StartScreen();
        layout.getStartButton().setOnMousePressed(e -> {
                primaryStage.setScene(startGame());
            });
        listCivilization = layout.getCivList();
        scene = new Scene(layout, 900, 900);
        primaryStage.setScene(scene);
        primaryStage.show();
        String path = "src/main/java/view/sounds/indianbell.mp3";
        Media song = new Media(new File(path).toURI().toString());
        MediaPlayer player = new MediaPlayer(song);
        player.play();
    }
    /**
     * This is the main method that launches the javafx application
     */
    public static void main(String[] args) {
        launch(args);
    }
    /**
    * This method is responsible for setting the scene to the corresponding
    * layout.
    * and returning the scene.
    * @return Scene
    */

    public Scene startGame() {
        //TODO
        CivEnum selectedCivilization = listCivilization
            .getSelectionModel().getSelectedItem();
        switch (selectedCivilization) {
        case ANCIENT_EGYPT: GameController.setCivilization(new Egypt());
                            break;
        case QIN_DYNASTY: GameController.setCivilization(new QinDynasty());
                          break;
        case ROMAN_EMPIRE: GameController.setCivilization(new RomanEmpire());
                             break;
        default: GameController.setCivilization(new Civilization(
                     "DefaultCivilization"));
                 break;
        }
        TextInputDialog dialog = new TextInputDialog("Town Name");
        dialog.setTitle("A New Settlement");
        dialog.setHeaderText("You have built a Settlement!");
        dialog.setContentText("Enter the name of your first town:");
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            String townName = result.get();
            Map gridMap = GridFX.getMap();
            gridMap.putSettlement(
                townName, GameController.getCivilization(), 0, 9);
            gridMap.addEnemies(new Bandit(), 1);
            return new Scene(new GameScreen());
        } else {
            return scene;
        }
    }
}