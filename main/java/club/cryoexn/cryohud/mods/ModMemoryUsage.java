package club.cryoexn.cryohud.mods;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import club.cryoexn.cryohud.gui.ScreenPosition;
import net.minecraft.client.gui.GuiScreen;

public class ModMemoryUsage extends ModDraggable {
	
	private ScreenPosition pos;
	private int color = 0xffffff;
	private int dummyColor = 0xff0000;
	private String label = "Usage ";
	private boolean background = true;
	private boolean enabled;
	
	@Override
	public int getWidth() {
		double total = Long.parseLong(mc.getPlayerUsageSnooper().getCurrentStats().get("memory_total")) * Math.pow(10, -6);
 		double max   = Long.parseLong(mc.getPlayerUsageSnooper().getCurrentStats().get("memory_max")) * Math.pow(10, -6);
 		double free  = Long.parseLong(mc.getPlayerUsageSnooper().getCurrentStats().get("memory_free")) * Math.pow(10, -6);
 		
		String mem = String.format("%.0f %%", ((total - free) / max) * 100);
		
		return font.getStringWidth(this.label + mem);
	}

	@Override
	public int getHeight() {
		return font.FONT_HEIGHT;
	}

	@Override
	public void render() {
		if(this.pos != null) {
 			if(background)
 				GuiScreen.drawRect(this.pos.getAbsoluteX() - 1, this.pos.getAbsoluteY() - 1, this.getWidth() + this.pos.getAbsoluteX() + 2, this.getHeight() + this.pos.getAbsoluteY() + 1, 0x4f000000);
 			
 			double total = Long.parseLong(mc.getPlayerUsageSnooper().getCurrentStats().get("memory_total")) * Math.pow(10, -6);
 	 		double max   = Long.parseLong(mc.getPlayerUsageSnooper().getCurrentStats().get("memory_max")) * Math.pow(10, -6);
 	 		double free  = Long.parseLong(mc.getPlayerUsageSnooper().getCurrentStats().get("memory_free")) * Math.pow(10, -6);
 	 		
 	 		String mem = String.format(this.label + "%.0f %%", ((total - free) / max) * 100);
 	 		
 	 		mc.fontRendererObj.drawString(mem, this.pos.getAbsoluteX() + 1, this.pos.getAbsoluteY() + 1, this.color, true);
 	 		
 		} else {
 			this.pos = this.load();
 		}	
	}

	@Override
	public void renderDummy() {
		if(this.pos != null) {
			double total = Long.parseLong(mc.getPlayerUsageSnooper().getCurrentStats().get("memory_total")) * Math.pow(10, -6);
	 		double max   = Long.parseLong(mc.getPlayerUsageSnooper().getCurrentStats().get("memory_max")) * Math.pow(10, -6);
	 		double free  = Long.parseLong(mc.getPlayerUsageSnooper().getCurrentStats().get("memory_free")) * Math.pow(10, -6);
	 		
	 		String mem = String.format(this.label + "%.0f %%", ((total - free) / max) * 100);
	 		
	 		mc.fontRendererObj.drawString(mem, this.pos.getAbsoluteX() + 1, this.pos.getAbsoluteY() + 1, this.dummyColor, true);
 		} else {
 			this.pos = this.load();
 		}
	}

	@Override
	public ScreenPosition getScreenPosition() {
		return this.pos;
	}

	@Override
	public void setScreenPosition(ScreenPosition sp) {
		this.pos = sp;
	}

	@Override
	public void save() {
		File config = new File("config/hud/memUsage.txt");
		
		try {
			
			BufferedWriter bw = new BufferedWriter(new FileWriter(config));
			bw.write(this.enabled + "," + this.pos.getAbsoluteX() + "," + this.pos.getAbsoluteY() + "," + Integer.toHexString(this.color) + "," + this.label + "," + this.background);
			
			bw.flush();
			bw.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public ScreenPosition load() {
		File config = new File("config/hud/memUsage.txt");

		try {
			
			BufferedReader br = new BufferedReader(new FileReader(config));
			String[] args = br.readLine().split(",");
			br.close();
			
			this.enabled = Boolean.parseBoolean(args[0]);
			this.color = Integer.parseInt(args[3], 16);
			this.label = args[4];
			this.background = Boolean.parseBoolean(args[5]);
			
			return new ScreenPosition(Integer.parseInt(args[1]), Integer.parseInt(args[2]));
			
		} catch (IOException e) {
			e.printStackTrace();
			
			// Default Position in case of Exception.
			return new ScreenPosition(1,1);	
		}
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
	public void setColor(int color) {
		this.color = color;
	}

	@Override
	public void setLabel(String label) {
		this.label = label;
	}

	@Override
	public void setBackgroundOn(boolean background) {
		this.background = background;
	}
	
}// end ModMemortUsage.
