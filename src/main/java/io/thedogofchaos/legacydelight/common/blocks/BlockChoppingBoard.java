package io.thedogofchaos.legacydelight.common.blocks;

import javax.annotation.Nullable;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import com.gtnewhorizon.gtnhlib.client.model.ModelISBRH;

import io.thedogofchaos.legacydelight.common.tileentity.TileEntityChoppingBoard;
import io.thedogofchaos.legacydelight.common.util.BlockUtils;

public class BlockChoppingBoard extends BlockContainer {

    public BlockChoppingBoard() {
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
        if (tileEntity instanceof TileEntityChoppingBoard boardTE) { // sanity check
            if (world.isRemote) return false; // If this logic is being performed on the client, we GTFO

            /*
             *TODO: Write proper logic for the following:
             * - Block: If the player isn't sneaking, and is holding something,
             *   attempt to add the held item(s) (or a copy of the item(s), if in creative) to the chopping board.
             *   If this succeeds, return true.
             *   - TE: If the internal inventory isn't full, or the player isn't trying to add two different items at once,
             *     add the item(s) onto the chopping board, and return true.
             *   - TE: Else, attempt to perform a chopping recipe, and bubble up the return value.
             *      - TE: If there is no recipe containing the input, send a chat message saying the item doesn't look cuttable, and return false.
             *      - TE: If there is a recipe containing the input, send a chat message saying the player should use a different tool, and return false.
             *      - TE: Else, perform the recipe, and return true.
             * - Block: If the player is sneaking, and is holding something,
             *   attempt to add the held item (or a copy of the item, if in creative) to the chopping board,
             *   also noting to the TE that it might be a tool being stabbed into the board.
             *   (kinda like the thing with axes IRL where you swing them into wood and leave them there)
             *   If this succeeds, return true.
             *   - TE: If it's a KNOWN TOOL (id or oredict), and the internal inventory is empty,
             *     add the tool (or a copy of it, if in creative) onto the board,
             *     updating a (YET TO BE NAMED) field to TRUE for the TESR to check, and return true.
             * - Block: If the player is sneaking, and isn't holding anything,
             *   attempt to remove any items on the board. If this succeeds, return true.
             *   - TE: If the board has any items on it, remove (from the internal inventory) & return said items inside an Optional. Else, return Optional.empty()
             *     - Block: If the returned Optional isn't empty,
             *       - Block: If the player ISN'T in Creative mode, attempt to add the returned item to the player's inventory.
             *         - Block: If false, drop the items on top of the board.
             *       - Block: Regardless, return true.
             *     - Block: Else, do absolutely nothing, and return false.
             * - Block: Else, return false.
             */

            @Nullable ItemStack heldStack = player.getHeldItem();
            if (!player.isSneaking() && heldStack != null) { // If the player isn't sneaking and has something in their hand...
                if (boardTE.addItem(player.capabilities.isCreativeMode ? heldStack.copy() : heldStack)) { // ... we attempt to add items to the chopping board... (If the player is in creative mode, copy the item instead of consuming it.)
                    // ...if that succeeded, we return with success...
                    player.addChatMessage(new ChatComponentText("Added item to board."));
                    return true;
                } else {
                    player.addChatMessage(new ChatComponentText("Failed to add item to board. Attempting recipe..."));
                    if (boardTE.attemptRecipe(heldStack, player)) { // ...else, we try to perform a recipe.
                        // TODO: add particles or soemthing
                        return true;
                    }
                }
            } else if (player.isSneaking() && heldStack == null) { // If the player is sneaking and has nothing in their hand...
                @Nullable ItemStack removedItems = boardTE.removeItems(); // ...we try to remove the item...
                if (!player.capabilities.isCreativeMode) { // ...if the player isn't in creative mode...
                    if (!player.inventory.addItemStackToInventory(removedItems)) { // ...we try to add it to the player's inventory...
                        // ...and if that doesn't work, we drop the item on top of the chopping board.
                        EntityItem droppedItems = new EntityItem(world, x, y, z, removedItems);
                        droppedItems.delayBeforeCanPickup = 10;
                        world.spawnEntityInWorld(droppedItems);
                    }
                } // ...else, if the player is in creative mode, we do nothing with the removed item.
            }
        }
        return false;
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityChoppingBoard();
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
        BlockUtils.setSaneBounds(this, 1, 0, 1, 15, 1, 15);;
        return AxisAlignedBB
            .getBoundingBox(x + this.minX, y + this.minY, z + this.minZ, x + this.maxX, y + this.maxY, z + this.maxZ);
    }
}
