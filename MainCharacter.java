import java.awt.*;
import java.awt.geom.Line2D;
import java.lang.reflect.Array;

/**
     * Created by chales on 11/6/2017.
     */
    public class MainCharacter {

    //VARIABLE DECLARATION SECION
    //Here's where you state which variables you are going to use.
    public String name;                //holds the name of the hero
    public int xpos;                //the x position
    public int ypos;                //the y position
    public int dx;                    //the speed of the hero in the x direction
    public int dy;                    //the speed of the hero in the y direction
    public int width;
    public int height;
    public boolean isAlive;//a boolean to denote if the hero is alive or dead.

    public Rectangle rec;

    public Line2D line;

    public boolean isChrashing;

    public Array pastclosest;


    // METHOD DEFINITION SECTION

    // Constructor Definition
    // A constructor builds the object when called and sets variable values.


    //This is a SECOND constructor that takes 3 parameters.  This allows us to specify the hero's name and position when we build it.
    // if you put in a String, an int and an int the program will use this constructor instead of the one above.
    public MainCharacter(int pXpos, int pYpos) {
        xpos = pXpos;
        ypos = pYpos;
        width = 60;
        height = 60;
        isAlive = true;
        rec = new Rectangle(xpos, ypos, width, height);

        isChrashing = false;

    } // constructor

    //The move method.  Everytime this is run (or "called") the hero's x position and y position change by dx and d


    /**
     * this is where you would add if statements to try and keep the astronauts on the screen
     * you could use a bouncing movement or a wrapping movement.
     */
    public double getDistance(int x1, int y1, int x2, int y2) {

        return Math.sqrt(((x2 - x1) * (x2 - x1)) + ((y2 - y1) * (y2 - y1)));
    }




    public void moveWrap(int dx, int dy) {


        if (xpos >= 980) {
            xpos -= 979;
        }

        if (xpos <= 0) {
            xpos += 979;
        }

        if (ypos >= 680) {
            ypos -= 679;
        }

        if (ypos <= 0) {
            ypos += 679;

        }


        xpos += dx;
        ypos += dy;
        rec = new Rectangle(xpos, ypos, width, height);
    }
}