package fr.zaral.npcreward.npc;

import fr.zaral.npcreward.NpcReward;
import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.entity.Villager;

import java.util.ArrayList;

/**
 * Created by Zaral on 28/04/2016.
 */
public class NpcManager {

    private NpcReward pl;

    @Getter
    private ArrayList<Npc> npcList = new ArrayList<>();


    public NpcManager(NpcReward pl) {
        this.pl = pl;
    }

    public void spawnNpc(String name, Location location, Villager.Profession profession) {
        new Npc(this, name, location, profession);
    }

    public void spawnNpc(String name, Location location) {
        new Npc(this, name, location);
    }

}