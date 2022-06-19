package GameEnviroment;

import java.util.Random;

public class Spawn {
	private Handler handler;
	private HUD hud;
	private Game game;
	private float scoreKeep=0;
	private Random r = new Random();
	
	public Spawn(Handler handler , HUD hud, Game game) {
		this.handler= handler;
		this.hud = hud;
		this.game = game;
	}
	
	public void tick() {
		scoreKeep+= 4 + hud.getLevel()/4;
		if(scoreKeep >= 1000) {
			scoreKeep=0;
			hud.setLevel(hud.getLevel()+1);	
			if (game.diff == 0) {
				if (hud.getLevel()%2 == 0) {
					handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH),r.nextInt(Game.HEIGHT),ID.Enemy,handler));
				}
				if (hud.getLevel()%4 == 0) {
					handler.addObject(new TargetingEnemy(r.nextInt(Game.WIDTH),r.nextInt(Game.HEIGHT),ID.TargetingEnemy,handler));
				}
				if (hud.getLevel()%10 == 0) {
					handler.addObject(new Boss(r.nextInt(Game.WIDTH),r.nextInt(Game.HEIGHT),ID.Boss,handler));
					hud.HEALTH=200;	
				}
				if (hud.getLevel() == 2 ) {
					handler.clearEnemies();
					handler.addObject(new MegaBoss(Game.WIDTH/2-48,-120,ID.MegaBoss,handler));
					handler.addObject(new BossLine(Game.WIDTH/2-48,-120,ID.BossLine,handler));
					hud.HEALTH=200;	
				}
			}else if (game.diff==1) {
				if (hud.getLevel()%2 == 0) {
					handler.addObject(new MediumEnemy(r.nextInt(Game.WIDTH),r.nextInt(Game.HEIGHT),ID.MediumEnemy,handler));
				}
				if (hud.getLevel()%4 == 0) {
					handler.addObject(new TargetingEnemy(r.nextInt(Game.WIDTH),r.nextInt(Game.HEIGHT),ID.TargetingEnemy,handler));
				}
				if (hud.getLevel()%10 == 0) {
					handler.addObject(new Boss(r.nextInt(Game.WIDTH),r.nextInt(Game.HEIGHT),ID.Boss,handler));
					hud.HEALTH=200;	
				}
				if (hud.getLevel() == 20 ) {
					handler.clearEnemies();
					handler.addObject(new MegaBoss(Game.WIDTH/2-48,-120,ID.MegaBoss,handler));
					handler.addObject(new BossLine(Game.WIDTH/2-48,-120,ID.BossLine,handler));
					hud.HEALTH=200;	
				}
			}else if (game.diff ==2){
				if (hud.getLevel()%2 == 0) {
					handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH),r.nextInt(Game.HEIGHT),ID.HardEnemy,handler));
				}
				if (hud.getLevel()%4 == 0) {
					handler.addObject(new TargetingEnemy(r.nextInt(Game.WIDTH),r.nextInt(Game.HEIGHT),ID.TargetingEnemy,handler));
				}
				if (hud.getLevel()%10 == 0) {
					handler.addObject(new Boss(r.nextInt(Game.WIDTH),r.nextInt(Game.HEIGHT),ID.Boss,handler));
					hud.HEALTH=200;	
				}
				if (hud.getLevel() == 20 ) {
					handler.clearEnemies();
					handler.addObject(new MegaBoss(Game.WIDTH/2-48,-120,ID.MegaBoss,handler));
					handler.addObject(new BossLine(Game.WIDTH/2-48,-120,ID.BossLine,handler));
					hud.HEALTH=200;	
				}
			}
		}	
	}
}
