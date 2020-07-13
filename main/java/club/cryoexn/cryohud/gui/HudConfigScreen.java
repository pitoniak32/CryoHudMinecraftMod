package club.cryoexn.cryohud.gui;

import java.io.IOException;
import java.util.HashMap;

import org.lwjgl.input.Keyboard;

import club.cryoexn.cryohud.mods.ModDraggable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;

public class HudConfigScreen extends GuiScreen {
	
	private static Minecraft mc = Minecraft.getMinecraft();
	private HashMap<String, ModDraggable> hudItems;
	private ModDraggable selMod;
	private int prevX, prevY;
	
	public HudConfigScreen(HashMap<String, ModDraggable> hudItems) {
		this.hudItems = hudItems;
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		
		// Draw black transparent background.
		super.drawDefaultBackground();
		
		ScaledResolution sr = new ScaledResolution(mc);
		
		// Draw x and y axis to help with repositioning.
		this.drawVerticalLine(sr.getScaledWidth() / 2, 0, sr.getScaledHeight() - 20, 0x4f666666);
		this.drawHorizontalLine(0, sr.getScaledWidth(), sr.getScaledHeight() / 2, 0x4f666666);
		
		// Backup the zLevel.
		final float zBackup = this.zLevel;
		this.zLevel = 200;
		
		// Render the dummy of each Hud component.
		for(String modKey : this.hudItems.keySet()) {
			
			ModDraggable mod = this.hudItems.get(modKey);
			
			if(mod.isEnabled()) {
				ScreenPosition pos = mod.getScreenPosition();
				
				mod.renderDummy();
				
				int height = mod.getHeight();
				int width  = mod.getWidth();
					
				this.drawHollowRect(pos.getAbsoluteX() - 2, pos.getAbsoluteY() - 2, width + 4, height + 3, 0x4fffffff);
			}
		}
		
		this.zLevel = zBackup;
	}
	
	private void drawHollowRect(int x, int y, int w, int h, int c) {
		this.drawHorizontalLine(x, x + w, y, c);
		this.drawHorizontalLine(x,  x + w, y + h , c);
		
		this.drawVerticalLine(x, y, y + h, c);
		this.drawVerticalLine(x + w, y, y + h, c);
	}
	
	@Override
	protected void keyTyped(char typedChar, int keyCode) throws IOException {
		
		if(keyCode == Keyboard.KEY_ESCAPE) {
			for(String modKey : this.hudItems.keySet()) {
				this.hudItems.get(modKey).save();
			}
			
			mc.displayGuiScreen(null);
			mc.updateDisplay();
		}
	}
	
	@Override
	protected void mouseClickMove(int x, int y, int button, long time) {
		if(this.selMod != null) {
			if(this.selMod.isEnabled())
				moveSelectedRenderBy(x - prevX, y - prevY);
		}
		
		this.prevX = x;
		this.prevY = y;
	}

	private void moveSelectedRenderBy(int offSetX, int offSetY) {
		ModDraggable item = this.selMod;
		
		ScreenPosition pos = item.getScreenPosition();
		
		pos.setAbsolute(pos.getAbsoluteX() + offSetX, pos.getAbsoluteY() + offSetY);
		
		adjustBounds(item, pos);
	}
	
	@Override
	public void onGuiClosed() {
		for(String modKey : this.hudItems.keySet()) {
			this.hudItems.get(modKey).save();
		}
	}
	
	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}
	
	private void adjustBounds(IHudItem item, ScreenPosition pos) {
		ScaledResolution res = new ScaledResolution(Minecraft.getMinecraft());
		
		int screenWidth = res.getScaledWidth();
		int screenHeight = res.getScaledHeight();
		
		int absoluteX = Math.max(0,  Math.min(pos.getAbsoluteX(), Math.max(screenWidth - item.getWidth(), 0)));
		int absoluteY = Math.max(0,  Math.min(pos.getAbsoluteY(), Math.max(screenHeight - item.getHeight(), 0)));
		
		pos.setAbsolute(absoluteX, absoluteY);
	}

	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
		this.prevX = mouseX;
		this.prevY = mouseY;
		
		loadMouseOver(mouseX, mouseY);
	}
	
	private void loadMouseOver(int mouseX, int mouseY) {
		this.selMod = getMousedOverItem(mouseX, mouseY);
	}
	
	private ModDraggable getMousedOverItem(int mouseX, int mouseY) {
		for(String modKey : this.hudItems.keySet()) {
		
			ModDraggable mod = this.hudItems.get(modKey);
			
			ScreenPosition posMod = this.hudItems.get(modKey).getScreenPosition();
			
			int absoluteX = posMod.getAbsoluteX();
			int absoluteY = posMod.getAbsoluteY();
			
			if(mouseX >= absoluteX && mouseX <= absoluteX + mod.getWidth()) {
				if(mouseY >= absoluteY && mouseY <= absoluteY + mod.getHeight()) {
					return mod;
				}
			}
		}
		
		return null;
	}
}
