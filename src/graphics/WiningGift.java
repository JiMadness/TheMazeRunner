package graphics;


        import controller.Game;
        import javafx.scene.image.Image;
        import model.Maze;

public class WiningGift extends Gifts {
    public WiningGift(){
        super();
        this.type=Type.Wining;
        this.Icon=new Image("win.png");
        GiftsCanvas.setVisible(false);
        Game.getInstance().getLayers().getChildren().add(GiftsCanvas);
    }
    @Override
    public void Show() {

        if(!isTaken()){
            this.Value=(int) Math.floor(Math.random()*10);
            this.PosX= Maze.getXPath();
            ApperantY=Maze.getYPath()+150;
            this.PosY=Maze.getYPath();
            this.setHidden(false);
            GiftsCanvas.getGraphicsContext2D().drawImage(Icon,PosX,PosY);
            GiftsCanvas.setVisible(true);
        }
    }

    @Override
    public void AddBonus() {
        GiftsCanvas.setVisible(false);
        main.Main.getInstance().goToWinningStage();

    }
}
