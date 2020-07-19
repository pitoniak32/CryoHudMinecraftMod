package club.cryoexn.cryohud.mods;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import club.cryoexn.cryohud.gui.ScreenPosition;
import net.minecraft.item.ItemStack;

public class ModArmorBoots extends ModDraggable {
	
	private String modName;
	private ScreenPosition pos;
	private boolean enabled;
	
	public ModArmorBoots() {
		this.modName = "Boots Hud";
	}
	
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
			
			// Display the players helmet.
			renderItemStack(mc.thePlayer.inventory.armorInventory[0], pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1);
 		} else {
 			// load saved ScreenPosition.
 			this.pos = load();
 		}
	}
	
	@Override
	public void renderDummy() {
		if(this.pos != null) {
			// Display the players helmet.
			renderItemStack(mc.thePlayer.inventory.armorInventory[0], pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1);
 		} else {
 			// load saved ScreenPosition.
 			this.pos = load();
 		}
	}
	
	private void renderItemStack(ItemStack item, int x, int y) {
		mc.getRenderItem().renderItemAndEffectIntoGUI(item, x, y);
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
		File config = new File("config/hud/armorBoots.txt");
		
		try {
			
			BufferedWriter bw = new BufferedWriter(new FileWriter(config));
			bw.write(this.enabled + "," + this.pos.getAbsoluteX() + "," + this.pos.getAbsoluteY());
			
			bw.flush();
			bw.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//Not Used for this Mod
	@Override
	public ScreenPosition load() {
		File config = new File("config/hud/armorBoots.txt");

		try {
			
			BufferedReader br = new BufferedReader(new FileReader(config));
			String[] args = br.readLine().split(",");
			br.close();
			
			this.enabled = Boolean.parseBoolean(args[0]);
			
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
	
	// Not used in this Mod.
	@Override
	public void setBackgroundOn(boolean background) {}		
	
	@Override
	public boolean isBackgroundOn() {
		return false;
	}
	
	// Not used in this Mod.
	@Override
	public void setColor(int color) {}	
	
	@Override
	public int getColor() {
		return 0;
	}

	// Not used in this Mod.
	@Override
	public void setLabel(String label) {}

	@Override
	public void setScreenPosition(ScreenPosition sp) {
		if(sp != null)
			this.pos = sp;
	}

	@Override
	public String getModName() {
		return this.modName;
	}		
	
}
