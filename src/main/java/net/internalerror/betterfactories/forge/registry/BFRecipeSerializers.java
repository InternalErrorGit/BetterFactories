package net.internalerror.betterfactories.forge.registry;

import lombok.extern.slf4j.Slf4j;
import net.internalerror.betterfactories.world.item.crafting.CrushingRecipe;
import net.internalerror.betterfactories.world.item.crafting.CrushingRecipeSerializer;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static net.internalerror.betterfactories.BetterFactories.MOD_ID;

@Slf4j
public final class BFRecipeSerializers {
  
  private static final DeferredRegister<RecipeSerializer<?>> registry = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, MOD_ID);
  
  public static final RegistryObject<RecipeSerializer<CrushingRecipe>> CRUSHING_RECIPE_SERIALIZER = register("crushing_recipe_serializer", CrushingRecipeSerializer.INSTANCE);
  
  private static <T extends RecipeSerializer<?>> RegistryObject<T> register(String pName, T pInstance) {
    logger.info("Registering Fluid >> {}", pName);
    RegistryObject<T> regObj = registry.register(pName, () -> pInstance);
    logger.debug("Registered Fluid as: {}", regObj.getId());
    return regObj;
  }
  
  
  public static void register(IEventBus pModEventBus) {
    registry.register(pModEventBus);
  }
}

