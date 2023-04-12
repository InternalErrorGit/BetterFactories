package net.internalerror.betterfactories.forge.items;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;

public class ReadonlySlotItemHandler extends SlotItemHandler {
  public ReadonlySlotItemHandler(IItemHandler pItemHandler, int pIndex, int pXPosition, int pYPosition) {
    super(pItemHandler, pIndex, pXPosition, pYPosition);
  }
  
  @Override
  public boolean mayPlace(@NotNull ItemStack stack) {
    return false;
  }
}
