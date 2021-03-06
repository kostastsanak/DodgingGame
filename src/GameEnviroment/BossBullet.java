package GameEnviroment;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class BossBullet extends GameObject{
	
	private Handler handler;
	Random r = new Random();
	
	
	public BossBullet(float x, float y, ID id,Handler handler) {
		super(x, y, id);
		this.handler = handler;
		velX = (r.nextInt(5 - -5)+ -5);
		velY=6;
	}
		
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,16,16);
	}
	
	public void tick() {
		x+= velX;
		y+=velY;
//		if (y <=0 || y>=Game.HEIGHT -40) {
//			velY *=-1;
//		}
//		if (x <=0 || x >= Game.WIDTH - 24) {
//			velX *= -1;
//		}
		
		if (y>=Game.HEIGHT) handler.removeObject(this);
		
		handler.addObject(new Trail(x,y,ID.Trail,Color.getHSBColor(r.nextInt(256),r.nextInt(256),r.nextInt(256)),16,16,0.03f,handler));
	}

	public void render(Graphics g) {
		g.setColor(new Color(r.nextInt(256),r.nextInt(256),r.nextInt(256)));
		g.fillRect((int)x, (int)y, 16, 16);
	
		
	}


	
	
}
