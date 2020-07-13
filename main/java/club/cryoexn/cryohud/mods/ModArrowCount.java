package club.cryoexn.cryohud.mods;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import club.cryoexn.cryohud.gui.ScreenPosition;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class ModArrowCount extends ModDraggable {

	private ScreenPosition pos;
	private boolean enabled;
	private boolean background;
	
	@Override
	public int getWidth() {
		return 16;
	}

	@Override
	public int getHeight() {
		return 16;
	}

	@Override
	public void render() {
		if(this.pos != null) {
			int arrows = 0;
			
			if(background)
				GuiScreen.drawRect(pos.getAbsoluteX(), pos.getAbsoluteY(), this.getWidth() + pos.getAbsoluteX() + 1, this.getHeight() + pos.getAbsoluteY() + 1, 0x4f000000);
			
			for(ItemStack item : mc.thePlayer.inventory.mainInventory) {
				if(item != null) {
					if(item.getItem() == Items.arrow)
						arrows += item.stackSize;
							
				}
			}
				
			// Display the players held item.
			renderArrowStack(arrows, pos.getAbsoluteX(), pos.getAbsoluteY());
			
 		} else {
 			// load saved ScreenPosition.
 			this.pos = load();
 		}
	}

	@Override
	public void renderDummy() {
		if(this.pos != null) {

			renderArrowStack(64, pos.getAbsoluteX(), pos.getAbsoluteY());
			
 		} else {
 			// load saved ScreenPosition.
 			this.pos = load();
 		}
	}
	
	private void renderArrowStack(int size, int x, int y) {
		ItemStack arrows = new ItemStack(Items.arrow, size);
		
		mc.getRenderItem().renderItemAndEffectIntoGUI(arrows, x, y);
		mc.getRenderItem().renderItemOverlays(mc.fontRendererObj, arrows, x, y);
	}

	@Override
	public ScreenPosition getScreenPosition() {
		if(this.pos == null) {
			this.pos = this.load();
		}
		
		return this.pos;
	}

	@Override
	public void save() {
		File config = new File("config/hud/arrows.txt");
		
		try {
			
			BufferedWriter bw = new BufferedWriter(new FileWriter(config));
			bw.write(this.enabled + "," + this.pos.getAbsoluteX() + "," + this.pos.getAbsoluteY() + "," + this.background);
			
			bw.flush();
			bw.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public ScreenPosition load() {
		File config = new File("config/hud/arrows.txt");

		try {
			
			BufferedReader br = new BufferedReader(new FileReader(config));
			String[] args = br.readLine().split(",");
			br.close();
			
			this.enabled = Boolean.parseBoolean(args[0]);
			this.background = Boolean.parseBoolean(args[3]);
			
			return new ScreenPosition(Integer.parseInt(args[1]), Integer.parseInt(args[2]));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// Default Position in case of Exception.
		return new ScreenPosition(1,1);
	}
	
	@Override
	public void setEnabled(boolean isEnabled) {
		this.enabled = isEnabled;
	}

	@Override
	public boolean isEnabled() {
		return this.enabled;
	}
	
	@Override
	public void setBackgroundOn(boolean background) {
		this.background = background;
	}		
	
	// Not used in this Mod.
	@Override
	public void setColor(int color) {}
	
	// Not used in this Mod.
	@Override
	public void setLabel(String label) {}
	
	@Override
	public void setScreenPosition(ScreenPosition sp) {
		if(sp != null)
			this.pos = sp;
	}
}
