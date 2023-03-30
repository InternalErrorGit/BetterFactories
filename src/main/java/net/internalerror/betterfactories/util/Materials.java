package net.internalerror.betterfactories.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.internalerror.betterfactories.forge.registry.BFBlocks;
import net.internalerror.betterfactories.forge.registry.BFItems;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

@Getter
@AllArgsConstructor
public enum Materials {
    NICKEL(Blocks.NICKEL, Items.NICKEL);
    private final Blocks blocks;
    private final Items items;

    @Getter
    @AllArgsConstructor
    public enum Items {
        NICKEL(BFItems.NICKEL_CLUMP.get(), BFItems.NICKEL_CRYSTAL.get(), BFItems.NICKEL_DIRTY_DISSOLVED_BUCKET.get(), BFItems.NICKEL_DISSOLVED_BUCKET.get(), BFItems.NICKEL_DUST.get(), BFItems.NICKEL_INGOT.get(), BFItems.NICKEL_NUGGET.get(), BFItems.NICKEL_PLATE.get(), BFItems.NICKEL_RAW.get(), BFItems.NICKEL_ROD.get(), BFItems.NICKEL_SHARD.get(), BFItems.NICKEL_WIRE.get(), BFItems.NICKEL_DEEPSLATE_ORE.get(), BFItems.NICKEL_ORE.get(), BFItems.NICKEL_RAW_STORAGE_BLOCK.get(), BFItems.NICKEL_STORAGE_BLOCK.get());
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
    }

    @Getter
    @AllArgsConstructor
    public enum Blocks {
        NICKEL(BFBlocks.NICKEL_DEEPSLATE_ORE.get(), BFBlocks.NICKEL_ORE.get(), BFBlocks.NICKEL_RAW_STORAGE_BLOCK.get(), BFBlocks.NICKEL_STORAGE_BLOCK.get());
        private final Block deepslateOre;
        private final Block ore;
        private final Block rawStorageBlock;
        private final Block storageBlock;
    }

}
