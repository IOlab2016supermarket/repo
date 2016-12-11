package gui;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Window;

public class Common {
  
  /**
   * Show frame on the center of screen 
   */
  public static void showFrame(Window frame) {
    int screenWidth = 0, screenHeight = 0;
    
    GraphicsDevice[] screenDevices = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices();
        for (GraphicsDevice graphicsDevice : screenDevices) {
            screenWidth = graphicsDevice.getDefaultConfiguration().getBounds().width;
            screenHeight = graphicsDevice.getDefaultConfiguration().getBounds().height;
        }
    
        Rectangle r = frame.getBounds();
    
    int frameWidth = r.width, frameHeight = r.height;
    int posX = (screenWidth - frameWidth) / 2;
    int posY = (screenHeight - frameHeight) / 2 - 20;
    
    frame.setPreferredSize(new Dimension(frameWidth, frameHeight));
    frame.setBounds(posX, posY, r.width, r.height);
    
    frame.setVisible(true);
  }

}
