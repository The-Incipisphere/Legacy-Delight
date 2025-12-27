package io.thedogofchaos.legacydelight.common.registry;

import net.minecraft.block.Block;

import cpw.mods.fml.common.registry.GameRegistry;
import io.thedogofchaos.legacydelight.common.blocks.BlockCuttingBoard;

import static io.thedogofchaos.legacydelight.LegacyDelight.resLoc;

public class ModBlocks {

    public static void register() {
        initBlocks();
        registerBlocks();
    }

    public static Block cutting_board;

    private static void initBlocks() {
        cutting_board = new BlockCuttingBoard().setBlockName("cutting_board")
            .setBlockTextureName(resLoc("cutting_board").toString());
    }

    private static void registerBlocks() {
        GameRegistry.registerBlock(cutting_board, "cutting_board");
    }
}
