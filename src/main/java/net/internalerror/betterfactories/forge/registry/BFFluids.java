package net.internalerror.betterfactories.forge.registry;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import net.internalerror.betterfactories.util.BFNames;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.ForgeFlowingFluid;
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
public final class BFFluids implements BFNames {

    private static final DeferredRegister<Fluid> registry = DeferredRegister.create(ForgeRegistries.FLUIDS, MOD_ID);

    public static final RegistryObject<FlowingFluid> SOURCE_DISSOLVED_NICKEL_FLUID = register(NAME_SOURCE_DISSOLVED_NICKEL_FLUID,
            () -> new ForgeFlowingFluid.Source(BFFluids.DISSOLVED_NICKEL_PROPERTIES));

    public static final RegistryObject<FlowingFluid> FLOWING_DISSOLVED_NICKEL_FLUID = register(NAME_FLOWING_DISSOLVED_NICKEL_FLUID,
            () -> new ForgeFlowingFluid.Flowing(BFFluids.DISSOLVED_NICKEL_PROPERTIES));

    public static final RegistryObject<FlowingFluid> SOURCE_DIRTY_DISSOLVED_NICKEL_FLUID = register(NAME_SOURCE_DIRTY_DISSOLVED_NICKEL_FLUID,
            () -> new ForgeFlowingFluid.Source(BFFluids.DIRTY_DISSOLVED_NICKEL_PROPERTIES));

    public static final RegistryObject<FlowingFluid> FLOWING_DIRTY_DISSOLVED_NICKEL_FLUID = register(NAME_FLOWING_DIRTY_DISSOLVED_NICKEL_FLUID,
            () -> new ForgeFlowingFluid.Flowing(BFFluids.DIRTY_DISSOLVED_NICKEL_PROPERTIES));

    public static final ForgeFlowingFluid.Properties DISSOLVED_NICKEL_PROPERTIES = new ForgeFlowingFluid.Properties(
            BFFluidTypes.DISSOLVED_NICKEL_FLUID_TYPE, SOURCE_DISSOLVED_NICKEL_FLUID, FLOWING_DISSOLVED_NICKEL_FLUID
    ).slopeFindDistance(2).levelDecreasePerBlock(2).block(BFBlocks.NICKEL_DISSOLVED_FLUID_BLOCK).bucket(BFItems.DISSOLVED_NICKEL_BUCKET);

    public static final ForgeFlowingFluid.Properties DIRTY_DISSOLVED_NICKEL_PROPERTIES = new ForgeFlowingFluid.Properties(
            BFFluidTypes.DIRTY_DISSOLVED_NICKEL_FLUID_TYPE, SOURCE_DIRTY_DISSOLVED_NICKEL_FLUID, FLOWING_DIRTY_DISSOLVED_NICKEL_FLUID
    ).slopeFindDistance(2).levelDecreasePerBlock(2).block(BFBlocks.NICKEL_DIRTY_DISSOLVED_FLUID_BLOCK).bucket(BFItems.DIRTY_DISSOLVED_NICKEL_BUCKET);

    private static RegistryObject<FlowingFluid> register(String pPath, Supplier<FlowingFluid> pSupplier) {
        logger.info("Registering Fluid >> {}", pPath);
        RegistryObject<FlowingFluid> regObj = registry.register(pPath, pSupplier);
        logger.debug("Registered Fluid as: {}", regObj.getId());
        return regObj;
    }

    public static void register(IEventBus pEventBus) {
        registry.register(pEventBus);
    }

}
