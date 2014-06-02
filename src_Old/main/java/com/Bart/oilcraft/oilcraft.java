package com.Bart.oilcraft;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.Bart.oilcraft.Fluids.ModFluids;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;


@Mod (modid = oilcraft.modid, version = oilcraft.version)


public class oilcraft {
	public static final String modid = "Oilcraft";
	public static final String version = "Beta 1.0";
	
	@Instance
	public static oilcraft instance;
	
	

	public static CreativeTabs OilTab = new CreativeTabs("OilCraft"){
		public Item getTabIconItem() {
			return Items.cookie;
	 }
	};
	
	public static Block CobbleCompressor;
	
	
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent e){
		
		
		ModFluids.init();
		ModBlocks.init();
		CobbleCompressor = new CobbleCompressor(Material.iron).setBlockName("CobbleCompressor").setCreativeTab(OilTab);
	    GameRegistry.registerBlock(CobbleCompressor, "CobbleCompressor");
	    GameRegistry.addRecipe(new ItemStack(CobbleCompressor), new Object[]
	    {
	    	"CPC",
	    	"I I",
	    	"CCC",
	    	'C', Blocks.cobblestone,'P', Blocks.piston, 'I', Items.iron_ingot	    		
	    });
	}
	
	
        

	
	
	@EventHandler
	public void init(FMLInitializationEvent e){
		System.out.println("lol");
		
	}
	
}
