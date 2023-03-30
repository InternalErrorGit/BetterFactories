package net.internalerror.betterfactories.forge.registry;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import net.internalerror.betterfactories.world.item.BFCreativeModeTab;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

import static net.internalerror.betterfactories.BetterFactories.BETTER_FACTORIES;

/**
 * {@link net.internalerror.betterfactories.BetterFactories}
 *
 * @author InternalErrorGit
 * @version 1.0 Snapshot
 */
@Slf4j
@UtilityClass
public final class BFItems {


    private static final DeferredRegister<Item> registry = DeferredRegister.create(ForgeRegistries.ITEMS, BETTER_FACTORIES);

    public static final RegistryObject<Item> NICKEL_CLUMP = register("nickel_clump", () -> new Item(new Item.Properties().tab(BFCreativeModeTab.TAB)));
    public static final RegistryObject<Item> NICKEL_CRYSTAL = register("nickel_crystal", () -> new Item(new Item.Properties().tab(BFCreativeModeTab.TAB)));
    public static final RegistryObject<Item> NICKEL_DIRTY_DISSOLVED_BUCKET = register("nickel_dirty_dissolved_bucket", () -> new BucketItem(BFFluids.NICKEL_SOURCE_DIRTY_DISSOLVED_FLUID, new Item.Properties().tab(BFCreativeModeTab.TAB)));
    public static final RegistryObject<Item> NICKEL_DISSOLVED_BUCKET = register("nickel_dissolved_bucket", () -> new BucketItem(BFFluids.NICKEL_SOURCE_DISSOLVED_FLUID, new Item.Properties().tab(BFCreativeModeTab.TAB)));
    public static final RegistryObject<Item> NICKEL_DUST = register("nickel_dust", () -> new Item(new Item.Properties().tab(BFCreativeModeTab.TAB)));
    public static final RegistryObject<Item> NICKEL_INGOT = register("nickel_ingot", () -> new Item(new Item.Properties().tab(BFCreativeModeTab.TAB)));
    public static final RegistryObject<Item> NICKEL_NUGGET = register("nickel_nugget", () -> new Item(new Item.Properties().tab(BFCreativeModeTab.TAB)));
    public static final RegistryObject<Item> NICKEL_PLATE = register("nickel_plate", () -> new Item(new Item.Properties().tab(BFCreativeModeTab.TAB)));
    public static final RegistryObject<Item> NICKEL_RAW = register("nickel_raw", () -> new Item(new Item.Properties().tab(BFCreativeModeTab.TAB)));
    public static final RegistryObject<Item> NICKEL_ROD = register("nickel_rod", () -> new Item(new Item.Properties().tab(BFCreativeModeTab.TAB)));
    public static final RegistryObject<Item> NICKEL_SHARD = register("nickel_shard", () -> new Item(new Item.Properties().tab(BFCreativeModeTab.TAB)));
    public static final RegistryObject<Item> NICKEL_WIRE = register("nickel_wire", () -> new Item(new Item.Properties().tab(BFCreativeModeTab.TAB)));
    public static final RegistryObject<Item> NICKEL_DEEPSLATE_ORE = register("nickel_deepslate_ore", () -> new BlockItem(BFBlocks.NICKEL_DEEPSLATE_ORE.get(), new Item.Properties().tab(BFCreativeModeTab.TAB)));
    public static final RegistryObject<Item> NICKEL_ORE = register("nickel_ore", () -> new BlockItem(BFBlocks.NICKEL_ORE.get(), new Item.Properties().tab(BFCreativeModeTab.TAB)));
    public static final RegistryObject<Item> NICKEL_RAW_STORAGE_BLOCK = register("nickel_raw_storage_block", () -> new BlockItem(BFBlocks.NICKEL_RAW_STORAGE_BLOCK.get(), new Item.Properties().tab(BFCreativeModeTab.TAB)));
    public static final RegistryObject<Item> NICKEL_STORAGE_BLOCK = register("nickel_storage_block", () -> new BlockItem(BFBlocks.NICKEL_STORAGE_BLOCK.get(), new Item.Properties().tab(BFCreativeModeTab.TAB)));


    public static RegistryObject<Item> register(String pName, Supplier<Item> pSupplier) {
        logger.info("Registering Item >> {}", pName);
        RegistryObject<Item> regObj = registry.register(pName, pSupplier);
        logger.debug("Registered Item as: {}", regObj.getId());
        return regObj;
    }

    public static void register(IEventBus pEventBus) {
        registry.register(pEventBus);
    }

}
