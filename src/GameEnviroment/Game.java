package GameEnviroment;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1272177376689917087L;
	
	public static final int WIDTH=1040,HEIGHT = WIDTH /12 *9;
	
	
	private Thread thread;
	private boolean running = false;
	
	public static boolean paused = false;
	public int diff =0;
	//0 = easy
	//1 = normal
	//2 = hard
	private Random r;
	private Handler handler;
	private HUD hud;
	private Spawn spawn;
	private Menu menu;
	private Shop shop;
	
	public enum STATE {
		Menu,
		SELECT,
		Shop,
		Help,
		Game,
		End
	};
	
	public static STATE gameState = STATE.Menu;
	
	
	
	public Game() {
		

		
		handler = new Handler();
		hud =new HUD();
		shop = new Shop(handler, hud);
		menu = new Menu(this,handler,hud);
		this.addKeyListener(new KeyInput(handler, this));
		this.addMouseListener(menu);
		this.addMouseListener(shop);
		
		AudioPlayer.load();
		
		AudioPlayer.getMusic("music").loop();
		
		new Window(WIDTH , HEIGHT,"Just a game!",this);
		

		
		spawn = new Spawn(handler , hud, this);
		r = new Random();

		if(gameState == STATE.Game) {
			handler.addObject(new Player(WIDTH/2-32, HEIGHT/2-32, ID.Player,handler));	
			handler.addObject(new BasicEnemy(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.Enemy,handler));
		}else {
			handler.addObject(new Player(WIDTH/2-32, HEIGHT/2-32, ID.Player,handler));	
			for (int i=0;i <18;i++)
				handler.addObject(new MenuEnemy(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.MenuEnemy,handler));	
		}
		
				

	}
	
	
	public void start () {
		thread = new Thread (this);
		thread.start();
		running = true;
	}
	
	public void stop() {
		try {
			thread.join();
			running = false;
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void run() {
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns= 1000000000 / amountOfTicks;
		double delta= 0;
		long timer = System.currentTimeMillis();
		int frames = 0 ;
		while (running) {
			long now = System.nanoTime();
			delta +=(now - lastTime)/ns;
			lastTime = now;
			while(delta>=1) {
				tick();
				delta -- ;
			}
			if (running) {
				render();
			}
			frames ++ ;
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				frames = 0;
			}
		}
		stop();	
	}
	


	private void tick() {
		
		
		if(gameState == STATE.Game) {
			
			if(!paused) {
			    hud.tick();
			    spawn.tick();
			    handler.tick();
			    
				if(hud.HEALTH<=0) {
					gameState = STATE.End;
					handler.clearAllEnemies();
					for (int i=0;i <18;i++)
						handler.addObject(new MenuEnemy(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.MenuEnemy,handler));
					hud.HEALTH = 200;
					}
			}
			

		}else if(gameState == STATE.Menu || gameState == STATE.End || gameState == STATE.SELECT) {
			menu.tick();
		    handler.tick();
		}
		
		
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		Font fnt3 = new Font("arial",1,15);
		g.setFont(fnt3);
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		
		if(paused) {
			Font fnt = new Font("arial",1,60);
			g.setFont(fnt);
			g.setColor(Color.red);
			g.drawString("PAUSED", 365, 230);
			Font fnt2 = new Font("arial",1,15);
			g.setFont(fnt2);
		}
		
		if(gameState == STATE.Game) {
			hud.render(g);
			handler.render(g);
		}else if(gameState == STATE.Menu || gameState == STATE.Help || gameState == STATE.End || gameState == STATE.SELECT)
		{
			menu.render(g);
			handler.render(g);
		}else if(gameState == STATE.Shop) {
			shop.render(g);
		}
		
		
		

		
		g.dispose();
		bs.show();
	}
	
	
	
	public static float clamp(float var, float min, float max) {
		if(var>=max) {
			return var=max;
		}else if(var <= min) {
			return var = min;
		}else {
			return var;
		}
		
		
	}
	
	
	
	
	public static void main(String[] args) {
		new Game();
	}

}
