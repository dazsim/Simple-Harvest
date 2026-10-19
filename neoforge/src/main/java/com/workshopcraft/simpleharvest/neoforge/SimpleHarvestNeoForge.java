package com.workshopcraft.simpleharvest.neoforge;

import com.workshopcraft.simpleharvest.SimpleHarvest;
import net.minecraft.world.InteractionResult;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

@Mod(SimpleHarvest.MOD_ID)
public class SimpleHarvestNeoForge {
    public SimpleHarvestNeoForge() {
        NeoForge.EVENT_BUS.addListener(SimpleHarvestNeoForge::onUseBlock);
    }

    private static void onUseBlock(PlayerInteractEvent.RightClickBlock e) {
        if (SimpleHarvest.harvest(e.getLevel(), e.getPos(), e.getEntity(), e.getHand())) {
            e.setCanceled(true);
            e.setCancellationResult(InteractionResult.SUCCESS);
        }
    }
}
