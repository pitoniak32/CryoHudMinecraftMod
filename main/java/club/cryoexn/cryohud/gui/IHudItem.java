package club.cryoexn.cryohud.gui;

public interface IHudItem extends IHudFile {
	
	int getWidth();
	int getHeight();
	
	void render();
	
	void renderDummy();
	
	public boolean isEnabled();
	
	public ScreenPosition getScreenPosition();
	
	public void setScreenPosition(ScreenPosition sp);
}
