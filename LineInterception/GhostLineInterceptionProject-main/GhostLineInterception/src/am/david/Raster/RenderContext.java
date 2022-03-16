package am.david.Raster;

public class RenderContext extends Bitmap {

    public RenderContext(int width, int height){
        super(width, height);
    }
    public void DrawPixel(int x, int y, byte g, byte b, byte r)
    {
        super.DrawPixel(x, y, g, b, r);
    }
}
