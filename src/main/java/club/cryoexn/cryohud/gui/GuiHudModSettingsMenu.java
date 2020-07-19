package club.cryoexn.cryohud.gui;

import java.io.IOException;

import org.lwjgl.input.Keyboard;

import club.cryoexn.cryohud.CryoGlobals;
import club.cryoexn.cryohud.mods.ModArrowCount;
import club.cryoexn.cryohud.mods.ModDraggable;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class GuiHudModSettingsMenu extends GuiScreen {

	private final int COLOR_ENABLED = 0x9dff7d;
	private final int COLOR_DISABLED = 0xff837d;
	
	private final int BTN_WIDTH = 100;
	private final int BTN_HEIGHT = 20;
	
	private ModDraggable mod;
	private ScaledResolution sr;
	private ScreenPosition btnsPos;
	private GuiTextField text;
	
	public GuiHudModSettingsMenu(ModDraggable mod) {
		this.mod = mod;
	}
	
	@Override
	public void initGui() {
		sr = new ScaledResolution(mc);

		btnsPos = new ScreenPosition((sr.getScaledWidth() / 2) - BTN_WIDTH / 2, (sr.getScaledHeight() / 2) - 10 - BTN_HEIGHT / 2 );

		GuiButton btn = new GuiButton(0, btnsPos.getAbsoluteX(), btnsPos.getAbsoluteY(), BTN_WIDTH, BTN_HEIGHT, "Background: " + (mod.isBackgroundOn() ? "On" : "Off"));
		btn.packedFGColour = mod.isBackgroundOn() ? COLOR_ENABLED : COLOR_DISABLED;
		
		this.buttonList.add(btn);
		
		
		this.text = new GuiTextField(0, this.fontRendererObj, btnsPos.getAbsoluteX(), btnsPos.getAbsoluteY() + BTN_HEIGHT + 5, BTN_WIDTH, BTN_HEIGHT);
	    this.text.setMaxStringLength(23);
	    this.text.setText(!(mod instanceof ModArrowCount) ? Integer.toHexString(mod.getColor()) : "N/A");
	    this.text.setEnabled(!(mod instanceof ModArrowCount) ? true : false);
	    this.text.setFocused(true);
		
		super.initGui();
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		ScreenPosition posTitle = new ScreenPosition((sr.getScaledWidth() - this.fontRendererObj.getStringWidth(this.mod.getModName())) / 2, sr.getScaledHeight() / 2 - BTN_HEIGHT * 2);
		
		super.drawDefaultBackground();
		
		if(mod instanceof ModArrowCount) {
			if(this.mod.isBackgroundOn())
				GuiScreen.drawRect(posTitle.getAbsoluteX() - 4 + mod.getWidth() * 2, posTitle.getAbsoluteY(), mod.getWidth() * 3 + posTitle.getAbsoluteX() - 3, mod.getHeight() + posTitle.getAbsoluteY() + 1, 0x4f000000);
			
				mc.getRenderItem().renderItemAndEffectIntoGUI(new ItemStack(Items.arrow), posTitle.getAbsoluteX() - 4 + mod.getWidth() * 2, posTitle.getAbsoluteY());
		} else {
			if(this.mod.isBackgroundOn())
				GuiScreen.drawRect(posTitle.getAbsoluteX() - 2, posTitle.getAbsoluteY() - 2, this.fontRendererObj.getStringWidth(this.mod.getModName()) + posTitle.getAbsoluteX() + 1, this.mod.getHeight() + posTitle.getAbsoluteY() + 1, 0x4f000000);
			
			this.fontRendererObj.drawString(this.mod.getModName(), posTitle.getAbsoluteX(), posTitle.getAbsoluteY(), this.mod.getColor());
		}
		
		this.text.drawTextBox();
		
		super.drawScreen(mouseX, mouseY, partialTicks);
	}

	@Override
	protected void keyTyped(char typedChar, int keyCode) throws IOException {
		
		// If the user quits the menu.
		if(keyCode == Keyboard.KEY_ESCAPE) {
			
			// Change the mods color.
			setModColor();
			
			// Return to the previous screen.
			CryoGlobals.HudMan.openStatus();
		
		// Set the color of the mod when user hits enter.
		} else if(keyCode == Keyboard.KEY_RETURN) {
			
			setModColor();
			
		// Make sure the uder only enters keys into the field that are hex.
		} else if(keyCode == Keyboard.KEY_0 || keyCode == Keyboard.KEY_8 ||
				  keyCode == Keyboard.KEY_1 ||keyCode == Keyboard.KEY_9 ||
				  keyCode == Keyboard.KEY_2 ||keyCode == Keyboard.KEY_A ||
				  keyCode == Keyboard.KEY_3 ||keyCode == Keyboard.KEY_B ||
				  keyCode == Keyboard.KEY_4 ||keyCode == Keyboard.KEY_C ||
				  keyCode == Keyboard.KEY_5 ||keyCode == Keyboard.KEY_D ||
				  keyCode == Keyboard.KEY_6 ||keyCode == Keyboard.KEY_E ||
				  keyCode == Keyboard.KEY_7 ||keyCode == Keyboard.KEY_F ||
				  keyCode == Keyboard.KEY_BACK) {
			
			this.text.textboxKeyTyped(typedChar, keyCode);
		}
	}
	
	/**
	 * Check that the color in the text box is a hex color value. 
	 * if its not a hex color value the color is set to white.
	 */
	private void setModColor() {
		try {
			this.mod.setColor(Integer.parseInt(this.text.getText(), 16));
		} catch(NumberFormatException ex) {
			this.mod.setColor(0xffffff);
		}
	}

	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		if(button.id == 0) {
			if(!mod.isBackgroundOn()) {
				button.displayString = "Background: On";
				button.packedFGColour = COLOR_ENABLED;
				mod.setBackgroundOn(true);
			} else {
				button.displayString = "Background: Off";
				button.packedFGColour = COLOR_DISABLED;
				mod.setBackgroundOn(false);
			}
		}
	}

	@Override
	public void updateScreen() {
		super.updateScreen();
		this.text.updateCursorCounter();
	}

	@Override
	public void onGuiClosed() {
		super.onGuiClosed();
	}

	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}

	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
		super.mouseClicked(mouseX, mouseY, mouseButton);
		this.text.mouseClicked(mouseX, mouseY, mouseButton);
	}
	
}
