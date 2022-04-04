package am.david.game;

import am.david.InterSectStore.MCont;
import am.david.Raster.Display;
import am.david.mathlib.Vector2;

import java.awt.*;
import java.awt.geom.Point2D;

public class Scratch {


    public void PlotCrossHair(Graphics display, Vector2 ordinate, Color color, int hlen) {
        // int hlen = 15;
        display.setColor(color);
        display.drawLine((int) ordinate.x - hlen, (int) ordinate.y + 0, (int) ordinate.x + hlen, (int) ordinate.y + 0);
        display.drawLine((int) ordinate.x + 0, (int) ordinate.y - hlen, (int) ordinate.x + 0, (int) ordinate.y + hlen);
    }

    public void Plot_slope_MP_fromPointLSP(Graphics display, MCont MCont) {

        int dX = (int) MCont.RVP.x - (int) MCont.LSP.x;
        int dY = (int) MCont.RVP.y - (int) MCont.LSP.y;

        display.setColor(Color.WHITE);
        display.drawLine((int) MCont.RVP.x, (int) MCont.RVP.y, ((int) MCont.LSP.x), ((int) MCont.LSP.y));

        double slope = 0;
        if (dX != 0) {
            //  System.out.println("is not zero");
            slope = (double) dY / (double) dX;
        }
        MCont.mP = slope;
    }

    public void Plot_slope_MQ_fromPointLSQ(Graphics display, MCont MCont) {

        double dX = MCont.RVQ.x - MCont.LSQ.x;
        double dY = MCont.RVQ.y - MCont.LSQ.y;


        display.setColor(Color.WHITE);
        display.drawLine((int) MCont.RVQ.x, (int) MCont.RVQ.y, ((int) MCont.LSQ.x), ((int) MCont.LSQ.y));

        double slope = 0;
        // Actually should use epsilon....
        if ((int) dX != 0) {
            System.out.println("is not zero");
            slope = dY / dX;
        }
        MCont.mQ = slope;
    }

    public void HaveMagnitudesHaveSlopeWillTravel(Graphics graphics, MCont MCont) {


        graphics.setColor(Color.YELLOW);

        // Set Lines to new Intersection

        // Draw Point LSP to T
        graphics.drawLine((int) (MCont.LSP.x), (int) (MCont.LSP.y), (int) (MCont.PT.x), (int) (MCont.PT.y));

        // Draw Point LSQ to T
        graphics.drawLine((int) (MCont.LSQ.x), (int) (MCont.LSQ.y), (int) (MCont.PT.x), (int) (MCont.PT.y));

    }


    public static Vector2 ClosestPoint(Vector2 T, Vector2 P, Vector2 Q) {
        final double xDelta = Q.x - P.x;
        final double yDelta = Q.y - P.y;

        if ((xDelta == 0) && (yDelta == 0)) {
            throw new IllegalArgumentException(" P and Q cannot be the same point!");
        }

        final double u = ((T.x - P.x) * xDelta + (T.y - P.y) * yDelta) / (xDelta * xDelta + yDelta * yDelta);

        final Vector2 closestPoint;
        if (u < 0) {
            closestPoint = P;
        } else if (u > 1) {
            closestPoint = Q;
        } else {
            closestPoint = new Vector2(P.x + u * xDelta, P.y + u * yDelta);
        }
        return closestPoint;
    }


    public void QuestToSolveForConstant(Graphics graphics, MCont MCont) {


        Vector2 P = MCont.LSP;

        Vector2 T = MCont.PT;

        Vector2 Q = MCont.LSQ;

        Vector2 C = ClosestPoint(T, P, Q);  // Point C is projected some where on line segment P to Q right angle to T

        MCont.C = C;  // just store it for what ever reason ...


        graphics.setColor(Color.ORANGE);


        graphics.drawLine((int) T.x, (int) T.y, (int) C.x, (int) C.y);

        ProjectAontoB(grapics, mMcount);
        
    }
    
       public void ProjectAontoB(Graphics graphics, MCont mMCont){

        // TransLate Vector back to Origin One way to Prep for Magnitude

        Vector2 a = TransLate(mMCont.PT, new Vector2(-320, -240));
        Vector2 b = TransLate(mMCont.LSP, new Vector2(-320, -240));

        double on = dotProduct(a, b);
        double bdotProduct = dotProduct(b,b);

      //  https://www.youtube.com/watch?v=xYYzhHjG94A
     // Projection of a on b is.

        double ONx = ((on / bdotProduct ) * b.x) + 320;
        double ONy = ((on / bdotProduct ) * b.y) + 240;

        // same
        //  double amag = Magnitude(a);
        //  double bmag = Magnitude(b);
        // double ONx = ((on / (bmag*bmag)) * b.x) + 320;
        // double ONy = ((on / (bmag*bmag)) * b.y) + 240;

        graphics.setColor(Color.RED);
        graphics.drawOval((int)  (ONx) , (int)(ONy) , 4, 4);
    }
    
     public double dotProduct(Vector2 a, Vector2 b)
    {
        return ((a.x*b.x) + (a.y * b.y));
    }
    
    
        /*
        Formula of Magnitude of a Vector
            Used to calculate the length of a vector and is denoted by |v|.
            In one case, the magnitude is calculated for a vector when its endpoints is
            at origin (0,0) while in the other case, the starting and ending point of the
            vector is at certain points (x1,y1) and (x2,y2) respectively.

            Magnitude Formula for a Vector When End Point is Origin...
            |v| = Math.sqrt( x1*x1 + y1*y1);

            Magnitude Formula for a Vector when starting points are (x1,y1) and endpoints
            are (x2,y2).
            |v| = Math.sqrt( ( <x2 + x1> * <x2 + x1> ) + ( <y2 + y1> * <y2 + y1> ) );
     */
    
      public double GetVectorLength(Vector2 vec){
        return Math.sqrt( Math.abs( vec.x * vec.x + vec.y * vec.y ) );
    }


    public double Length(Vector2 vec){
        return Math.sqrt( Math.abs( vec.x * vec.x + vec.y * vec.y ));
    }
    public double Magnitude(Vector2 vec) {
       return Length(vec);
    }


    public Vector2 NormalizeToUnitVector(Vector2 vec){
        double mag = GetVectorLength(vec);
        // |a| = sqrt((a.x*a.x) + (a.y*a.y));

        // Normalized to UnitVector
        // x = a.x / |a|
        // y = a.y / |a|
        return new Vector2(vec.x / mag, vec.y / mag);
    }
    
    
    
    

    
    
    
    
    

    public void CaseUnknownPlotDeltaRelativeToOrigin(MCont MCont, Graphics display, Object objectOrigin) {
        //    Point origin = new Point();
        Vector2 origin = new Vector2();
        if (objectOrigin instanceof Display) {
            origin.x = ((Display) objectOrigin).GetBitmapBufferWidth();
            origin.y = ((Display) objectOrigin).GetBitmapBufferHeight();
        }
        if (objectOrigin instanceof Point) {
            origin.x = ((Point) objectOrigin).x;
            origin.y = ((Point) objectOrigin).y;
        }
        if (objectOrigin instanceof Vector2) {
            origin.x = ((Vector2) objectOrigin).x;
            origin.y = ((Vector2) objectOrigin).y;
        }


        MCont.CartesianP = new Vector2(origin.x / 2, origin.y / 2);

        // Primarily we will draw Primary P (to origin and from origin to other) Quadrant Q of our line segment P to Q.

        display.setColor(Color.WHITE);
        display.drawLine((int) MCont.LSP.x, (int) MCont.LSP.y, (int) MCont.CartesianP.x, (int) MCont.CartesianP.y);


        double dX = origin.x - MCont.LSP.x;
        double dY = origin.y - MCont.LSP.y;


        // Draw from Center of Screen to other Quadrant to Q.
        display.setColor(Color.MAGENTA);
        display.drawLine((int) MCont.CartesianP.x, (int) MCont.CartesianP.y, (int) dX, (int) dY);
        MCont.LSQ = new Vector2(dX, dY);

    }

    // Algebra
    public Vector2 lineIntersect(Vector2 P, double mP, Vector2 Q, double mQ) {
        Vector2 temp = new Vector2(0, 0);

        temp.x = ((mP * P.x) - (mQ * Q.x) + (Q.y - P.y)) / (mP - mQ);

        temp.y = (mP * (temp.x - P.x) + P.y);

        return temp;
    }

}
