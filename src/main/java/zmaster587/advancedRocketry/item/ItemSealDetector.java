package zmaster587.advancedRocketry.item;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fluids.IFluidBlock;
import zmaster587.advancedRocketry.util.SealableBlockHandler;
import zmaster587.libVulpes.LibVulpes;

/**
 * Detects if a block can be used to seal a room.
 * Created by Dark(DarkGuardsman, Robert) on 1/6/2016.
 */
public class ItemSealDetector extends Item
{
    //TODO make consume power?

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn,
			World worldIn, EntityPlayer playerIn, EnumHand hand) {
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemStackIn);
	}
	
	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer player,
			World world, BlockPos pos, EnumHand hand, EnumFacing facing,
			float hitX, float hitY, float hitZ) {
        if (!world.isRemote)
        {
            if (SealableBlockHandler.INSTANCE.isBlockSealed(world, pos))
            {
                player.addChatComponentMessage(new TextComponentString(LibVulpes.proxy.getLocalizedString("msg.sealdetector.sealed")));
            }
            else
            {
            	IBlockState state = world.getBlockState(pos);
                Material mat = state.getMaterial();
                if (SealableBlockHandler.INSTANCE.isMaterialBanned(mat))
                {
                    player.addChatComponentMessage(new TextComponentString(LibVulpes.proxy.getLocalizedString("msg.sealdetector.notsealmat")));
                }
                else if (SealableBlockHandler.INSTANCE.isBlockBanned(state.getBlock()))
                {
                    player.addChatComponentMessage(new TextComponentString(LibVulpes.proxy.getLocalizedString("msg.sealdetector.notsealblock")));
                }
                else if (SealableBlockHandler.isFulBlock(world, pos))
                {
                    player.addChatComponentMessage(new TextComponentString(LibVulpes.proxy.getLocalizedString("msg.sealdetector.notfullblock")));
                }
                else if (state.getBlock() instanceof IFluidBlock)
                {
                    player.addChatComponentMessage(new TextComponentString(LibVulpes.proxy.getLocalizedString("msg.sealdetector.fluid")));
                }
                else
                {
                    player.addChatComponentMessage(new TextComponentString(LibVulpes.proxy.getLocalizedString("msg.sealdetector.other")));
                }
            }
        }
        return EnumActionResult.SUCCESS;
	}
	
}
