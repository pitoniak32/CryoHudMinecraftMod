package club.cryoexn.cryohud.mods;

import club.cryoexn.cryohud.gui.IHudItem;
import club.cryoexn.cryohud.gui.ScreenPosition;

public abstract class ModDraggable extends Mod implements IHudItem {

	public final int getLineOffset(ScreenPosition pos, int lineNum) {
		return pos.getAbsoluteY() + getLineOffset(lineNum);
	}
	 
	private int getLineOffset(int lineNum) {
		return (font.FONT_HEIGHT + 3) * lineNum; 
	}
	
	public abstract String getModName();
	public abstract void setColor(int color);
	public abstract int getColor();
	public abstract void setLabel(String label);
	public abstract void setBackgroundOn(boolean background);
	public abstract boolean isBackgroundOn();
}
