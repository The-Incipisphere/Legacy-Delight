package io.thedogofchaos.legacydelight.common.tileentities

import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.tileentity.TileEntity
import java.util.*

/** TODO: Implement this bullshit. */
class TileEntityCuttingBoard : TileEntity() {
    fun tryAddItem(itemStack: ItemStack): Boolean {
        return false
    }

    fun tryProcessRecipe(heldStack: ItemStack, player: EntityPlayer): Boolean {
        return false
    }

    fun removeItems(): Optional<ItemStack> {
        return Optional.empty<ItemStack>()
    }

    fun tryStabTool(itemStack: ItemStack): Boolean {
        return false
    }
}
