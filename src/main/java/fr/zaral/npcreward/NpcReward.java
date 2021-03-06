package fr.zaral.npcreward;

import fr.zaral.npcreward.commands.CommandManager;
import fr.zaral.npcreward.listeners.ListenerManager;
import fr.zaral.npcreward.objects.RewardManager;
import fr.zaral.npcreward.objects.StageManager;
import fr.zaral.npcreward.utils.CodeUtils;
import fr.zaral.npcreward.utils.Metrics;
import lombok.Getter;

import java.io.IOException;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Zaral on 28/04/2016.
 */
public class NpcReward extends JavaPlugin {

    @Getter
    private static NpcReward instance;
    @Getter
    private Settings settings;

    @Override
    public void onEnable() {
        CodeUtils.logToConsole("Loading plugin...");
        instance = this;
       	

        CodeUtils.logToConsole("Loading settings...");
        settings = new Settings(this);
        CodeUtils.logToConsole("Loading messages...");
        Lang.initMessages();

        CodeUtils.logToConsole("Loading commands...");
        new CommandManager(this);

        CodeUtils.logToConsole("Loading listeners...");
        new ListenerManager(this);

        CodeUtils.logToConsole("Loading Stage Manager...");
        StageManager.get();
        
        CodeUtils.logToConsole("Loading Rewards...");
        RewardManager.get().loadRewards();

        try {
			new Metrics(this).start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @Override
    public void onDisable() {
    	StageManager.get().getStageList().forEach(stage-> {
    		stage.removeStageNoCd();
    	});
    }
}
