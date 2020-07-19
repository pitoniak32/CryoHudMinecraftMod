package club.cryoexn.cryohud;

import java.util.ArrayList;

import club.cryoexn.cryohud.gui.GuiHudMainMenu;
import club.cryoexn.cryohud.gui.GuiHudModPosition;
import club.cryoexn.cryohud.gui.GuiHudModListMenu;
import club.cryoexn.cryohud.gui.ScreenPosition;
import club.cryoexn.cryohud.mods.ModArmorBoots;
import club.cryoexn.cryohud.mods.ModArmorChest;
import club.cryoexn.cryohud.mods.ModArmorHelm;
import club.cryoexn.cryohud.mods.ModArmorPants;
import club.cryoexn.cryohud.mods.ModArrowCount;
import club.cryoexn.cryohud.mods.ModCoords;
import club.cryoexn.cryohud.mods.ModDirFacing;
import club.cryoexn.cryohud.mods.ModDraggable;
import club.cryoexn.cryohud.mods.ModFPSCounter;
import club.cryoexn.cryohud.mods.ModHeldItem;
import club.cryoexn.cryohud.mods.ModMemoryUsage;
import club.cryoexn.cryohud.mods.ModNightQuestTimer;
import net.minecraft.client.Minecraft;

/**
 * Manage Hud Mods in a list of active mods.
 * Allows user to change values for mods and save the values.
 * 
 * @author David Pitoniak (Cryoexn)
 */
public class HudManager {
	
	private static Minecraft mc; // Easier access to minecraft.

	// Keys for mod hashmap.
	public static final int HUD_QUEST_TIMER = 0;
	public static final int HUD_MEM_USAGE   = 1;
	public static final int HUD_FPS_COUNTER = 2;
	public static final int HUD_COORDS      = 3;
	public static final int HUD_DIR_FACING  = 4;
	public static final int HUD_ARMOR_HELM  = 5;
	public static final int HUD_ARMOR_CHEST = 6;
	public static final int HUD_ARMOR_PANTS = 7;
	public static final int HUD_ARMOR_BOOTS = 8;
	public static final int HUD_HELD_ITEM   = 9;
	public static final int HUD_ARROW_COUNT = 10;

	// Map of active mods.
	private ArrayList<ModDraggable> mods;
	
	/**
	 * Constructor populates the active mods list.
	 */
	public HudManager() {
		
		mc = Minecraft.getMinecraft();
		
		mods = new ArrayList<ModDraggable>();
		
		//Add active Hud Items to the list of Mods
		mods.add(new ModNightQuestTimer());
		mods.add(new ModMemoryUsage());
		mods.add(new ModFPSCounter());
		mods.add(new ModCoords());
		mods.add(new ModDirFacing());
		mods.add(new ModArmorHelm());
		mods.add(new ModArmorChest());
		mods.add(new ModArmorPants());
		mods.add(new ModArmorBoots());
		mods.add(new ModHeldItem());
		mods.add(new ModArrowCount());
	}
	
	/**
	 * Calls render fucntions for all armor pieces.
	 */
	public void renderArmor() {
		renderArmorHelm();
		renderArmorChest();
		renderArmorPants();
		renderArmorBoots();
	}
	
	/**
	 * Calls the Helmet render function if the mod is not null.
	 */
	private void renderArmorHelm() {
		if(mods.get(HUD_ARMOR_HELM).isEnabled())
			mods.get(HUD_ARMOR_HELM).render();
	}
	
	/**
	 * Calls the Chest render function if the mod is not null.
	 */
	private void renderArmorChest() {
		if(mods.get(HUD_ARMOR_CHEST).isEnabled())
			mods.get(HUD_ARMOR_CHEST).render();
	}
	
	/**
	 * Calls the Pants render function if the mod is not null.
	 */
	private void renderArmorPants() {
		if(mods.get(HUD_ARMOR_PANTS).isEnabled())
			mods.get(HUD_ARMOR_PANTS).render();
	}

	/**
	 * Calls the Boots render function if the mod is not null.
	 */
	private void renderArmorBoots() {
		if(mods.get(HUD_ARMOR_BOOTS).isEnabled())
			mods.get(HUD_ARMOR_BOOTS).render();
	}
	
	/**
	 * Calls the Helmet render function if the mod is not null.
	 */
	public void renderHeldItem() {
		if(mods.get(HUD_HELD_ITEM).isEnabled())
			mods.get(HUD_HELD_ITEM).render();
	}
	
	public void renderArrows() {
		if(mods.get(HUD_ARROW_COUNT).isEnabled())
			mods.get(HUD_ARROW_COUNT).render();
	}
	
	/**
	 * Calls the Coords render function if the mod is not null.
	 */
 	public void renderCoords() {
 		if(mods.get(HUD_COORDS).isEnabled())
			mods.get(HUD_COORDS).render();
 	}
 	
 	/**
	 * Calls the Facing render function if the mod is not null.
	 */
 	public void renderFacing() {
 		if(mods.get(HUD_DIR_FACING).isEnabled())
			mods.get(HUD_DIR_FACING).render();
 	}
 	
 	/**
	 * Calls the Fps render function if the mod is not null.
	 */
 	public void renderFps() {
 		if(mods.get(HUD_FPS_COUNTER).isEnabled())
 			mods.get(HUD_FPS_COUNTER).render();
 	}
 	
 	/**
	 * Calls the Memory render function if the mod is not null.
	 */
 	public void renderMemory() {
 		if(mods.get(HUD_MEM_USAGE).isEnabled())
 			mods.get(HUD_MEM_USAGE).render();
 	}
 	
 	public void renderQuestTimer() {
 		if(mods.get(HUD_QUEST_TIMER).isEnabled()) {
 			mods.get(HUD_QUEST_TIMER).render();
 		}
 	}
 	
	/**
	 * Sets the color for fps mod to be display in.
	 * 
	 * @param color - Hex color integer.
	 */
	public void setColorFps(int color) {
		if(mods.get(HUD_FPS_COUNTER) != null)
 			mods.get(HUD_FPS_COUNTER).setColor(color);
	}
	
	/**
	 * Sets the color for coords mod to be display in.
	 * 
	 * @param color - Hex color integer.
	 */
	public void setColorCoords(int color) {
		if(mods.get(HUD_COORDS) != null)
 			mods.get(HUD_COORDS).setColor(color);
	}
	
	/**
	 * Sets the color for facing mod to be display in.
	 * 
	 * @param color - Hex color integer.
	 */
	public void setColorFacing(int color) {
		if(mods.get(HUD_DIR_FACING) != null)
 			mods.get(HUD_DIR_FACING).setColor(color);
	}
	
	/**
	 * Sets the color for memory mod to be display in.
	 * 
	 * @param color - Hex color integer.
	 */
	public void setColorMem(int color) {
		if(mods.get(HUD_MEM_USAGE) != null)
 			mods.get(HUD_MEM_USAGE).setColor(color);
	}
	
	public void setColorQuest(int color) {
		if(mods.get(HUD_QUEST_TIMER) != null)
 			mods.get(HUD_QUEST_TIMER).setColor(color);
	}
	
	/**
	 * Sets the label to be appended to fps String.
	 * 
	 * @param label - label after fps.
	 */
	public void setLabelFps(String label) {
		if(mods.get(HUD_FPS_COUNTER) != null)
 			mods.get(HUD_FPS_COUNTER).setLabel(label);
	}
	
	/**
	 * Sets the label to be prepended to coords String.
	 * 
	 * @param label - label before coords.
	 */
	public void setLabelCoords(String label) {
		if(mods.get(HUD_COORDS) != null)
 			mods.get(HUD_COORDS).setLabel(label);
	}
	
	/**
	 * Sets the label to be prepended to facing String.
	 * 
	 * @param label - label before facing.
	 */
	public void setLabelFacing(String label) {
		if(mods.get(HUD_DIR_FACING) != null)
 			mods.get(HUD_DIR_FACING).setLabel(label);
	}
	
	/**
	 * Sets the label to be prepended to memory String.
	 * 
	 * @param label - label before facing.
	 */
	public void setLabelMem(String label) {
		if(mods.get(HUD_MEM_USAGE) != null)
 			mods.get(HUD_MEM_USAGE).setLabel(label);
	}
	
	public void setLabelQuest(String label) {
		if(mods.get(HUD_QUEST_TIMER) != null)
 			mods.get(HUD_QUEST_TIMER).setLabel(label);
	}
	
	/**
	 * Sets the boolean variable for fps background.
	 * 
	 * @param background - background on or off.
	 */
	public void setFpsBackGroundOn(boolean background) {
		if(mods.get(HUD_FPS_COUNTER) != null)
 			mods.get(HUD_FPS_COUNTER).setBackgroundOn(background);
	}
	
	/**
	 * Sets the boolean variable for coords background.
	 * 
	 * @param background - background on or off.
	 */
	public void setCoordsBackGroundOn(boolean background) {
		if(mods.get(HUD_COORDS) != null)
 			mods.get(HUD_COORDS).setBackgroundOn(background);
	}
	
	/**
	 * Sets the boolean variable for facing background.
	 * 
	 * @param background - background on or off.
	 */
	public void setFacingBackGroundOn(boolean background) {
		if(mods.get(HUD_DIR_FACING) != null)
 			mods.get(HUD_DIR_FACING).setBackgroundOn(background);
	}
	
	/**
	 * Sets the boolean variable for memory background.
	 * 
	 * @param background - background on or off.
	 */
	public void setMemBackGroundOn(boolean background) {
		if(mods.get(HUD_MEM_USAGE) != null)
 			mods.get(HUD_MEM_USAGE).setBackgroundOn(background);
	}
	
	public void setQuestBackGroundOn(boolean background) {
		if(mods.get(HUD_QUEST_TIMER) != null)
 			mods.get(HUD_QUEST_TIMER).setBackgroundOn(background);
	}
	
	public void setArrowBackGroundOn(boolean background) {
		if(mods.get(HUD_ARROW_COUNT) != null)
 			mods.get(HUD_ARROW_COUNT).setBackgroundOn(background);
	}
	
	public void setEnabledFps(boolean enabled) {
		if(mods.get(HUD_FPS_COUNTER) != null)
 			mods.get(HUD_FPS_COUNTER).setEnabled(enabled);
	}
	
	public void setEnabledCoords(boolean enabled) {
		if(mods.get(HUD_COORDS) != null)
 			mods.get(HUD_COORDS).setEnabled(enabled);
	}
	
	public void setEnabledFacing(boolean enabled) {
		if(mods.get(HUD_DIR_FACING) != null)
 			mods.get(HUD_DIR_FACING).setEnabled(enabled);
	}
	
	public void setEnabledMem(boolean enabled) {
		if(mods.get(HUD_MEM_USAGE) != null)
 			mods.get(HUD_MEM_USAGE).setEnabled(enabled);
	}
	
	public void setEnabledQuest(boolean enabled) {
		if(mods.get(HUD_QUEST_TIMER) != null)
 			mods.get(HUD_QUEST_TIMER).setEnabled(enabled);
	}
	
	public void setEnabledArmor(boolean enabled) {
		
		if(mods.get(HUD_ARMOR_HELM) != null)
 			mods.get(HUD_ARMOR_HELM).setEnabled(enabled);
		
		if(mods.get(HUD_ARMOR_CHEST) != null)
 			mods.get(HUD_ARMOR_CHEST).setEnabled(enabled);
		
		if(mods.get(HUD_ARMOR_PANTS) != null)
 			mods.get(HUD_ARMOR_PANTS).setEnabled(enabled);
		
		if(mods.get(HUD_ARMOR_BOOTS) != null)
 			mods.get(HUD_ARMOR_BOOTS).setEnabled(enabled);
	}
	
	public void setEnabledItem(boolean enabled) {
		if(mods.get(HUD_HELD_ITEM) != null)
 			mods.get(HUD_HELD_ITEM).setEnabled(enabled);
	}
	
	public void setEnabledArrow(boolean enabled) {
		if(mods.get(HUD_ARROW_COUNT) != null)
 			mods.get(HUD_ARROW_COUNT).setEnabled(enabled);
	}
	
	public void startQuestTimer(String quest) {
		((ModNightQuestTimer)mods.get(HUD_QUEST_TIMER)).startQuestTimer(quest);
	}
	
	public boolean isQuestTimerStarted() {
		return ((ModNightQuestTimer)mods.get(HUD_QUEST_TIMER)).isStarted();
	}
	
	/**
	 * Saves the current state of all active mods.
	 */
	public void saveMods() {
		for(ModDraggable mod : this.mods) {
			mod.save();
		}
	}
	
	/**
	 * Resets all active mods to the 1, 1 position.
	 */
	public void resetHud() {
		for(ModDraggable mod : this.mods) {
			mod.setScreenPosition(new ScreenPosition(1,1));
		}
	}
	
	/**
	 * Created a new instance of HudConfigScreen
	 * and updates the display.
	 */
	public void openConfigScreen() {
 		mc.displayGuiScreen(new GuiHudModPosition(this.mods));
 		mc.updateDisplay();
	}
	
	public void openConfigMenu() {
		mc.displayGuiScreen(new GuiHudMainMenu());
		mc.updateDisplay();
	}
	
	public void openStatus() {
		mc.displayGuiScreen(new GuiHudModListMenu(this.mods));
	}
	
}// end HudManager.
