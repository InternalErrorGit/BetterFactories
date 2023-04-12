package net.internalerror.betterfactories.forge.registry;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import net.internalerror.betterfactories.util.BFNames;
import net.internalerror.betterfactories.world.level.block.entity.CrushingMachineBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

import static net.internalerror.betterfactories.BetterFactories.MOD_ID;

/**
 * {@link net.internalerror.betterfactories.BetterFactories}
 *
 * @author InternalErrorGit
 * @version 1.0 Snapshot
 */
@Slf4j
@UtilityClass
public final class BFBlockEntities implements BFNames {
  private static final DeferredRegister<BlockEntityType<?>> registry = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, MOD_ID);
  
  public static final RegistryObject<BlockEntityType<CrushingMachineBlockEntity>> CRUSHING_MACHINE_BLOCK_ENTITY = register(NAME_CRUSHING_MACHINE_BLOCK_ENTITY, () -> BlockEntityType.Builder.of(CrushingMachineBlockEntity::new, BFBlocks.CRUSHING_MACHINE.get()).build(null));
  
  public static <T extends BlockEntity> RegistryObject<BlockEntityType<T>> register(String pName, Supplier<BlockEntityType<T>> pSupplier) {
    logger.info("Registering Block >> {}", pName);
    RegistryObject<BlockEntityType<T>> regObj = registry.register(pName, pSupplier);
    logger.debug("Registered Block as: {}", regObj.getId());
    return regObj;
  }
  
  public static void register(IEventBus pEventBus) {
    registry.register(pEventBus);
  }
  
}
