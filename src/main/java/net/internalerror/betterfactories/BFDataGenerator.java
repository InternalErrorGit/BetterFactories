package net.internalerror.betterfactories;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import net.internalerror.betterfactories.data.recipes.BFMaterialRecipeProvider;
import net.internalerror.betterfactories.data.tags.BFBlockTagsProvider;
import net.internalerror.betterfactories.data.tags.BFItemTagsProvider;
import net.internalerror.betterfactories.forge.client.model.generators.BFBlockStateProvider;
import net.internalerror.betterfactories.forge.client.model.generators.BFItemModelProvider;
import net.internalerror.betterfactories.forge.common.data.BFLanguageProvider;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static net.internalerror.betterfactories.BetterFactories.MOD_ID;

/**
 * {@link net.internalerror.betterfactories.BetterFactories}
 *
 * @author InternalErrorGit
 * @version 1.0 Snapshot
 */
@Slf4j
@UtilityClass
@Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class BFDataGenerator {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent pGatherDataEvent) {
        DataGenerator dataGenerator = pGatherDataEvent.getGenerator();
        ExistingFileHelper existingFileHelper = pGatherDataEvent.getExistingFileHelper();

        boolean run = pGatherDataEvent.includeServer();

        dataGenerator.addProvider(run, new BFBlockStateProvider(dataGenerator, existingFileHelper));
        dataGenerator.addProvider(run, new BFItemModelProvider(dataGenerator, existingFileHelper));
        BFBlockTagsProvider blocktagsProvider = new BFBlockTagsProvider(dataGenerator, existingFileHelper);
        dataGenerator.addProvider(run, blocktagsProvider);
        dataGenerator.addProvider(run, new BFItemTagsProvider(dataGenerator, blocktagsProvider, existingFileHelper));
        dataGenerator.addProvider(run, new BFLanguageProvider(dataGenerator));
        dataGenerator.addProvider(run, new BFMaterialRecipeProvider(dataGenerator));
    }

}