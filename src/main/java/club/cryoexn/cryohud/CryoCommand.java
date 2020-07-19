package club.cryoexn.cryohud;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

/**
 * Chat Command Handler for Customization and Management of Hud Mods.
 * 
 * @author David Pitoniak (Cryoexn)
 */
public class CryoCommand extends CommandBase {
	
	// Command Constants.
	private final String COMMAND_NAME   = "cryohud";
	private final String DELIM          = EnumChatFormatting.DARK_PURPLE + "|" + EnumChatFormatting.LIGHT_PURPLE;
	private final String COMMAND_USAGE  = "/cryohud [subcommand, ...] or /ch [subcommand, ...]";
	private final String COMMAND_RESET  = "resethudpos";
	private final String COMMAND_LABEL  = "label fps" + DELIM + "coords" + DELIM + "facing" + DELIM + "mem" + DELIM + "quest {label}";
	private final String COMMAND_PREFIX = EnumChatFormatting.AQUA + "CryoHud" + 
										  EnumChatFormatting.DARK_AQUA + " - ";
	@Override
	public String getCommandName() {
		return COMMAND_NAME;
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		return COMMAND_USAGE;
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args) throws CommandException {
		
		if(sender instanceof EntityPlayer) {
			
			final EntityPlayer player = (EntityPlayer)sender;
			
			// If the command isnt empty.
			if(args.length > 0) {
				
				// If the command is to reset the hud position.
				if(args[0].equals("resethudpos")) {
					
					resetHudPosition(player);
				
				// If the command is to change the color
				// and there are the correct number of arguments.
				} else if (args[0].equals("label") && (args.length == 3 || args.length == 2)) {
					
					String label = "";
					
					// Decode spaces.
					if(args.length == 2) {
						label = "";
					} else if(args[2].contains("%s")) {
						label = args[2].replace("%s", " ");
					} else {
						label = args[2];
					}
					
					if(args[1].equals("fps")) {
						
						setFpsLabel(player, args, label);
						
					} else if(args[1].equals("coords")) {
						
						setCoordsLabel(player, args, label);
						
					} else if(args[1].equals("facing")) {
						
						setFacingLabel(player, args, label);
						
					} else if(args[1].equals("mem")) { 
						
						setMemLabel(player, args, label);
						
					}else if(args[1].equals("quest")) { 
						
						setQuestLabel(player, args, label);
						
					} else {
						
						sendInvalidCommandFormatMsg(player, args);
					}
					
				} else {
					
					sendInvalidCommandMsg(player);
				}
				
			} else {
				
				displayCommandMenu(player);
			}
		}
	}
	
	/**
	 * Tells HudManager to reset position of all mods
	 * and Notifies user the command worked.
	 * 
	 * @param player - player chat messages are sent to.
	 */
	private void resetHudPosition(EntityPlayer player) {
		
		// Tell the HudManager to reset the components.
		CryoGlobals.HudMan.resetHud();
		
		addCommandBarrier(player);
		
		// Tell the player that the components were reset successfully.
		player.addChatMessage(makeMsg(COMMAND_PREFIX + 
									  EnumChatFormatting.DARK_GRAY + "Hud has been reset to " + 
									  EnumChatFormatting.DARK_AQUA + "( " + 
									  EnumChatFormatting.AQUA + "1" + 
									  EnumChatFormatting.DARK_GRAY + ", " + 
									  EnumChatFormatting.AQUA + "1" + 
									  EnumChatFormatting.DARK_AQUA + " )" +
									  EnumChatFormatting.DARK_GRAY + "."));
		
		addCommandBarrier(player);
	}
	
	/**
	 * Tells HudManager to set fps label variable
	 * and Notifies user the command worked.
	 * 
	 * @param player - player chat messages are sent to.
	 */
	private void setFpsLabel(EntityPlayer player, String [] args, String label) {
		
		// Tell HudManager to set the label.
		CryoGlobals.HudMan.setLabelFps(label);
		
		addCommandBarrier(player);
		
		// Tell player the label has been changed.
		player.addChatMessage(makeMsg(COMMAND_PREFIX + 
								      EnumChatFormatting.DARK_GRAY + "Label for "  + 
								      EnumChatFormatting.AQUA + args[1] + 
								      EnumChatFormatting.DARK_GRAY + " changed to " + 
								      EnumChatFormatting.AQUA + label + 
								      EnumChatFormatting.DARK_GRAY + "."));
		
		addCommandBarrier(player);
	}
	
	/**
	 * Tells HudManager to set coords label variable
	 * and Notifies user the command worked.
	 * 
	 * @param player - player chat messages are sent to.
	 */
	private void setCoordsLabel(EntityPlayer player, String [] args, String label) {
		
		// Tell HudManager to set the label.
		CryoGlobals.HudMan.setLabelCoords(label);
		
		addCommandBarrier(player);
		
		// Tell player the label has been changed.
		player.addChatMessage(makeMsg(COMMAND_PREFIX + 
								      EnumChatFormatting.DARK_GRAY + "Label for "  + 
								      EnumChatFormatting.AQUA + args[1] + 
								      EnumChatFormatting.DARK_GRAY + " changed to " + 
								      EnumChatFormatting.AQUA + label + 
								      EnumChatFormatting.DARK_GRAY + "."));
		
		addCommandBarrier(player);
	}
	
	/**
	 * Tells HudManager to set facing label variable
	 * and Notifies user the command worked.
	 * 
	 * @param player - player chat messages are sent to.
	 */
	private void setFacingLabel(EntityPlayer player, String [] args, String label) {
		
		// Tell HudManager to set the label.
		CryoGlobals.HudMan.setLabelFacing(label);
		
		addCommandBarrier(player);
		
		// Tell player the label has been changed.
		player.addChatMessage(makeMsg(COMMAND_PREFIX + 
								      EnumChatFormatting.DARK_GRAY + "Label for "  + 
								      EnumChatFormatting.AQUA + args[1] + 
								      EnumChatFormatting.DARK_GRAY + " changed to " + 
								      EnumChatFormatting.AQUA + label + 
								      EnumChatFormatting.DARK_GRAY + "."));
		
		addCommandBarrier(player);
	}
	
	/**
	 * Tells HudManager to set facing label variable
	 * and Notifies user the command worked.
	 * 
	 * @param player - player chat messages are sent to.
	 */
	private void setMemLabel(EntityPlayer player, String [] args, String label) {
		
		// Tell HudManager to set the label.
		CryoGlobals.HudMan.setLabelMem(label);
		
		addCommandBarrier(player);
		
		// Tell player the label has been changed.
		player.addChatMessage(makeMsg(COMMAND_PREFIX + 
								      EnumChatFormatting.DARK_GRAY + "Label for "  + 
								      EnumChatFormatting.AQUA + args[1] + 
								      EnumChatFormatting.DARK_GRAY + " changed to " + 
								      EnumChatFormatting.AQUA + label + 
								      EnumChatFormatting.DARK_GRAY + "."));
		
		addCommandBarrier(player);
	}
	
	private void setQuestLabel(EntityPlayer player, String [] args, String label) {
		
		// Tell HudManager to set the label.
		CryoGlobals.HudMan.setLabelQuest(label);
		
		addCommandBarrier(player);
		
		// Tell player the label has been changed.
		player.addChatMessage(makeMsg(COMMAND_PREFIX + 
								      EnumChatFormatting.DARK_GRAY + "Label for "  + 
								      EnumChatFormatting.AQUA + args[1] + 
								      EnumChatFormatting.DARK_GRAY + " changed to " + 
								      EnumChatFormatting.AQUA + label + 
								      EnumChatFormatting.DARK_GRAY + "."));
		
		addCommandBarrier(player);
	}
	
	/**
	 * Display the menu of available commands for Cryohud.
	 * 
	 * @param player - player chat messages are sent to.
	 */
	private void displayCommandMenu(EntityPlayer player) {
		
		addCommandBarrier(player);
		
		// Display how the cryohud command is used.
		player.addChatMessage(makeMsg(EnumChatFormatting.AQUA + " Usage" + EnumChatFormatting.DARK_GRAY + " - " + EnumChatFormatting.AQUA + COMMAND_USAGE));
		
		addCommandBarrier(player);
		
		// Display all subcommands.
		player.addChatMessage(makeMsg(EnumChatFormatting.DARK_PURPLE + "  1. " + EnumChatFormatting.LIGHT_PURPLE + COMMAND_LABEL));
		player.addChatMessage(makeMsg(EnumChatFormatting.DARK_PURPLE + "  2. " + EnumChatFormatting.LIGHT_PURPLE + COMMAND_RESET));
		
		addCommandBarrier(player);
	}
	
	/**
	 * Sends player an error message for if a command doesnt exist.
	 * 
	 * @param player - player chat messages are sent to.
	 */
	private void sendInvalidCommandMsg(EntityPlayer player) {
		addCommandBarrier(player);
		player.addChatMessage(makeMsg(COMMAND_PREFIX + 
									  EnumChatFormatting.DARK_GRAY + "There is no such command."));
		addCommandBarrier(player);
	}
	
	/**
	 * Sends player an error message for if a command format is wrong.
	 * 
	 * @param player - player chat messages are sent to.
	 */
	private void sendInvalidCommandFormatMsg(EntityPlayer player, String [] args) {
		
		addCommandBarrier(player);
		
		player.addChatMessage(makeMsg(COMMAND_PREFIX + 
									  EnumChatFormatting.DARK_GRAY + "Invalid Formatting for " + 
									  EnumChatFormatting.AQUA + args[1] + 
									  EnumChatFormatting.DARK_GRAY + " Command."));
		
		addCommandBarrier(player);
	}
	
	/**
	 * Sends player a boundry for chat message formatting.
	 * 51 '-' characters with a strikethrough to make the 
	 * line solid.
	 * 
	 * @param player - player chat messages are sent to.
	 */
	private void addCommandBarrier(EntityPlayer player) {
		player.addChatMessage(makeMsg(EnumChatFormatting.DARK_GRAY + "" + 
									  EnumChatFormatting.STRIKETHROUGH + "---------------------------------------------------"));
	}
	
	/**
	 * Creates a ChatComponentText to reduce line length in methods.
	 * 
	 * @param msg - String being used for the Chat.
	 */
	private ChatComponentText makeMsg(String msg) {
		return new ChatComponentText(msg);
	}

	@SuppressWarnings("serial")
	@Override
	public List<String> getCommandAliases() {
		return new ArrayList<String>() {{ add("ch");} };
	}

	@Override
	public boolean canCommandSenderUseCommand(ICommandSender sender) {
		return true;
	}
	
}// end CryoCommand.
