package rubikscube.controls;


import javafx.scene.input.MouseEvent;
import javafx.scene.transform.Rotate;

import rubikscube.App;
import rubikscube.cube.Cube;


public final class MouseListener {

    public static double mouseDraggedX;
    public static double mouseDraggedY;

    public static double mousePressedX;
    public static double mousePressedY;


    public static void init() {
        Rotate cubeRotateX = new Rotate(30, 0, 0, 0, Rotate.X_AXIS);
        Rotate cubeRotateY = new Rotate(-30, 0, 0, 0, Rotate.Y_AXIS);
        Cube.mainAnchorPane.getTransforms().addAll(cubeRotateX, cubeRotateY);

        App.getStage().getScene().addEventHandler(MouseEvent.MOUSE_PRESSED, event -> {
            MouseListener.mouseDraggedX = event.getSceneX();
            MouseListener.mouseDraggedY = event.getSceneY();

            MouseListener.mousePressedX = event.getSceneX();
            MouseListener.mousePressedY = event.getSceneY();
        });

        App.getStage().getScene().addEventHandler(MouseEvent.MOUSE_DRAGGED, event -> {
            double mousePosX = event.getSceneX();
            double mousePosY = event.getSceneY();

            cubeRotateX.setAngle(cubeRotateX.getAngle() + mousePosY - MouseListener.mouseDraggedY);
            cubeRotateY.setAngle(cubeRotateY.getAngle() - mousePosX + MouseListener.mouseDraggedX);

            MouseListener.mouseDraggedX = mousePosX;
            MouseListener.mouseDraggedY = mousePosY;
        });
    }

}
