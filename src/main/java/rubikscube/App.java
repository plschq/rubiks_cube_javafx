package rubikscube;


import javafx.animation.Interpolator;
import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import rubikscube.controls.*;
import rubikscube.cube.*;
import rubikscube.dataclasses.*;


public final class App extends Application {

    private static Stage stage;


    public static Stage getStage() {
        return App.stage;
    }

    public static void setCameraRemoteness(double cameraRemoteness) {
        App.stage.getScene().getCamera().setTranslateZ(-cameraRemoteness);
    }

    private static Scene createBasicScene() {
        return new Scene(new Group(
                        Cube.mainAnchorPane,
                        new AmbientLight(Color.web("#eee"))
                ), 500, 500, true, SceneAntialiasing.BALANCED);
    }

    private static void setup(Stage stage) {
        App.stage = stage;
        App.stage.setTitle("JavaFX 3x3x3 Rubik's cube 3D");
        App.stage.setScene(App.createBasicScene());
        App.stage.getScene().setFill(Color.web("#181818"));
        App.stage.getScene().setCamera(new PerspectiveCamera(true));
        App.stage.getScene().getCamera().setTranslateX(0);
        App.stage.getScene().getCamera().setTranslateY(0);
        App.stage.getScene().getCamera().setTranslateZ(-1000);
        App.stage.getScene().getCamera().setFarClip(0);
        App.stage.getScene().getCamera().setFarClip(10000);
        App.stage.setFullScreen(false);
        App.stage.setAlwaysOnTop(true);
        App.stage.setResizable(true);
        App.stage.setMinWidth(300);
        App.stage.setMinHeight(300);

        Piece.setSize(90);
        Sticker.setRelativeSize(90);
        Sticker.setRelativeDepth(1.5);
        Turn.setTurnTime(250);
        Turn.setTurnInterpolator(Interpolator.LINEAR);
        App.setCameraRemoteness(1000);

        Cube.init();
        MouseListener.init();

        App.stage.show();
    }

    @Override
    public void start(Stage stage) {
        App.setup(stage);
        Workspace.init();
    }

    @Override
    public void stop() {
        App.stage.close();
    }

    public static void main(String[] args) {
        App.launch(args);
    }

}
