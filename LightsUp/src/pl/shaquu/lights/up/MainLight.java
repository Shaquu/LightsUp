package pl.shaquu.lights.up;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class MainLight extends JavaPlugin implements CommandExecutor {
	public FileConfiguration config;
	
	@Override
	public void onEnable(){
		loadConfig();
		new Listener(this);
    	getCommand("lightsup").setExecutor(this);
    	getCommand("li").setExecutor(this);
        }
	@Override
	public void onDisable(){}
	public void loadConfig(){
		config = getConfig();
		config.options().copyDefaults(true);
		saveConfig();
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player)sender;
			if(cmd.getName().equalsIgnoreCase("lightsup") || cmd.getName().equalsIgnoreCase("li") && p.hasPermission("lightsup.command")){
				//set
				if(args[0].equalsIgnoreCase("set")){
						if(args[1]!=null){
							if(args[2]==null){args[2]="0";}
							getConfig().set("Blocks."+args[1], args[2]);
								saveConfig();
							p.sendMessage(args[1]+" set to "+args[2]);
						}
				}
				//delete
				else if(args[0].equalsIgnoreCase("delete")){
					if(args[1]!=null){
						getConfig().set("Blocks."+args[1], null); 
						p.sendMessage(args[1]+" deleted!");
						saveConfig();
					}
				}
				//default
				else if(args[0].equalsIgnoreCase("default")){
					if(args[1].equalsIgnoreCase("true")){
						getConfig().set("Blocks."+args[0], args[1]);
						saveConfig();
						p.sendMessage("Default switches enabled!");
					}
					if(args[1].equalsIgnoreCase("false")){
						getConfig().set("Blocks."+args[0], args[1]);
						saveConfig();
						p.sendMessage("Default switches disabled!");
					}
			}
				else if(args.length==0){
				p.sendMessage("Use /lightsup set [from] [to]"); 
				p.sendMessage("Or /lightsup default [true/false]");
				p.sendMessage("And /lightsup delete [id]");}
			}
			return true;
	}
}
