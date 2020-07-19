package club.cryoexn.cryohud.mods;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import club.cryoexn.cryohud.gui.ScreenPosition;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.EnumChatFormatting;

public class ModNightQuestTimer extends ModDraggable {

	private final int DURATION = 720;
	
	private String modName;
	private ScreenPosition pos;
	private int color;
	private int dummyColor;
	private String label;
	private String quest;
	private boolean background;
	private boolean timerRunning;
	private boolean enabled;
	private TimerTask task;
	private int count; // Timer duration seconds.
	
	private SimpleDateFormat df;
	
	public ModNightQuestTimer() {
		this.modName = "Night Quest";
		this.color = 0xffffff;
		this.dummyColor = 0xff0000;
		this.quest = label;
		this.background = true;
		this.timerRunning = false;
		this.count = DURATION;
		this.df = new SimpleDateFormat("mm:ss");
		
		this.pos = this.load();
	}
	
	@Override
	public int getWidth() {
		String timeLeft = df.format(new Date((long)count*1000));
		
		if(this.timerRunning) {
			return font.getStringWidth(this.quest + timeLeft + " : ");
		} 
		
		return font.getStringWidth(this.label + timeLeft);
		
	}

	@Override
	public int getHeight() {
		return font.FONT_HEIGHT;
	}

	@Override
	public void render() {
		if(timerRunning) {
			if(this.pos != null) {
				
				String timeLeft = df.format(new Date((long)count*1000));
				
	 			if(background)
	 				GuiScreen.drawRect(this.pos.getAbsoluteX() - 1, this.pos.getAbsoluteY() - 1, this.getWidth() + this.pos.getAbsoluteX() + 2, this.getHeight() + this.pos.getAbsoluteY() + 1, 0x4f000000);
	 			
	 			font.drawString(EnumChatFormatting.BOLD + this.quest + EnumChatFormatting.RESET + " : " + EnumChatFormatting.WHITE + timeLeft + EnumChatFormatting.RESET, this.pos.getAbsoluteX() + 1, this.pos.getAbsoluteY() + 1, this.color, true);
	 		} else {
	 			this.pos = this.load();
	 		}
		}
	}

	@Override
	public void renderDummy() {
		String timeLeft = df.format(new Date((long)count*1000));
		
		font.drawString(this.label + timeLeft, this.pos.getAbsoluteX() + 1, this.pos.getAbsoluteY() + 1, this.dummyColor, true);
	}

	@Override
	public ScreenPosition getScreenPosition() {
		return this.pos;
	}

	@Override
	public void setScreenPosition(ScreenPosition sp) {
		this.pos = sp;
	}

	@Override
	public void save() {
		File config = new File("config/hud/nightQuest.txt");
		
		try {
			
			BufferedWriter bw = new BufferedWriter(new FileWriter(config));
			bw.write(this.enabled + "," + this.pos.getAbsoluteX() + "," + this.pos.getAbsoluteY() + "," + Integer.toHexString(this.color) + "," + this.label + "," + this.background);
			
			bw.flush();
			bw.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public ScreenPosition load() {
		File config = new File("config/hud/nightQuest.txt");

		try {
			
			BufferedReader br = new BufferedReader(new FileReader(config));
			String[] args = br.readLine().split(",");
			br.close();
			
			this.enabled = Boolean.parseBoolean(args[0]);
			this.color = Integer.parseInt(args[3], 16);
			this.label = EnumChatFormatting.BOLD + args[4] + EnumChatFormatting.RESET;
			this.background = Boolean.parseBoolean(args[5]);
			
			return new ScreenPosition(Integer.parseInt(args[1]), Integer.parseInt(args[2]));
			
		} catch (IOException e) {
			e.printStackTrace();
			
			// Default Position in case of Exception.
			return new ScreenPosition(1,1);	
		}
	}

	@Override
	public void setColor(int color) {
		this.color = color;
	}

	@Override
	public int getColor() {
		return this.color;
	}
	
	@Override
	public void setLabel(String label) {
		this.label = label;
	}

	@Override
	public void setBackgroundOn(boolean background) {
		this.background = background;
	}
	
	@Override
	public boolean isBackgroundOn() {
		return this.background;
	}

	@Override
	public void setEnabled(boolean isEnabled) {
		this.enabled = isEnabled;
	}

	@Override
	public boolean isEnabled() {
		return this.enabled;
	}
	
	public void startQuestTimer(String quest) {
		int delay  = 0;
		this.quest = label + quest;
		Timer timer = new Timer();
		
		this.task = new TimerTask() {
			
			@Override
			public void run() {
				
				timerRunning = true;

				while(count != 0) {
					try {
						Thread.sleep(1000L);
						count--;
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
				count = DURATION; // Reset timer.
				
				timerRunning = false;
			}
		};
		
		timer.schedule(task, delay);
	}
	
	public boolean isStarted() {
		return this.timerRunning;
	}

	@Override
	public String getModName() {
		return this.modName;
	}
}
