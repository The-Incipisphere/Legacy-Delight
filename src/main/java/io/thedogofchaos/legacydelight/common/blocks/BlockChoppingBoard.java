package io.thedogofchaos.legacydelight.common.blocks;

import cpw.mods.fml.client.registry.RenderingRegistry;
import io.thedogofchaos.legacydelight.common.tileentity.TileEntityChoppingBoard;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockChoppingBoard extends BlockContainer {
    public BlockChoppingBoard() {
        super(Material.wood);
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityChoppingBoard();
    }

    public static int renderID = RenderingRegistry.getNextAvailableRenderId();
}
