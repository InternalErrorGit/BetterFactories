package net.internalerror.betterfactories.forge.registry;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import net.internalerror.betterfactories.util.BFNames;
import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.*;
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
public final class BFPlacedFeatures implements BFNames {
    public static final DeferredRegister<PlacedFeature> registry =
            DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, MOD_ID);

    public static final RegistryObject<PlacedFeature> NICKEL_ORE_PLACED = register(NAME_NICKEL_ORE_PLACED_FEATURE,
            () -> new PlacedFeature(BFConfiguredFeatures.NICKEL_ORE_FEATURE.getHolder().orElseThrow(),
                    commonOrePlacement(7,
                            HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80)))));

    private static RegistryObject<PlacedFeature> register(String pName, Supplier<PlacedFeature> pSupplier) {
        logger.info("Registering ConfiguredFeature >> {}", pName);
        RegistryObject<PlacedFeature> regObj = registry.register(pName, pSupplier);
        logger.debug("Registered ConfiguredFeature as: {}", regObj.getId());
        return regObj;
    }

    public static List<PlacementModifier> orePlacement(PlacementModifier pPlacementModifier, PlacementModifier pPlacementModifier2) {
        return List.of(pPlacementModifier, InSquarePlacement.spread(), pPlacementModifier2, BiomeFilter.biome());
    }

    public static List<PlacementModifier> commonOrePlacement(int pCount, PlacementModifier pPlacementModifier) {
        return orePlacement(CountPlacement.of(pCount), pPlacementModifier);
    }

    public static List<PlacementModifier> rareOrePlacement(int pChance, PlacementModifier pPlacementModifier) {
        return orePlacement(RarityFilter.onAverageOnceEvery(pChance), pPlacementModifier);
    }


    public static void register(IEventBus eventBus) {
        registry.register(eventBus);
    }
}
