package erc.block;

import erc.message.ERC_MessageRailMiscStC;
import erc.message.ERC_PacketHandler;
import erc.tileEntity.TileEntityRailBase;
import erc.tileEntity.TileEntityRailRedstoneAccelerator;
import net.minecraft.block.Block;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * @author MOTTY
 *
 */
public class blockRailRedstoneAccelerator extends blockRailBase{
	
	@Override
	public TileEntityRailBase getTileEntityInstance() 
	{
		return new TileEntityRailRedstoneAccelerator();
	}

	@Override
	public boolean canProvidePower() 
	{
		return true;
	}

	public void onBlockAdded(World world, int x, int y, int z)
    {
        if (!world.isRemote)
        {
        	boolean flag = world.isBlockIndirectlyGettingPowered(x, y, z);
        	
        	if (flag)
            {
//        		 ERC_TileEntityRailRedstoneAccelerator rail = (ERC_TileEntityRailRedstoneAccelerator)world.getTileEntity(x, y, z);
//        		 boolean tgle = rail.getToggleFlag();
             	
//        		 if (flag != tgle)
                 {
//                 	rail.changeToggleFlag();
                 	world.setBlockMetadataWithNotify(x, y, z, 8^world.getBlockMetadata(x, y, z), 2);
//                 	ERC_PacketHandler.INSTANCE.sendToAll(new ERC_MessageRailMiscStC(rail));
                 	world.playAuxSFXAtEntity((PlayerEntity)null, 1003, x, y, z, 0); //Œø‰Ê‰¹H
                 }
            } 
        }
    }
    @Override
	public void onBlockPlacedBy(World world, int x, int y, int z, LivingEntity player, ItemStack p_149689_6_) 
    {
		super.onBlockPlacedBy(world, x, y, z, player, p_149689_6_);
		TileEntityRailRedstoneAccelerator rail = (TileEntityRailRedstoneAccelerator) world.getTileEntity(x, y, z);
		rail.setToggleFlag(0 != (8 & world.getBlockMetadata(x, y, z)));
    }

	// ÔÎ“ü—Í—p
    public void onNeighborBlockChange(World world, int x, int y, int z, Block block)
    {
        if (!world.isRemote)
        {
            boolean flag = world.isBlockIndirectlyGettingPowered(x, y, z);
            
            if (flag || block.canProvidePower())
            {
            	TileEntityRailRedstoneAccelerator rail = (TileEntityRailRedstoneAccelerator)world.getTileEntity(x, y, z);
            	boolean tgle = rail.getToggleFlag();

                if (flag != tgle)
                {
                	rail.changeToggleFlag();
                	ERC_PacketHandler.INSTANCE.sendToAll(new ERC_MessageRailMiscStC(rail));
                	world.playAuxSFXAtEntity((PlayerEntity)null, 1003, x, y, z, 0); //Œø‰Ê‰¹H
                }
            }
        }
    }
}
