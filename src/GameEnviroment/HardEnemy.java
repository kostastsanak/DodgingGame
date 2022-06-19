package GameEnviroment;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class HardEnemy extends GameObject{
	
	private Handler handler;
	private Random r = new Random();
	public HardEnemy(float x, float y, ID id,Handler handler) {
		super(x, y, id);
		this.handler = handler;
		velX = 9;
		velY=9;
	}
		
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,16,16);
	}
	
	public void tick() {
		x+= velX;
		y+=velY;
		if (y <=0 || y>=Game.HEIGHT -40) {
			int number = r.nextInt(6);
			if (number > 1) {
				velY*=(-1 -1/number);
			}else {velY *= -1;}

		}
		if (x <=0 || x >= Game.WIDTH - 24) {
			int number = r.nextInt(6);
			if (number > 1) {
				velX*=(-1 -1/number);
			}else {velX *= -1;}
		}
		
		handler.addObject(new Trail(x,y,ID.Trail,Color.yellow,16,16,0.03f,handler));
	}

	public void render(Graphics g) {
		g.setColor(Color.yellow);
		g.fillRect((int)x, (int)y, 16, 16);
		
	}


	
	
}
