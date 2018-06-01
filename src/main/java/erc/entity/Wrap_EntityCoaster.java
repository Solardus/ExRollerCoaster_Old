package erc.entity;

import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;

public abstract class Wrap_EntityCoaster extends Entity{

    public float rotationRoll;
    public float prevRotationRoll;

    
	public Wrap_EntityCoaster(World world) {
		super(world);
	}

	public boolean canBeRidden()
    {
        return true; // true : ����
    }
	
    public void setPosition(double x, double y, double z)
    {
        this.posX = x;
        this.posY = y;
        this.posZ = z;
        double f = this.width / 2.0F;
        double f1 = this.height / 2.0F;
        this.setEntityBoundingBox(new AxisAlignedBB(x - f, y - f1, z - f, x + f, y + f1, z + f));
    }
    
    @SideOnly(Side.CLIENT)
    public void setAngles(float deltax, float deltay)
    {
//    	ERC_CoasterAndRailManager.setAngles(deltax, deltay);
    }
	
	public void SyncCoasterMisc_Send(ByteBuf buf, int flag){}
	public void SyncCoasterMisc_Receive(ByteBuf buf, int flag){}
	public float getRoll(float partialTicks)
	{
		return prevRotationRoll + (rotationRoll - prevRotationRoll)*partialTicks;
	}
}