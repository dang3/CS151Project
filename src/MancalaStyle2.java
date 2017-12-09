import java.awt.Color;

/**
 * First Style of Mancala
 * @author Dennis Dang, Vera Wong, Marissa Xiong
 * Ver 1.1
 * 12/9/2017
 *
 */
public class MancalaStyle2 implements Style {
	/**
	 * returns the color of the board
	 * @return color of board
	 */
	public Color colorOfBoard(){
		return Color.blue;
	}
	/**
	 * returns the color of the pockets
	 * @return color of pockets
	 */
	public Color colorOfPockets() {
		return Color.black;
	}
}
