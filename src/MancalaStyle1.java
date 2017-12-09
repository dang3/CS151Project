import java.awt.Color;

/**
 * First Style of Mancala
 * @author Dennis Dang, Vera Wong, Marissa Xiong
 * Ver 1.1
 * 12/9/2017
 *
 */
public class MancalaStyle1 implements Style {
	/**
	 * returns the color of the board
	 * @return color of board
	 */
	public Color colorOfBoard(){
		Color c = Color.orange;
		
		return c;
	}
	/**
	 * returns color of outline of pockets
	 * @return color of pockets/pits
	 */
	public Color colorOfPockets() {
		Color c = Color.magenta;
		
		return c;
	}
}
