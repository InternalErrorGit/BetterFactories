package net.internalerror.betterfactories.world.level.block.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.internalerror.betterfactories.fml.config.Configuration;
import net.internalerror.betterfactories.forge.energy.BFEnergyStorage;
import net.internalerror.betterfactories.forge.energy.IHasEnergyStorage;
import net.internalerror.betterfactories.forge.registry.BFBlockEntities;
import net.internalerror.betterfactories.network.NetworkMessages;
import net.internalerror.betterfactories.network.packet.EnergySyncS2CPacket;
import net.internalerror.betterfactories.network.packet.ItemStackSyncS2CPacket;
import net.internalerror.betterfactories.util.IHasItemStackHandler;
import net.internalerror.betterfactories.util.Util;
import net.internalerror.betterfactories.world.inventory.CrushingMachineMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Setter
@Getter
@Slf4j
public final class CrushingMachineBlockEntity extends BlockEntity implements IHasItemStackHandler, IHasEnergyStorage, MenuProvider {
  
  public static final int SLOT_COUNT = 12;
  
  public static final int SLOT_INDEX_INPUT = 0;
  public static final int SLOT_INDEX_SPEED_UPGRADE = 1;
  public static final int SLOT_INDEX_ENERGY_UPGRADE = 2;
  public static final int SLOT_INDEX_OUTPUT1 = 3;
  public static final int SLOT_INDEX_OUTPUT2 = 4;
  public static final int SLOT_INDEX_OUTPUT3 = 5;
  public static final int SLOT_INDEX_OUTPUT4 = 6;
  public static final int SLOT_INDEX_OUTPUT5 = 7;
  public static final int SLOT_INDEX_OUTPUT6 = 8;
  public static final int SLOT_INDEX_OUTPUT7 = 9;
  public static final int SLOT_INDEX_OUTPUT8 = 10;
  public static final int SLOT_INDEX_OUTPUT9 = 11;
  
  public static final int DATA_SIZE = 2;
  public static final int DATA_INDEX_PROGRESS = 0;
  public static final int DATA_INDEX_MAX_PROGRESS = 1;
  
  public static final String NBT_KEY_PROGRESS = "crushing_machine.progress";
  public static final String NBT_KEY_INVENTORY = "crushing_machine.inventory";
  public static final String NBT_KEY_ENERGY = "crushing_machine.energy";
  
  private ItemStackHandler itemStackHandler = createItemStackHandler();
  private BFEnergyStorage energyStorage = createEnergyStorage();
  
  private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();
  private LazyOptional<IEnergyStorage> lazyEnergyHandler = LazyOptional.empty();
  
  private ContainerData containerData;
  
  private int progress;
  private int maxProgress;
  
  public CrushingMachineBlockEntity(BlockPos pPos, BlockState pBlockState) {
    super(BFBlockEntities.CRUSHING_MACHINE_BLOCK_ENTITY.get(), pPos, pBlockState);
    containerData = createDataContainer();
  }
  
  private ContainerData createDataContainer() {
    return new ContainerData() {
      @Override
      public int get(int pIndex) {
        switch (pIndex) {
          case DATA_INDEX_PROGRESS -> {
            return progress;
          }
          case DATA_INDEX_MAX_PROGRESS -> {
            return maxProgress;
          }
          default -> throw new IllegalStateException("Unexpected value: " + pIndex);
        }
      }
      
      @Override
      public void set(int pIndex, int pValue) {
        switch (pIndex) {
          case DATA_INDEX_PROGRESS -> progress = pValue;
          case DATA_INDEX_MAX_PROGRESS -> maxProgress = pValue;
          default -> throw new IllegalStateException("Unexpected value: " + pIndex);
        }
      }
      
      @Override
      public int getCount() {
        return DATA_SIZE;
      }
    };
  }
  
  @Override
  public ItemStackHandler createItemStackHandler() {
    return new ItemStackHandler(SLOT_COUNT) {
      @Override
      protected void onContentsChanged(int slot) {
        setChanged();
        if (level == null) {
          logger.error("Level is 'null'", new NullPointerException());
        } else if (! level.isClientSide()) {
          NetworkMessages.sendToClients(ItemStackSyncS2CPacket.create(this, getBlockPos()));
        }
      }
    };
  }
  
  @Override
  public int getEnergyCapacity() {
    //TODO: Add energy upgrade implementation
    return Configuration.CRUSHING_MACHINE_BASE_ENERGY_CAPACITY.get();
  }
  
  @Override
  public int getMaxTransfer() {
    return Configuration.CRUSHING_MACHINE_BASE_ENERGY_TRANSFER.get();
  }
  
  @Override
  public BFEnergyStorage createEnergyStorage() {
    return new BFEnergyStorage(getEnergyCapacity(), getMaxTransfer()) {
      @Override
      public void onEnergyChanged() {
        setChanged();
        NetworkMessages.sendToClients(EnergySyncS2CPacket.create(getEnergyStored(), getBlockPos()));
      }
    };
  }
  
  @Override
  public @NotNull Component getDisplayName() {
    return Component.translatable("block.futuristic.factories.crushing_machine");
  }
  
  @Override
  public @NotNull AbstractContainerMenu createMenu(int pContainerId, @NotNull Inventory pPlayerInventory, @NotNull Player pPlayer) {
    NetworkMessages.sendToClients(EnergySyncS2CPacket.create(getEnergyStored(), getBlockPos()));
    return new CrushingMachineMenu(pContainerId, pPlayerInventory, this, this.containerData);
  }
  
  @Override
  public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> pCapability, @Nullable Direction pDirection) {
    if (pCapability == ForgeCapabilities.ENERGY) {
      return lazyEnergyHandler.cast();
    }
    
    if (pCapability == ForgeCapabilities.ITEM_HANDLER) {
      return lazyItemHandler.cast();
    }
    
    return super.getCapability(pCapability, pDirection);
  }
  
  @Override
  public void onLoad() {
    super.onLoad();
    lazyItemHandler = LazyOptional.of(() -> itemStackHandler);
    lazyEnergyHandler = LazyOptional.of(() -> energyStorage);
  }
  
  @Override
  public void invalidateCaps() {
    super.invalidateCaps();
    lazyItemHandler.invalidate();
    lazyEnergyHandler.invalidate();
  }
  
  @Override
  protected void saveAdditional(@NotNull CompoundTag pNBT) {
    pNBT.put(NBT_KEY_INVENTORY, itemStackHandler.serializeNBT());
    pNBT.putInt(NBT_KEY_PROGRESS, progress);
    pNBT.putInt(NBT_KEY_ENERGY, getEnergyStored());
    super.saveAdditional(pNBT);
  }
  
  @Override
  public void load(@NotNull CompoundTag pNBT) {
    itemStackHandler.deserializeNBT(pNBT.getCompound(NBT_KEY_INVENTORY));
    setProgress(pNBT.getInt(NBT_KEY_PROGRESS));
    setEnergy(pNBT.getInt(NBT_KEY_ENERGY));
    super.load(pNBT);
  }
  
  public void dropContents() {
    if (level == null) {
      logger.error("Level is 'null'", new NullPointerException());
    } else {
      Containers.dropContents(level, worldPosition, Util.copyInventory(getItemStackHandler()));
    }
  }
  
  
  public void tick(Level level) {
  
  }
}
