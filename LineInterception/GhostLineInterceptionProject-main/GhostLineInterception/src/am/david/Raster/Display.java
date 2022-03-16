package am.david.Raster;

import am.david.game.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

public class Display {
    private static Game game;

    private final RenderContext byteBitmapBuffer;
    private final BufferedImage bufferedImage;
    private final byte[] imageBytesRGBComponents;
    private final BufferStrategy strategy;
    private Graphics graphics;
    private Color color;

    public void drawLine(int a, int b, int c, int d) {
        graphics.drawLine(a, b, c, d);
    }

    public void DrawPixel(int lWidth, int lHeight, byte r, byte g, byte b)
    {
        byteBitmapBuffer.DrawPixel(lWidth, lHeight, g, b, r);
    }

    public Display(Game game, int width, int height, String title)
    {
        Display.game = game;
        JTextField nTitle = new JTextField(title);
        JFrame localJFrame = game.getFrame();
        localJFrame.setTitle(nTitle.getText());
        localJFrame.setLocation(100, 100);
        localJFrame.requestFocus();
        game.setFrame(localJFrame);

        Canvas localCanvas = Display.game.getCanvas();
        localCanvas.requestFocus();
        localCanvas.repaint();
        game.setCanvas(localCanvas);

        byteBitmapBuffer = new RenderContext(width, height);
        bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        imageBytesRGBComponents = ((DataBufferByte) bufferedImage.getRaster().getDataBuffer()).getData();

        byteBitmapBuffer.clear((byte)0x00);
        int lWidth = (int)(localCanvas.getWidth() / 2);
        int lHeight= (int)(localCanvas.getHeight() / 2);
        byteBitmapBuffer.DrawPixel(lWidth, lHeight, (byte)0xff, (byte)0x00, (byte)0xff);

        localCanvas.createBufferStrategy(1);
        if(localCanvas.getBufferStrategy() == null) System.exit(1);

        strategy = localCanvas.getBufferStrategy();
        game.setCanvas(localCanvas);
        graphics = strategy.getDrawGraphics();
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setGraphics(Graphics graphics) {
        this.graphics = graphics;
    }

    public BufferStrategy getStrategy() {
        return strategy;
    }

    public BufferedImage getBufferedImage() {
        return bufferedImage;
    }

    public int GetBitmapBufferWidth() { return byteBitmapBuffer.getWidth(); }

    public int GetBitmapBufferHeight() { return  byteBitmapBuffer.getHeight(); }

    public void SwapBuffers(Point point, Point origin)
    {
        byteBitmapBuffer.copyToByteArray(imageBytesRGBComponents);
        graphics.drawImage(bufferedImage, 0, 0, byteBitmapBuffer.getWidth(), byteBitmapBuffer.getHeight(), null);

        Point forecast = new Point();
        forecast.x = point.x % origin.x;
        forecast.y = point.y % origin.y;
        if(point.x > origin.x)
            point.x = origin.x - forecast.x;
        if(point.x > origin.x)
            point.x = origin.y - forecast.y;

        int screen_Center_Width = origin.x;
        int screen_Center_Height = origin.y;

        graphics.setColor(Color.WHITE);
        int startX = point.x;
        int startY = point.y;
        int deltaX = (screen_Center_Width - startX);
        int deltaY = (screen_Center_Height - startY);
        int endRectX = deltaX * 2;
        int endRectY = deltaY * 2;

        graphics.fillRect(startX, startY, Math.abs(endRectX), Math.abs(endRectY));
        graphics.setColor(Color.RED);
        graphics.drawOval(startX, startY, Math.abs(endRectX), Math.abs(endRectY));

        graphics.setColor(Color.GREEN);
        graphics.drawLine(startX, startY, screen_Center_Width + deltaX, screen_Center_Height + deltaY);

        graphics.drawLine(screen_Center_Width + deltaX, startY, startX, screen_Center_Height + deltaY);

        strategy.show();;
    }

    public void SwapBuffers()
    {
        byteBitmapBuffer.copyToByteArray(imageBytesRGBComponents);
        graphics.drawImage(bufferedImage,0, 0, byteBitmapBuffer.getWidth(), byteBitmapBuffer.getHeight(), null);
        strategy.show();
    }

    public  void SwapBuffers(Graphics graphics)
    {
        strategy.show();
    }

    public void SurfaceData()
    {
        byteBitmapBuffer.copyToByteArray(imageBytesRGBComponents);
        graphics.drawImage(bufferedImage, 0, 0, byteBitmapBuffer.getWidth(), byteBitmapBuffer.getHeight(), null);
    }
}

