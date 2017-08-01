package view;

//import model.Civilization;
//import view.CivEnum;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
//import javafx.scene.text.TextBuilder;
//import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.paint.Color;
import javafx.geometry.Insets;
// import javafx.scene.Scene;
// import javafx.stage.Stage;

/**
 * Created by Tian-Yo Yang on 11/11/2016.
 * This class represents the Start Screen for the Civ applicatios. This is the
 * layout that should be displayed upon running the Civ application.
 *
 * This class should have and display
 * 1. a background
 * 2. a list of Civilizations
 * 3. a Start button
 */
public class StartScreen extends StackPane {
    private Button startButton;
    private ObservableList<CivEnum> civilizations;
    private Image backgroundImage;
    private ListView<CivEnum> listViewCivs;

    /**
    * constuctor of the start screen. Should set the background
    * image and display a list of civilizations and a start button
    */
    public StartScreen() {
        //TODO
        startButton = new Button("START");
        civilizations = FXCollections.observableArrayList(CivEnum.values());
        listViewCivs = new ListView<CivEnum>(civilizations);
        listViewCivs.setMaxWidth(200);
        listViewCivs.setMaxHeight(80);

        backgroundImage = new Image(
            "File:./src/main/java/view/civ_background.png");
        ImageView backgroundImageView = new ImageView();
        backgroundImageView.setImage(backgroundImage);
        Label civLabel = new Label("Select a Civilization to Begin");
        civLabel.setTextFill(Color.RED);
        civLabel.setFont(new Font("Times New Roman", 15));
        VBox startScreenVBox = new VBox();
        startScreenVBox.setPadding(new Insets(0, 0, 200, 0));
        startScreenVBox.setAlignment(Pos.BOTTOM_CENTER);
        startScreenVBox.getChildren()
            .addAll(civLabel, listViewCivs, startButton);
        this.getChildren().addAll(backgroundImageView, startScreenVBox);

    }
    /**
    * gets the start button
    * @return the start button
    */
    public Button getStartButton() {
        //TODO
        return startButton;
    }
    /**
    * return a ListView of CivEnums representing the list of
    * available civilizations to choose from
    * @return listview of CivEnum
    */
    public ListView<CivEnum> getCivList() {
        //TODO
        return listViewCivs;
    }
}