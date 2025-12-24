package io.thedogofchaos.legacydelight.common.registry;

import io.thedogofchaos.data_retroportata.DataRetroportata;
import net.minecraft.block.Block;

import cpw.mods.fml.common.registry.GameRegistry;
import io.thedogofchaos.legacydelight.common.blocks.BlockChoppingBoard;

public class ModBlocks {

    public static void register() {
        initBlocks();
        registerBlocks();
    }

    public static Block choppingBoard;

    private static void initBlocks() {
        choppingBoard = new BlockChoppingBoard().setBlockName("chopping_board")
            .setBlockTextureName(DataRetroportata.resLoc("chopping_board").toString());
    }

    private static void registerBlocks() {
        GameRegistry.registerBlock(choppingBoard, "chopping_board");
    }
}
