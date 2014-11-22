/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package gui.ws.prak.auf4;

/**
 *
 * @author Jakub Juszczak <juszczak.j@googlemail.com>
 */
public class Stadium extends Diagramm {
    

    public int beginn;
    public double dauer;
    public int position;
    public Object farbe;
    public double xPos, yPos, breite, hoehe;
    
    public Stadium(Double dauer, Object farbe, 
                        double breite, double hoehe, double yPos, 
                        int position) {
         
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
    
    /**
     * Testet ob die Mausposition im Bereich eines Stadiums ist.
     * 
     * @param x X Position
     * @param y Y Position
     * @return true / false ob die Maus in einem Stadium Bereich ist.
     */
    public boolean istImBreich(int x, int y) {
        boolean istInStadiumX = (x >= this.xPos && (x <= (this.xPos + this.breite)));
        boolean istInStadiumY = (y >= this.yPos && (y <= (this.yPos + this.hoehe)));
        
        return (istInStadiumX && istInStadiumY);
    }

}
