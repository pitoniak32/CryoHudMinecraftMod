package club.cryoexn.cryohud.gui;

import java.io.IOException;

import org.lwjgl.input.Keyboard;

import club.cryoexn.cryohud.CryoGlobals;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ecf;

/**
 * Graphical User Interface for the Hud Main Menu.i
 * Displays options for the user to adjust mod positions or adjust the mod configurations.
 *
 * @author David Pitoniak (Cryoexn)
 */
public class GuiHudMainMenu extends GuiScreen {
   
    // local constants
    private final int ID_POSITIONS = 0;
	private final int ID_STATUS    = 1;
	private final int SPACING = 2;
	private final int BTN_HEIGHT = 20;
	private final int BTN_WIDTH = 200;
	private final String TITLE = ecf.BOLD + "" + ecf.AQUA + "*" + ecf.BOLD +
                                 ecf.DARK_AQUA + " CryoHud " + ecf.BOLD + 
                                 ecf.AQUA + "*";
	
    // Abreviations for code brevity.    
	private static Minecraft mc = Minecraft.getMinecraft();
	private static EnumChatFormatting ecf;
    
    // Positions for gui components.
    private ScaledResolution sr;
	private ScreenPosition btnsPos;
	
	@Override
	public void initGui() {
		sr = new ScaledResolution(mc);
		
		btnsPos = new ScreenPosition((sr.getScaledWidth() / 2) - BTN_WIDTH / 2,
                                     (sr.getScaledHeight() / 2) - 10 - BTN_HEIGHT / 2 );
		
		this.buttonList.add(new GuiButton(ID_POSITIONS, 
                            btnsPos.getAbsoluteX(),
                            btnsPos.getAbsoluteY() - BTN_HEIGHT * 3,
                            BTN_WIDTH ,
                            BTN_HEIGHT,
                            "Positions"));

		this.buttonList.add(new GuiButton(ID_STATUS, 
                            btnsPos.getAbsoluteX(), 
                            btnsPos.getAbsoluteY() + SPACING - BTN_HEIGHT * 2,
                            BTN_WIDTH, BTN_HEIGHT,
                            "Mod Settings"));
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {

        // Draw black transparent background.
		super.drawDefaultBackground();
		
		ScreenPosition posTitle = new ScreenPosition((sr.getScaledWidth() - this.fontRendererObj.getStringWidth(TITLE) + 8) / 2, sr.getScaledHeight() / 2 - BTN_HEIGHT * 5);
		ScreenPosition posBorder = new ScreenPosition(btnsPos.getAbsoluteX(), sr.getScaledHeight() / 2 - BTN_HEIGHT * 5);
		
		this.fontRendererObj.drawString(TITLE,                   // String to draw 
                                        posTitle.getAbsoluteX(), // X position 
                                        posTitle.getAbsoluteY(), // Y position
                                        0xffffff);               // Color
		
		this.drawHollowRect(posBorder.getAbsoluteX() - 7,            // X position
                            posBorder.getAbsoluteY() + 3,            // Y position
                            BTN_WIDTH + 13,                          // Width 
                            (BTN_HEIGHT + SPACING) * 3, 0x24ffffff); // Height
		
		super.drawScreen(mouseX, mouseY, partialTicks);
	}
	
	private void drawHollowRect(int x, int y, int w, int h, int c) {
        
        // Modified Rectanlge with a space in the top side.
        this.drawHorizontalLine(x, x + (BTN_WIDTH - 50) / 2, y, c);
		this.drawHorizontalLine(x + w - this.fontRendererObj.getStringWidth(TITLE) - 11, x + w, y, c);
		this.drawHorizontalLine(x,  x + w, y + h , c);
		
		this.drawVerticalLine(x, y, y + h, c);
		this.drawVerticalLine(x + w, y, y + h, c);
	}
 	
    @Override
	protected void keyTyped(char typedChar, int keyCode) throws IOException {
		
		if(keyCode == Keyboard.KEY_ESCAPE) {
			
			// Save all the mods.
			CryoGlobals.HudMan.saveMods();
			
			mc.displayGuiScreen(null);
			mc.updateDisplay();
		}
	}
 
	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		if (button.id == ID_POSITIONS)
			CryoGlobals.HudMan.openConfigScreen();
		if(button.id == ID_STATUS)
			CryoGlobals.HudMan.openStatus();
	}

	@Override
	public void updateScreen() {
		super.updateScreen();
		sr = new ScaledResolution(mc);
		btnsPos = new ScreenPosition((sr.getScaledWidth() / 2) - BTN_WIDTH / 2, (sr.getScaledHeight() / 2) - 10 - BTN_HEIGHT / 2 );
	}

	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}
    
} // end GuiHudMainMenu.
