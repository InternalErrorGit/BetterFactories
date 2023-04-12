package net.internalerror.betterfactories.tags;

import lombok.experimental.UtilityClass;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import static net.minecraftforge.versions.forge.ForgeVersion.MOD_ID;

/**
 * {@link net.internalerror.betterfactories.BetterFactories}
 *
 * @author InternalErrorGit
 * @version 1.0 Snapshot
 */
@UtilityClass
public final class BFItemTags {
    public static final TagKey<Item> NICKEL_ORES = bind("nickel_ores");
    public static final TagKey<Item> ORES_NICKEL = tag("ores/nickel");
    public static final TagKey<Item> NUGGETS_NICKEL = tag("nuggets/nickel");
    public static final TagKey<Item> PLATES_NICKEL = tag("plates/nickel");
    public static final TagKey<Item> DUSTS_NICKEL = tag("dusts/nickel");
    public static final TagKey<Item> SHARDS_NICKEL = tag("shards/nickel");
    public static final TagKey<Item> CRYSTALS_NICKEL = tag("crystals/nickel");
    public static final TagKey<Item> CLUMPS_NICKEL = tag("clumps/nickel");
    public static final TagKey<Item> RODS_NICKEL = tag("rods/nickel");
    public static final TagKey<Item> WIRE_NICKEL = tag("wire/nickel");
    public static final TagKey<Item> INGOTS_NICKEL = tag("ingots/nickel");
    public static final TagKey<Item> RAW_MATERIALS_NICKEL = tag("raw_materials/nickel");
    public static final TagKey<Item> STORAGE_BLOCKS_NICKEL = tag("storage_blocks/nickel");
    public static final TagKey<Item> STORAGE_BLOCKS_RAW_NICKEL = tag("storage_blocks/raw_nickel");


    private static TagKey<Item> bind(String pName) {
        return TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation(MOD_ID, pName));
    }


    private static TagKey<Item> tag(String name) {
        return ItemTags.create(new ResourceLocation(MOD_ID, name));
    }
}
