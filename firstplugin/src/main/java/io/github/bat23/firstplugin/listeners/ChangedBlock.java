package io.github.bat23.firstplugin.listeners;

import java.util.List;

import org.bukkit.DyeColor;
import org.bukkit.GrassSpecies;
import org.bukkit.Material;
import org.bukkit.TreeSpecies;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Wolf;
import org.bukkit.material.Leaves;
import org.bukkit.material.LongGrass;
import org.bukkit.material.MaterialData;
import org.bukkit.material.SmoothBrick;
import org.bukkit.material.Tree;
import org.bukkit.material.Wool;
import org.bukkit.plugin.java.JavaPlugin;

public final class ChangedBlock {
	 private JavaPlugin plugin;
	    private Material originalMaterial;
	    private MaterialData originalData;
	    private Block original;
	    private long delay;
	    private int taskId;
	    private List<ChangedBlock> theList;
		private DyeColor originalWoolColor;
		private BlockFace originallogdirection;
		private TreeSpecies originallogtype;
		private TreeSpecies originalleavestype;
		private GrassSpecies originalgrassspecies;
		private Material originalsmoothbrickmaterial;

	    public ChangedBlock(JavaPlugin p, Block b, List<ChangedBlock> l, int d){
	        plugin = p;
	        original = b;
	        originalMaterial = b.getType();
	        originalData = b.getState().getData();
	        theList = l;
	        delay = d;
	        taskId = plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new ResetBlock(), delay);
	        this.getOriginalBlockActualData();
	        //if(block.getState().getData() instanceof Wool) {
	        	//BlockState bs = block.getState();
	        	//Wool wool = (Wool) bs.getData();
	        	//wool.setColor(DyeColor.MAGENTA);
	        	//bs.update();
	        //}
	    }

	    public boolean isSameLocation(Block b){
	        if(b.getLocation().getBlockX() == original.getLocation().getBlockX()
	                || b.getLocation().getBlockY() == original.getLocation().getBlockY()
	                || b.getLocation().getBlockZ() == original.getLocation().getBlockZ()){
	            return true;
	        }
	        return false;
	    }

	    public void setDelay(int d){
	        delay = 60;
	        plugin.getServer().getScheduler().cancelTask(taskId);
	        taskId = plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new ResetBlock(), delay);
	    }
	    
	    public void getOriginalBlockActualData() {
	    	if(originalData instanceof Wool) {
	            	Wool wool = (Wool) originalData;
	            	originalWoolColor = wool.getColor();
	        }
	    	if(originalData instanceof Tree) {
	    		Tree tree = (Tree) originalData;
	    		originallogtype = tree.getSpecies();
	    		originallogdirection = tree.getDirection();
	    	}
	    	if(originalData instanceof Leaves) {
	    		Leaves leaves = (Leaves) originalData;
	    		originalleavestype = leaves.getSpecies();
	    	}
	    	if(originalData instanceof LongGrass) {
	    		LongGrass longgrass = (LongGrass) originalData;
	    		originalgrassspecies = longgrass.getSpecies();
	    	}
	    	if(originalData instanceof SmoothBrick) {
	    		SmoothBrick smoothbrick = (SmoothBrick) originalData;
	    		originalsmoothbrickmaterial = smoothbrick.getMaterial();
	    	}
	    }

	    public class ResetBlock implements Runnable{

	        @Override
	        public void run() {
	            original.setType(originalMaterial);
	            //original.getState().setData(originalData);
		    	if(originalData instanceof Wool) {
		    		Wool wool = (Wool) originalData;
	            	wool.setColor(originalWoolColor);
	            	original.getState().update();
	            	return;
		    	}
		    	if(originalData instanceof Tree) {
		    		Tree tree = (Tree) originalData;
		    		originallogtype = tree.getSpecies();
		    		originallogdirection = tree.getDirection();
		    		return;
		    	}
		    	if(originalData instanceof Leaves) {
		    		Leaves leaves = (Leaves) originalData;
		    		originalleavestype = leaves.getSpecies();
		    		return;
		    	}
		    	if(originalData instanceof LongGrass) {
		    		LongGrass longgrass = (LongGrass) originalData;
		    		originalgrassspecies = longgrass.getSpecies();
		    		return;
		    	}
		    	if(originalData instanceof SmoothBrick) {
		    		SmoothBrick smoothbrick = (SmoothBrick) originalData;
		    		originalsmoothbrickmaterial = smoothbrick.getMaterial();
		    		return;
		    	}
	            original.getState().update();
	            theList.remove(this);
	            return;
	        }
	    }
	}
