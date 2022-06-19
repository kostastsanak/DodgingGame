package GameEnviroment;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import GameEnviroment.Game.STATE;

public class Menu extends MouseAdapter{
	private Game game;
	private Handler handler;
	private HUD hud;
	private Random r= new Random();

	public Menu(Game game , Handler handler,HUD hud) {
		this.game = game;
		this.handler = handler;
		this.hud = hud;
	}
	


	public void mousePressed(MouseEvent e) {
		int mx= e.getX();
		int my = e.getY();
		if(game.gameState == STATE.Menu) {
			//play button
			if(mouseOver(mx,my,380, 300, 250, 64)) {
//				game.gameState = STATE.Game;
//				handler.clearAllEnemies();
//				handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player,handler));
//				handler.clearEnemies();
//				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.Enemy,handler));
				game.gameState = STATE.SELECT;
				
				AudioPlayer.getSound("Menu_sound").play();
				return; 
			}
			
	//2-player button
			if(mouseOver(mx,my,380, 400, 250, 64)) {
				System.exit(-1);
				AudioPlayer.getSound("Menu_sound").play();
			}
			
			
			
	//quit button
			if(mouseOver(mx,my,380, 500, 250, 64)) {
				System.exit(-1);
			}
	//help button
			if(mouseOver(mx,my,380, 600, 250, 64)) {
				game.gameState = STATE.Help;
				AudioPlayer.getSound("Menu_sound").play();
			}
		}
		
		
		
		
		
		if(game.gameState == STATE.SELECT) {
	//easy button
			if(mouseOver(mx,my,380, 300, 250, 64)) {
				game.gameState = STATE.Game;
				handler.clearAllEnemies();
				handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player,handler));
				handler.clearEnemies();
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.Enemy,handler));
				game.diff =0;
				
				AudioPlayer.getSound("Menu_sound").play();
			}
			
	//normal button
			if(mouseOver(mx,my,380, 400, 250, 64)) {
				game.gameState = STATE.Game;
				handler.clearAllEnemies();
				handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player,handler));
				handler.clearEnemies();
				handler.addObject(new MediumEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.MediumEnemy,handler));
				game.diff =1;
				
				AudioPlayer.getSound("Menu_sound").play();
			}
			
			
			
	//hard button
			if(mouseOver(mx,my,380, 500, 250, 64)) {
				game.gameState = STATE.Game;
				handler.clearAllEnemies();
				handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player,handler));
				handler.clearEnemies();
				handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.HardEnemy,handler));
				game.diff =2;
				AudioPlayer.getSound("Menu_sound").play();
			}
	//back button
			if(mouseOver(mx,my,380, 600, 250, 64)) {
				game.gameState = STATE.Menu;
				AudioPlayer.getSound("Menu_sound").play();
				return;
			}
		}
		
		
		
		
//back button for help menu
		if(game.gameState == STATE.Help) {
			if(mouseOver(mx,my,70, 652, 130, 44)) {
				game.gameState = STATE.Menu;
				AudioPlayer.getSound("Menu_sound").play();
				return;
			}
		}
//Try again button
		if(game.gameState == STATE.End) {
			if(mouseOver(mx,my,740, 652, 230, 45)) {
				handler.clearAllEnemies();
				hud.setLevel(1);
				hud.setScore(0);
				game.gameState = STATE.SELECT;
				handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player,handler));
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.Enemy,handler));
				AudioPlayer.getSound("Menu_sound").play();
			}
//Go to menu button
			if(mouseOver(mx,my,70, 652, 230, 45)) {
				handler.clearAllEnemies();
				hud.setLevel(1);
				hud.setScore(0);
				game.gameState = STATE.Menu;
				handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player,handler));
				for (int i=0;i <18;i++)
					handler.addObject(new MenuEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.MenuEnemy,handler));	
				AudioPlayer.getSound("Menu_sound").play();
			}
		}
	}

	
	public void mouseReleased(MouseEvent e) {
		
	}
	
	private boolean mouseOver(int mx,int my,int x , int y ,int width, int height) {
		if (mx > x && mx < x+ width) {
			if (my > y && my < y+ height) {
				return true;
			}else {return false;}
		}else {return false;}
	}
	
	public void tick() {	
	}
	
	public void render(Graphics g) {
		if(game.gameState == STATE.Menu) {
		
		
		Font fnt = new Font("arial",1,140);
		g.setFont(fnt);
		g.setColor(Color.white);
		g.drawString("Menu", 335, 230);
		
		Font fnt2 = new Font("arial",1,40);
		g.setFont(fnt2);
		g.setColor(Color.green);
		g.drawRect(380, 300, 250, 64);	
		g.drawString("Play", 464, 342);
		

		g.setColor(Color.blue);
		g.drawRect(380, 400, 250, 64);	
		g.drawString("2-Player", 428, 442);
		
		g.setColor(Color.gray);
		g.drawRect(380, 600, 250, 64);	
		g.drawString("Help", 454, 642);
		
		g.setColor(Color.red);
		g.drawRect(380, 500, 250, 64);	
		g.drawString("Quit", 464, 542);
	
		
		}else if(game.gameState == STATE.Help) {
			Font fnt = new Font("arial",1,90);
			g.setFont(fnt);
			g.setColor(Color.white);	
			g.drawString("Help", 400, 110);
			
			
			
			Font fnt3 = new Font("arial",1,20);
			g.setFont(fnt3);
			g.setColor(Color.white);
			g.drawString("--Dodge bullets and fight enemies wave by wave.Give your best.", 100, 260);
			g.drawString("-USE WASD to move your players or ESC to close game.", 80, 290);

			
			
			
			
			Font fnt2 = new Font("arial",1,30);
			g.setFont(fnt2);
			
			//g.drawRect(70, 652, 130, 44);
			g.setColor(Color.red);
			g.drawString("Back", 90, 682);
		}else if(game.gameState == STATE.End) {
			Font fnt = new Font("arial",1,90);
			g.setFont(fnt);
			g.setColor(Color.RED);	
			g.drawString("Game Over", 300, 110);
			
			
			
			Font fnt3 = new Font("arial",1,20);
			g.setFont(fnt3);
			g.setColor(Color.white);
			g.drawString("You lost with a score of: "+hud.getscore(), 100, 260);

			
			
			
			
			Font fnt2 = new Font("arial",1,30);
			g.setFont(fnt2);
			g.setColor(Color.pink);
			g.drawRect(740, 652, 230, 45);
			g.setColor(Color.GREEN);
			g.drawString("Try again", 794, 688);
			
			g.setColor(Color.blue);
			g.drawRect(70, 652, 230, 45);
			g.setColor(Color.red);
			g.drawString("Go to menu", 96, 688);
		}
		
		
		else if(game.gameState == STATE.SELECT) {
			Font fnt = new Font("arial",1,140);
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("SELECT DIFFICULTY", 235, 230);
			
			Font fnt2 = new Font("arial",1,40);
			g.setFont(fnt2);
			g.setColor(Color.WHITE);
			g.drawRect(380, 300, 250, 64);	
			g.drawString("EASY", 464, 342);
			

			g.setColor(Color.GREEN);
			g.drawRect(380, 400, 250, 64);	
			g.drawString("MEDIUM", 428, 442);
			
			g.setColor(Color.red);
			g.drawRect(380, 600, 250, 64);	
			g.drawString("BACK", 454, 642);
					
			g.setColor(Color.BLUE);
			g.drawRect(380, 500, 250, 64);	
			g.drawString("HARD", 454, 542);
		
			
		}



	}
	
	
}
