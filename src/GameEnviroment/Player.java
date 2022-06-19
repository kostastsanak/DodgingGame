
package GameEnviroment;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Player extends GameObject{
	Handler handler;
	
	public Player(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		
		
	}
	
	
		public Rectangle getBounds() {
		return new Rectangle((int )x,(int)y,30,30);
	}
	
	public void tick() {
		x += velX;
		y += velY;
		
		x= Game.clamp(x,0 , Game.WIDTH-36);
		y= Game.clamp(y,0 , Game.HEIGHT-58);
		handler.addObject(new Trail(x,y,ID.Trail,Color.white,32,32,0.09f,handler));
		
		collision();
	}
	
	public void collision() {
		for(int i=0;i<handler.object.size();i++) {
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.Enemy ) {
				if(getBounds().intersects(tempObject.getBounds())) {
					//collision mode
					HUD.HEALTH-=3;
				}
			}
			if(tempObject.getId() == ID.TargetingEnemy ) {
				if(getBounds().intersects(tempObject.getBounds())) {
					//collision mode
					HUD.HEALTH-=2;
				}
			}
			if(tempObject.getId() == ID.Boss) {
				if(getBounds().intersects(tempObject.getBounds())) {
					//collision mode
					HUD.HEALTH-=9;
				}
			}
			if(tempObject.getId() == ID.MegaBoss) {
				if(getBounds().intersects(tempObject.getBounds())) {
					HUD.HEALTH-=100;
				}
			}
			if(tempObject.getId() == ID.BossLine) {
				if(getBounds().intersects(tempObject.getBounds())) {
					HUD.HEALTH-=100;
				}
			}
			if(tempObject.getId() == ID.MediumEnemy ) {
				if(getBounds().intersects(tempObject.getBounds())) {
					//collision mode
					HUD.HEALTH-=5;
				}
			}
			if(tempObject.getId() == ID.HardEnemy ) {
				if(getBounds().intersects(tempObject.getBounds())) {
					//collision mode
					HUD.HEALTH-=9;
				}
			}
		}	
	}
	
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		g.setColor(Color.green);
		g2d.draw(getBounds());
//		else if (id == ID.Player2) {
//			g.setColor(Color.white);
//		}
		
		
		
		g.setColor(Color.white);
		g.fillRect((int)x,(int) y, 30, 30);
		
	}

}
