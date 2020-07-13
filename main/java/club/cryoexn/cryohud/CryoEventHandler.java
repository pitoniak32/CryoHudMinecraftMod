package club.cryoexn.cryohud;

import club.cryoexn.cryohud.proxy.ClientProxy;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Handles the Forge Events Nessesary for Hud to be displayed and updated.
 * Registered in the Forge Event Bus.
 * 
 * @author David Pitoniak (Cryoexn)
 */
public class CryoEventHandler {
	
	@SubscribeEvent
	public void chatMessageEvent(ClientChatReceivedEvent e) {
		String msg = e.message.getUnformattedText();
		
		// Implement QuickMaths.
		
		// Implement Night Quest Timer.
		if(msg.contains("NIGHT QUEST!") && 
		   Character.isLowerCase(msg.charAt(msg.length() - 2)) && 
		   msg.charAt(msg.length() - 1) == '!' &&
		   !CryoGlobals.HudMan.isQuestTimerStarted()) {
			
			CryoGlobals.HudMan.startQuestTimer(removePlayerName(msg) + " : ");
		}
	}
	
	private String removePlayerName(String msg) {
		return msg.substring(msg.indexOf("!") + 2);
	}
	
	@SubscribeEvent
	public void renderGameOverlayEvent(RenderGameOverlayEvent.Text rEvent) {
		
		// Render the active text hud mods.
		CryoGlobals.HudMan.renderCoords();
		CryoGlobals.HudMan.renderFacing();
		CryoGlobals.HudMan.renderFps();
		CryoGlobals.HudMan.renderMemory();
		CryoGlobals.HudMan.renderQuestTimer();
		CryoGlobals.HudMan.renderArrows();
		
		// Remove the lighting glitch on items.
		RenderHelper.enableGUIStandardItemLighting();
		
		CryoGlobals.HudMan.renderArmor();
		CryoGlobals.HudMan.renderHeldItem();

	}
	
	@SideOnly(Side.CLIENT)
	@SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
	public void onEvent(KeyInputEvent event)
	{
	    // make local copy of key binding array
	    KeyBinding[] keyBindings = ClientProxy.getKeyBinds();
	   
	    // check each enumerated key binding type for pressed and take appropriate action
	    if (keyBindings[0].isPressed()) 
	    {
	    	// Open the drag menu for HUD items.
	        CryoGlobals.HudMan.openConfigScreen();
	    }
	}
	
}// end CryoEventHandler.
