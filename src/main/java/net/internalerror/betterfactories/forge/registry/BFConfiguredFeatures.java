package net.internalerror.betterfactories.forge.registry;

import com.google.common.base.Suppliers;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import net.internalerror.betterfactories.util.BFNames;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

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
public final class BFConfiguredFeatures implements BFNames {

    public static final DeferredRegister<ConfiguredFeature<?, ?>> registry = DeferredRegister.create(Registry.CONFIGURED_FEATURE_REGISTRY, MOD_ID);

    public static final Supplier<List<OreConfiguration.TargetBlockState>> OVERWORLD_NICKEL_ORES = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, BFBlocks.NICKEL_ORE.get().defaultBlockState()),
            OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, BFBlocks.DEEPSLATE_NICKEL_ORE.get().defaultBlockState())
    ));

    public static final RegistryObject<ConfiguredFeature<?, ?>> NICKEL_ORE_FEATURE = register(NAME_NICKEL_ORE_CONFIGURED_FEATURE,
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(OVERWORLD_NICKEL_ORES.get(), 7)));

    private static RegistryObject<ConfiguredFeature<?, ?>> register(String pName, Supplier<ConfiguredFeature<?, ?>> pSupplier) {
        logger.info("Registering ConfiguredFeature >> {}", pName);
        RegistryObject<ConfiguredFeature<?, ?>> regObj = registry.register(pName, pSupplier);
        logger.debug("Registered ConfiguredFeature as: {}", regObj.getId());
        return regObj;
    }


    public static void register(IEventBus eventBus) {
        registry.register(eventBus);
    }
}
