import java.awt.*;

public class Tokens {
    //holds the name of the hero
    public int xpos;                //the x position
    public int ypos;                //the y position

    public int width;
    public int height;
    public boolean isTaken;//a boolean to denote if the trash is alive or dead.


    public Rectangle rec;



    // METHOD DEFINITION SECTION

    // Constructor Definition
    // A constructor builds the object when called and sets variable values.


    //This is a SECOND constructor that takes 3 parameters.  This allows us to specify the hero's name and position when we build it.
    // if you put in a String, an int and an int the program will use this constructor instead of the one above.
    public Tokens(int pXpos, int pYpos, boolean IsTaken) {
        xpos = pXpos;
        ypos = pYpos;
        width = 20;
        height = 20;
        rec = new Rectangle(xpos, ypos, width, height);
        isTaken = IsTaken;

    } // constructor
}
