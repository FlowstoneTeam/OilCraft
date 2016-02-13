package thaumcraft.api.wands;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public interface IWandTriggerManager {

	/**
	 * This class will be called by wands with the proper parameters. It is up to you to decide what to do with them.
	 */
	boolean performTrigger(World world, ItemStack wand, EntityPlayer player,
						   BlockPos pos, EnumFacing side, int event);
	
}
