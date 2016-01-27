package graphics;


import controller.Game;
import javafx.scene.canvas.Canvas;
import model.Player;

import java.util.ArrayList;

public class Pool {
    public static ArrayList<Ammo> _available = new ArrayList<>();
    public static ArrayList<Ammo> _inUse = new ArrayList<>();
    private static int AmmoLimit=10;

    public static Ammo GetAmmo(Player.Moving movingDirection, double posX, double posY, int ref){
        {
            if (_available.size() != 0)
            {
                Ammo po = _available.get(0);
                po.AmmoCanvas = new Canvas(Game.getInstance().getFrame().getWidth(), Game.getInstance().getFrame().getHeight());
                po.dir = movingDirection;
                po.setRefreshTime(ref);
                po.setPosX(posX);
                po.setPosY(posY);
                Game.getInstance().getLayers().getChildren().add(po.AmmoCanvas);
                po.AmmoCanvas.setVisible(false);
                _inUse.add(po);
                _available.remove(0);
                AmmoLimit--;
                System.out.println("X:"+po.getX()+"Y:"+po.getY());
                return po;
            }
            else if (AmmoLimit>0)
            {
                Ammo po = new Ammo(movingDirection,posX,posY,ref);
                _inUse.add(po);
                AmmoLimit--;
                System.out.println("X:"+po.getX()+"Y:"+po.getY());
                return po;
            }
        }
        return null;
    }
    public static void ReleaseAmmos(int bonus)
    {
        AmmoLimit+=bonus;
        for(int i=0;i<bonus;i++){
            if(_inUse.size()>0){
            Ammo po =_inUse.get(_inUse.size()-1);
        {
            _available.add(po);
            _inUse.remove(po);
        }
        }
            else{
                Ammo po=new Ammo();
                _available.add(po);
            }
        }
    }
    public static int getAmmoLimit(){return AmmoLimit;}

}
