package club.cryoexn.cryohud;

import club.cryoexn.cryohud.proxy.CommonProxy;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/**
 * 
 * @author David Pitoniak (Cryoexn)
 */
@Mod(modid = CryoGlobals.MOD_ID, name = CryoGlobals.MOD_NAME, version = CryoGlobals.VERSION)
public class CryoHudDriver
{
	@Instance(CryoGlobals.MOD_ID)
	public static CryoHudDriver instance;
	
	@SidedProxy(clientSide = CryoGlobals.CLIENT_PROXY, serverSide = CryoGlobals.COMMON_PROXY)
	public static CommonProxy proxy;
	
    @EventHandler
    public void preInit(FMLPreInitializationEvent preEvent) {
    	CryoHudDriver.proxy.preInit(preEvent);
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event) {
    	CryoHudDriver.proxy.init(event);
    	
    	// Register the command base for hud mod.
    	ClientCommandHandler.instance.registerCommand(new CryoCommand());
    	
    	// Register Event Handlers.
    	MinecraftForge.EVENT_BUS.register(instance);
    	MinecraftForge.EVENT_BUS.register(new CryoEventHandler());
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent postEvent) {
    	CryoHudDriver.proxy.postInit(postEvent);
    }
    
}// end CryoHudDriver.
