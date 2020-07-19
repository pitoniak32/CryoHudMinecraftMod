package club.cryoexn.cryohud.gui;

/**
 * Modified ScreenPositon class
 *
 * @author OrangeMarshall (original)
 * @author David Pitoniak (editied)
 */
public class ScreenPosition {

	private double x, y;
	
	public ScreenPosition(double x, double y) {
		setRelative(x, y);
	}
	
	public ScreenPosition(int x, int y) {
		setAbsolute(x, y);
	}
	
	public int getAbsoluteX() {
		return (int)(this.x);
	}
	
	public int getAbsoluteY() {
		return (int)(this.y);
	}
	
	public double getRelativeX() {
		return x;
	}
	
	public double getRelativeY() {
		return y;
	}
	
	public void setRelative(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public void setAbsolute(int x, int y) {
		this.x = (double)x;
		this.y = (double)y;
	}

} // end ScreenPosition.
