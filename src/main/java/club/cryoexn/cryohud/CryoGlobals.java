package club.cryoexn.cryohud;

/**
 * Globals for Cryohud.
 *
 * @author David Pitoniak (Cryoexn)
 */
public class CryoGlobals {
	public static final String MOD_ID   = "Hud";
	public static final String MOD_NAME = "CryoHud";
    public static final String VERSION  = "1.2";
    
    public static final String CLIENT_PROXY = "club.cryoexn.cryohud.proxy.ClientProxy";
    public static final String COMMON_PROXY = "club.cryoexn.cryohud.proxy.CommonProxy";
    
    // Manage the active hud mods.
    public static HudManager HudMan = new HudManager();
    
}// end CryoGlobals.
