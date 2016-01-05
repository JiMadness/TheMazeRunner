package model;

import controller.Game;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Decorations {
    private static Decorations instance = new Decorations();
    private Canvas decorationsCanvas = new Canvas(Game.getInstance().getFrame().getWidth(),Game.getInstance().getFrame().getHeight());
    public static Decorations getInstance() {
        return instance;
    }
    private Decorations() {
        decorationsCanvas.getGraphicsContext2D().setFill(Color.WHITE);
        decorationsCanvas.getGraphicsContext2D().setFont(Font.font("Arial",20));
        Game.getInstance().getLayers().getChildren().add(decorationsCanvas);
        decorationsCanvas.getGraphicsContext2D().fillText("Score: "+Player.getInstance().getScore(),10,20);
        decorationsCanvas.getGraphicsContext2D().fillText("Ammo: "+Player.getInstance().getAmmo(),10,40);
    }
    public void updateDecorations(){
        decorationsCanvas.getGraphicsContext2D().clearRect(0,0,Game.getInstance().getFrame().getWidth(),Game.getInstance().getFrame().getHeight());
        decorationsCanvas.getGraphicsContext2D().fillText("Score: "+Player.getInstance().getScore(),10,20);
        decorationsCanvas.getGraphicsContext2D().fillText("Ammo: "+Player.getInstance().getAmmo(),10,40);
    }
}
