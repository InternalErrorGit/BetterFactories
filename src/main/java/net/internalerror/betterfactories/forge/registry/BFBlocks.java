package net.internalerror.betterfactories.forge.registry;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
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
public final class BFBlocks {

    private static final DeferredRegister<Block> registry = DeferredRegister.create(ForgeRegistries.BLOCKS, BETTER_FACTORIES);

    public static final RegistryObject<Block> NICKEL_DEEPSLATE_ORE = register("nickel_deepslate_ore", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));
    public static final RegistryObject<Block> NICKEL_ORE = register("nickel_ore", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));
    public static final RegistryObject<Block> NICKEL_RAW_STORAGE_BLOCK = register("nickel_raw_storage_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));
    public static final RegistryObject<Block> NICKEL_STORAGE_BLOCK = register("nickel_storage_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));

    public static final RegistryObject<LiquidBlock> NICKEL_DIRTY_DISSOLVED_FLUID_BLOCK = register("nickel_dirty_dissolved_fluid_block", () -> new LiquidBlock(BFFluids.NICKEL_SOURCE_DIRTY_DISSOLVED_FLUID, BlockBehaviour.Properties.copy(Blocks.WATER)));
    public static final RegistryObject<LiquidBlock> NICKEL_DISSOLVED_FLUID_BLOCK = register("nickel_dissolved_fluid_block", () -> new LiquidBlock(BFFluids.NICKEL_SOURCE_DISSOLVED_FLUID, BlockBehaviour.Properties.copy(Blocks.WATER)));

    public static <T extends Block> RegistryObject<T> register(String pName, Supplier<T> pSupplier) {
        logger.info("Registering Block >> {}", pName);
        RegistryObject<T> regObj = registry.register(pName, pSupplier);
        logger.debug("Registered Block as: {}", regObj.getId());
        return regObj;
    }

    public static void register(IEventBus pEventBus) {
        registry.register(pEventBus);
    }


}
