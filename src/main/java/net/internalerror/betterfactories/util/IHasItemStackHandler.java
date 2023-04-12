package net.internalerror.betterfactories.util;

import net.minecraftforge.items.ItemStackHandler;

public interface IHasItemStackHandler {
  void setItemStackHandler(ItemStackHandler pItemStackHandler);
  
  ItemStackHandler getItemStackHandler();
  
  ItemStackHandler createItemStackHandler();
  
}
