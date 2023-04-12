package net.internalerror.betterfactories.forge.registry;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import net.internalerror.betterfactories.util.BFNames;
import net.internalerror.betterfactories.world.level.block.CrushingMachineBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
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
public final class BFBlocks implements BFNames {

    private static final DeferredRegister<Block> registry = DeferredRegister.create(ForgeRegistries.BLOCKS, MOD_ID);
    public static List<RegistryObject<Block>> list = new ArrayList<>();

    public static final RegistryObject<Block> DEEPSLATE_NICKEL_ORE = register(NAME_DEEPSLATE_NICKEL_ORE, () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));
    public static final RegistryObject<Block> NICKEL_ORE = register(NAME_NICKEL_ORE, () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));
    public static final RegistryObject<Block> RAW_NICKEL_BLOCK = register(NAME_RAW_NICKEL_BLOCK, () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));
    public static final RegistryObject<Block> NICKEL_BLOCK = register(NAME_NICKEL_BLOCK, () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));

    public static final RegistryObject<LiquidBlock> NICKEL_DIRTY_DISSOLVED_FLUID_BLOCK = register(NAME_DIRTY_DISSOLVED_NICKEL_FLUID_BLOCK, () -> new LiquidBlock(BFFluids.SOURCE_DIRTY_DISSOLVED_NICKEL_FLUID, BlockBehaviour.Properties.copy(Blocks.WATER)));
    public static final RegistryObject<LiquidBlock> NICKEL_DISSOLVED_FLUID_BLOCK = register(NAME_DISSOLVED_NICKEL_FLUID_BLOCK, () -> new LiquidBlock(BFFluids.SOURCE_DISSOLVED_NICKEL_FLUID, BlockBehaviour.Properties.copy(Blocks.WATER)));
    
    public static final RegistryObject<Block> CRUSHING_MACHINE = register("crushing_machine", () -> new CrushingMachineBlock(BlockBehaviour.Properties.of(Material.METAL).strength(6f).requiresCorrectToolForDrops().noOcclusion()));

    @SuppressWarnings("unchecked")
    public static <T extends Block> RegistryObject<T> register(String pName, Supplier<T> pSupplier) {
        logger.info("Registering Block >> {}", pName);
        RegistryObject<T> regObj = registry.register(pName, pSupplier);
        list.add((RegistryObject<Block>) regObj);
        logger.debug("Registered Block as: {}", regObj.getId());
        return regObj;
    }

    public static void register(IEventBus pEventBus) {
        registry.register(pEventBus);
    }


}
