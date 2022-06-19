package GameEnviroment;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class MegaBoss extends GameObject{
	
	private Handler handler;
	Random r = new Random();
	private int timer = 100;
	private int timer2 = 50;
	public MegaBoss(float x, float y, ID id,Handler handler) {
		super(x, y, id);
		this.handler = handler;
		velX = 0;
		velY=2;
	}
		
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,96,96);
	}
	
	public void tick() {
		x+= velX;
		y+=velY;
		if(timer <=0) {
			velY =0;
		}else {timer --;}
		
		if(timer <=0) {timer2 --;}
		if(timer2 <=0) {
			
			if(velX==0) {velX=3;}
			if(velX > 0) {
				velX +=0.01f; 
			}else if(velX <0) {
				velX -=0.01f;
			}
			int spawn = r.nextInt(4);
			if(spawn == 0) handler.addObject(new BossBullet((int)x+40,(int)y+90, ID.Enemy,handler));
		}
		
		
		if (x <=0 || x >= Game.WIDTH - 100) {
		velX *= -1;
		}
		
		handler.addObject(new Trail(x,y,ID.Trail,Color.red,106,106,1.0f,handler));
	}

	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect((int)x, (int)y, 96,96);
		
	}


	
	
}
