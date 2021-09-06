/**
 * @Author : BEYZA YÜKSEL 
**/

package com.SnakeGame;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;

public class Play extends Frame {

	private boolean inGame;        // Game over status
	private int feed_x;            // Feed coordinates as x and y
	private int feed_y;
	private int score_feed;        // Number of feed of snake eats
	private int tail;              // Snake tail number
	private String key;            // Direction key in game.
	
	Board border = new Board();    // Object generate others classes
	Snake snake = new Snake();
	MyListener myListener = new MyListener();
	 
	private int cnt = 0;   // counter check direction
	private String direction = "p";  // dummy direction is "p" as first direction
	private int temp1 ;    // current x coordinate, so after snake moving.
	private int temp2 ;    // current y coordinate 

	// Default Constructor
	public Play() {

		setTitle("Snake Game");                          // Title of Game Screen.
		setSize(border.getWidth(), border.getHeight());  // Board(or Game Area) is Width: 600, Height: 600 
		setResizable(false);                             // Area size not change.  
		addKeyListener(myListener);                      // Start listening to keys.
		setVisible(true);
	
		addWindowListener(new WindowAdapter() {          // Terminate the program upon receiving the closing event for the window.
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		// ---- when snake play game ----
		this.score_feed = 0;                           // Fist feed score(or number) is 0, tail equals 0.
		this.tail = 0;
		this.inGame = true;                            // inGame is start of the game. -> inGame means snake is not game over.
		newFeed();									   // game generates randomly feeds.
	}

	// generate randomly feeds
	private void newFeed() {
		int rand = (int) Math.floor(Math.random() * 40)+ 10;  //betwwen 10...50
		feed_x = rand * 10; // Coordinate can be smallest : 10*10=100 , biggest : 50*10=500
		int rand2 = (int) Math.floor(Math.random() * 40)+ 10;  //betwwen 10...50
		feed_y = rand2 * 10;
	}

	// Snake eats feed
	private void checkSnakeFeedApple() {
		
		int head_x = snake.getX().get(0);
		int head_y = snake.getY().get(0);
			
		if ( ((head_x + snake.getWidth() / 2>= feed_x) && (head_x - snake.getWidth() / 2<= feed_x)) &&
		    ((head_y + snake.getWidth() / 2>= feed_y) && (head_y - snake.getWidth() / 2<= feed_y)) ) {
			tail++;  			// snakes grows
			score_feed++;		// score feed increase as 1.
			newFeed(); 		    // Generate feed randomly.
		}
	}

	// ----------DRAW SCREEN START----------
	@Override
	// Snake and feed shape draws.
	public void paint(Graphics g) {
		
		Random random = new Random(); 
		Graphics2D g2 = (Graphics2D) g;
		
		if (inGame) {
			// draw snake
			for (int i = 0; i < snake.getX().size(); i++) {
				g2.setColor(Color.GREEN);
				g2.drawRect(snake.getX().get(i), snake.getY().get(i), snake.getWidth(), snake.getHeight());
				g2.fillRect(snake.getX().get(i), snake.getY().get(i), snake.getWidth(), snake.getHeight());

			}
			// draw feed
			g2.setColor(new Color( random.nextInt(255), random.nextInt(255), random.nextInt(255) ));
			g2.drawOval(feed_x, feed_y, 20, 20);
			g2.fillOval(feed_x, feed_y, 20, 20);
			
			
			// draw score feed writes
			g2.setColor(Color.RED);
			String score_txt = "Score: ";
			Font small = new Font("Ink Free", Font.BOLD, 30);
			FontMetrics metr = getFontMetrics(small);
			g2.setFont(small);
			g2.drawString(score_txt + score_feed, (border.getWidth() - metr.stringWidth(score_txt + score_feed)) / 2, border.getHeight() - 530);


		}
		// inGame == false -> Game over screen shows
		else {
			// draw game over screen
			g2.setColor(Color.BLACK);
			g2.drawRect(0, 0, border.getWidth(), border.getHeight());
			g2.fillRect(0, 0, border.getWidth(), border.getHeight());

			gameOver(g);
		}
	}
	
	// Draw finish game screen
	private void gameOver(Graphics g) {

		Graphics2D g2 = (Graphics2D) g;
		
		// Score writing.
		// Last Score writes of gamer.
		g2.setColor(Color.RED);
		String Text = "Score: ";
		Font small = new Font("Ink Free", Font.BOLD, 30);
		FontMetrics metr = getFontMetrics(small);
		g2.setFont(small);
		g2.drawString(Text + score_feed, (border.getWidth() - metr.stringWidth(Text + score_feed)) / 2, border.getHeight() - 530);

		// Draw Game Finish Screen
		String finishedText = "Game Over";
		Font small2 = new Font("Ink Free", Font.BOLD, 30);
		FontMetrics metr2 = getFontMetrics(small);

		g2.setColor(Color.RED);
		g2.setFont(small2);
		g2.drawString(finishedText, (border.getWidth() - metr2.stringWidth(finishedText)) / 2, border.getHeight() / 2);
	}
	// ----------DRAW SCREEN END----------
	
	// Right -> not move Left , Up -> not move Down , Left -> not move Right , Down -> not move Up  
	private void checkDirectionOppositeMove()
	{	
		key = myListener.getDirection();
		if (    (key.equals("w") && !snake.getDirection().equals("s")) ||
				(key.equals("a") && !snake.getDirection().equals("d")) || 
				(key.equals("d") && !snake.getDirection().equals("a")) ||
				(key.equals("s") && !snake.getDirection().equals("w"))  ) {
			snake.setDirection(key);
		}
	}
	
	private void checkCollision() {

		// snake tail collision
		for (int i=1; i<snake.getX().size(); i++) 
		{
			if (       (snake.getX().get(0) + snake.getWidth()/3 >= snake.getX().get(i) )
					&& (snake.getX().get(0) - snake.getWidth()/3 <= snake.getX().get(i) )
					&& (snake.getY().get(0) + snake.getWidth()/3 >= snake.getY().get(i) )
					&& (snake.getY().get(0) - snake.getWidth()/3 <= snake.getY().get(i) ) )
			{
				System.out.println("x "+ (snake.getX().get(0) + snake.getWidth()/3));
				System.out.println("x1 "+ snake.getX().get(i) );
				System.out.println("y "+ (snake.getX().get(0) - snake.getWidth()/3)); 
				System.out.println("y1 "+ snake.getY().get(i));
				inGame = false; 
			} 
		}

		// if the snake's head is greater than or equal to the border length, width,
		// it has hit the border and the game is over.
		if (snake.getY().get(0) >= border.getHeight()) {
			inGame = false;
		}

		if (snake.getY().get(0) < 0) {
			inGame = false;
		}

		if (snake.getX().get(0) >= border.getWidth()) {
			inGame = false;
		}

		if (snake.getX().get(0) < 0) {
			inGame = false;
		}
	}
	
	
	//------------------------------- CHECK PART END -------------------------------
	
	
	//  Snake moves right direction.
	public void rightMove()
	{
		boolean d = snake.getY().get(0) > snake.getY().get(1); // if snake move up. -> true

		int xtemp = snake.getX().get(0);  // xtemp variable keeps to first x coordinate of snake
		int ytemp = snake.getY().get(0);  // ytemp variable keeps to first y coordinate of snake		
		snake.getX().set(0, snake.getX().get(0) + snake.getSpeed()); // snake head moves right 

		for (int i = 1; i < snake.getX().size(); i++) {

			// if snake move right when going down.
			if (cnt == 0 && d) {
				temp1 = snake.getX().get(i);
				temp2 = snake.getY().get(i);
				snake.getY().set(i, snake.getY().get(i) + snake.getSpeed());
				cnt++;
			}
			// if snake move right when going up.
			else if (cnt == 0) {
				temp1 = snake.getX().get(i);
				temp2 = snake.getY().get(i);
				snake.getY().set(i, snake.getY().get(i) - snake.getSpeed());
				cnt++;

			} 
			// if snake move same(right) direction.
			else {
				temp1 = snake.getX().get(i);
				temp2 = snake.getY().get(i);
			}
			snake.getX().set(i, xtemp);  // set getX of snake
			snake.getY().set(i, ytemp);  // set getY of snake

			xtemp = temp1;  // current x coordinate(temp1) assign to xtemp variable.
			ytemp = temp2;  // current y coordinate(temp2) assign to ytemp variable.
		}
	}
	
	// Snake moves Left direction
	public void leftMove()
	{
		boolean d = snake.getY().get(0) < snake.getY().get(1); // if snake move up. -> true

		int xtemp = snake.getX().get(0);  // xtemp variable keeps to first x coordinate of snake
		int ytemp = snake.getY().get(0);  // ytemp variable keeps to first y coordinate of snake		
		snake.getX().set(0, snake.getX().get(0) - snake.getSpeed());  // snake head moves left

		for (int i = 1; i < snake.getX().size(); i++) {

			// if snake move left when going up.
			if (cnt == 0 && d) {
				temp1 = snake.getX().get(i);
				temp2 = snake.getY().get(i);
				snake.getY().set(i, snake.getY().get(i) - snake.getSpeed());
				cnt++;
			}
			// if snake move left when going down. 
			else if (cnt == 0) {
				temp1 = snake.getX().get(i);
				temp2 = snake.getY().get(i);
				snake.getY().set(i, snake.getY().get(i) + snake.getSpeed());
				cnt++;
			}
			// if snake move same(left) direction.
			else {
				temp1 = snake.getX().get(i);
				temp2 = snake.getY().get(i);
			}
			snake.getX().set(i, xtemp);  // set getX of snake
			snake.getY().set(i, ytemp);  // set getY of snake

			xtemp = temp1;  // current x coordinate(temp1) assign to xtemp variable.
			ytemp = temp2;  // current y coordinate(temp2) assign to ytemp variable.
		}
		
	}
	// Snake moves Up direction
	private void upMove()
	{
		boolean d = snake.getX().get(0) > snake.getX().get(1); // if snake move down. -> true

		int xtemp = snake.getX().get(0);  // xtemp variable keeps to first x coordinate of snake
		int ytemp = snake.getY().get(0);  // ytemp variable keeps to first y coordinate of snake		
		snake.getY().set(0, snake.getY().get(0) - snake.getSpeed());  // snake head moves up

		for (int i = 1; i < snake.getY().size(); i++) {

			// At the start of the game snake tail will move one unit
			// if snake move up when going right.
			if (cnt == 0 && d) {    
				temp1 = snake.getX().get(i);  // temp1 keeps snake moving in current x coordinate
				temp2 = snake.getY().get(i);  // temp2 keeps snake moving in current y coordinate
				snake.getX().set(i, snake.getX().get(i) + snake.getSpeed());
				cnt++;
			// if snake move up when going left.	
			} else if (cnt == 0) {    
				temp1 = snake.getX().get(i);
				temp2 = snake.getY().get(i); 
				snake.getX().set(i, snake.getX().get(i) - snake.getSpeed());
				cnt++;
			// if snake move same(up) direction.
			} else {        
				temp1 = snake.getX().get(i);
				temp2 = snake.getY().get(i);
			}

			snake.getX().set(i, xtemp);  // set getX of snake
			snake.getY().set(i, ytemp);  // set getY of snake

			xtemp = temp1;  // current x coordinate(temp1) assign to xtemp variable.
			ytemp = temp2;  // current y coordinate(temp2) assign to ytemp variable.
		}

	}
	// Snake moves Down direction
	private void downMove()
	{
		boolean d = snake.getX().get(0) > snake.getX().get(1); // if snake move down. -> true

		int xtemp = snake.getX().get(0);  // xtemp variable keeps to first x coordinate of snake
		int ytemp = snake.getY().get(0);  // ytemp variable keeps to first y coordinate of snake
		snake.getY().set(0, snake.getY().get(0) + snake.getSpeed());  // snake head moves down
  
		for (int i = 1; i < snake.getY().size(); i++) {

			// if snake move down when going right.
			if (cnt == 0 && d) {
				temp1 = snake.getX().get(i); // temp1 keeps snake moving in current x coordinate
				temp2 = snake.getY().get(i); // temp2 keeps snake moving in current y coordinate
				snake.getX().set(i, snake.getX().get(i) + snake.getSpeed()); //snake move to down
				cnt++; 
			} 
			// if snake move down when going left.
			else if (cnt == 0) {
				temp1 = snake.getX().get(i); 
				temp2 = snake.getY().get(i);
				snake.getX().set(i, snake.getX().get(i) - snake.getSpeed());
				cnt++;
			} 
			// if snake move same(down) direction.
			else {
				temp1 = snake.getX().get(i);
				temp2 = snake.getY().get(i);
			}

			snake.getX().set(i, xtemp);  // set getX of snake
			snake.getY().set(i, ytemp);  // set getY of snake

			xtemp = temp1;  // current x coordinate(temp1) assign to xtemp variable.
			ytemp = temp2;  // current y coordinate(temp2) assign to ytemp variable.
		}

	}
	
	public void moveIt() {
		
		while (inGame) {  // if inGame do not equals false, game continues.
			
			key = myListener.getDirection();
			checkDirectionOppositeMove();     // Method calls
			checkSnakeFeedApple(); 
			checkCollision();
			revalidate();
			repaint();

			// first direction dummy direction assigned as "p" is when the game starts
			if (!direction.equals(snake.getDirection())) { // getDirection="s", direction="p" is when the game starts
				direction = snake.getDirection();  // first direction gets as "s"(down direction)  
				cnt = 0;
			}
			if (snake.getDirection().equals("w")) {        // Up direction
				upMove();
			} 
			else if (snake.getDirection().equals("a")) {   // Left direction
				leftMove();
			} 		
			else if (snake.getDirection().equals("s")) {   // Down direction
				downMove();
			} 
			else if (snake.getDirection().equals("d")) {   // Right direction
				rightMove(); 
			} 
			try {
				Thread.sleep(80);
			} catch (Exception e) {
				e.printStackTrace();
			}
			// increases in snake size as the number of feeds
			if (tail > 0) {
				snake.getX().add(temp1); 
				snake.getY().add(temp2);
				tail--;
			}			
		}
	}
}
