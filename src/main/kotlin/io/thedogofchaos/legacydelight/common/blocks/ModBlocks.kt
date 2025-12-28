package io.thedogofchaos.legacydelight.common.blocks

import cpw.mods.fml.common.registry.GameRegistry
import io.thedogofchaos.legacydelight.LegacyDelight
import net.minecraft.block.Block

object ModBlocks {
    fun register() {
        initBlocks()
        registerBlocks()
    }
    lateinit var cutting_board: Block;

    private fun initBlocks() {
        cutting_board = BlockCuttingBoard().setBlockName("cutting_board")
            .setBlockTextureName(LegacyDelight.resLoc("cutting_board").toString())
    }

    fun registerBlocks() {
        GameRegistry.registerBlock(cutting_board, "cutting_board")
    }
}
