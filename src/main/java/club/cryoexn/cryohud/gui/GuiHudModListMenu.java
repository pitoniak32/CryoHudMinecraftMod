package club.cryoexn.cryohud.gui;

import java.io.IOException;
import java.util.ArrayList;

import org.lwjgl.input.Keyboard;

import club.cryoexn.cryohud.HudManager;
import club.cryoexn.cryohud.mods.ModArmorBoots;
import club.cryoexn.cryohud.mods.ModArmorChest;
import club.cryoexn.cryohud.mods.ModArmorHelm;
import club.cryoexn.cryohud.mods.ModArmorPants;
import club.cryoexn.cryohud.mods.ModDraggable;
import club.cryoexn.cryohud.mods.ModHeldItem;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;

/**
 * Graphical User Interface for list of active mods. 
 * Allows user to enable and disable hud mods and access the settings menu for applicable mods.
 * 
 * @author David Pitoniak (Cryoexn)
 */
public class GuiHudModListMenu extends GuiScreen {
    
    // local constants
	private final int COLOR_ENABLED = 0x9dff7d;
	private final int COLOR_DISABLED = 0xff837d;	
	private final int BTN_HEIGHT = 20;
	private final int BTN_WIDTH = 150;
	private final int BTN_SML_WIDTH = 75;
	
	private ArrayList<ModDraggable> mods;
    
    // Default Constructor    
	public GuiHudModListMenu(ArrayList<ModDraggable> mods) {
		this.mods = mods;
	}

    @Override
	public void initGui() {
		int posIndex = 0;
		int idIndex  = 0;
		
		ScaledResolution sr = new ScaledResolution(mc);
		ScreenPosition posBtns = new ScreenPosition((sr.getScaledWidth() / 2) - (BTN_WIDTH + BTN_SML_WIDTH) / 2, (sr.getScaledHeight() / 4) - 10 - BTN_HEIGHT / 2 );
		
		for(ModDraggable mod : this.mods) {
			GuiButton btn;
			
			btn = new GuiButton(idIndex, posBtns.getAbsoluteX(), posBtns.getAbsoluteY() + posIndex * BTN_HEIGHT, BTN_WIDTH, BTN_HEIGHT, mod.getModName());
			
			if((mod instanceof ModArmorHelm) || (mod instanceof ModArmorChest)|| (mod instanceof ModArmorPants) || (mod instanceof ModArmorBoots) || (mod instanceof ModHeldItem)) {
				btn.enabled = false;
			}
			
			this.buttonList.add(btn);
			
			idIndex++;
			
			btn = new GuiButton(idIndex, posBtns.getAbsoluteX() + BTN_WIDTH, posBtns.getAbsoluteY() + posIndex * BTN_HEIGHT, BTN_SML_WIDTH, BTN_HEIGHT, mod.isEnabled() ? "Enabled" : "Disabled");
			btn.packedFGColour = mod.isEnabled() ? COLOR_ENABLED : COLOR_DISABLED;
			
			this.buttonList.add(btn);
			
			idIndex++;
			
			posIndex++;
		}
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		super.drawDefaultBackground();
		super.drawScreen(mouseX, mouseY, partialTicks);
	}

	@Override
	protected void keyTyped(char typedChar, int keyCode) throws IOException {

		if(keyCode == Keyboard.KEY_ESCAPE) {
			mc.displayGuiScreen(new GuiHudMainMenu());
			mc.updateDisplay();
		}
	}

	@Override
	public void updateScreen() {
		super.updateScreen();
	}

	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}

	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		ModDraggable mod;
		
		if(button.id == 0) {
			mod = this.mods.get(HudManager.HUD_QUEST_TIMER);
			
			mc.displayGuiScreen(new GuiHudModSettingsMenu(mod));
			mc.updateDisplay();
			
		} else if(button.id == 1) {
			mod = this.mods.get(HudManager.HUD_QUEST_TIMER);
			
			if(!mod.isEnabled()) {
				button.displayString = "Enabled";
				button.packedFGColour = COLOR_ENABLED;
				mod.setEnabled(true);
			} else {
				button.displayString = "Disabled";
				button.packedFGColour = COLOR_DISABLED;
				mod.setEnabled(false);
			}
		} else if(button.id == 2) {
			mod = this.mods.get(HudManager.HUD_MEM_USAGE);
			mc.displayGuiScreen(new GuiHudModSettingsMenu(mod));
			mc.updateDisplay();
		} else if(button.id == 3) {
			mod = this.mods.get(HudManager.HUD_MEM_USAGE);
			if(!mod.isEnabled()) {
				button.displayString = "Enabled";
				button.packedFGColour = COLOR_ENABLED;
				mod.setEnabled(true);
			} else {
				button.displayString = "Disabled";
				button.packedFGColour = COLOR_DISABLED;
				mod.setEnabled(false);
			}
		} else if(button.id == 4) {
			mod = this.mods.get(HudManager.HUD_FPS_COUNTER);
			mc.displayGuiScreen(new GuiHudModSettingsMenu(mod));
			mc.updateDisplay();
		} else if(button.id == 5) {
			mod = this.mods.get(HudManager.HUD_FPS_COUNTER);
			if(!mod.isEnabled()) {
				button.displayString = "Enabled";
				button.packedFGColour = COLOR_ENABLED;
				mod.setEnabled(true);
			} else {
				button.displayString = "Disabled";
				button.packedFGColour = COLOR_DISABLED;
				mod.setEnabled(false);
			}
		} else if(button.id == 6) {
			mod = this.mods.get(HudManager.HUD_COORDS);
			mc.displayGuiScreen(new GuiHudModSettingsMenu(mod));
			mc.updateDisplay();
		} else if(button.id == 7) {
			mod = this.mods.get(HudManager.HUD_COORDS);
			if(!mod.isEnabled()) {	
				button.displayString = "Enabled";
				button.packedFGColour = COLOR_ENABLED;
				mod.setEnabled(true);
			} else {
				button.displayString = "Disabled";
				button.packedFGColour = COLOR_DISABLED;
				mod.setEnabled(false);
			}
		} else if(button.id == 8) {
			mod = this.mods.get(HudManager.HUD_DIR_FACING);
			mc.displayGuiScreen(new GuiHudModSettingsMenu(mod));
			mc.updateDisplay();
		} else if(button.id == 9) {
			mod = this.mods.get(HudManager.HUD_DIR_FACING);
			if(!mod.isEnabled()) {	
				button.displayString = "Enabled";
				button.packedFGColour = COLOR_ENABLED;
				mod.setEnabled(true);
			} else {
				button.displayString = "Disabled";
				button.packedFGColour = COLOR_DISABLED;
				mod.setEnabled(false);
			}
		} else if(button.id == 10) {
			mod = this.mods.get(HudManager.HUD_ARMOR_HELM);
			mc.displayGuiScreen(new GuiHudModSettingsMenu(mod));
			mc.updateDisplay();
		} else if(button.id == 11) {
			mod = this.mods.get(HudManager.HUD_ARMOR_HELM);
			if(!mod.isEnabled()) {	
				button.displayString = "Enabled";
				button.packedFGColour = COLOR_ENABLED;
				mod.setEnabled(true);
			} else {
				button.displayString = "Disabled";
				button.packedFGColour = COLOR_DISABLED;
				mod.setEnabled(false);
			}
		} else if(button.id == 12) {
			mod = this.mods.get(HudManager.HUD_ARMOR_CHEST);
			mc.displayGuiScreen(new GuiHudModSettingsMenu(mod));
			mc.updateDisplay();
		} else if(button.id == 13) {
			mod = this.mods.get(HudManager.HUD_ARMOR_CHEST);
			if(!mod.isEnabled()) {	
				button.displayString = "Enabled";
				button.packedFGColour = COLOR_ENABLED;
				mod.setEnabled(true);
			} else {
				button.displayString = "Disabled";
				button.packedFGColour = COLOR_DISABLED;
				mod.setEnabled(false);
			}
		} else if(button.id == 14) {
			mod = this.mods.get(HudManager.HUD_ARMOR_PANTS);
			mc.displayGuiScreen(new GuiHudModSettingsMenu(mod));
			mc.updateDisplay();
		} else if(button.id == 15) {
			mod = this.mods.get(HudManager.HUD_ARMOR_PANTS);
			if(!mod.isEnabled()) {	
				button.displayString = "Enabled";
				button.packedFGColour = COLOR_ENABLED;
				mod.setEnabled(true);
			} else {
				button.displayString = "Disabled";
				button.packedFGColour = COLOR_DISABLED;
				mod.setEnabled(false);
			}
		} else if(button.id == 16) {
			mod = this.mods.get(HudManager.HUD_ARMOR_BOOTS);
			mc.displayGuiScreen(new GuiHudModSettingsMenu(mod));
			mc.updateDisplay();
		} else if(button.id == 17) {
			mod = this.mods.get(HudManager.HUD_ARMOR_BOOTS);
			if(!mod.isEnabled()) {	
				button.displayString = "Enabled";
				button.packedFGColour = COLOR_ENABLED;
				mod.setEnabled(true);
			} else {
				button.displayString = "Disabled";
				button.packedFGColour = COLOR_DISABLED;
				mod.setEnabled(false);
			}
		} else if(button.id == 18) {
			mod = this.mods.get(HudManager.HUD_HELD_ITEM);
			mc.displayGuiScreen(new GuiHudModSettingsMenu(mod));
			mc.updateDisplay();
		} else if(button.id == 19) {
			mod = this.mods.get(HudManager.HUD_HELD_ITEM);
			if(!mod.isEnabled()) {	
				button.displayString = "Enabled";
				button.packedFGColour = COLOR_ENABLED;
				mod.setEnabled(true);
			} else {
				button.displayString = "Disabled";
				button.packedFGColour = COLOR_DISABLED;
				mod.setEnabled(false);
			}
		} else if(button.id == 20) {
			mod = this.mods.get(HudManager.HUD_ARROW_COUNT);
			mc.displayGuiScreen(new GuiHudModSettingsMenu(mod));
			mc.updateDisplay();
		} else if(button.id == 21) {
			mod = this.mods.get(HudManager.HUD_ARROW_COUNT);
			if(!mod.isEnabled()) {	
				button.displayString = "Enabled";
				button.packedFGColour = COLOR_ENABLED;
				mod.setEnabled(true);
			} else {
				button.displayString = "Disabled";
				button.packedFGColour = COLOR_DISABLED;
				mod.setEnabled(false);
			}
		}
	}
}
