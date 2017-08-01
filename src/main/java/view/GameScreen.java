package view;

import controller.GameController;
import controller.GameController.GameState;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

/**
 * This class represents the GameScreen class
 */
public class GameScreen extends BorderPane {


    /**
     * Creates a new view into the game. this layout should include
     * the rescource bar, grid map, and action menus
     *
     */
    private static ResourcesMenu resourceMenu;
    private static GridFX gridPane;
    private static VBox menu;
    public GameScreen() {
        //TODO
        menu = new StatusMenu().getRootNode();
        gridPane = GridFX.getInstance();
        resourceMenu = new ResourcesMenu();
        this.setTop(resourceMenu.getRootNode());
        this.setLeft(menu);
        this.setCenter(gridPane);
        GameController.setLastClicked(null);
        update();
    }

    /**
     * This method should update the gridfx and the resouce bar
     */
    public static void update() {
      //TODO
        GridFX.update(); //gridPane?
        resourceMenu.update();
    }
    /**
    * this method should return the resource menu
    * @return reosuce menu
    */
    public static ResourcesMenu getResources() {
        //TODO
        return resourceMenu;
    }


    /**
     * This method switches menus based on passed in game state.
     * Game.java calls this to selectively control which menus are displayed
     * @param state
     */
    public static void switchMenu(GameController.GameState state) {
       //TODO
        VBox newMenu;
        if (state == GameState.MILITARY) {
            newMenu = new MilitaryMenu().getRootNode();
        } else if (state == GameState.WORKER) {
            newMenu = new WorkerMenu().getRootNode();
        } else if (state == GameState.RECRUITING) {
            newMenu = new RecruitMenu().getRootNode();
        } else if (state == GameState.BUILDING) {
            newMenu = new BuildingMenu().getRootNode();
        } else if (state == GameState.NEUTRAL) {
            newMenu = new StatusMenu().getRootNode();
        } else {
            newMenu = new StatusMenu().getRootNode();
        }
        menu.getChildren().setAll(newMenu.getChildren());
        update();
    }
}