import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
/**
 * MouseAdapter Class 
 * 
 *
 */
public abstract class MouseAdapter implements MouseListener {
	/**
	 * when a mouse clicks on a space in frame
	 */
	public void mouseClicked(MouseEvent e){}
	/**
	 * detects when a mouse is pressed on a space in the frame
	 */
	public void mousePressed(MouseEvent e) {}
	/**
	 * detects when a mouse is released on a space in the frame
	 */
	public void mouseReleased(MouseEvent e) {}
	/**
	 * detects when a mouse is entered into the frame
	 */
	public void mouseEntered(MouseEvent e) {}
	/**
	 * detects when a mouse is exited out of the frame
	 */
	public void mouseExited(MouseEvent e) {}
}
