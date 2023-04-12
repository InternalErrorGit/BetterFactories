package net.internalerror.betterfactories.forge.registry;

import com.mojang.math.Vector3f;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import net.internalerror.betterfactories.forge.fluids.BFFluidType;
import net.internalerror.betterfactories.util.BFNames;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidType;
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
public final class BFFluidTypes implements BFNames {

    private static final DeferredRegister<FluidType> registry = DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, MOD_ID);

    public static final ResourceLocation NICKEL_DISSOLVED_OVERLAY = new ResourceLocation(MOD_ID, "misc/dissolved_nickel");
    public static final ResourceLocation NICKEL_DIRTY_DISSOLVED_OVERLAY = new ResourceLocation(MOD_ID, "misc/dirty_dissolved_nickel");

    public static final RegistryObject<FluidType> DISSOLVED_NICKEL_FLUID_TYPE = register(NAME_DISSOLVED_NICKEL_FLUID_TYPE, () ->
            new BFFluidType(FluidType.Properties.create(), NICKEL_DISSOLVED_OVERLAY, 0xA1123456, new Vector3f(224f / 255f, 56f / 255f, 208f / 255f)));

    public static final RegistryObject<FluidType> DIRTY_DISSOLVED_NICKEL_FLUID_TYPE = register(NAME_DIRTY_DISSOLVED_NICKEL_FLUID_TYPE, () ->
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
