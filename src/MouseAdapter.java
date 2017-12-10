import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
/**
 * MouseAdapter Class 
 * 
 *
 */
public abstract class MouseAdapter implements MouseListener {

	public void mouseClicked(MouseEvent e){}
	/**
	 * detects when a mouse clicks on a space in the frame
	 */
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
}
