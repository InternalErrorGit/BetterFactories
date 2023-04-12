package net.internalerror.betterfactories.data.tags;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import static net.internalerror.betterfactories.BetterFactories.MOD_ID;
import static net.internalerror.betterfactories.forge.registry.BFBlocks.*;
import static net.internalerror.betterfactories.tags.BFBlockTags.*;
import static net.minecraft.tags.BlockTags.*;
import static net.minecraftforge.common.Tags.Blocks.*;

public class BFBlockTagsProvider extends BlockTagsProvider {

    public BFBlockTagsProvider(DataGenerator pDataGenerator, @Nullable ExistingFileHelper pExistingFileHelper) {
        super(pDataGenerator, MOD_ID, pExistingFileHelper);
    }

    @Override
    protected void addTags() {
        tag(BEACON_BASE_BLOCKS).add(NICKEL_BLOCK.get());
        tag(MINEABLE_WITH_PICKAXE).add(NICKEL_ORE.get(), DEEPSLATE_NICKEL_ORE.get(), NICKEL_BLOCK.get(), RAW_NICKEL_BLOCK.get());
        tag(NEEDS_STONE_TOOL).add(NICKEL_BLOCK.get(), RAW_NICKEL_BLOCK.get(), NICKEL_ORE.get(), DEEPSLATE_NICKEL_ORE.get());
        tag(NICKEL_ORES).add(NICKEL_ORE.get(), DEEPSLATE_NICKEL_ORE.get());
        tag(ORE_RATES_SINGULAR).add(DEEPSLATE_NICKEL_ORE.get(), NICKEL_ORE.get());
        tag(ORES).addTag(ORES_NICKEL);
        tag(ORES_NICKEL).addTag(NICKEL_ORES);
        tag(ORES_IN_GROUND_DEEPSLATE).add(DEEPSLATE_NICKEL_ORE.get());
        tag(ORES_IN_GROUND_STONE).add(NICKEL_ORE.get());
        tag(OVERWORLD_CARVER_REPLACEABLES).addTag(NICKEL_ORES).add(RAW_NICKEL_BLOCK.get());
        tag(SNAPS_GOAT_HORN).add(NICKEL_ORE.get());
        tag(STORAGE_BLOCKS).addTag(STORAGE_BLOCKS_NICKEL).addTag(STORAGE_BLOCKS_RAW_NICKEL);
        tag(STORAGE_BLOCKS_NICKEL).add(NICKEL_BLOCK.get());
        tag(STORAGE_BLOCKS_RAW_NICKEL).add(RAW_NICKEL_BLOCK.get());
    }


}
