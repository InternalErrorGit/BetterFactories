package net.internalerror.betterfactories.world.item.crafting;

import net.minecraft.world.item.crafting.RecipeType;

/**
 * {@link net.internalerror.betterfactories.BetterFactories}
 *
 * @author InternalErrorGit
 * @version 1.0 Snapshot
 */
public class CrushingRecipeType implements RecipeType<CrushingRecipe> {
  
  public static final CrushingRecipeType INSTANCE = new CrushingRecipeType();
  public static final String NAME = "crushing";
  
  private CrushingRecipeType() {
    // private constructor
  }
}
