package net.internalerror.betterfactories.forge.registry;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.ForgeFlowingFluid;
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
public final class BFFluids {

    private static final DeferredRegister<Fluid> registry = DeferredRegister.create(ForgeRegistries.FLUIDS, BETTER_FACTORIES);

    public static final RegistryObject<FlowingFluid> NICKEL_SOURCE_DISSOLVED_FLUID = register("nickel_dissolved_fluid",
            () -> new ForgeFlowingFluid.Source(BFFluids.NICKEL_DISSOLVED_PROPERTIES));

    public static final RegistryObject<FlowingFluid> NICKEL_FLOWING_DISSOLVED_FLUID = register("nickel_flowing_dissolved_fluid",
            () -> new ForgeFlowingFluid.Flowing(BFFluids.NICKEL_DISSOLVED_PROPERTIES));

    public static final RegistryObject<FlowingFluid> NICKEL_SOURCE_DIRTY_DISSOLVED_FLUID = register("nickel_dirty_dissolved_fluid",
            () -> new ForgeFlowingFluid.Source(BFFluids.NICKEL_DIRTY_DISSOLVED_PROPERTIES));

    public static final RegistryObject<FlowingFluid> NICKEL_FLOWING_DIRTY_DISSOLVED_FLUID = register("nickel_dirty_flowing_dissolved_fluid",
            () -> new ForgeFlowingFluid.Flowing(BFFluids.NICKEL_DIRTY_DISSOLVED_PROPERTIES));

    public static final ForgeFlowingFluid.Properties NICKEL_DISSOLVED_PROPERTIES = new ForgeFlowingFluid.Properties(
            BFFluidTypes.NICKEL_DISSOLVED_FLUID_TYPE, NICKEL_SOURCE_DISSOLVED_FLUID, NICKEL_FLOWING_DISSOLVED_FLUID
    ).slopeFindDistance(2).levelDecreasePerBlock(2).block(BFBlocks.NICKEL_DISSOLVED_FLUID_BLOCK).bucket(BFItems.NICKEL_DISSOLVED_BUCKET);

    public static final ForgeFlowingFluid.Properties NICKEL_DIRTY_DISSOLVED_PROPERTIES = new ForgeFlowingFluid.Properties(
            BFFluidTypes.NICKEL_DIRTY_DISSOLVED_FLUID_TYPE, NICKEL_SOURCE_DIRTY_DISSOLVED_FLUID, NICKEL_FLOWING_DIRTY_DISSOLVED_FLUID
    ).slopeFindDistance(2).levelDecreasePerBlock(2).block(BFBlocks.NICKEL_DIRTY_DISSOLVED_FLUID_BLOCK).bucket(BFItems.NICKEL_DIRTY_DISSOLVED_BUCKET);

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
