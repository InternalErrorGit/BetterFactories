package net.internalerror.betterfactories.forge.registry;

import com.mojang.math.Vector3f;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import net.internalerror.betterfactories.forge.fluids.BFFluidType;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidType;
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
public final class BFFluidTypes {

    private static final DeferredRegister<FluidType> registry = DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, BETTER_FACTORIES);

    public static final ResourceLocation NICKEL_DISSOLVED_OVERLAY = new ResourceLocation(BETTER_FACTORIES, "misc/nickel_dissolved");
    public static final ResourceLocation NICKEL_DIRTY_DISSOLVED_OVERLAY = new ResourceLocation(BETTER_FACTORIES, "misc/nickel_dirty_dissolved");

    public static final RegistryObject<FluidType> NICKEL_DISSOLVED_FLUID_TYPE = register("nickel_dissolved_fluid_type", () ->
            new BFFluidType(FluidType.Properties.create(), NICKEL_DISSOLVED_OVERLAY, 0xA1123456, new Vector3f(224f / 255f, 56f / 255f, 208f / 255f)));

    public static final RegistryObject<FluidType> NICKEL_DIRTY_DISSOLVED_FLUID_TYPE = register("nickel_dirty_dissolved_fluid_type", () ->
            new BFFluidType(FluidType.Properties.create(), NICKEL_DIRTY_DISSOLVED_OVERLAY, 0xA1123456, new Vector3f(224f / 255f, 56f / 255f, 208f / 255f)));


    private static RegistryObject<FluidType> register(String pName, Supplier<FluidType> pSupplier) {
        logger.info("Registering FluidType >> {}", pName);
        RegistryObject<FluidType> regObj = registry.register(pName, pSupplier);
        logger.debug("Registered FluidType as: {}", regObj.getId());
        return regObj;
    }

    public static void register(IEventBus eventBus) {
        registry.register(eventBus);
    }

}
