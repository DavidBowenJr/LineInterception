package am.david.InterSectStore;

import am.david.mathlib.Vector2;

public class MCont {

    // Our Line Segment from P to Q is plotted with the first mouse placement.
    // Thus we Plot P then Q will follow  in the other Quadrant.

    // RVP and RVQ  will be our ray direction plotted with our mouse.

    // mP and mQ are just slopes rise over run
    // Where mp is the Vector ray relative to RVP - LSP
    // Where mQ is the Vector ray relative to RVQ - LSQ
    public double mP; // mP and mQ are just slopes rise over run.
    public double mQ;

    public Vector2 CartesianP;

    public Vector2 Origin;

    public Vector2 LSP; // LSP and LSQ are just (Line Segments) who begin at P and end at Q
    public Vector2 LSQ;

    public Vector2 RVP; // relative Vector P is a ray with some magnitude and direction from our mouse.

    public Vector2 RVQ; // relative Vector Q is a ray with some magnitude and direction from our mouse.


   // Is the position where the line cross.  where opp and hyp meet maybe the Brim Apex
    public Vector2 PT;  // line Segment  P to Q with T perpendicular to P and Q


    public Vector2 C;  // C is the standard 90 degree Coefficient position that Lies on the Line Segment of P to Q
    // it is Relative to PT  in That Climb is also the standard Climb Point that rises to the Apex PT
    // The magnitude from PT to C is labeled as our Height. on most triangles.
    // C can be thought as a tangent.  of the line thought P through Q.
    // for the 90 degrees that  normally represent the height of our triangle.


    // Thus Pythagoren Theorem can be used to verify the results.
    //            A^2 + B^2 = C^2
    //            C^2 - A^2 = B^2
    //            C^2 - B^2 = A^2

    // Note The user will move the mouse position to direct a ray
    // Note When user has ray pointing in the direction just press a mouse button.
    // Note And again for the second ray direction...
    // Note

    public double magnitudeOfAToB;
    public double magnitudeOfDToB;

    public MCont() {}
}
