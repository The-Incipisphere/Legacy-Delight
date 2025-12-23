package io.thedogofchaos.legacydelight.common.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

/// TODO: Implement this bullshit.
public class TileEntityChoppingBoard extends TileEntity {

    public boolean addItem(ItemStack itemStack) {
        return false;
    }

    public boolean attemptRecipe(ItemStack heldStack, EntityPlayer player) {
        return false;
    }

    public ItemStack removeItems() {
        return null;
    }


}
