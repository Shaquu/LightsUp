package pl.shaquu.lights.up;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockRedstoneEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class Listener implements org.bukkit.event.Listener {

	private MainLight plugin;

    public Listener(MainLight plugin) {
		this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }
	@EventHandler
	public void onClickDef(PlayerInteractEvent e){
	    Action act = e.getAction();
	    Player p = e.getPlayer();
		if(act==Action.RIGHT_CLICK_BLOCK && p.getItemInHand().getType()==Material.AIR && p.hasPermission("lightsup.custom")){
			Block b = e.getClickedBlock();
				int id = e.getClickedBlock().getTypeId();
					if(this.plugin.config.getString("Blocks."+id)!=null){
			    		b.setTypeId(Integer.parseInt(this.plugin.config.getString("Blocks."+id)));
					}
		}
	}
	public void onPowered(BlockRedstoneEvent e){
		Block b = e.getBlock();
		int id = b.getTypeId();
			if(this.plugin.config.getString("Blocks."+id)!=null){
				b.setTypeId(Integer.parseInt(this.plugin.config.getString("Blocks."+id)));
			}
	}
	@EventHandler
	public void onClick(PlayerInteractEvent e){
		if(this.plugin.config.getString("Blocks.default").equalsIgnoreCase("true")){
	    Action act = e.getAction();
	    Player p = e.getPlayer();
	    if((act==Action.RIGHT_CLICK_BLOCK) && p.getItemInHand().getTypeId()==0){
		    Block b = e.getClickedBlock();
	    	if((b.getTypeId()==89)||(b.getTypeId()==124) && p.hasPermission("lightsup.glowstone")){
	    		b.setTypeId(123);
	    	}
	    	else if((b.getTypeId()==123) && p.hasPermission("lightsup.glowstone")){
	    		b.setTypeId(89);
	    	}
	    	else if((b.getTypeId()==75) && p.hasPermission("lightsup.torch")){
	    		b.setTypeId(50);
	    	}
	    	else if((b.getTypeId()==50) && p.hasPermission("lightsup.torch")){
	    		b.setTypeId(75);
	    	}
	    	else if((b.getTypeId()==86) && p.hasPermission("lightsup.pumpkin")){
	    		b.setTypeId(91);
	    	}
	    	else if((b.getTypeId()==91) && p.hasPermission("lightsup.pumpkin")){
	    		b.setTypeId(86);
	    	}
	    }
	}
	}	
}
