package io.thedogofchaos.legacydelight.common.blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.audio.SoundCategory;
import net.minecraft.client.audio.SoundList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import com.gtnewhorizon.gtnhlib.client.model.ModelISBRH;

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
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float subX, float subY, float subZ) {
        TileEntity tileEntity = world.getTileEntity(x, y, z);
        if (tileEntity instanceof TileEntityChoppingBoard boardTE) { // sanity check
            // if the world is loaded on the client, bail early.
            if (world.isRemote) return true;

            /* TODO: Write proper logic for allowing the player to:
             * - Attempt to add items to the chopping board by right-clicking with any item stack
             *   - TE: If the internal inventory is full, or the player is trying to add two different items at once, fail the attempt.
             *   - TE: Else, add the item onto the chopping board.
             * - Attempt to perform a chopping recipe by right-clicking the board with a tool while an item is present.
             *   - TE: If there is no recipe containing the input, fail the attempt with a chat message saying the item doesn't look cuttable.
             *   - TE: If there is a recipe containing the input, but the required tool is not the tool being used,
             *     fail the attempt with a chat message saying the player should use a different tool.
             *   - TE: Else, perform the recipe.
             */

//            ItemStack heldStack = player.getHeldItem();
//            if (boardTE.isEmpty()) { // If the board has nothing on it..
//
//
//            }
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
    public void setBlockBoundsBasedOnState(IBlockAccess worldIn, int x, int y, int z) {
        this.setBlockBounds(1f, 0f, 1f, 15f, 1f, 15f);
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World worldIn, int x, int y, int z) {
        this.setBlockBounds(1f, 0f, 1f, 15f, 1f, 15f);
        return AxisAlignedBB.getBoundingBox(x + this.minX, y + this.minY, z + this.minZ, x + this.maxX, y + this.maxY, z + this.maxZ);
    }
}
