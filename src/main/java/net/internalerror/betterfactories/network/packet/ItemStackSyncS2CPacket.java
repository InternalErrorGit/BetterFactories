package net.internalerror.betterfactories.network.packet;

import net.internalerror.betterfactories.util.IHasItemStackHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.network.NetworkEvent;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;

public final class ItemStackSyncS2CPacket {
  private final ItemStackHandler itemStackHandler;
  private final BlockPos blockPos;
  
  public ItemStackSyncS2CPacket(ItemStackHandler pItemStackHandler, BlockPos pBlockPos) {
    this.itemStackHandler = pItemStackHandler;
    this.blockPos = pBlockPos;
  }
  
  public ItemStackSyncS2CPacket(FriendlyByteBuf pBuffer) {
    List<ItemStack> collection = pBuffer.readCollection(ArrayList::new, FriendlyByteBuf::readItem);
    itemStackHandler = new ItemStackHandler(collection.size());
    for (int i = 0; i < collection.size(); i++) {
      itemStackHandler.insertItem(i, collection.get(i), false);
    }
    
    blockPos = pBuffer.readBlockPos();
  }
  
  public static ItemStackSyncS2CPacket create(ItemStackHandler pItemStackHandler, BlockPos pBlockPos) {
    return new ItemStackSyncS2CPacket(pItemStackHandler, pBlockPos);
  }
  
  public void toBytes(FriendlyByteBuf pBuffer) {
    Collection<ItemStack> list = new ArrayList<>();
    for(int i = 0; i < itemStackHandler.getSlots(); i++) {
      list.add(itemStackHandler.getStackInSlot(i));
    }
    
    pBuffer.writeCollection(list, FriendlyByteBuf::writeItem);
    pBuffer.writeBlockPos(blockPos);
  }
  
  public boolean handle(Supplier<NetworkEvent.Context> pSupplier) {
    NetworkEvent.Context context = pSupplier.get();
    context.enqueueWork(() -> {
      if (Minecraft.getInstance().level == null) {
        throw new NullPointerException("Level = null");
      }
      if(Minecraft.getInstance().level.getBlockEntity(blockPos) instanceof IHasItemStackHandler blockEntity) {
        blockEntity.setItemStackHandler(itemStackHandler);
      }
    });
    return true;
  }
}
