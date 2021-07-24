package net.kunmc.lab.jumpdead;

import com.destroystokyo.paper.event.player.PlayerJumpEvent;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.GameRule;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;


public final class JumpDead extends JavaPlugin implements Listener {
    private static JumpDead plugin;
    private static List<World> worldList = Bukkit.getWorlds();

    public static JumpDead getPlugin(){
        return plugin;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        worldList.forEach(world -> world.setGameRule(GameRule.SHOW_DEATH_MESSAGES, false));
        this.getServer().getPluginManager().registerEvents(this,this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler
    public void getJumpPlayer(PlayerJumpEvent e){
        Player player = e.getPlayer();
        if (player.getGameMode().equals(GameMode.CREATIVE)
                || player.getGameMode().equals(GameMode.SPECTATOR)
                || player.isInWater()) {
            return;
        }
        player.damage(1000);
        Bukkit.broadcastMessage(String.format("%sはジャンプをしたため死亡した", player.getName()));
    }
}
