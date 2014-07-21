package io.github.bat23.firstplugin.items;

import java.awt.List;
import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Spider;
import org.bukkit.entity.Zombie;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public final class Wand {
	String currentspellname;
	ItemStack is;
	ItemMeta im;
	ArrayList<String> lore;
	Player wandowner;
	
	public Wand(Player owner, String spellname) {
		is = new ItemStack(Material.STICK);
		im = is.getItemMeta();
		lore = new ArrayList<String>();
		wandowner = owner;
		currentspellname = spellname;
	}
	
	public void setWandOwner(Player player) {
		wandowner = player;
	}
	
	public Player getWandOwner() {
		return wandowner;
	}
	
	public String getCurrentSpell() {
		return currentspellname;
	}
	
	public void setCurrentSpell(String spellname) {
		currentspellname = spellname;
	}
	
	public ItemStack getCurrentWandState(Wand wand) {
		im.setDisplayName(wandowner.getName() + "'s Wand");
		lore.add(ChatColor.RED + "Spells are in your Spell Book!");
		im.setLore(lore);
		is.setItemMeta(im);
		return is;
	}
	
	public void performSpell(PlayerInteractEvent ievent, PlayerInteractEntityEvent ientityevent) {
		switch (currentspellname) {
		case "Reducto" :
			ievent.getClickedBlock().breakNaturally(new ItemStack(Material.DIAMOND_PICKAXE));
			break;
		case "Confringo" :
			ientityevent.getRightClicked().setFireTicks(120);
			break;
		case "Avada Kedavra" :
			//if(ientityevent.getRightClicked() instanceof Zombie) {
				//Zombie zombie = (Zombie) ientityevent.getRightClicked();
				//zombie.setHealth(0);
			//}
			//if(ientityevent.getRightClicked() instanceof Player) {
				//Player player = (Player) ientityevent.getRightClicked();
				//player.setHealth(0);
			//}
			if(ientityevent.getRightClicked() instanceof LivingEntity) {
				LivingEntity alivethingy = (LivingEntity) ientityevent.getRightClicked();
				alivethingy.setHealth(0);
			}
			break;
		}
	}
}
