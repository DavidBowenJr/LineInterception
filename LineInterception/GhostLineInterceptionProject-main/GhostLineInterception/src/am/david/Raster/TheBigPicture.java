package am.david.Raster;

import am.david.InterSectStore.MCont;
import am.david.game.Game;
import am.david.game.Scratch;
import am.david.inputs.MouseEvents;
import am.david.mathlib.Vector2;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class TheBigPicture {

    static double mag =0;
    public static Point point;
    static int state = 1;
    static MCont MCont = new MCont(); // Just a My Container to hold some values....
    static Scratch scratch = null;

    public static Display display;
    public static Game game;
    public TheBigPicture(Display display, Game game) {
        TheBigPicture.display = display;
        TheBigPicture.game = game;
        TheBigPicture.scratch = new Scratch();
    }

    public void go()
    {

        // First We will use our mouse to plot a line for a line segment.
        // The line will be reflected into the other Quadrant for us.
        // Second We will use our mouse to generate a ray Vector  on (line segment P)
        // Third  We will use our mouse to generate a ray Vector  on (line segment Q)
        // Fourth the ray Vector will be converted to m slope form.
        // Then we will use a line intersection function to Give us  (Point T)
        // Next we will solve for a perpendicular line that runs from line segment (P__Q) right angle to T
        // Thus somewhere on line segment (P__Q) a Point position should be found.
        // Stored a C.


        Boolean goandtry = true;
        if(MouseEvents.getMouseEventTrap(goandtry) != null)
        {
            mag = Objects.requireNonNull(MouseEvents.getMouseEventTrap(goandtry)).getPoint().distance(display.GetBitmapBufferWidth()/2, display.GetBitmapBufferHeight()/2);
            point = MouseEvents.getMouseEventTrap(goandtry).getPoint();
            boolean b = Objects.requireNonNull(MouseEvents.getMouseEventTrap(goandtry)).isConsumed();
            MouseEvents.getMouseEventTrap(b);

            FactorProcess(state);
        }
    }


    private void FactorProcess(int xstate) {
        Graphics graphics = display.getStrategy().getDrawGraphics();

        switch (xstate)
        {
            case 1:

                display.SurfaceData();

                TheBigPicture.scratch.PlotCrossHair(graphics,new Vector2(display.GetBitmapBufferWidth()/2, display.GetBitmapBufferHeight()/2),
                        Color.yellow, display.GetBitmapBufferHeight()/2);


                System.out.println(" (1.) P______Q " + point);

                MCont.LSP = new Vector2(point);

                TheBigPicture.scratch.PlotCrossHair(graphics, MCont.LSP , Color.GREEN , 2);
              /////////////////////  MCont.pA = new Vector2(point);
                MCont.Origin = new Vector2(display.GetBitmapBufferWidth(), display.GetBitmapBufferHeight());
                TheBigPicture.scratch.CaseUnknownPlotDeltaRelativeToOrigin(MCont, graphics, display);
                TheBigPicture.scratch.PlotCrossHair(graphics, MCont.LSQ, Color.RED, 2);
                display.SwapBuffers(null);
                state++;
                break;

            case 2:

                System.out.println(" (2.) mouse ray generated related to P  " + point);
                MCont.RVP = new Vector2(point);
                TheBigPicture.scratch.Plot_slope_MP_fromPointLSP(graphics, MCont);
                state++;
                break;

            case 3:
                System.out.println(" (3.) mouse ray generated related to Q  " + point);

                MCont.RVQ = new Vector2(point);
                TheBigPicture.scratch.Plot_slope_MQ_fromPointLSQ(graphics, MCont);
                state++;
                break;

            case 4:


                 MCont.PT = TheBigPicture.scratch.lineIntersect(MCont.LSP, MCont.mP, MCont.LSQ, MCont.mQ);

                System.out.println(" (4.) T is the ray intercept at this position  " + MCont.PT);

                graphics.setColor(Color.orange);
                graphics.fillOval((int)MCont.PT.x, (int)MCont.PT.y, 10,10);
                graphics.drawOval((int)MCont.PT.x, (int)MCont.PT.y, 4, 4);

                MCont.magnitudeOfAToB = Point.distance(MCont.LSP.x, MCont.LSP.y, MCont.PT.x, MCont.PT.y);
                MCont.magnitudeOfDToB = Point.distance(MCont.LSQ.x, MCont.LSQ.y, MCont.PT.x, MCont.PT.y);

                System.out.println(" Just some extra visual's  have mag have slope will travel");
                TheBigPicture.scratch.HaveMagnitudesHaveSlopeWillTravel(graphics, MCont);




                // P to Q is a line segment.
                // T being our Apex
                // with P to Q  and T a independent point we will solve the for a right angle position that lies on to P and Q. Thus we get C


                //    SaveImage();

                state++;
                //  state = 1;
                break;

            case 5:
                //finishing steps

                TheBigPicture.scratch.QuestToSolveForConstant(graphics, MCont);

                System.out.println(" (2.) Now somewhere on line segment P___Q there is a point that right angle to T it point will drop onto P___Q  " + point);


                state = 1;
                break;

            default:
                state = 1;
                break;

        }
    }

    public void SaveImage()
    {
        Rectangle screenRect =  game.getCanvas().getBounds();

        Point canvasPoint = new Point(game.getCanvas().getLocationOnScreen());
        Point framePoint = new Point( game.getFrame().getLocationOnScreen());
        Point menuPointWH = new Point( (canvasPoint.x - framePoint.x) , (canvasPoint.y - framePoint.y));

        int locX = (game.getFrame().getLocationOnScreen().x + menuPointWH.x);
        int locY = (game.getFrame().getLocationOnScreen().y + menuPointWH.y);

        screenRect.setLocation(locX, locY);

        try {
            Robot robot = new Robot();

            String format = "png";
            String fileName = " Puppy_Fixed." + format;

            BufferedImage screenFullImage = robot.createScreenCapture(screenRect);

            ImageIO.write(screenFullImage, format, new File(fileName));

            System.out.println("A full screenshot saved!");
        }

        catch (AWTException | IOException ex) {
            System.err.println(ex);
        }
    }

}
