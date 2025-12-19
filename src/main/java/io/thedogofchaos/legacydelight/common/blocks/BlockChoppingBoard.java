package io.thedogofchaos.legacydelight.common.blocks;

import javax.annotation.Nullable;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
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

            /* TODO: Write proper logic for allowing the player to:
             * - Block: If the player isn't sneaking, and is holding something,
             *   attempt to add items (or a copy of the items, if in creative) to the chopping board. - DONE
             *   - TE: If the internal inventory isn't full, or the player isn't trying to add two different items at once,
             *     add the item(s) onto the chopping board.
             *   - TE: Else, fail the attempt.
             *   - Block: If the attempt to add items failed,
             *     attempt to perform a chopping recipe. - DONE
             *      - TE: If there is no recipe containing the input, fail the attempt with a chat message saying the item doesn't look cuttable.
             *      - TE: If there is a recipe containing the input, but the required tool is not the tool being used,
             *        fail the attempt with a chat message saying the player should use a different tool.
             *      - TE: Else, perform the recipe.
             * - Block: If the player is sneaking, and isn't holding anything,
             *   attempt to remove any items on the board.
             *     - TE: If the board has any items on it, remove & return said items. Else, return null.
             *         - Block: If the player is in Creative mode, do absolutely nothing with the return result of the attempt.
             *         - Block: Else, attempt to add the returned item to the player's inventory.
             *             - Block: Else, drop the items on top of the board.
             */

            @Nullable
            ItemStack heldStack = player.getHeldItem();
            // If the player isn't sneaking and has something in their hand...
            if (!player.isSneaking() && heldStack != null) {
                // ... we attempt to add items to the chopping board...
                // (If the player is in creative mode, copy the item instead of consuming it.)
                if (boardTE.addItem(player.capabilities.isCreativeMode ? heldStack.copy() : heldStack)) {
                    // ...if that succeeded, we return with success...
                    player.addChatMessage(new ChatComponentText("Added item to board."));
                    return true;
                } else {
                    // ...else, we try to perform a recipe.
                    player.addChatMessage(new ChatComponentText("Failed to add item to board. Attempting recipe..."));
                    if (boardTE.attemptRecipe(heldStack, player)) {
                        // TODO: add particles or soemthing
                        return true;
                    }
                }
            } else if (player.isSneaking() && heldStack == null) {

            }

//            if (!player.isSneaking() && heldStack != null) { // If the player is NOT sneaking and their hand is NOT empty...
//                if (player.getHeldItem().getItem() instanceof ItemFood) { // ...we check if the item they're holding is food...
//                    if (boardTE.addItem(player.capabilities.isCreativeMode ? heldStack.copy() : heldStack)) { // ...if so, we try to add the stack to the board.
//                        world.playSoundEffect(x, y, z, "", 1f, 0.8f);
//                        player.addChatMessage(new ChatComponentText("Added food stack to chopping board."));
//                        return true;
//                    } else {
//                        player.addChatMessage(new ChatComponentText("Failed add food stack to chopping board."));
//                    }
//                } else if ()
//            }
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
