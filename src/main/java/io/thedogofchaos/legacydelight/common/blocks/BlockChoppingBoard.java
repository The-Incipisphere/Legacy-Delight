package io.thedogofchaos.legacydelight.common.blocks;

import static com.gtnewhorizon.gtnhlib.client.model.ModelISBRH.JSON_ISBRH_ID;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import io.thedogofchaos.legacydelight.common.tileentity.TileEntityChoppingBoard;

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

    @Override
    public int getRenderType() {
        return JSON_ISBRH_ID;
    }
}
