package com.mafuyu404.worntear.init;

import net.minecraft.resources.ResourceLocation;

import java.util.HashMap;
import java.util.List;

public class RuleCache {
    private static final HashMap<String, String> ItemMapCache = new HashMap<>();
    private static final HashMap<String, String> TagMapCache = new HashMap<>();

    public static HashMap<String, String> getItemMapCache() {
        return ItemMapCache;
    }

    public static String matchItem(String id) {
        List<ResourceLocation> tags = Utils.getItemTags(Utils.getItemById(id));
        if (tags.isEmpty()) return ItemMapCache.getOrDefault(id, null);
        for (ResourceLocation tag : tags) {
            String item = matchTag(tag);
            if (item != null) return item;
        }
        return ItemMapCache.getOrDefault(id, null);
    }

    public static String matchTag(String tagId) {
        return TagMapCache.getOrDefault(tagId, null);
    }

    public static String matchTag(ResourceLocation tagId) {
        return tagId != null ? matchTag(tagId.toString()) : null;
    }

    public static void putRule(Rule rule) {
        String match = rule.getMatch();
        if (match.startsWith("#")) {
            TagMapCache.put(match.replace("#", ""), rule.getRule());
        } else {
            ItemMapCache.put(match, rule.getRule());
        }
    }

    public static void clearCache() {
        ItemMapCache.clear();
        TagMapCache.clear();
    }
}