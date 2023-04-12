package net.internalerror.betterfactories.world.inventory;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.internalerror.betterfactories.forge.items.FilteredSlotItemHandler;
import net.internalerror.betterfactories.forge.items.ReadonlySlotItemHandler;
import net.internalerror.betterfactories.forge.registry.BFBlocks;
import net.internalerror.betterfactories.forge.registry.BFItems;
import net.internalerror.betterfactories.forge.registry.BFMenuTypes;
import net.internalerror.betterfactories.util.IBFMenu;
import net.internalerror.betterfactories.world.level.block.entity.CrushingMachineBlockEntity;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import org.jetbrains.annotations.NotNull;

@Getter
@Slf4j
public final class CrushingMachineMenu extends AbstractContainerMenu implements IBFMenu {
  
  private final CrushingMachineBlockEntity blockEntity;
  private final Level level;
  private final ContainerData data;
  
  public CrushingMachineMenu(int pContainerId, Inventory pPlayerInventory, FriendlyByteBuf pBuffer) {
    this(pContainerId, pPlayerInventory, pPlayerInventory.player.level.getBlockEntity(pBuffer.readBlockPos()), new SimpleContainerData(2));
  }
  
  public CrushingMachineMenu(int pContainerId, Inventory pPlayerInventory, BlockEntity pBlockEntity, ContainerData pData) {
    super(BFMenuTypes.CRUSHING_MACHINE_MENU.get(), pContainerId);
    
    checkContainerSize(pPlayerInventory, 6);
    blockEntity = (CrushingMachineBlockEntity) pBlockEntity;
    level = pPlayerInventory.player.level;
    data = pData;
    
    addPlayerInventory(pPlayerInventory);
    addPlayerHotbar(pPlayerInventory);
    
    blockEntity.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(handler -> {
      addSlot(new FilteredSlotItemHandler(handler, CrushingMachineBlockEntity.SLOT_INDEX_INPUT, 7, 18, Tags.Items.ORES));
      addSlot(new FilteredSlotItemHandler(handler, CrushingMachineBlockEntity.SLOT_INDEX_SPEED_UPGRADE, 7, 54, BFItems.SPEED_UPGRADE.get()));
      addSlot(new FilteredSlotItemHandler(handler, CrushingMachineBlockEntity.SLOT_INDEX_ENERGY_UPGRADE, 25, 54, BFItems.ENERGY_UPGRADE.get()));
      addSlot(new ReadonlySlotItemHandler(handler, CrushingMachineBlockEntity.SLOT_INDEX_OUTPUT1, 79, 18));
      addSlot(new ReadonlySlotItemHandler(handler, CrushingMachineBlockEntity.SLOT_INDEX_OUTPUT2, 97, 18));
      addSlot(new ReadonlySlotItemHandler(handler, CrushingMachineBlockEntity.SLOT_INDEX_OUTPUT3, 115, 18));
      addSlot(new ReadonlySlotItemHandler(handler, CrushingMachineBlockEntity.SLOT_INDEX_OUTPUT4, 79, 36));
      addSlot(new ReadonlySlotItemHandler(handler, CrushingMachineBlockEntity.SLOT_INDEX_OUTPUT5, 97, 36));
      addSlot(new ReadonlySlotItemHandler(handler, CrushingMachineBlockEntity.SLOT_INDEX_OUTPUT6, 115, 36));
      addSlot(new ReadonlySlotItemHandler(handler, CrushingMachineBlockEntity.SLOT_INDEX_OUTPUT7, 79, 54));
      addSlot(new ReadonlySlotItemHandler(handler, CrushingMachineBlockEntity.SLOT_INDEX_OUTPUT8, 97, 54));
      addSlot(new ReadonlySlotItemHandler(handler, CrushingMachineBlockEntity.SLOT_INDEX_OUTPUT9, 115, 54));
    });
    
    addDataSlots(data);
  }
  
  private static final int HOTBAR_SLOT_COUNT = 9;
  private static final int PLAYER_INVENTORY_ROW_COUNT = 3;
  private static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
  private static final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT;
  private static final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;
  private static final int VANILLA_FIRST_SLOT_INDEX = 0;
  private static final int TE_INVENTORY_FIRST_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT;
  private static final int TE_INVENTORY_SLOT_COUNT = 6;
  
  @Override
  public @NotNull ItemStack quickMoveStack(@NotNull Player pPlayer, int pIndex) {
    Slot sourceSlot = slots.get(pIndex);
    if (! sourceSlot.hasItem()) return ItemStack.EMPTY;
    ItemStack sourceStack = sourceSlot.getItem();
    ItemStack copyOfSourceStack = sourceStack.copy();
    
    if (pIndex < VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT) {
      if (! moveItemStackTo(sourceStack, TE_INVENTORY_FIRST_SLOT_INDEX, TE_INVENTORY_FIRST_SLOT_INDEX
          + TE_INVENTORY_SLOT_COUNT, false)) {
        return ItemStack.EMPTY;
      }
    } else if (pIndex < TE_INVENTORY_FIRST_SLOT_INDEX + TE_INVENTORY_SLOT_COUNT) {
      if (! moveItemStackTo(sourceStack, VANILLA_FIRST_SLOT_INDEX, VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT, false)) {
        return ItemStack.EMPTY;
      }
    } else {
      logger.warn("Invalid slotIndex: {}", pIndex);
      return ItemStack.EMPTY;
    }
    if (sourceStack.getCount() == 0) {
      sourceSlot.set(ItemStack.EMPTY);
    } else {
      sourceSlot.setChanged();
    }
    sourceSlot.onTake(pPlayer, sourceStack);
    return copyOfSourceStack;
  }
  
  @Override
  public boolean stillValid(@NotNull Player pPlayer) {
    return stillValid(ContainerLevelAccess.create(level, blockEntity.getBlockPos()),
        pPlayer, BFBlocks.CRUSHING_MACHINE.get());
  }
  
  public boolean isCrafting() {
    return data.get(CrushingMachineBlockEntity.DATA_INDEX_PROGRESS) > 0;
  }
  
  public int getScaledProgress() {
    int progress = data.get(CrushingMachineBlockEntity.DATA_INDEX_PROGRESS);
    int maxProgress = data.get(CrushingMachineBlockEntity.DATA_INDEX_MAX_PROGRESS);
    int progressArrowSize = 26;
    
    return maxProgress != 0 && progress != 0 ? progress * progressArrowSize / maxProgress : 0;
  }
  
  private void addPlayerInventory(Inventory pPlayerInventory) {
    for (int i = 0; i < 3; ++ i) {
      for (int l = 0; l < 9; ++ l) {
        this.addSlot(new Slot(pPlayerInventory, l + i * 9 + 9, 8 + l * 18, 106 + i * 18));
      }
    }
  }
  
  private void addPlayerHotbar(Inventory pPlayerInventory) {
    for (int i = 0; i < 9; ++ i) {
      this.addSlot(new Slot(pPlayerInventory, i, 8 + i * 18, 164));
    }
  }
}
