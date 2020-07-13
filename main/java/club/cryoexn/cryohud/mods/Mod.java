package club.cryoexn.cryohud.mods;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraftforge.common.MinecraftForge;

public class Mod {
	private boolean isEnabled = true;
	
	protected final Minecraft mc;
	protected final FontRenderer font;
	//protected final Client client;
	
	public Mod() {
		this.mc = Minecraft.getMinecraft();
		this.font = this.mc.fontRendererObj;
		
		setEnabled(isEnabled);
	}
	
	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
		if(isEnabled) {
			MinecraftForge.EVENT_BUS.register(this);
		} else {
			MinecraftForge.EVENT_BUS.unregister(this);
		}
	}
	
	public boolean isEnabled() {
		return isEnabled;
	}
}
