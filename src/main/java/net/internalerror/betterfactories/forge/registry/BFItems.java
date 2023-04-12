package net.internalerror.betterfactories.forge.registry;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import net.internalerror.betterfactories.util.BFNames;
import net.internalerror.betterfactories.world.item.BFCreativeModeTab;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.List;
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
public final class BFItems implements BFNames {
  
  private static final DeferredRegister<Item> registry = DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);
  public static final List<RegistryObject<Item>> list = new ArrayList<>();
  
  public static final RegistryObject<Item> NICKEL_CLUMP = register(NAME_NICKEL_CLUMP, () -> new Item(new Item.Properties().tab(BFCreativeModeTab.TAB)));
  public static final RegistryObject<Item> NICKEL_CRYSTAL = register(NAME_NICKEL_CRYSTAL, () -> new Item(new Item.Properties().tab(BFCreativeModeTab.TAB)));
  public static final RegistryObject<Item> DIRTY_DISSOLVED_NICKEL_BUCKET = register(NAME_DIRTY_DISSOLVED_NICKEL_BUCKET, () -> new BucketItem(BFFluids.SOURCE_DIRTY_DISSOLVED_NICKEL_FLUID, new Item.Properties().tab(BFCreativeModeTab.TAB)));
  public static final RegistryObject<Item> DISSOLVED_NICKEL_BUCKET = register(NAME_DISSOLVED_NICKEL_BUCKET, () -> new BucketItem(BFFluids.SOURCE_DISSOLVED_NICKEL_FLUID, new Item.Properties().tab(BFCreativeModeTab.TAB)));
  public static final RegistryObject<Item> NICKEL_DUST = register(NAME_NICKEL_DUST, () -> new Item(new Item.Properties().tab(BFCreativeModeTab.TAB)));
  public static final RegistryObject<Item> NICKEL_INGOT = register(NAME_NICKEL_INGOT, () -> new Item(new Item.Properties().tab(BFCreativeModeTab.TAB)));
  public static final RegistryObject<Item> NICKEL_NUGGET = register(NAME_NICKEL_NUGGET, () -> new Item(new Item.Properties().tab(BFCreativeModeTab.TAB)));
  public static final RegistryObject<Item> NICKEL_PLATE = register(NAME_NICKEL_PLATE, () -> new Item(new Item.Properties().tab(BFCreativeModeTab.TAB)));
  public static final RegistryObject<Item> RAW_NICKEL = register(NAME_RAW_NICKEL, () -> new Item(new Item.Properties().tab(BFCreativeModeTab.TAB)));
  public static final RegistryObject<Item> NICKEL_ROD = register(NAME_NICKEL_ROD, () -> new Item(new Item.Properties().tab(BFCreativeModeTab.TAB)));
  public static final RegistryObject<Item> NICKEL_SHARD = register(NAME_NICKEL_SHARD, () -> new Item(new Item.Properties().tab(BFCreativeModeTab.TAB)));
  public static final RegistryObject<Item> NICKEL_WIRE = register(NAME_NICKEL_WIRE, () -> new Item(new Item.Properties().tab(BFCreativeModeTab.TAB)));
  public static final RegistryObject<Item> DEEPSLATE_NICKEL_ORE = register(NAME_DEEPSLATE_NICKEL_ORE, () -> new BlockItem(BFBlocks.DEEPSLATE_NICKEL_ORE.get(), new Item.Properties().tab(BFCreativeModeTab.TAB)));
  public static final RegistryObject<Item> NICKEL_ORE = register(NAME_NICKEL_ORE, () -> new BlockItem(BFBlocks.NICKEL_ORE.get(), new Item.Properties().tab(BFCreativeModeTab.TAB)));
  public static final RegistryObject<Item> RAW_NICKEL_BLOCK = register(NAME_RAW_NICKEL_BLOCK, () -> new BlockItem(BFBlocks.RAW_NICKEL_BLOCK.get(), new Item.Properties().tab(BFCreativeModeTab.TAB)));
  public static final RegistryObject<Item> NICKEL_BLOCK = register(NAME_NICKEL_BLOCK, () -> new BlockItem(BFBlocks.NICKEL_BLOCK.get(), new Item.Properties().tab(BFCreativeModeTab.TAB)));
  
  public static final RegistryObject<Item> SPEED_UPGRADE = register(NAME_SPEED_UPGRADE, () -> new Item(new Item.Properties().tab(BFCreativeModeTab.TAB)));
  public static final RegistryObject<Item> ENERGY_UPGRADE = register(NAME_ENERGY_UPGRADE, () -> new Item(new Item.Properties().tab(BFCreativeModeTab.TAB)));
  
  public static final RegistryObject<Item> CRUSHING_MACHINE = register(NAME_CRUSHING_MACHINE, () -> new BlockItem(BFBlocks.CRUSHING_MACHINE.get(), new Item.Properties().tab(BFCreativeModeTab.TAB)));
  
  public static RegistryObject<Item> register(String pName, Supplier<Item> pSupplier) {
    logger.info("Registering Item >> {}", pName);
    RegistryObject<Item> regObj = registry.register(pName, pSupplier);
    list.add(regObj);
    logger.debug("Registered Item as: {}", regObj.getId());
    return regObj;
  }
  
  
  public static void register(IEventBus pEventBus) {
    registry.register(pEventBus);
  }
  
}
