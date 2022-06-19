package GameEnviroment;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class MenuEnemy extends GameObject{
	
	private Handler handler;
	private Random r = new Random();
	private int red = r.nextInt(255);
	private int green = r.nextInt(255);
	private int blue = r.nextInt(255);
	private Color col;
	private int number;
	public MenuEnemy(float x, float y, ID id,Handler handler) {
		super(x, y, id);
		this.handler = handler;
		number = r.nextInt(4);
		if(number == 0) {
			velX = 3;
			velY=9;
		}else if(number == 1) {
			velX = -3;
			velY=9;
		}else if(number == 2) {
			velX = 3;
			velY=-9;
		}else if(number == 3) {
			velX = -3;
			velY=-9;
		}
		col = new Color(red , green , blue);
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
		
		handler.addObject(new Trail(x,y,ID.Trail,col,16,16,0.07f,handler));
	}

	public void render(Graphics g) {
		g.setColor(col);
		g.fillRect((int)x, (int)y, 12, 12);
		
	}


	
	
}
