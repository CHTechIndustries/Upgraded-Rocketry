package zmaster587.advancedRocketry.entity;

import zmaster587.advancedRocketry.tile.station.TilePlanetaryHologram;
import zmaster587.libVulpes.inventory.modules.IButtonInventory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class EntityUIButton extends EntityUIPlanet {

	int id;
	TilePlanetaryHologram tile;
	
	public EntityUIButton(World worldIn, int id, TilePlanetaryHologram tile) {
		this(worldIn);
		this.id = id;
		this.tile = tile;
	}
	
	public EntityUIButton(World worldIn) {
		super(worldIn);
		setSize(0.2f, 0.2f);
	}
	
	@Override
	protected void entityInit() {
		this.dataManager.register(planetID, id);
		this.dataManager.register(scale, 1f);
		this.dataManager.register(selected, false);
		
	}
	
	@Override
	public boolean processInitialInteract(EntityPlayer player, ItemStack stack,
			EnumHand hand) {
		if(!worldObj.isRemote && tile != null) {
			tile.onInventoryButtonPressed(getPlanetID());
		}
		return true;
	}
	
	public int getPlanetID() {
		return this.dataManager.get(planetID);
	}
}
