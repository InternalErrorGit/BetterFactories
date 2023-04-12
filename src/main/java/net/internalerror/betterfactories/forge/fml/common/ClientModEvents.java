package net.internalerror.betterfactories.forge.fml.common;

import lombok.extern.slf4j.Slf4j;
import net.internalerror.betterfactories.client.gui.screens.inventory.CrushingMachineScreen;
import net.internalerror.betterfactories.forge.registry.BFFluids;
import net.internalerror.betterfactories.forge.registry.BFMenuTypes;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import static net.internalerror.betterfactories.BetterFactories.MOD_ID;

@Slf4j
@Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class ClientModEvents {
  
  @SubscribeEvent
  public static void onClientStartup(FMLClientSetupEvent pEvent) {
    logger.info("Running client startup event");
    ItemBlockRenderTypes.setRenderLayer(BFFluids.SOURCE_DIRTY_DISSOLVED_NICKEL_FLUID.get(), RenderType.translucent());
    ItemBlockRenderTypes.setRenderLayer(BFFluids.SOURCE_DISSOLVED_NICKEL_FLUID.get(), RenderType.translucent());
    
    MenuScreens.register(BFMenuTypes.CRUSHING_MACHINE_MENU.get(), CrushingMachineScreen::new);
  }
}
