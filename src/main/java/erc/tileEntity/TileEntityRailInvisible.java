package erc.tileEntity;

import erc.block.blockRailInvisible;
import erc.entity.ERC_EntityCoaster;
import erc.item.ERC_ItemWrench;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class TileEntityRailInvisible extends TileEntityRailBase{

	public TileEntityRailInvisible()
	{
		super();
		RailTexture = new ResourceLocation("textures/blocks/glass.png");
	}
	
	@Override
	public void SpecialRailProcessing(ERC_EntityCoaster EntityCoaster) {}

	@Override
	public World getWorldObj() {
		return this.world;
	}

	@Override
	public void render(Tessellator tess)
	{
		ItemStack is = Minecraft.getMinecraft().player.getHeldItemMainhand();
		if(!is.isEmpty()) {
			Item heldItem = is.getItem();
			if (Block.getBlockFromItem(heldItem) instanceof blockRailInvisible ||
					heldItem instanceof ERC_ItemWrench)
				super.render(tess);
		}
	}
}
