package com.workshopcraft.SimpleHarvest;

import java.util.Collection;
import java.util.Iterator;

import net.minecraft.block.BlockCrops;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.Loader;
//import net.minecraftforge.event.entity.player.PlayerInteractEvent.Action;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;



public class CropHandler {

	public static final PropertyInteger AGE = PropertyInteger.create("age", 0, 7);
	public static final PropertyInteger AGE2 = PropertyInteger.create("age", 0, 3);
	
	
	public boolean isHarvestcraftInstalled()
	{

		if (Loader.isModLoaded("harvestcraft"))
		{
			return true;
		}
		return false;
		
	}
	
	public boolean isFlowercraftInstalled()
	{

		if (Loader.isModLoaded("flowercraft"))
		{
			return true;
		}
		return false;
		
	}
	
	@SubscribeEvent
	public void onPlayerUse(PlayerInteractEvent event)
	{
		//event.entityPlayer.getHeldItem().
		
		//Action a = event.getAction();
		World w = event.getWorld();
		BlockPos p = event.getPos();
		String un = "";
		String smod = "";
		EntityPlayer e = event.getEntityPlayer();
		boolean updated = false;
		
		if (event.getHand() == EnumHand.OFF_HAND)
		{
				//System.out.println(w.getBlockState(p).getBlock().getClass().getName());
				if  (w.getBlockState(p).getBlock() instanceof BlockCrops )
				{
						//you just right clicked a bush
						
						IBlockState b = w.getBlockState(p);

						int stage = 0;
						boolean fail = false;
						un = b.getBlock().getUnlocalizedName().substring(5);
						
						//smod = w.getBlockState(p).getBlock().getUnlocalizedName().substring(0,12);
						
						//System.out.println(un);
						if ((isHarvestcraftInstalled()) || (isFlowercraftInstalled()))
						{
							if (b.getBlock().getUnlocalizedName().substring(5, 8).equals("pam"))
							{
								{
									stage = (Integer)b.getValue(AGE2).intValue();
									//System.out.println("PAM CROP");
								} 
							} else
								if (un.equals("beetroots"))
								{
								
									stage = (Integer)b.getValue(AGE2).intValue();
								}
								else
								{
									//System.out.println("7 stage crop");
									stage = (Integer)b.getValue(AGE).intValue();
								}
								//System.out.println(stage);
						} else
						{
							if (un.equals("beetroots"))
							{
							
								stage = (Integer)b.getValue(AGE2).intValue();
							}
							else
							{
								//System.out.println("7 stage crop");
								stage = (Integer)b.getValue(AGE).intValue();
							}
							//System.out.println(stage);
						}
						
							if (!w.isRemote)
							{
							//System.out.println(stage);
							if ((isHarvestcraftInstalled()) || (isFlowercraftInstalled()))
							{
								if (b.getBlock().getUnlocalizedName().length()>9)
								{
									if (b.getBlock().getUnlocalizedName().substring(5, 8).equals("pam"))
									{
										Collection<ItemStack> items =b.getBlock().getDrops(w, p, b, 0);
										Iterator<ItemStack> i = items.iterator();
										while (i.hasNext())
										{
											ItemStack s = i.next();
											e.inventory.addItemStackToInventory(s);
											updated = true;
										}
									
									
										w.setBlockState(p, w.getBlockState(p).getBlock().getDefaultState());
									}
								}
							}
							if ((un.equals("beetroots")) && (stage == 3))
							{
									//special case for beetroot as it has 3 stages rather than 7.
									Collection<ItemStack> items =b.getBlock().getDrops(w, p, b, 0);
									Iterator<ItemStack> i = items.iterator();
									while (i.hasNext())
									{
										ItemStack s = i.next();
										e.inventory.addItemStackToInventory(s);
										updated = true;
									}
									
									
									w.setBlockState(p, w.getBlockState(p).getBlock().getDefaultState());
								
							}
							else if (stage == 7)
							{
								//reset stage to 0
								//getdrops
								
								Collection<ItemStack> items =b.getBlock().getDrops(w, p, b, 0);
								Iterator<ItemStack> i = items.iterator();
								while (i.hasNext())
								{
									ItemStack s = i.next();
									e.inventory.addItemStackToInventory(s);
									updated = true;
								}
								
								
								w.setBlockState(p, w.getBlockState(p).getBlock().getDefaultState());
								
							}
						}
						
						if (updated)
						{
							e.inventory.inventoryChanged=updated;
							
						}
						
						
				}
		
	    }
		
			
		
	}

}