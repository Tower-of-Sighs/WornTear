package com.mafuyu404.worntear.init;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.tags.ITagManager;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Utils {
    public static String getItemRegistryName(Item item) {
        if (item == null) {
            return null;
        }

        ResourceLocation resourceLocation = ForgeRegistries.ITEMS.getKey(item);

        if (resourceLocation == null) return null;

        return resourceLocation.toString();
    }

    public static Item getItemById(String registryName) {
        return ForgeRegistries.ITEMS.getValue(new ResourceLocation(registryName));
    }

    public static List<ResourceLocation> getItemTags(Item item) {
        ITagManager<Item> tagManager = ForgeRegistries.ITEMS.tags();

        if (tagManager == null) {
            return Collections.emptyList();
        }

        return tagManager.getReverseTag(item)
                .map(reverseTag ->
                        reverseTag.getTagKeys()
                                .map(TagKey::location)
                                .collect(Collectors.toList()))
                .orElse(Collections.emptyList());
    }

    public static void loadAllRule() {
        RuleCache.clearCache();
        RuleLoader.loadAll().forEach(RuleCache::putRule);
    }

    public static float resolveRule(String rule, float base) {
        String[] str1 = rule.split("\\+");
        float fixed = Float.parseFloat(str1[0]);
        String[] str2 = str1[1].split("\\*");
        float scale = Float.parseFloat(str2[1]);
        String[] str3 = str2[0].replace("[", "").replace("]", "").replace(" ", "").split(",");
        float start = Float.parseFloat(str3[0]);
        float end = Float.parseFloat(str3[1]);
        if (base > start && base < end) scale *= (base - start) / (end - start);
        return fixed + scale;
    }

    public static float getDamagePercent(ItemStack itemStack) {
        return 1 - 1f * itemStack.getDamageValue() / itemStack.getMaxDamage();
    }
}
