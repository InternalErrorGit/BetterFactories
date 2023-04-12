package net.internalerror.betterfactories.world.item.crafting;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

/**
 * {@link net.internalerror.betterfactories.BetterFactories}
 *
 * @author InternalErrorGit
 * @version 1.0 Snapshot
 */
@Getter
@AllArgsConstructor
public class CrushingRecipe implements Recipe<SimpleContainer> {
  
  private final ResourceLocation id;
  private final ItemStack resultItem;
  private final float resultItemChance;
  private final ItemStack secondaryResultItem;
  private final float secondaryResultItemChance;
  private final int processingTime;
  private final String group;
  private final Ingredient ingredient;
  
  @Override
  public boolean matches(@NotNull SimpleContainer pContainer, @NotNull Level pLevel) {
    if (pLevel.isClientSide()) {
      return false;
    }
    
    return true; //TODO: Fix this to test if the inventory of the crushing machine to match the recipe
  }
  
  @Override
  public @NotNull ItemStack assemble(@NotNull SimpleContainer pContainer) {
    return getResultItem();
  }
  
  @Override
  public boolean canCraftInDimensions(int pWidth, int pHeight) {
    return true;
  }
  
  @Override
  public @NotNull RecipeSerializer<?> getSerializer() {
    return CrushingRecipeSerializer.INSTANCE;
  }
  
  @Override
  public @NotNull RecipeType<?> getType() {
    return CrushingRecipeType.INSTANCE;
  }
  
}
