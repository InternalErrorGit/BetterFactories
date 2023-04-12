package net.internalerror.betterfactories.forge.items;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;

public class FilteredSlotItemHandler extends SlotItemHandler {
  
  private ItemLike itemFilter;
  private TagKey<Item> tagFilter;
  
  public FilteredSlotItemHandler(IItemHandler itemHandler, int index, int xPosition, int yPosition, TagKey<Item> pFilter) {
    super(itemHandler, index, xPosition, yPosition);
    this.tagFilter = pFilter;
  }
  
  public FilteredSlotItemHandler(IItemHandler itemHandler, int index, int xPosition, int yPosition, ItemLike pFilter) {
    super(itemHandler, index, xPosition, yPosition);
    this.itemFilter = pFilter;
  }
  
  @Override
  public boolean mayPlace(@NotNull ItemStack stack) {
    if (itemFilter != null && !itemFilter.asItem().equals(stack.getItem())) {
      return false;
    }
    
    if (tagFilter != null && ! stack.is(tagFilter)) {
      return false;
    }
    
    return super.mayPlace(stack);
  }
}

