package com.workshopcraft.simpleharvest;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CocoaBlock;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.NetherWartBlock;
import net.minecraft.world.level.block.state.BlockState;

public final class SimpleHarvest {
    public static final String MOD_ID = "simpleharvest";

    private SimpleHarvest() {
    }

    public static boolean harvest(Level level, BlockPos pos, Player player, InteractionHand hand) {
        if (hand != InteractionHand.MAIN_HAND || player.isSpectator()) return false;
        BlockState state = level.getBlockState(pos);
        BlockState replant = replant(state);
        if (replant == null) return false;
        if (level instanceof ServerLevel server) {
            for (ItemStack stack : Block.getDrops(state, server, pos, null)) {
                if (!player.getInventory().add(stack)) player.drop(stack, false);
            }
            level.setBlock(pos, replant, Block.UPDATE_ALL);
        }
        return true;
    }

    private static BlockState replant(BlockState state) {
        if (state.getBlock() instanceof CropBlock crop) return crop.isMaxAge(state) ? crop.getStateForAge(0) : null;
        if (state.getBlock() instanceof NetherWartBlock) {
            return state.getValue(NetherWartBlock.AGE) == NetherWartBlock.MAX_AGE ? state.setValue(NetherWartBlock.AGE, 0) : null;
        }
        if (state.getBlock() instanceof CocoaBlock) {
            return state.getValue(CocoaBlock.AGE) == CocoaBlock.MAX_AGE ? state.setValue(CocoaBlock.AGE, 0) : null;
        }
        return null;
    }
}
