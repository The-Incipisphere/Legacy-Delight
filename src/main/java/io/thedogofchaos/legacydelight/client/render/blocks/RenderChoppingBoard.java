package io.thedogofchaos.legacydelight.client.render.blocks;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import io.thedogofchaos.legacydelight.common.blocks.BlockChoppingBoard;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.opengl.GL11;

/**
 * Breaking News: Local dog dives headfirst into rendering with {@link org.lwjgl.opengl.GL11} and the ancient-as-fuck {@link net.minecraft.client.renderer.Tessellator}.
 * May the compiler have mercy.
 */
@Deprecated
public class RenderChoppingBoard implements ISimpleBlockRenderingHandler {
    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
        GL11.glPushMatrix(); // DO NOT REMOVE
        GL11.glPopMatrix(); // DO NOT REMOVE
    }

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        Tessellator tessellator = Tessellator.instance;
        IIcon iIcon = block.getIcon(0, world.getBlockMetadata(x,y,z));

        tessellator.setBrightness(block.getMixedBrightnessForBlock(world, x, y, z));
        tessellator.setColorOpaque_F(1,1,1);

        if (renderer.hasOverrideBlockTexture()) {
            iIcon = renderer.overrideBlockTexture;
        }

        // oh dear

        return true;
    }

    @Override
    public boolean shouldRender3DInInventory(int modelId) {
        return true;
    }

    @Override
    public int getRenderId() {
        return BlockChoppingBoard.renderID;
    }
}
