package io.thedogofchaos.legacydelight.common.blocks;

import javax.annotation.Nullable;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import com.gtnewhorizon.gtnhlib.client.model.ModelISBRH;

import io.thedogofchaos.legacydelight.common.tileentity.TileEntityCuttingBoard;
import io.thedogofchaos.legacydelight.common.util.BlockUtils;

import java.util.Optional;

public class BlockCuttingBoard extends BlockContainer {

    public BlockCuttingBoard() {
        super(Material.wood);
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float subX,
        float subY, float subZ) {
        TileEntity tileEntity = world.getTileEntity(x, y, z);
        if (tileEntity instanceof TileEntityCuttingBoard boardTE) { // sanity check
            if (world.isRemote) return false; // If this logic is being performed on the client, we GTFO

            /*
             *TODO: Write proper logic for the following:
             * - Block: If the player isn't sneaking, and is holding something,
             *   attempt to add the held item(s) (or a copy of the item(s), if in creative) to the cutting board.
             *   If this succeeds, return true.
             *   - TE: If the internal inventory isn't full, or the player isn't trying to add two different items at once,
             *     add the item(s) onto the cutting board, and return true.
             *   - TE: Else, attempt to perform a cutting recipe, and bubble up the return value.
             *      - TE: If there is no recipe containing the input, send a chat message saying the item doesn't look cuttable, and return false.
             *      - TE: If there is a recipe containing the input, but the player is holding the wrong tool, send a chat message saying the player should use a different tool, and return false.
             *      - TE: Else, perform the recipe, and return true.
             * - Block: If the player is sneaking, and is holding something,
             *   attempt to add the held item (or a copy of the item, if in creative) to the cutting board,
             *   also noting to the TE that it might be a tool being stabbed into the board.
             *   (kinda like the thing with axes IRL where you swing them into wood and leave them there)
             *   If this succeeds, return true.
             *   - TE: If it's a KNOWN TOOL (id or oredict), and the internal inventory is empty,
             *     add the tool (or a copy of it, if in creative) onto the board,
             *     updating a (YET TO BE NAMED) field to TRUE for the TESR to check, and return true.
             * - Block: If the player is sneaking, and isn't holding anything,
             *   attempt to remove any items on the board.
             *   - TE: If the board has any items on it, remove (from the internal inventory) & return said items inside an Optional. Else, return Optional.empty()
             *     - Block: If the returned Optional isn't empty,
             *       - Block: If the player ISN'T in Creative mode, attempt to add the returned item to the player's inventory.
             *         - Block: If false, drop the items on top of the board.
             *       - Block: Regardless, return true.
             *     - Block: Else, do absolutely nothing, and return false.
             * - Block: Else, return false.
             */

            @Nullable ItemStack heldStack = player.getHeldItem();
            if (!player.isSneaking() && heldStack != null) {
                if (boardTE.tryAddItem(player.capabilities.isCreativeMode ? heldStack.copy() : heldStack)) {
                    return true;
                } else {
                    return boardTE.tryProcessRecipe(heldStack, player);
                }
            } else if (player.isSneaking() && heldStack != null) {
                return boardTE.tryStabTool(player.capabilities.isCreativeMode ? heldStack.copy() : heldStack);
            } else if (player.isSneaking() && heldStack == null) {
                Optional<ItemStack> removedItems = boardTE.removeItems();
                removedItems.ifPresent(itemStack -> {
                    if (!player.capabilities.isCreativeMode && !player.inventory.addItemStackToInventory(itemStack)) {
                        EntityItem droppedItems = new EntityItem(world, x, y, z, itemStack);
                        droppedItems.delayBeforeCanPickup = 10;
                        world.spawnEntityInWorld(droppedItems);
                    } // if the player is in creative, or has free inventory space, our job here is done.
                });
                return removedItems.isPresent();
            }
        }
        return false;
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityCuttingBoard();
    }

    @Override
    public int getRenderType() {
        return ModelISBRH.JSON_ISBRH_ID;
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess p_149719_1_, int p_149719_2_, int p_149719_3_,
        int p_149719_4_) {
        BlockUtils.setSaneBounds(this, 1, 0, 1, 15, 1, 15);
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
        BlockUtils.setSaneBounds(this, 1, 0, 1, 15, 1, 15);
        return AxisAlignedBB
            .getBoundingBox(x + this.minX, y + this.minY, z + this.minZ, x + this.maxX, y + this.maxY, z + this.maxZ);
    }
}
