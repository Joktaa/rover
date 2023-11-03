package org.ynov.MissionControl;
import org.ynov.Commandes.Orientation;
import org.ynov.Commandes.Position;
import org.ynov.Topologie.Planet;

import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Carte {

    public Carte(Planet planet) {
        this.UI(planet);
    }

    public void UI(Planet planet){
        try{
            JFrame f = new JFrame("Carte de Mars");
            JPanel panel = new JPanel();
            panel.setBounds(0,0,10,10);

            BufferedImage img = ImageIO.read(new File("src/main/resources/img/sol_mars.png"));
            JLabel pic = new JLabel(new ImageIcon(img));
            panel.add(pic);
            f.add(panel);
            f.setSize(planet.x_size,planet.y_size);
            f.setLayout(null);
            f.setVisible(true);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
