package GameEnviroment;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class TargetingEnemy extends GameObject{
	
	private Handler handler;
	private GameObject player;
	
	
	public TargetingEnemy(float x, float y, ID id,Handler handler) {
		super(x, y, id);
		this.handler = handler;
		
		for (int i=0;i <handler.object.size();i++) {
			if(handler.object.get(i).getId() == ID.Player ) {
				player = handler.object.get(i);
			}
		}
	}
		
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,16,16);
	}
	
	public void tick() {
		x+= velX;
		y+=velY;
		
		float diffX = x - player.getX();
		float diffY = x - player.getY() ;
		float distance = (float) Math.sqrt( (x - player.getX()) * (x-player.getX() ) + (y - player.getY()) * (y-player.getY() ) );
		
		velX =  ((-1/distance * diffX));
		velY =  ((-1/distance * diffY));
		
		if (y <=0 || y>=Game.HEIGHT -40) {
			velY *=-1;
		}
		if (x <=0 || x >= Game.WIDTH - 24) {
			velX *= -1;
		}
		
		handler.addObject(new Trail(x,y,ID.Trail,Color.green,16,16,0.03f,handler));
	}

	public void render(Graphics g) {
		g.setColor(Color.green);
		g.fillRect((int)x,(int) y, 16, 16);
		
	}


	
	
}
