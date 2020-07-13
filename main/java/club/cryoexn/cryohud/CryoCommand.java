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
	private final String COMMAND_COLOR  = "color fps" + DELIM + "coords" + DELIM + "facing" + DELIM + "mem" + DELIM + "quest {hex-color}";
	private final String COMMAND_RESET  = "resethudpos";
	private final String COMMAND_SAVE   = "save";
	private final String COMMAND_LABEL  = "label fps" + DELIM + "coords" + DELIM + "facing" + DELIM + "mem" + DELIM + "quest {label}";
	private final String COMMAND_BG     = "background or bg fps" + DELIM + "coords" + DELIM + "facing" + DELIM + "mem" + DELIM + "quest {on|off}";
	private final String COMMAND_STATUS = "enable or disable {fps" + DELIM + "coords" + DELIM + "facing" + DELIM + "mem" + DELIM + "quest" + DELIM + "armor" + DELIM + "item}";
	private final String COMMAND_HELP   = "help {command-name}";
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
				} else if(args[0].equals("color") && args.length == 3) {
					
					// Check which text component is specified 
					// and change its color to the specified color.
					if(args[1].equals("fps")) {
						
						setFpsColor(player, args);
						
					} else if(args[1].equals("coords")) {
						
						setCoordsColor(player, args);
						
					} else if(args[1].equals("facing")) {
						
						setFacingColor(player, args);
						
					} else if(args[1].equals("mem")) {
						
						setMemColor(player, args);
						
					} else if(args[1].equals("quest")) {
					
						setQuestColor(player, args);
						
					} else {
						
						sendInvalidCommandFormatMsg(player, args);
					}
					
				// If the command was set label 
				// and there are the correct number of args.
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
					
				} else if(args[0].equals("bg") && args.length == 3 || args[0].equals("background") && args.length == 3) {
						
					if(args[1].equals("fps")) {
						
						setFpsBackground(player, args);
						
					} else if(args[1].equals("coords")) {
						
						setCoordsBackground(player, args);
						
					} else if(args[1].equals("facing")) {
						
						setFacingBackground(player, args);
						
					} else if(args[1].equals("mem")) {
						
						setMemBackground(player, args);
					
				    } else if(args[1].equals("quest")) {
						
						setQuestBackground(player, args);
					
				    } else if(args[1].equals("arrows")) {
						
						setArrowsBackground(player, args);
					
				    } else {
				   
						sendInvalidCommandFormatMsg(player, args);
					}
						
				} else if(args[0].equals("enable") || args[0].equals("disable")) {
					
					if(args[1].equals("fps")) {
						
						setFpsStatus(player, args);
						
					} else if(args[1].equals("coords")) {
						
						setCoordsStatus(player, args);
						
					} else if(args[1].equals("facing")) {
						
						setFacingStatus(player, args);
						
					} else if(args[1].equals("mem")) {
						
						setMemStatus(player, args);
					
				    }else if(args[1].equals("quest")) {
				    	
				    	setQuestStatus(player, args);
				    	
				    } else if(args[1].equals("armor")) {
					
						setArmorStatus(player, args);
					
				    } else if(args[1].equals("item")) {
						
						setItemStatus(player, args);
					
				    }else if(args[1].equals("arrows")) {
				    	
				    	setArrowsStatus(player, args);
				    	
				    } else {
						
						sendInvalidCommandFormatMsg(player, args);
					}
					
				} else if(args[0].equals("save")) {
					
					saveConfig(player);
					
				} else if(args[0].equals("help")) {
					
					if(args[1].equals("color")) {
						
						displayHelpColor(player);
						
					} else if(args[1].equals("resethudpos")) {
						
						displayHelpReset(player);
						
					} else if(args[1].equals("save")) {
						
						displayHelpSave(player);
						
					} else if(args[1].equals("label")) {
						
						displayHelpLabel(player);
						
					} else if(args[1].equals("bg") || args[1].equals("background")) { 
						
						displayHelpBg(player);
						
					} else {
						
						sendInvalidCommandMsg(player);
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
	 * Tells HudManager to set fps color variable
	 * and Notifies user the command worked.
	 * 
	 * @param player - player chat messages are sent to.
	 */
	private void setFpsColor(EntityPlayer player, String[] args) {
		
		// Tell HudManager to set fps color.
		CryoGlobals.HudMan.setColorFps(Integer.parseInt(args[2], 16));
		
		addCommandBarrier(player);
		
		// Tell player the color has been changed.
		player.addChatMessage(makeMsg(COMMAND_PREFIX + 
									  EnumChatFormatting.DARK_GRAY + "Color for "  + 
									  EnumChatFormatting.AQUA + args[1] + 
									  EnumChatFormatting.DARK_GRAY + " changed to " + 
									  EnumChatFormatting.AQUA + "0x" + args[2] +
									  EnumChatFormatting.DARK_GRAY + "."));
		
		addCommandBarrier(player);
	}
	
	/**
	 * Tells HudManager to set coords color variable
	 * and Notifies user the command worked.
	 * 
	 * @param player - player chat messages are sent to.
	 */
	private void setCoordsColor(EntityPlayer player, String[] args) {
		
		// Tell HudManager to set coords color.
		CryoGlobals.HudMan.setColorCoords(Integer.parseInt(args[2], 16));
		
		addCommandBarrier(player);
		
		// Tell player the color has been changed.
		player.addChatMessage(makeMsg(COMMAND_PREFIX + 
									  EnumChatFormatting.DARK_GRAY + "Color for "  + 
									  EnumChatFormatting.AQUA + args[1] + 
									  EnumChatFormatting.DARK_GRAY + " changed to " + 
									  EnumChatFormatting.AQUA + "0x" + args[2] + 
									  EnumChatFormatting.DARK_GRAY + "."));
		
		addCommandBarrier(player);
	}
	
	/**
	 * Tells HudManager to set facing color variable
	 * and Notifies user the command worked.
	 * 
	 * @param player - player chat messages are sent to.
	 */
	private void setFacingColor(EntityPlayer player, String[] args) {
		
		// Tell HudManager to set facing color.
		CryoGlobals.HudMan.setColorFacing(Integer.parseInt(args[2], 16));
		
		addCommandBarrier(player);
		
		// Tell player the color has been changed.
		player.addChatMessage(makeMsg(COMMAND_PREFIX + 
								      EnumChatFormatting.DARK_GRAY + "Color for "  + 
								      EnumChatFormatting.AQUA + args[1] + 
								      EnumChatFormatting.DARK_GRAY + " changed to " + 
								      EnumChatFormatting.AQUA + "0x" + args[2] + 
								      EnumChatFormatting.DARK_GRAY + "."));
		
		addCommandBarrier(player);
	}
	
	/**
	 * Tells HudManager to set memory color variable
	 * and Notifies user the command worked.
	 * 
	 * @param player - player chat messages are sent to.
	 */
	private void setMemColor(EntityPlayer player, String[] args) {
		
		// Tell HudManager to set facing color.
		CryoGlobals.HudMan.setColorMem(Integer.parseInt(args[2], 16));
		
		addCommandBarrier(player);
		
		// Tell player the color has been changed.
		player.addChatMessage(makeMsg(COMMAND_PREFIX + 
								      EnumChatFormatting.DARK_GRAY + "Color for "  + 
								      EnumChatFormatting.AQUA + args[1] + 
								      EnumChatFormatting.DARK_GRAY + " changed to " + 
								      EnumChatFormatting.AQUA + "0x" + args[2] + 
								      EnumChatFormatting.DARK_GRAY + "."));
		
		addCommandBarrier(player);
	}
	
	private void setQuestColor(EntityPlayer player, String[] args) {
		
		// Tell HudManager to set facing color.
		CryoGlobals.HudMan.setColorQuest(Integer.parseInt(args[2], 16));
		
		addCommandBarrier(player);
		
		// Tell player the color has been changed.
		player.addChatMessage(makeMsg(COMMAND_PREFIX + 
								      EnumChatFormatting.DARK_GRAY + "Color for "  + 
								      EnumChatFormatting.AQUA + args[1] + 
								      EnumChatFormatting.DARK_GRAY + " changed to " + 
								      EnumChatFormatting.AQUA + "0x" + args[2] + 
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
	 * Tells HudManager to set fps background to on or off
	 * and Notifies user the command worked.
	 * 
	 * @param player - player chat messages are sent to.
	 */
	private void setFpsBackground(EntityPlayer player, String [] args) {
		
		// Tell HudManager to set the label.
		if(args[2].equals("on")) { 
			CryoGlobals.HudMan.setFpsBackGroundOn(true);
			addCommandBarrier(player);
			
			// Tell player the label has been changed.
			player.addChatMessage(makeMsg(COMMAND_PREFIX + 
									      EnumChatFormatting.DARK_GRAY + "Background for "  + 
									      EnumChatFormatting.AQUA + args[1] + 
									      EnumChatFormatting.DARK_GRAY + " set to " + 
									      EnumChatFormatting.AQUA + "on" + 
									      EnumChatFormatting.DARK_GRAY + "."));
			
			addCommandBarrier(player);
		} else if(args[2].equals("off")) {
			CryoGlobals.HudMan.setFpsBackGroundOn(false);
			addCommandBarrier(player);
			
			// Tell player the label has been changed.
			player.addChatMessage(makeMsg(COMMAND_PREFIX + 
									      EnumChatFormatting.DARK_GRAY + "Background for "  + 
									      EnumChatFormatting.AQUA + args[1] + 
									      EnumChatFormatting.DARK_GRAY + " set to " + 
									      EnumChatFormatting.AQUA + "off" + 
									      EnumChatFormatting.DARK_GRAY + "."));
			
			addCommandBarrier(player);
		} else {
			addCommandBarrier(player);
			player.addChatMessage(makeMsg(COMMAND_PREFIX + 
				      EnumChatFormatting.DARK_GRAY + "Background for "  + 
				      EnumChatFormatting.AQUA + args[1] + 
				      EnumChatFormatting.DARK_GRAY + " must be set to " + 
				      EnumChatFormatting.AQUA + "on" + 
				      EnumChatFormatting.DARK_AQUA + " or " + 
				      EnumChatFormatting.AQUA + "off" + 
				      EnumChatFormatting.DARK_GRAY + "."));
			addCommandBarrier(player);
		}
	}
	
	/**
	 * Tells HudManager to set coords background to on or off
	 * and Notifies user the command worked.
	 * 
	 * @param player - player chat messages are sent to.
	 */
	private void setCoordsBackground(EntityPlayer player, String [] args) {
		// Tell HudManager to set the label.
		if(args[2].equals("on")) { 
			CryoGlobals.HudMan.setCoordsBackGroundOn(true);
			addCommandBarrier(player);
			
			// Tell player the label has been changed.
			player.addChatMessage(makeMsg(COMMAND_PREFIX + 
									      EnumChatFormatting.DARK_GRAY + "Background for "  + 
									      EnumChatFormatting.AQUA + args[1] + 
									      EnumChatFormatting.DARK_GRAY + " set to " + 
									      EnumChatFormatting.AQUA + "on" + 
									      EnumChatFormatting.DARK_GRAY + "."));
			
			addCommandBarrier(player);
		} else if(args[2].equals("off")) {
			CryoGlobals.HudMan.setCoordsBackGroundOn(false);
			addCommandBarrier(player);
			
			// Tell player the label has been changed.
			player.addChatMessage(makeMsg(COMMAND_PREFIX + 
									      EnumChatFormatting.DARK_GRAY + "Background for "  + 
									      EnumChatFormatting.AQUA + args[1] + 
									      EnumChatFormatting.DARK_GRAY + " set to " + 
									      EnumChatFormatting.AQUA + "off" + 
									      EnumChatFormatting.DARK_GRAY + "."));
			
			addCommandBarrier(player);
		} else {
			addCommandBarrier(player);
			player.addChatMessage(makeMsg(COMMAND_PREFIX + 
				      EnumChatFormatting.DARK_GRAY + "Background for "  + 
				      EnumChatFormatting.AQUA + args[1] + 
				      EnumChatFormatting.DARK_GRAY + " must be set to " + 
				      EnumChatFormatting.AQUA + "on" + 
				      EnumChatFormatting.DARK_AQUA + " or " + 
				      EnumChatFormatting.AQUA + "off" + 
				      EnumChatFormatting.DARK_GRAY + "."));
			addCommandBarrier(player);
		}
	}
	
	/**
	 * Tells HudManager to set facing background to on or off
	 * and Notifies user the command worked.
	 * 
	 * @param player - player chat messages are sent to.
	 */
	private void setFacingBackground(EntityPlayer player, String [] args) {
		// Tell HudManager to set the label.
		if(args[2].equals("on")) { 
			CryoGlobals.HudMan.setFacingBackGroundOn(true);
			addCommandBarrier(player);
			
			// Tell player the label has been changed.
			player.addChatMessage(makeMsg(COMMAND_PREFIX + 
									      EnumChatFormatting.DARK_GRAY + "Background for "  + 
									      EnumChatFormatting.AQUA + args[1] + 
									      EnumChatFormatting.DARK_GRAY + " set to " + 
									      EnumChatFormatting.AQUA + "on" + 
									      EnumChatFormatting.DARK_GRAY + "."));
			
			addCommandBarrier(player);
		} else if(args[2].equals("off")) {
			CryoGlobals.HudMan.setFacingBackGroundOn(false);
			addCommandBarrier(player);
			
			// Tell player the label has been changed.
			player.addChatMessage(makeMsg(COMMAND_PREFIX + 
									      EnumChatFormatting.DARK_GRAY + "Background for "  + 
									      EnumChatFormatting.AQUA + args[1] + 
									      EnumChatFormatting.DARK_GRAY + " set to " + 
									      EnumChatFormatting.AQUA + "off" + 
									      EnumChatFormatting.DARK_GRAY + "."));
			
			addCommandBarrier(player);
		} else {
			addCommandBarrier(player);
			player.addChatMessage(makeMsg(COMMAND_PREFIX + 
				      EnumChatFormatting.DARK_GRAY + "Background for "  + 
				      EnumChatFormatting.AQUA + args[1] + 
				      EnumChatFormatting.DARK_GRAY + " must be set to " + 
				      EnumChatFormatting.AQUA + "on" + 
				      EnumChatFormatting.DARK_AQUA + " or " + 
				      EnumChatFormatting.AQUA + "off" + 
				      EnumChatFormatting.DARK_GRAY + "."));
			addCommandBarrier(player);
		}
	}
	
	/**
	 * Tells HudManager to set memory background to on or off
	 * and Notifies user the command worked.
	 * 
	 * @param player - player chat messages are sent to.
	 */
	private void setMemBackground(EntityPlayer player, String [] args) {
		// Tell HudManager to set the label.
		if(args[2].equals("on")) { 
			CryoGlobals.HudMan.setMemBackGroundOn(true);
			addCommandBarrier(player);
			
			// Tell player the label has been changed.
			player.addChatMessage(makeMsg(COMMAND_PREFIX + 
									      EnumChatFormatting.DARK_GRAY + "Background for "  + 
									      EnumChatFormatting.AQUA + args[1] + 
									      EnumChatFormatting.DARK_GRAY + " set to " + 
									      EnumChatFormatting.AQUA + "on" + 
									      EnumChatFormatting.DARK_GRAY + "."));
			
			addCommandBarrier(player);
		} else if(args[2].equals("off")) {
			CryoGlobals.HudMan.setMemBackGroundOn(false);
			addCommandBarrier(player);
			
			// Tell player the label has been changed.
			player.addChatMessage(makeMsg(COMMAND_PREFIX + 
									      EnumChatFormatting.DARK_GRAY + "Background for "  + 
									      EnumChatFormatting.AQUA + args[1] + 
									      EnumChatFormatting.DARK_GRAY + " set to " + 
									      EnumChatFormatting.AQUA + "off" + 
									      EnumChatFormatting.DARK_GRAY + "."));
			
			addCommandBarrier(player);
		} else {
			addCommandBarrier(player);
			player.addChatMessage(makeMsg(COMMAND_PREFIX + 
				      EnumChatFormatting.DARK_GRAY + "Background for "  + 
				      EnumChatFormatting.AQUA + args[1] + 
				      EnumChatFormatting.DARK_GRAY + " must be set to " + 
				      EnumChatFormatting.AQUA + "on" + 
				      EnumChatFormatting.DARK_AQUA + " or " + 
				      EnumChatFormatting.AQUA + "off" + 
				      EnumChatFormatting.DARK_GRAY + "."));
			addCommandBarrier(player);
		}
	}
	
	private void setQuestBackground(EntityPlayer player, String [] args) {
		// Tell HudManager to set the label.
		if(args[2].equals("on")) { 
			CryoGlobals.HudMan.setQuestBackGroundOn(true);
			addCommandBarrier(player);
			
			// Tell player the label has been changed.
			player.addChatMessage(makeMsg(COMMAND_PREFIX + 
									      EnumChatFormatting.DARK_GRAY + "Background for "  + 
									      EnumChatFormatting.AQUA + args[1] + 
									      EnumChatFormatting.DARK_GRAY + " set to " + 
									      EnumChatFormatting.AQUA + "on" + 
									      EnumChatFormatting.DARK_GRAY + "."));
			
			addCommandBarrier(player);
		} else if(args[2].equals("off")) {
			CryoGlobals.HudMan.setQuestBackGroundOn(false);
			addCommandBarrier(player);
			
			// Tell player the label has been changed.
			player.addChatMessage(makeMsg(COMMAND_PREFIX + 
									      EnumChatFormatting.DARK_GRAY + "Background for "  + 
									      EnumChatFormatting.AQUA + args[1] + 
									      EnumChatFormatting.DARK_GRAY + " set to " + 
									      EnumChatFormatting.AQUA + "off" + 
									      EnumChatFormatting.DARK_GRAY + "."));
			
			addCommandBarrier(player);
		} else {
			addCommandBarrier(player);
			player.addChatMessage(makeMsg(COMMAND_PREFIX + 
				      EnumChatFormatting.DARK_GRAY + "Background for "  + 
				      EnumChatFormatting.AQUA + args[1] + 
				      EnumChatFormatting.DARK_GRAY + " must be set to " + 
				      EnumChatFormatting.AQUA + "on" + 
				      EnumChatFormatting.DARK_AQUA + " or " + 
				      EnumChatFormatting.AQUA + "off" + 
				      EnumChatFormatting.DARK_GRAY + "."));
			addCommandBarrier(player);
		}
	}
	
	private void setArrowsBackground(EntityPlayer player, String [] args) {
		// Tell HudManager to set the label.
		if(args[2].equals("on")) { 
			CryoGlobals.HudMan.setArrowBackGroundOn(true);
			addCommandBarrier(player);
			
			// Tell player the label has been changed.
			player.addChatMessage(makeMsg(COMMAND_PREFIX + 
									      EnumChatFormatting.DARK_GRAY + "Background for "  + 
									      EnumChatFormatting.AQUA + args[1] + 
									      EnumChatFormatting.DARK_GRAY + " set to " + 
									      EnumChatFormatting.AQUA + "on" + 
									      EnumChatFormatting.DARK_GRAY + "."));
			
			addCommandBarrier(player);
		} else if(args[2].equals("off")) {
			CryoGlobals.HudMan.setArrowBackGroundOn(false);
			addCommandBarrier(player);
			
			// Tell player the label has been changed.
			player.addChatMessage(makeMsg(COMMAND_PREFIX + 
									      EnumChatFormatting.DARK_GRAY + "Background for "  + 
									      EnumChatFormatting.AQUA + args[1] + 
									      EnumChatFormatting.DARK_GRAY + " set to " + 
									      EnumChatFormatting.AQUA + "off" + 
									      EnumChatFormatting.DARK_GRAY + "."));
			
			addCommandBarrier(player);
		} else {
			addCommandBarrier(player);
			player.addChatMessage(makeMsg(COMMAND_PREFIX + 
				      EnumChatFormatting.DARK_GRAY + "Background for "  + 
				      EnumChatFormatting.AQUA + args[1] + 
				      EnumChatFormatting.DARK_GRAY + " must be set to " + 
				      EnumChatFormatting.AQUA + "on" + 
				      EnumChatFormatting.DARK_AQUA + " or " + 
				      EnumChatFormatting.AQUA + "off" + 
				      EnumChatFormatting.DARK_GRAY + "."));
			addCommandBarrier(player);
		}
	}
	
	private void setFpsStatus(EntityPlayer player, String [] args) {
		
		if(args[0].equals("enable")) {
			
			CryoGlobals.HudMan.setEnabledFps(true);
			
		} else {
			
			CryoGlobals.HudMan.setEnabledFps(false);
			
		}
			
		addCommandBarrier(player);
		player.addChatMessage(makeMsg(COMMAND_PREFIX + 
			      EnumChatFormatting.AQUA + args[1] + 
			      EnumChatFormatting.DARK_GRAY + " Has been " + 
			      EnumChatFormatting.AQUA + args[0] +  "d" +
			      EnumChatFormatting.DARK_GRAY + "."));
		addCommandBarrier(player);
	}
	
	private void setCoordsStatus(EntityPlayer player, String [] args) {
		
		if(args[0].equals("enable")) {
			
			CryoGlobals.HudMan.setEnabledCoords(true);
			
		} else {
			
			CryoGlobals.HudMan.setEnabledCoords(false);
			
		}
			
		addCommandBarrier(player);
		player.addChatMessage(makeMsg(COMMAND_PREFIX + 
			      EnumChatFormatting.AQUA + args[1] + 
			      EnumChatFormatting.DARK_GRAY + " Has been " + 
			      EnumChatFormatting.AQUA + args[0] +  "d" +
			      EnumChatFormatting.DARK_GRAY + "."));
		addCommandBarrier(player);
	}

	private void setFacingStatus(EntityPlayer player, String [] args) {
	
		if(args[0].equals("enable")) {
			
			CryoGlobals.HudMan.setEnabledFacing(true);
			
		} else {
			
			CryoGlobals.HudMan.setEnabledFacing(false);
			
		}
			
		addCommandBarrier(player);
		player.addChatMessage(makeMsg(COMMAND_PREFIX + 
			      EnumChatFormatting.AQUA + args[1] + 
			      EnumChatFormatting.DARK_GRAY + " Has been " + 
			      EnumChatFormatting.AQUA + args[0] +  "d" +
			      EnumChatFormatting.DARK_GRAY + "."));
		addCommandBarrier(player);
	}
	
	private void setMemStatus(EntityPlayer player, String [] args) {
		
		if(args[0].equals("enable")) {
			
			CryoGlobals.HudMan.setEnabledMem(true);
			
		} else {
			
			CryoGlobals.HudMan.setEnabledMem(false);
			
		}
			
		addCommandBarrier(player);
		player.addChatMessage(makeMsg(COMMAND_PREFIX + 
			      EnumChatFormatting.AQUA + args[1] + 
			      EnumChatFormatting.DARK_GRAY + " Has been " + 
			      EnumChatFormatting.AQUA + args[0] +  "d" + 
			      EnumChatFormatting.DARK_GRAY + "."));
		addCommandBarrier(player);
	}
	
	private void setQuestStatus(EntityPlayer player, String [] args) {
		
		if(args[0].equals("enable")) {
			
			CryoGlobals.HudMan.setEnabledQuest(true);
			
		} else {
			
			CryoGlobals.HudMan.setEnabledQuest(false);
			
		}
			
		addCommandBarrier(player);
		player.addChatMessage(makeMsg(COMMAND_PREFIX + 
			      EnumChatFormatting.AQUA + args[1] + 
			      EnumChatFormatting.DARK_GRAY + " Has been " + 
			      EnumChatFormatting.AQUA + args[0] +  "d" + 
			      EnumChatFormatting.DARK_GRAY + "."));
		addCommandBarrier(player);
	}
	
	private void setArmorStatus(EntityPlayer player, String [] args) {
		
		if(args[0].equals("enable")) {
			
			CryoGlobals.HudMan.setEnabledArmor(true);
			
		} else {
			
			CryoGlobals.HudMan.setEnabledArmor(false);
			
		}
			
		addCommandBarrier(player);
		player.addChatMessage(makeMsg(COMMAND_PREFIX + 
			      EnumChatFormatting.AQUA + args[1] + 
			      EnumChatFormatting.DARK_GRAY + " Has been " + 
			      EnumChatFormatting.AQUA + args[0] +  "d" +
			      EnumChatFormatting.DARK_GRAY + "."));
		addCommandBarrier(player);
	}

	private void setItemStatus(EntityPlayer player, String [] args) {
		
		if(args[0].equals("enable")) {
			
			CryoGlobals.HudMan.setEnabledItem(true);
			
		} else {
			
			CryoGlobals.HudMan.setEnabledItem(false);
			
		}
			
		addCommandBarrier(player);
		player.addChatMessage(makeMsg(COMMAND_PREFIX + 
			      EnumChatFormatting.AQUA + args[1] + 
			      EnumChatFormatting.DARK_GRAY + " Has been " + 
			      EnumChatFormatting.AQUA + args[0] + "d" +
			      EnumChatFormatting.DARK_GRAY + "."));
		addCommandBarrier(player);
	}
	
	private void setArrowsStatus(EntityPlayer player, String [] args) {
		
		if(args[0].equals("enable")) {
			
			CryoGlobals.HudMan.setEnabledArrow(true);
			
		} else {
			
			CryoGlobals.HudMan.setEnabledArrow(false);
			
		}
			
		addCommandBarrier(player);
		player.addChatMessage(makeMsg(COMMAND_PREFIX + 
			      EnumChatFormatting.AQUA + args[1] + 
			      EnumChatFormatting.DARK_GRAY + " Has been " + 
			      EnumChatFormatting.AQUA + args[0] + "d" +
			      EnumChatFormatting.DARK_GRAY + "."));
		addCommandBarrier(player);
	}
	
	/**
	 * Tells HudManager to save the current values
	 * and Notifies user the command worked.
	 * 
	 * @param player - player chat messages are sent to.
	 */
	private void saveConfig(EntityPlayer player) {
		
		// Save the current config values to file.
		CryoGlobals.HudMan.saveMods();
		
		addCommandBarrier(player);
		
		// Tell the player the config values have been saved.
		player.addChatMessage(makeMsg(COMMAND_PREFIX + 
									  EnumChatFormatting.DARK_GRAY + "Hud Settings Have Been Saved To File."));
		
		addCommandBarrier(player);
	}
	
	/**
	 * Display the help details for the color command.
	 * 
	 * @param player - player chat messages are sent to.
	 */
	private void displayHelpColor(EntityPlayer player) {
		
		addCommandBarrier(player);
		
		// Display help message for color command.
		player.addChatMessage(makeMsg(EnumChatFormatting.AQUA + " Help" +
									  EnumChatFormatting.DARK_GRAY + " - " +
				  					  EnumChatFormatting.AQUA + COMMAND_COLOR + 
				  					  EnumChatFormatting.DARK_GRAY + "."));
		
		addCommandBarrier(player);
		
		player.addChatMessage(makeMsg(EnumChatFormatting.LIGHT_PURPLE + " Example usage for changing color of fps counter to white."));
		player.addChatMessage(makeMsg(EnumChatFormatting.LIGHT_PURPLE + " color fps ffffff "));
		player.addChatMessage(makeMsg(EnumChatFormatting.LIGHT_PURPLE + " Omit the preceding 0x from the hex color value."));
		player.addChatMessage(makeMsg(EnumChatFormatting.LIGHT_PURPLE + " to find a color you like google a hex color picker."));
		
		addCommandBarrier(player);
	}
	
	/**
	 * Display the help details for the resethudpos command.
	 * 
	 * @param player - player chat messages are sent to.
	 */
	private void displayHelpReset(EntityPlayer player) {
		
		addCommandBarrier(player);
		
		// Display help message for resethudpos command.
		player.addChatMessage(makeMsg(EnumChatFormatting.AQUA + " Help" +
									  EnumChatFormatting.DARK_GRAY + " - " +
				  					  EnumChatFormatting.AQUA + COMMAND_RESET + 
				  					  EnumChatFormatting.DARK_GRAY + "."));
		
		addCommandBarrier(player);
		player.addChatMessage(makeMsg(EnumChatFormatting.LIGHT_PURPLE + " This command resets all hud mods to 1, 1."));
		addCommandBarrier(player);
	}
	
	/**
	 * Display the help details for the save command.
	 * 
	 * @param player - player chat messages are sent to.
	 */
	private void displayHelpSave(EntityPlayer player) {

		addCommandBarrier(player);
		
		// Display help message for save command.
		player.addChatMessage(makeMsg(EnumChatFormatting.AQUA + " Help" +
									  EnumChatFormatting.DARK_GRAY + " - " +
				  					  EnumChatFormatting.AQUA + "save" + 
				  					  EnumChatFormatting.DARK_GRAY + "."));
		
		addCommandBarrier(player);
		
		player.addChatMessage(makeMsg(EnumChatFormatting.LIGHT_PURPLE + " This command saves color and position of hud mods."));
		
		addCommandBarrier(player);
	}
	
	/**
	 * Display the help details for the label command.
	 * 
	 * @param player - player chat messages are sent to.
	 */
	private void displayHelpLabel (EntityPlayer player) {
		addCommandBarrier(player);
		
		// Display help message for color command.
		player.addChatMessage(makeMsg(EnumChatFormatting.AQUA + " Help" +
									  EnumChatFormatting.DARK_GRAY + " - " +
				  					  EnumChatFormatting.AQUA + COMMAND_LABEL + 
				  					  EnumChatFormatting.DARK_GRAY + "."));
		
		addCommandBarrier(player);
		
		player.addChatMessage(makeMsg(EnumChatFormatting.LIGHT_PURPLE + " Example usage for changing label of fps counter to \" FPS\"."));
		player.addChatMessage(makeMsg(EnumChatFormatting.LIGHT_PURPLE + " label fps %sFPS"));
		player.addChatMessage(makeMsg(EnumChatFormatting.LIGHT_PURPLE + " In order to do a space type %s."));
		
		addCommandBarrier(player);
	}
	
	/**
	 * Display the help details for the background command.
	 * 
	 * @param player - player chat messages are sent to.
	 */
	private void displayHelpBg(EntityPlayer player) {
		
		addCommandBarrier(player);
		
		// Display help message for background command.
		player.addChatMessage(makeMsg(EnumChatFormatting.AQUA + " Help" +
									  EnumChatFormatting.DARK_GRAY + " - " +
				  					  EnumChatFormatting.AQUA + COMMAND_BG + 
				  					  EnumChatFormatting.DARK_GRAY + "."));
		
		addCommandBarrier(player);
		player.addChatMessage(makeMsg(EnumChatFormatting.LIGHT_PURPLE + " This command will turn on or off a gray background"));
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
		player.addChatMessage(makeMsg(EnumChatFormatting.DARK_PURPLE + "  1. " + EnumChatFormatting.LIGHT_PURPLE + COMMAND_COLOR));
		player.addChatMessage(makeMsg(EnumChatFormatting.DARK_PURPLE + "  2. " + EnumChatFormatting.LIGHT_PURPLE + COMMAND_LABEL));
		player.addChatMessage(makeMsg(EnumChatFormatting.DARK_PURPLE + "  3. " + EnumChatFormatting.LIGHT_PURPLE + COMMAND_BG));
		player.addChatMessage(makeMsg(EnumChatFormatting.DARK_PURPLE + "  4. " + EnumChatFormatting.LIGHT_PURPLE + COMMAND_STATUS));
		player.addChatMessage(makeMsg(EnumChatFormatting.DARK_PURPLE + "  5. " + EnumChatFormatting.LIGHT_PURPLE + COMMAND_RESET));
		player.addChatMessage(makeMsg(EnumChatFormatting.DARK_PURPLE + "  6. " + EnumChatFormatting.LIGHT_PURPLE + COMMAND_HELP));
		player.addChatMessage(makeMsg(EnumChatFormatting.DARK_PURPLE + "  7. " + EnumChatFormatting.LIGHT_PURPLE + COMMAND_SAVE));
		
		
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
