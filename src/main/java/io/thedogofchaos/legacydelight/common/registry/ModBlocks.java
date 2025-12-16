package io.thedogofchaos.legacydelight.common.registry;

import io.thedogofchaos.legacydelight.common.blocks.BlockChoppingBoard;
import net.minecraft.block.Block;
import net.minecraft.item.Item;

import cpw.mods.fml.common.registry.GameRegistry;


public class ModBlocks {
    public static void register() {
        initBlocks();
        registerBlocks();
    }

    public static Block choppingBoard;

    private static void initBlocks() {
        choppingBoard = new BlockChoppingBoard().setBlockName("choppingBoard");
    }

    private static void registerBlocks() {
        GameRegistry.registerBlock(choppingBoard, "choppingBoard");
    }
}
