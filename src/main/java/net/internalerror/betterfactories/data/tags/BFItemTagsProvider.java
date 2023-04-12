package net.internalerror.betterfactories.data.tags;

import net.internalerror.betterfactories.forge.registry.BFItems;
import net.internalerror.betterfactories.tags.BFBlockTags;
import net.internalerror.betterfactories.tags.BFItemTags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import static net.internalerror.betterfactories.BetterFactories.MOD_ID;

public class BFItemTagsProvider extends ItemTagsProvider {
  
  public BFItemTagsProvider(DataGenerator pDataGenerator, BlockTagsProvider pBlockTagsProvider, @Nullable ExistingFileHelper pExistingFileHelper) {
    super(pDataGenerator, pBlockTagsProvider, MOD_ID, pExistingFileHelper);
  }
  
  @Override
  protected void addTags() {
    copy(BFBlockTags.NICKEL_ORES, BFItemTags.NICKEL_ORES);
    tag(ItemTags.BEACON_PAYMENT_ITEMS).add(BFItems.NICKEL_INGOT.get());
    tag(Tags.Items.INGOTS).addTag(BFItemTags.INGOTS_NICKEL);
    tag(BFItemTags.INGOTS_NICKEL).add(BFItems.NICKEL_INGOT.get());
    tag(Tags.Items.NUGGETS).addTag(BFItemTags.NUGGETS_NICKEL);
    tag(BFItemTags.NUGGETS_NICKEL).add(BFItems.NICKEL_NUGGET.get());
    copy(BFBlockTags.ORES_NICKEL, BFItemTags.ORES_NICKEL);
    tag(Tags.Items.RAW_MATERIALS).addTag(BFItemTags.RAW_MATERIALS_NICKEL);
    tag(BFItemTags.RAW_MATERIALS_NICKEL).add(BFItems.RAW_NICKEL.get());
    copy(BFBlockTags.STORAGE_BLOCKS_NICKEL, BFItemTags.STORAGE_BLOCKS_NICKEL);
    copy(BFBlockTags.STORAGE_BLOCKS_RAW_NICKEL, BFItemTags.STORAGE_BLOCKS_RAW_NICKEL);
    tag(BFItemTags.PLATES_NICKEL).add(BFItems.NICKEL_PLATE.get());
    tag(BFItemTags.DUSTS_NICKEL).add(BFItems.NICKEL_DUST.get());
    tag(BFItemTags.SHARDS_NICKEL).add(BFItems.NICKEL_SHARD.get());
    tag(BFItemTags.CRYSTALS_NICKEL).add(BFItems.NICKEL_CRYSTAL.get());
    tag(BFItemTags.CLUMPS_NICKEL).add(BFItems.NICKEL_CLUMP.get());
    tag(BFItemTags.RODS_NICKEL).add(BFItems.NICKEL_ROD.get());
    tag(BFItemTags.WIRE_NICKEL).add(BFItems.NICKEL_WIRE.get());
    
  }
}
