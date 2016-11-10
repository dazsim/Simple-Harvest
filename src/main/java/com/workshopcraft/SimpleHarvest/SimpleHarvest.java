package com.workshopcraft.SimpleHarvest;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(modid = SimpleHarvest.MODID, version = SimpleHarvest.VERSION)
public class SimpleHarvest
{
    public static final String MODID = "simpleharvest";
    public static final String VERSION = "1.06";
    
    @EventHandler
    public void init(FMLInitializationEvent e) {
		//System.out.println("Registering Event Handler VanillaHandler");
			MinecraftForge.EVENT_BUS.register(new CropHandler());
	}
}
