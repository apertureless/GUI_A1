/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package gui.aufgabe1;

/**
 *
 * @author Jakub Juszczak <juszczak.j@googlemail.com>
 */
public class Stadium extends Diagramm {
    
    public String name;
    public int beginn;
    public double dauer;
    public int position;
    public Object farbe;
    public double xPos, yPos, breite, hoehe;
    
      public Stadium(String name, Double dauer, Object farbe, 
                        double breite, double hoehe, double yPos, 
                        int position) {
          
        this.name = name;
        this.dauer = dauer;
        this.farbe = farbe;
        this.breite = breite;
        this.hoehe = hoehe;
        this.yPos = yPos;
        this.position = position;
    }
    
    public void setBeginn(int beginn) {
        this.beginn = beginn;
    }
    
    public void setXPos(double x) {
        this.xPos = x;
    }
    
    public double getXPos() {
        return this.xPos;
    }
    
    public double getYPos() {
        return this.yPos;
    }
    
    public void setFarbe(Object farbe) {
        this.farbe = farbe;
    }
    
    public boolean istImBreich(int x, int y) {
        boolean istInStadiumX = (x >= this.xPos && (x <= (this.xPos + this.breite)));
        boolean istInStadiumY = (y >= this.yPos && (y <= (this.yPos + this.hoehe)));
        
        return (istInStadiumY && istInStadiumY);
    }

}
