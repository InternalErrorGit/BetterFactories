package net.internalerror.betterfactories.util;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.ForgeRegistries;

@Slf4j
@UtilityClass
public final class Util {

    public static String getItemPath(Item pItem) {
        ResourceLocation loc = ForgeRegistries.ITEMS.getKey(pItem);
        if (loc == null) {
            throw new NullPointerException(String.format("Item not present %s", pItem));
        }
        return loc.getPath();
    }

}
