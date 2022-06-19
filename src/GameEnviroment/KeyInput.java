package GameEnviroment;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

import GameEnviroment.Game.STATE;

public class KeyInput extends KeyAdapter{
	
	public Handler handler;
	private boolean[] keyDown = new boolean[4];
	Game game ;
	
	public KeyInput(Handler handler, Game game ) {
		this.handler=handler;
		this.game = game;
		keyDown[0] = false;
		keyDown[1] = false;
		keyDown[2] = false;
		keyDown[3] = false;
	}
	
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		for (int i =0; i<handler.object.size();i++) {
		    GameObject tempObject = handler.object.get(i);	
		
		    if (tempObject.getId()==ID.Player) {
			    //key events for player1
		    	if (key == KeyEvent.VK_W) {
		    		tempObject.setVelY(-handler.spd);
		    		keyDown[0]=true;
		    	}else if (key == KeyEvent.VK_S) {
		    		tempObject.setVelY(+handler.spd);
		    		keyDown[1]=true;
		    	}else if (key == KeyEvent.VK_A) {
		    		tempObject.setVelX(-handler.spd);
		    		keyDown[2]=true;
		    	}else if (key == KeyEvent.VK_D) {
		    		tempObject.setVelX(handler.spd);
		    		keyDown[3]=true;
		    	}	
		    }
		    
		    if(key == KeyEvent.VK_P) {
		    	
		    	if(game.gameState == STATE.Game) {
		    		if (Game.paused) {
			    		Game.paused = false;
			    	}else {Game.paused = true;}
		    	}

		    }
		    
		    
	        if(key == KeyEvent.VK_ESCAPE) {
	        	System.exit(1);
	        }
	        if(key == KeyEvent.VK_SPACE) {
	        	if(Game.gameState == STATE.Game) {
	        		Game.gameState = STATE.Shop;
	        	}else if(Game.gameState == STATE.Shop) {Game.gameState = STATE.Game;}
	        }
		    
//	player 2	    if(tempObject.getId()==ID.Player2) {
//		    	//key event for player2
//		    	if (key == KeyEvent.VK_UP) {
//		    		tempObject.setVelY(-4);
//		    	}else if (key == KeyEvent.VK_DOWN) {
//		    		tempObject.setVelY(+4);
//		    	}else if (key == KeyEvent.VK_LEFT) {
//		    		tempObject.setVelX(-4);
//		    	}else if (key == KeyEvent.VK_RIGHT) {
//		    		tempObject.setVelX(4);
//		    	}
//		    }  
		}		
	}
	public void keyReleased(KeyEvent e) {
	int key  = e.getKeyCode();
	
	for (int i =0; i<handler.object.size();i++) {
	    GameObject tempObject = handler.object.get(i);	
	    
	
        if (tempObject.getId()==ID.Player) {
	        //key events for player1
        	if (key == KeyEvent.VK_W) {
        		keyDown[0]=false;
         	}else if (key == KeyEvent.VK_S) {
         		keyDown[1]=false;
        	}else if (key == KeyEvent.VK_A) {
        		keyDown[2]=false;
        	}else if (key == KeyEvent.VK_D) {
        		keyDown[3]=false;
        	}	
        	
        	if(!keyDown[0] && !keyDown[1]) {tempObject.setVelY(0);}
        	if(!keyDown[2] && !keyDown[3]) {tempObject.setVelX(0);}
        	
        }
    
        
        
        

//        if(tempObject.getId()==ID.Player2) {
//        	//key event for player2
//        	if (key == KeyEvent.VK_UP) {
//        		tempObject.setVelY(0);
//        	}else if (key == KeyEvent.VK_DOWN) {
//        		tempObject.setVelY(0);
//        	}else if (key == KeyEvent.VK_LEFT) {
//    	    	tempObject.setVelX(0);
//        	}else if (key == KeyEvent.VK_RIGHT) {
//    	    	tempObject.setVelX(0);
//    	    }
//        } 
	
	
	
	
	
	    }
	
	}
}
