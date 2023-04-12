package net.internalerror.betterfactories.tags;

import lombok.experimental.UtilityClass;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

import static net.minecraftforge.versions.forge.ForgeVersion.MOD_ID;

/**
 * {@link net.internalerror.betterfactories.BetterFactories}
 *
 * @author InternalErrorGit
 * @version 1.0 Snapshot
 */
@UtilityClass
public final class BFBlockTags {
  
  public static final TagKey<Block> NICKEL_ORES = create("nickel_ores");
  public static final TagKey<Block> ORES_NICKEL = create("ores/nickel");
  public static final TagKey<Block> STORAGE_BLOCKS_NICKEL = create("storage_blocks/nickel");
  public static final TagKey<Block> STORAGE_BLOCKS_RAW_NICKEL = create("storage_blocks/raw_nickel");
  
  private static TagKey<Block> create(String pName) {
    return TagKey.create(Registry.BLOCK_REGISTRY, new ResourceLocation(MOD_ID, pName));
  }
  
  public static TagKey<Block> create(ResourceLocation pName) {
    return TagKey.create(Registry.BLOCK_REGISTRY, pName);
  }
}
