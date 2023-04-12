package net.internalerror.betterfactories.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.internalerror.betterfactories.forge.registry.BFBlocks;
import net.internalerror.betterfactories.forge.registry.BFItems;
import net.internalerror.betterfactories.tags.BFItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

/**
 * {@link net.internalerror.betterfactories.BetterFactories}
 *
 * @author InternalErrorGit
 * @version 1.0 Snapshot
 */
@Getter
@AllArgsConstructor
public enum Materials {
  NICKEL(Blocks.NICKEL, Items.NICKEL);
  private final Blocks blocks;
  private final Items items;
  
  @Getter
  @AllArgsConstructor
  public enum Items {
    NICKEL(BFItems.NICKEL_CLUMP.get(), BFItems.NICKEL_CRYSTAL.get(), BFItems.DIRTY_DISSOLVED_NICKEL_BUCKET.get(), BFItems.DISSOLVED_NICKEL_BUCKET.get(), BFItems.NICKEL_DUST.get(), BFItems.NICKEL_INGOT.get(), BFItems.NICKEL_NUGGET.get(), BFItems.NICKEL_PLATE.get(), BFItems.RAW_NICKEL.get(), BFItems.NICKEL_ROD.get(), BFItems.NICKEL_SHARD.get(), BFItems.NICKEL_WIRE.get(), BFItems.DEEPSLATE_NICKEL_ORE.get(), BFItems.NICKEL_ORE.get(), BFItems.RAW_NICKEL_BLOCK.get(), BFItems.NICKEL_BLOCK.get(), Tags.NICKEL);
    private final Item clump;
    private final Item crystal;
    private final Item dirtyDissolvedBucket;
    private final Item dissolvedBucket;
    private final Item dust;
    private final Item ingot;
    private final Item nugget;
    private final Item plate;
    private final Item raw;
    private final Item rod;
    private final Item shard;
    private final Item wire;
    private final Item deepslateOre;
    private final Item ore;
    private final Item rawStorageBlock;
    private final Item storageBlock;
    private final Tags tags;
    
    @Getter
    @AllArgsConstructor
    public enum Tags {
      NICKEL(BFItemTags.CLUMPS_NICKEL, BFItemTags.CRYSTALS_NICKEL, BFItemTags.DUSTS_NICKEL, BFItemTags.INGOTS_NICKEL, BFItemTags.NUGGETS_NICKEL, BFItemTags.PLATES_NICKEL,
          BFItemTags.RAW_MATERIALS_NICKEL, BFItemTags.RODS_NICKEL, BFItemTags.SHARDS_NICKEL, BFItemTags.WIRE_NICKEL, BFItemTags.ORES_NICKEL, BFItemTags.STORAGE_BLOCKS_NICKEL, BFItemTags.STORAGE_BLOCKS_RAW_NICKEL);
      
      private final TagKey<Item> clump;
      private final TagKey<Item> crystal;
      private final TagKey<Item> dust;
      private final TagKey<Item> ingot;
      private final TagKey<Item> nugget;
      private final TagKey<Item> plate;
      private final TagKey<Item> raw;
      private final TagKey<Item> rod;
      private final TagKey<Item> shard;
      private final TagKey<Item> wire;
      private final TagKey<Item> ores;
      private final TagKey<Item> storageBlock;
      private final TagKey<Item> rawStorageBlock;
    }
    
  }
  
  @Getter
  @AllArgsConstructor
  public enum Blocks {
    NICKEL(BFBlocks.DEEPSLATE_NICKEL_ORE.get(), BFBlocks.NICKEL_ORE.get(), BFBlocks.RAW_NICKEL_BLOCK.get(), BFBlocks.NICKEL_BLOCK.get());
    private final Block deepslateOre;
    private final Block ore;
    private final Block rawStorageBlock;
    private final Block storageBlock;
  }
  
}
