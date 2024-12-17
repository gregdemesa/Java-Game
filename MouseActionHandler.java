import java.awt.*;  
import java.awt.event.MouseEvent;  
import java.awt.event.MouseMotionListener;  

public class MouseActionHandler implements MouseMotionListener{

	int mouseX, mouseY;
	String textto = "";

	public MouseActionHandler(){

	}

	public void mouseDragged(MouseEvent e) {  

	}  
	
	public void mouseMoved(MouseEvent e) {

		mouseX=(int)e.getPoint().getX();
		mouseY=(int)e.getPoint().getY();
	
	}  

	public void currentCoordinates() {
		System.out.println(textto="X: "+mouseX+"Y: "+mouseY);
	}

	public int mouseX(){
		return mouseX;
	}

	public int mouseY(){
		return mouseY;
	}

}

//Maybe use mouse dragged instead?? So as to solve the constant rotation problem