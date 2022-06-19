package GameEnviroment;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Boss extends GameObject{
	
	private Handler handler;
	
	public Boss(float x, float y, ID id,Handler handler) {
		super(x, y, id);
		this.handler = handler;
		velX = 3;
		velY=9;
	}
		
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,12,12);
	}
	
	public void tick() {
		x+= velX;
		y+=velY;
		if (y <=0 || y>=Game.HEIGHT -40) {
			velY *=-1;
		}
		if (x <=0 || x >= Game.WIDTH - 24) {
			velX *= -1;
		}
		
		handler.addObject(new Trail(x,y,ID.Trail,Color.blue,16,16,0.07f,handler));
	}

	public void render(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect((int)x, (int)y, 12, 12);
		
	}


	
	
}
