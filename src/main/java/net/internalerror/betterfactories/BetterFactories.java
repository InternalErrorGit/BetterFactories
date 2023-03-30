package net.internalerror.betterfactories;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import net.internalerror.betterfactories.forge.client.model.generators.BFBlockStateProvider;
import net.internalerror.betterfactories.forge.client.model.generators.BFItemModelProvider;
import net.internalerror.betterfactories.forge.registry.BFBlocks;
import net.internalerror.betterfactories.forge.registry.BFFluidTypes;
import net.internalerror.betterfactories.forge.registry.BFFluids;
import net.internalerror.betterfactories.forge.registry.BFItems;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Slf4j
@Mod(BetterFactories.BETTER_FACTORIES)
public class BetterFactories {

    public static final String BETTER_FACTORIES = "betterfactories";

    public BetterFactories() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        BFFluidTypes.register(modEventBus);
        BFFluids.register(modEventBus);
        BFBlocks.register(modEventBus);
        BFItems.register(modEventBus);

        modEventBus.addListener(this::commonSetup);
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent pEvent) {

    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent pEvent) {

    }



}
