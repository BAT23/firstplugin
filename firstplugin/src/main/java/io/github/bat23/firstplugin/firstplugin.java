package io.github.bat23.firstplugin;

import io.github.bat23.firstplugin.items.CustomItems;
import io.github.bat23.firstplugin.items.CustomItems.Items;
import io.github.bat23.firstplugin.items.Wand;
import io.github.bat23.firstplugin.listeners.InteractListener;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
/*
 * This is the main plugin class of the firstplugin Bukkit Minecraft
 * server plugin. It is named accordingly considering this is a learning
 * experience and my first plugin I have ever designed. This main plugin
 * class contains the commands of the entire plugin. The general version
 * of this plugin is contained in the plugin.yml file.
 * 
 *  Warning!! This file is part of firstplugin designed by BAT23 to teach
 *  himself to design such Bukkit Minecraft Server plugins. This plugin 
 *  has no software licenses and is not intended to be distributed by any 
 *  means!
 * 
 * @author Tyler (BAT23) Braithwaite
 * 
 * @version 7/10/2014
 */
public final class firstplugin extends JavaPlugin {
	 /*
	  * This method is called when the server runs the plugin at startup
	  * or if the plugin is reloaded while the server is on.
	  * 
	  * @see org.bukkit.plugin.java.JavaPlugin#onEnable()
	  */
	@Override
	public void onEnable() {
		getLogger().info("onEnable has been invoked!");
		getServer().getPluginManager().registerEvents(new InteractListener(this), this);
	}
	/*
	 * This method is called when the server stops at shutdown
	 * or if the plugin is reloaded while the server is on.
	 * 
	 * @see org.bukkit.plugin.java.JavaPlugin#onDisable()
	 */
 
	@Override
	public void onDisable() {
		getLogger().info("onDisable has been invoked!");
	}
	/*
	 * This method is called whenever a player issues a command related to this plugin
	 * 
	 * @param CommandSender this is the sender of the command, no matter whether it be sent by console or by a player
	 * @param Command this is the command issued
	 * @param String I am not exactly sure what that parameter is used for
	 * @param String[] This is the arguments in the sent command, example: /give 1 BAT23, where 1 and BAT23 are the arguments to the give command
	 * 
	 * @return boolean I believe this is read by the server to determine whether the command was executed or not
	 * 
	 * @see org.bukkit.plugin.java.JavaPlugin#onCommand(org.bukkit.command.CommandSender, org.bukkit.command.Command, java.lang.String, java.lang.String[])
	 */
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		//The first command is /batwand which gives the player a custom wand implemented in the classes CustomItems and Wand in the items package of this plugin
		if ( cmd.getName().equalsIgnoreCase( "batwand" )) {
			if (sender instanceof Player) {
				Player player = (Player) sender;
				Wand wand = new Wand(player, null);
				//CustomItems customitem1 = new CustomItems(); this is a statement that I used back when I didn't have a wand.java and instead the enum of it was used
				player.getInventory().addItem(wand.getCurrentWandState(wand));
				
			}
			return true;
		} 
		//the second command is /batspellbook which gives the player a custom spell book implememnted in the class CustomItems as an enum type
		if ( cmd.getName().equalsIgnoreCase( "batspellbook" )) {
			if (sender instanceof Player) {
				Player player = (Player) sender;
				CustomItems customitem2 = new CustomItems();
				player.getInventory().addItem(customitem2.getCustomItems(Items.SpellBook));
			}
			return true;
		}
		//the third command is /batgun which gives the player a custom gun implemented in the class CustomItems as an enum type
		if ( cmd.getName().equalsIgnoreCase("batgun")) {
			if (sender instanceof Player) {
				Player player = (Player) sender;
				CustomItems customitem3 = new CustomItems();
				player.getInventory().addItem(customitem3.getCustomItems(Items.Gun));
			}
		}
		return false;
	}
}
