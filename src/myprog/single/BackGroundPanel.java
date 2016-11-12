package myprog.single;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;

class BackGroundPanel extends Panel {
    Image backGround;

    BackGroundPanel() {
         super();
    }

    public void paint(Graphics g) {

         // get the size of this panel (which is the size of the applet),
         // and draw the image
         g.drawImage(getBackGroundImage(), 0, 0,
             (int)getBounds().getWidth(), (int)getBounds().getHeight(), this);
    }

    public void setBackGroundImage(Image backGround) {
         this.backGround = backGround;    
    }

    private Image getBackGroundImage() {
         return backGround;    
    }
}
