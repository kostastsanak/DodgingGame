package GameEnviroment;

import java.awt.Color;
import java.awt.Graphics;

public class HUD {
	
	public int bounds = 0;
	
	public static float HEALTH = 200;
	
	private float greenValue = 255;
	
	private int score=0;
	private int level=1;
	
	public void tick() {
		HEALTH =  Game.clamp(HEALTH, 0, 100+(bounds/2));
		greenValue =  Game.clamp(greenValue, 0, 255);
		greenValue = HEALTH *2+55;
		score += 4 + getLevel()/4;
	}
	

	public void render (Graphics g) {
		g.setColor(Color.gray);
		g.fillRect(320, 15, 350+ bounds, 28);
		if(greenValue>255) greenValue=255;
		g.setColor(new Color(0,(int)greenValue,0));
		g.fillRect(320, 15, (int)HEALTH*4-50, 28);
		g.setColor(Color.white);
		g.drawRect(320, 15, 350+bounds, 28);
		
		g.drawString("Score: "+score, 20, 20);
		g.drawString("Level: "+level, 20, 40);
		g.drawString("Click Space for Shop", 20,60 );
		
	}
	public void setScore (int score) {
		this.score = score;
	}
	public int getscore() {
		return score;
	}
	public int getLevel() {
		return level;
		}
	public void setLevel(int level) {this.level = level;}
	
}
