package GameEnviroment;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class BossLine extends GameObject{
	
	private Handler handler;
	Random r = new Random();
	private int timer = 100;
	private int timer2= 50;
	public BossLine(float x, float y, ID id,Handler handler) {
		super(x, y, id);
		this.handler = handler;
		velY=2;
	}
		
	public Rectangle getBounds() {
		return new Rectangle(0,(int)y,1040,(int) (y-70));
	}
	
	public void tick() {
		y+=velY;
		if(timer <=0) {
			velY =0;
		}else {timer --;}
//		if(timer <= 0) {timer2 --;}
//		if(timer2 <= 0) {
//			if (velY==0) {velY=1;}
//			if(velY > 0) {
//				velY +=0.01f; 
//			}else if(velY <0) {
//				velY -=0.01f;
//			}
//		}
		
		
		if (x <=0 || x >= Game.WIDTH - 100) {
		velX *= -1;
		}
		
		handler.addObject(new Trail(0,y,ID.Trail,Color.cyan,1040,(int)y-70,1.0f,handler));
	}

	public void render(Graphics g) {
		g.setColor(Color.cyan);
		g.fillRect(0, (int)y, 1040, (int)(y-70));
		
	}


	
	
}
