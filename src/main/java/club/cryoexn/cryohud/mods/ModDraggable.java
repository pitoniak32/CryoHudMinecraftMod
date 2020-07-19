package club.cryoexn.cryohud.mods;

import club.cryoexn.cryohud.gui.ScreenPosition;

public abstract class ModDraggable extends Mod {

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
	public abstract void render();
	public abstract void renderDummy();
	public abstract ScreenPosition getScreenPosition();
	public abstract int getHeight();
	public abstract int getWidth();
	public abstract void save();
	public abstract ScreenPosition load();
	public abstract void setScreenPosition(ScreenPosition sp);
}
