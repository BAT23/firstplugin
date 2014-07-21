package io.github.bat23.firstplugin.items;

import io.github.bat23.firstplugin.spells.Spell;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public final class CustomItems {
	
	public enum Items {
		SpellBook, Wand, SpellReductoBlock, SpellConfringoBlock, SpellAvadaKedavraBlock, Gun;
	}
	
	public ItemStack getCustomItems(Items item) {
		ItemStack is = null;
		ItemMeta im;
		ArrayList<String> lore;
		Spell currentspell = null;
		switch (item) {
		case SpellBook :
			is = new ItemStack(Material.BOOK, 1);
			im = is.getItemMeta();
			lore = new ArrayList<String>();
			im.setDisplayName("Spell Book");
			lore.add(ChatColor.RED + "This book contains all the spells you know.");
			im.setLore(lore);
			is.setItemMeta(im);
			break;
		case Wand :
			is = new ItemStack(Material.STICK, 1);
			im = is.getItemMeta();
			lore = new ArrayList<String>();
			currentspell = new Spell();
			String currentspellname = currentspell.getType();
			im.setDisplayName("Wand");
			lore.add(ChatColor.RED + "Spells are in your Spell Book!");
			im.setLore(lore);
			is.setItemMeta(im);
			break;
		case SpellReductoBlock :
			is = new ItemStack(Material.GOLD_BLOCK, 1);
			im = is.getItemMeta();
			lore = new ArrayList<String>();
			im.setDisplayName("Reducto");
			lore.add(ChatColor.RED + "This spell will destroy everything you click!");
			im.setLore(lore);
			is.setItemMeta(im);
			break;
		case SpellConfringoBlock :
			is = new ItemStack(Material.FIRE, 1);
			im = is.getItemMeta();
			lore = new ArrayList<String>();
			im.setDisplayName("Confringo");
			lore.add(ChatColor.RED + "This spell will set your target on fire!");
			im.setLore(lore);
			is.setItemMeta(im);
			break;
		case SpellAvadaKedavraBlock :
			is = new ItemStack(Material.JACK_O_LANTERN, 1);
			im = is.getItemMeta();
			lore = new ArrayList<String>();
			im.setDisplayName("Avada Kedavra");
			lore.add(ChatColor.RED + "This spell will murder your target!");
			im.setLore(lore);
			is.setItemMeta(im);
			break;
		case Gun :
			is = new ItemStack(Material.IRON_BARDING, 1);
			im = is.getItemMeta();
			lore = new ArrayList<String>();
			im.setDisplayName("Gun");
			lore.add(ChatColor.RED + "This is a gun that fires high damage bullets!");
			im.setLore(lore);
			is.setItemMeta(im);
			break;
		}
		return is;
	}
}
