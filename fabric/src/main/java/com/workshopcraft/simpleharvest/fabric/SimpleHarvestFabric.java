package com.workshopcraft.simpleharvest.fabric;

import com.workshopcraft.simpleharvest.SimpleHarvest;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.world.InteractionResult;

public class SimpleHarvestFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        UseBlockCallback.EVENT.register((player, level, hand, hit) ->
                SimpleHarvest.harvest(level, hit.getBlockPos(), player, hand) ? InteractionResult.SUCCESS : InteractionResult.PASS);
    }
}
