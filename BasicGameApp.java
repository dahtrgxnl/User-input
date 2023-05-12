//Basic Game Application
//Version 2
// Basic Object, Image, Movement
// Astronaut moves to the right.
// Threaded

//K. Chun 8/2018

//*******************************************************************************
//Import Section
//Add Java libraries needed for the game
//import java.awt.Canvas;

//Graphics Libraries

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;


//*******************************************************************************
// Class Definition Section

/**
 * Alexander-- try to create another astronaut named sally, and move sally up and down
 * then if you want, try to keep the astronauts on the screen
 *
 */


public class BasicGameApp implements Runnable, KeyListener {

   //Variable Definition Section
   //Declare the variables used in the program
   //You can set their initial values too

   //Sets the width and height of the program window
   final int WIDTH = 1000;
	final int HEIGHT = 700;

   //Declare the variables needed for the graphics
   public JFrame frame;
	public Canvas canvas;
   public JPanel panel;

	public BufferStrategy bufferStrategy;
//DECLARE IMAGE (STEP 1)
public Image BobastroPic;

	public Image backround;

	public Image MainastroPic;

	public Image ArmstrongDeath;
	public Image Trashpic;

	public int e;



   //Declare the objects used in the program
   //These are things that are made up of more than one variable type


	public MainCharacter armstrong;

	public Astronaut[] stargazers;
	public Tokens[] Things;


   // Main method definition
   // This is the code that runs first and automatically
   public static void main(String[] args) {
	   BasicGameApp ex = new BasicGameApp();   //creates a new instance of the game
	   new Thread(ex).start();                 //creates a threads & starts up the code in the run( ) method
   }


   // Constructor Method
   // This has the same name as the class
   // This section is the setup portion of the program
   // Initialize your variables and construct your program objects here.
   public BasicGameApp() {

      setUpGraphics();

	   stargazers = new Astronaut[8];
	   Things = new Tokens[5];

	   for (int x = 0; x < stargazers.length; x ++){//making enemies
		   stargazers[x] = new Astronaut(x * 200 + 50, 750, 0 , 3);
	   }


	   for (int a = 0; a < Things.length; a ++){//making pickups
		   Things[a] = new Tokens((int)(Math.random() * WIDTH), (int)(Math.random() * HEIGHT), false);
	   }

	   armstrong = new MainCharacter((int)(Math.random() * WIDTH),HEIGHT/3);//making armstrong


	   //variable and objects
      //create (construct) the objects needed for the game and load up
	   //LOADING THE IMAGE INTO THE VARIABLE NAME ASTROPIC (STEP 2)
	   backround = Toolkit.getDefaultToolkit().getImage("milky-way-2695569__480.jpg");
	   BobastroPic = Toolkit.getDefaultToolkit().getImage("astronaut.png"); //load the picture
	   MainastroPic = Toolkit.getDefaultToolkit().getImage("Cute-astronaut-floating-with-balloon-cartoon-on-transparent-background-PNG.png");
	   ArmstrongDeath = Toolkit.getDefaultToolkit().getImage("kisspng-clip-art-explode-5ae84281275e74.3157940915251708171613.jpg");
	   Trashpic = Toolkit.getDefaultToolkit().getImage("PNGimage.PNG");

   }// BasicGameApp()


//*******************************************************************************
//User Method Section
//
// put your code to do things here.

   // main thread
   // this is the code that plays the game after you set things up
   public void run() {

	   //for the moment we will loop things forever.
	   while (armstrong.isAlive)  {
		   //keyPressed(KeyEvent e);
		   moveThings();  //move all the game objects
		   render();  // paint the graphics
		   pause(10); // sleep for 10 ms
	   }
	   render();  // paint the graphics
   }


	public void check_intercestion() {
		int z;
		int y;
		for (z = 0; z < stargazers.length; z++) {
			if (armstrong.rec.intersects(stargazers[z].rec)) {
				armstrong.isAlive = false;
			}
		}
		for (y = 0; y < Things.length; y++) {
			if (armstrong.rec.intersects(Things[y].rec) && !Things[y].isTaken) {
				Things[y].isTaken = true;
			}
		}
	}




	public void moveThings() {

		//canvas.addKeyListener(this);
		check_intercestion();
		armstrong.moveWrap(armstrong.dx, armstrong.dy);
		for (Astronaut stargazer : stargazers) {
			stargazer.move();
		}

	}

	//Pauses or sleeps the computer for the amount specified in milliseconds
   public void pause(int time ){
	   //sleep
	   try {
		   Thread.sleep(time);
	   } catch (InterruptedException e) {

	   }
   }

   //Graphics setup method
   private void setUpGraphics() {
      frame = new JFrame("Application Template");   //Create the program window or frame.  Names it.

      panel = (JPanel) frame.getContentPane();  //sets up a JPanel which is what goes in the frame
      panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));  //sizes the JPanel
      panel.setLayout(null);   //set the layout

      // creates a canvas which is a blank rectangular area of the screen onto which the application can draw
      // and trap input events (Mouse and Keyboard events)
      canvas = new Canvas();
      canvas.setBounds(0, 0, WIDTH, HEIGHT);
      canvas.setIgnoreRepaint(true);

      panel.add(canvas);  // adds the canvas to the panel.

      // frame operations
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //makes the frame close and exit nicely
      frame.pack();  //adjusts the frame and its contents so the sizes are at their default or larger
      frame.setResizable(false);   //makes it so the frame cannot be resized
      frame.setVisible(true);      //IMPORTANT!!!  if the frame is not set to visible it will not appear on the screen!

      // sets up things so the screen displays images nicely.
      canvas.createBufferStrategy(2);
      bufferStrategy = canvas.getBufferStrategy();
	  canvas.addKeyListener(this);
	   canvas.requestFocusInWindow();
	   System.out.println("DONE graphic setup");
   }


	//paints things on the screen using bufferStrategy
	private void render() {
		if (armstrong.isAlive) {
			Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
			g.clearRect(0, 0, WIDTH, HEIGHT);

			//ADD A BACKGROUND
			g.drawImage(backround, 0, 0, 1000, 700, null);

			//draw the image of the astronaut


			g.drawImage(MainastroPic, armstrong.xpos, armstrong.ypos, armstrong.width, armstrong.height, null);

			for (Astronaut stargazer : stargazers) {
				g.drawImage(BobastroPic, stargazer.xpos, stargazer.ypos, stargazer.width, stargazer.height, null);
			}
			for (e = 0; e < Things.length; e++) {
				if (Things[e].isTaken) {
					Things[e].isTaken = false;
					Things[e].xpos = (int) (Math.random() * WIDTH);
					Things[e].ypos = (int) (Math.random() * HEIGHT);
					Things[e].rec.setLocation(Things[e].xpos, Things[e].ypos);
				} else {
					g.drawImage(Trashpic, Things[e].xpos, Things[e].ypos, Things[e].width, Things[e].height, null);
				}
			}
			g.dispose();
			g.dispose();
			bufferStrategy.show();
		} else {
			Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
			g.clearRect(0, 0, WIDTH, HEIGHT);

			//ADD A BACKGROUND
			g.drawImage(backround, 0, 0, 1000, 700, null);

			//draw the image of the astronaut


			g.drawImage(ArmstrongDeath, armstrong.xpos, armstrong.ypos, armstrong.width + 50, armstrong.height + 50, null);



			g.dispose();
			g.dispose();
			bufferStrategy.show();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		char key=e.getKeyChar();
		int keyCode= e.getKeyCode();
	}

	@Override
	public void keyPressed(KeyEvent e) {

	   int keyCode= e.getKeyCode();
		if (keyCode == 38) {
			armstrong.dy = -5;
		} else if (keyCode == 40) {
			armstrong.dy = 5;
		} else if (keyCode == 37) {
			armstrong.dx = -5;
		} else if (keyCode == 39) {
			armstrong.dx = 5;
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		char key=e.getKeyChar();
		int keyCode= e.getKeyCode();
		if (keyCode == 38 || keyCode == 40){
			armstrong.dy = 0;
		} else if (keyCode == 37 || keyCode == 39) {
			armstrong.dx = 0;
		}
	}
}