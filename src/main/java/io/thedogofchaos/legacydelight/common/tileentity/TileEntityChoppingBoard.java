package io.thedogofchaos.legacydelight.common.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

import javax.annotation.Nullable;
import java.util.Optional;

/// TODO: Implement this bullshit.
public class TileEntityChoppingBoard extends TileEntity {

    public boolean tryAddItem(ItemStack itemStack) {
        return false;
    }

    public boolean tryProcessRecipe(ItemStack heldStack, @Nullable EntityPlayer player) {
        return false;
    }

    public Optional<ItemStack> removeItems() {
        return Optional.empty();
    }

    public boolean tryStabTool(ItemStack itemStack) {
        return false;
    }
}
