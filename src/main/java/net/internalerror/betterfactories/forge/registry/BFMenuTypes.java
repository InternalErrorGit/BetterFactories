package net.internalerror.betterfactories.forge.registry;

import lombok.extern.slf4j.Slf4j;
import net.internalerror.betterfactories.world.inventory.CrushingMachineMenu;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static net.internalerror.betterfactories.BetterFactories.MOD_ID;

@Slf4j
public final class BFMenuTypes {
  public static final DeferredRegister<MenuType<?>> registry =
      DeferredRegister.create(ForgeRegistries.MENU_TYPES, MOD_ID);
  
  public static final RegistryObject<MenuType<CrushingMachineMenu>> CRUSHING_MACHINE_MENU =
      registerMenuType(CrushingMachineMenu::new, "crushing_machine_menu");
  
  
  private static <T extends AbstractContainerMenu> RegistryObject<MenuType<T>> registerMenuType(IContainerFactory<T> pFactory, String pName) {
    logger.info("Registering Item >> {}", pName);
    RegistryObject<MenuType<T>> regObj = registry.register(pName, () -> IForgeMenuType.create(pFactory));
    logger.debug("Registered Item as: {}", regObj.getId());
    return regObj;
    
  }
  
  public static void register(IEventBus eventBus) {
    registry.register(eventBus);
  }
}