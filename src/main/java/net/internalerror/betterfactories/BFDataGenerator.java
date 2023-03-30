package net.internalerror.betterfactories;

import lombok.experimental.UtilityClass;
import net.internalerror.betterfactories.forge.client.model.generators.BFBlockStateProvider;
import net.internalerror.betterfactories.forge.client.model.generators.BFItemModelProvider;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static net.internalerror.betterfactories.BetterFactories.BETTER_FACTORIES;

@UtilityClass
@Mod.EventBusSubscriber(modid = BETTER_FACTORIES, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class BFDataGenerator {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent pGatherDataEvent) {
        DataGenerator dataGenerator = pGatherDataEvent.getGenerator();
        ExistingFileHelper existingFileHelper = pGatherDataEvent.getExistingFileHelper();

        boolean run = pGatherDataEvent.includeServer();

        dataGenerator.addProvider(run, new BFBlockStateProvider(dataGenerator, existingFileHelper));
        dataGenerator.addProvider(run, new BFItemModelProvider(dataGenerator, existingFileHelper));

    }

}