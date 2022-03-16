package am.david.Raster;

import java.util.Arrays;

public class Bitmap {
    private final int width;
    private final int height;

    public int getWidth() { return width; }
    public int getHeight() { return height; }

    private byte byte_RGB_components[];

    Bitmap(int width, int height)
    {
        this.width = width;
        this.height = height;
        byte_RGB_components = new byte[width * height * 3];
    }

    public void clear(byte shade) {
        Arrays.fill(byte_RGB_components, shade);
    }

    public void DrawPixel(int x, int y, byte g, byte b, byte r)
    {
        int index = 3*(width * y + x);
        if(byte_RGB_components != null)
        {
            if(index < byte_RGB_components.length -3) {
                byte_RGB_components[index] = b;
                byte_RGB_components[index] = g;
                byte_RGB_components[index] = r;
            }
        }
    }

    public synchronized void copyToByteArray(byte[] byte_dest_rgb)
    {
        for(int i = 0; i < width * height; i++) {
            byte_dest_rgb[3*i] = byte_RGB_components[3*i];
            byte_dest_rgb[3*i + 1] = byte_RGB_components[3*i + 1];
            byte_dest_rgb[3*i + 2] = byte_RGB_components[3*i + 2];
        }
    }


}
