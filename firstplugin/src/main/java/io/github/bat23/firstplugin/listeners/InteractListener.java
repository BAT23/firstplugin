package io.github.bat23.firstplugin.listeners;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import io.github.bat23.firstplugin.items.CustomItems;
import io.github.bat23.firstplugin.items.CustomItems.Items;
import io.github.bat23.firstplugin.items.Wand;

import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.material.SmoothBrick;
import org.bukkit.material.Wool;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.BlockIterator;

public final class InteractListener implements Listener {
	Wand wand = new Wand(null, null);
	private List<ChangedBlock> changedList = new LinkedList<ChangedBlock>();
	JavaPlugin plugin;
	
	public InteractListener(JavaPlugin p) {
		plugin = p;
	}
	
	//this method could cause serious damage (in-game) and should not be implemented in many versions unless for testing
	//TODO refine data code for reset of block 
	@EventHandler
	public void ProjectileHit(ProjectileHitEvent e) {
		BlockIterator iterator = new BlockIterator(e.getEntity().getWorld(), e.getEntity().getLocation().toVector(), e.getEntity().getVelocity().normalize(), 0.0D, 4);
		Block hitBlock = null;
		Player player = (Player) e.getEntity().getShooter();
		while(iterator.hasNext()) {
			hitBlock = iterator.next();
			if( hitBlock.getType() != Material.AIR) {
				if(hitBlock.getType() == Material.STAINED_CLAY) { // normally should be wool but is stained clay for testing purposes
					break;
				}
			}
		}
		//if (hitBlock.getType() != Material.WOOL) {
			//changedList.add(new ChangedBlock(plugin, hitBlock, changedList, 30));
		//if(hitBlock.getType() == Material.WOOL) {
		if (hitBlock.getType() == Material.STAINED_CLAY) {
			BlockState bs = hitBlock.getState();
			Wool wool = (Wool) bs.getData();
			wool.setColor(DyeColor.BLUE);
			bs.update(true);
			//if(hitBlock.getState().getData() instanceof Wool) {
			
			//}
		}
		//} else {
			//ListIterator<ChangedBlock> it = changedList.listIterator();
            //ChangedBlock cb = null;
            //while(it.hasNext()){
                //cb = it.next();
                //if(cb.isSameLocation(hitBlock)){
                    //cb.setDelay(30);
                //}
            //}
		//}
		
	}
	
	@EventHandler
	public void EntityDamageEntity(EntityDamageByEntityEvent event) {
		if (event.getDamager() instanceof Snowball) {
			Snowball damager = (Snowball) event.getDamager();
			Player shooter = (Player) damager.getShooter();
			CustomItems customitem1 = new CustomItems();
			String gunitemmetaname = customitem1.getCustomItems(Items.Gun).getItemMeta().getDisplayName();
			if (shooter.getItemInHand().getItemMeta().getDisplayName().contains(gunitemmetaname)) {
				event.setDamage(20.0);
			}
		}
	}
	@EventHandler
	public void EntityClick(PlayerInteractEntityEvent event) {
		if (event.getPlayer().getItemInHand().getItemMeta().getDisplayName().contains("Wand")) {
			event.setCancelled(true);
			switch (wand.getCurrentSpell()) {
			case "Avada Kedavra" :
				wand.performSpell(null, event);
				event.getPlayer().sendMessage("Avada Kedavra!");
				break;
			case "Confringo" :
				wand.performSpell(null, event);
				event.getPlayer().sendMessage("Confringo!");
				break;
			}
		}
	}
	@EventHandler
	public void InventoryClick(InventoryClickEvent e){
		 Player p = (Player) e.getWhoClicked();  

	     if(e.getInventory().getTitle().contains(p.getName() + "'s Spell Book")){
	    	 e.setCancelled(true); //Cancel the event so they can't take items out of the inventory

	    	 if(e.getCurrentItem() == null){
	    		 return;
	         }
	    	 else if(e.getCurrentItem().getType() == Material.GOLD_BLOCK || e.getCurrentItem().getType() == Material.FIRE || e.getCurrentItem().getType() == Material.JACK_O_LANTERN){
	    		 if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Confringo")) {
	    			 p.sendMessage("Spell set to Confringo!"); //send the player a message when they click it
		    		 wand.setCurrentSpell("Confringo");
		    		 p.closeInventory(); //closes spellbook inventory
	    		 }
	    		 if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Reducto")) {
	    			  p.sendMessage("Spell set to Reducto!");
	    			  wand.setCurrentSpell("Reducto");
	    			  p.closeInventory();
	    		 }
	    		 if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Avada Kedavra")) {
	    			 p.sendMessage("Spell set to Avada Kedavra!");
	    			 wand.setCurrentSpell("Avada Kedavra");
	    			 p.closeInventory();
	    		 }
	    	 }
	     }
	 }
	@EventHandler (priority = EventPriority.HIGH)
	public void onRightCLick(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		wand.setWandOwner(player);
		if (event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR) {
			String eventitemmetaname = event.getItem().getItemMeta().getDisplayName();
			CustomItems customitem = new CustomItems();
			String wanditemmetaname = wand.getCurrentWandState(wand).getItemMeta().getDisplayName();
			String gunitememtaname = customitem.getCustomItems(Items.Gun).getItemMeta().getDisplayName();
			//String wanditemmetaname = customitem.getCustomItems(Items.Wand).getItemMeta().getDisplayName();
			//player.sendMessage(eventitemmetaname + ", the wand's display name is " + wanditemmetaname);
			String spellbookitemmetaname = customitem.getCustomItems(Items.SpellBook).getItemMeta().getDisplayName();
			if (event.getMaterial() == Material.STICK) {
				int comparison = wanditemmetaname.compareTo(eventitemmetaname);
				switch (comparison) {
				case 0 :
					int comparison2 = wand.getCurrentSpell().compareTo("Reducto");
					switch (comparison2) {
					case 0 :
						wand.performSpell(event, null);
						player.sendMessage("Reducto!");
					}
				}
			}
			if (event.getMaterial() == Material.BOOK) {
				int comparison = spellbookitemmetaname.compareTo(eventitemmetaname);
				switch (comparison) {
				case 0 :
					Inventory inventory = player.getServer().createInventory(null, 9, player.getName() + "'s Spell Book");
					inventory.addItem(customitem.getCustomItems(Items.SpellReductoBlock));
					inventory.addItem(customitem.getCustomItems(Items.SpellConfringoBlock));
					inventory.addItem(customitem.getCustomItems(Items.SpellAvadaKedavraBlock));
					player.openInventory(inventory);
				}
			}
			if (event.getMaterial() == Material.IRON_BARDING) {
				int comparison = gunitememtaname.compareTo(eventitemmetaname);
				switch (comparison) {
				case 0 :
					Snowball snowball = player.getWorld().spawn(player.getLocation(), Snowball.class);
					snowball.setShooter(player);
					snowball.setVelocity(player.getEyeLocation().getDirection().multiply(3));
				}
			}
		}
	}
}