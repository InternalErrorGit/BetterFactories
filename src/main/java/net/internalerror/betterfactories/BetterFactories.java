package net.internalerror.betterfactories;

import lombok.extern.slf4j.Slf4j;
import net.internalerror.betterfactories.fml.config.Configuration;
import net.internalerror.betterfactories.forge.registry.*;
import net.internalerror.betterfactories.network.NetworkMessages;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

/**
 * {@link net.internalerror.betterfactories.BetterFactories}
 *
 * @author InternalErrorGit
 * @version 1.0 Snapshot
 */
@Slf4j
@Mod(BetterFactories.MOD_ID)
public class BetterFactories {
  
  public static final String MOD_ID = "betterfactories";
  
  public BetterFactories() {
    IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
    
    BFFluidTypes.register(modEventBus);
    BFFluids.register(modEventBus);
    BFBlocks.register(modEventBus);
    BFItems.register(modEventBus);
    
    BFBlockEntities.register(modEventBus);
    
    BFConfiguredFeatures.register(modEventBus);
    BFPlacedFeatures.register(modEventBus);
    
    BFMenuTypes.register(modEventBus);
    
    BFRecipeSerializers.register(modEventBus);
    
    modEventBus.addListener(this::commonSetup);
    ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Configuration.SPEC, MOD_ID + ".toml");
    MinecraftForge.EVENT_BUS.register(this);
  }
  
  private void commonSetup(final FMLCommonSetupEvent pEvent) {
    pEvent.enqueueWork(NetworkMessages::register);
  }
  
  @SubscribeEvent
  public void onServerStarting(ServerStartingEvent pEvent) {
  
  }
  
  
}
