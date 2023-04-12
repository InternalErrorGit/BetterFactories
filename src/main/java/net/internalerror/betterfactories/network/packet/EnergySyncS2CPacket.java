package net.internalerror.betterfactories.network.packet;

import net.internalerror.betterfactories.forge.energy.BFEnergyStorage;
import net.internalerror.betterfactories.forge.energy.IHasEnergyStorage;
import net.internalerror.betterfactories.util.IBFMenu;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public final class EnergySyncS2CPacket {
  
  private final int energy;
  private final BlockPos blockPos;
  
  public EnergySyncS2CPacket(int pEnergy, BlockPos pBlockPos) {
    energy = pEnergy;
    blockPos = pBlockPos;
  }
  
  public EnergySyncS2CPacket(FriendlyByteBuf pBuffer) {
    energy = pBuffer.readInt();
    blockPos = pBuffer.readBlockPos();
  }
  
  public static EnergySyncS2CPacket create(int pEnergy, BlockPos pBlockPos) {
    return new EnergySyncS2CPacket(pEnergy, pBlockPos);
  }
  
  
  public void toBytes(FriendlyByteBuf pBuffer) {
    pBuffer.writeInt(energy);
    pBuffer.writeBlockPos(blockPos);
  }
  
  
  public boolean handle(Supplier<NetworkEvent.Context> pSupplier) {
    NetworkEvent.Context context = pSupplier.get();
    
    context.enqueueWork(() -> {
      if (Minecraft.getInstance().level == null) {
        throw new NullPointerException("Level = null");
      }
      if (Minecraft.getInstance().level.getBlockEntity(blockPos) instanceof IHasEnergyStorage energyStorage) {
        energyStorage.setEnergy(energy);
        if (Minecraft.getInstance().player.containerMenu instanceof IBFMenu menuProvider &&
            menuProvider.getBlockEntity().getBlockPos().equals(blockPos)) {
          energyStorage.setEnergy(energy);
        }
      }
    });
    return true;
  }
  
}
