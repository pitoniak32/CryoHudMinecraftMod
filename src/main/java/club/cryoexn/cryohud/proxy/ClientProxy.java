package club.cryoexn.cryohud.proxy;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/**
 * Handles pre init, init, and post init events.
 * Also handles key bindings for mod config menu.
 * 
 * @author David Pitoniak (Cryoexn)
 */
public class ClientProxy extends CommonProxy {
	
	private static KeyBinding[] keyBindings;
	
	public void preInit(FMLPreInitializationEvent preEvent) {
		super.preInit(preEvent);
	}
	
	public void init(FMLInitializationEvent event) {
		super.init(event);
		keyBindings = new KeyBinding[1];
		
		// Defaults to Y.
		keyBindings[0] = new KeyBinding("Hud Config" , Keyboard.KEY_Y, "Cryo Hud");
		
		for(int i = 0; i < keyBindings.length; i++) {
			ClientRegistry.registerKeyBinding(keyBindings[i]);
		}
	}
	
	public void postInit(FMLPostInitializationEvent postEvent) {
		super.postInit(postEvent);
	}
	
	public static KeyBinding[] getKeyBinds() {
		return keyBindings;
	}
	
}// end ClientProxy.
