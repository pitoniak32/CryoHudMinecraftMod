package club.cryoexn.cryohud;

import club.cryoexn.cryohud.proxy.ClientProxy;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.EnumChatFormatting;
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
	
    /**
     * Receive all chat events from minecraft.
     */
    @SubscribeEvent
	public void chatMessageEvent(ClientChatReceivedEvent e) {
		String msg = e.message.getUnformattedText();

		// Implement Night Quest Timer.
        // Make sure that the chat message is the night quest message sent by the server.
		if(msg.contains("NIGHT QUEST!") &&
		   !userGenerated(msg) &&
		   !CryoGlobals.HudMan.isQuestTimerStarted()) {
			
            // TODO: Maybe make the quest text show the progression?
			CryoGlobals.HudMan.startQuestTimer(msg.substring(msg.indexOf('!') + 2));
		}
	}
	
	/**
	 * Make sure that the message that triggers the timer is not user generated.
	 * 
	 * @param msg - message being checked
	 * @return if the message was user generated
	 */
	private boolean userGenerated(String msg) {
		if(msg.contains(EnumChatFormatting.BLUE + "" + EnumChatFormatting.BOLD + "NIGHT QUEST!")) {
			return false;
		} 
		
		return true;
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
	    
        // Render the active item hud mods.    
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
	    	// Open the main menu for HUD items.
	        CryoGlobals.HudMan.openConfigMenu();
	    }
	}
	
} // end CryoEventHandler.
