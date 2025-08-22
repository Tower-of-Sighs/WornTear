package com.mafuyu404.worntear.init;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import net.minecraftforge.fml.loading.FMLPaths;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RuleLoader {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Path CONFIG_DIR = FMLPaths.CONFIGDIR.get().resolve("WornTear");
    private static final Path KJSDATA_DIR = FMLPaths.GAMEDIR.get()
            .resolve("kubejs")
            .resolve("data")
            .resolve("worntear")
            .resolve("rules");

    public static List<Rule> loadAll() {
        List<Rule> allRule = new ArrayList<>();

        allRule.addAll(loadFromDir(CONFIG_DIR));
//        if (ModList.get().isLoaded("kubejs")) {
//            if (Config.READ_KUBEJS_DATA.get()) allRule.addAll(loadFromDir(KJSDATA_DIR));
//        }
        allRule.addAll(loadFromDir(KJSDATA_DIR));

        return allRule;
    }

    private static List<Rule> loadFromDir(Path path) {
        List<Rule> allRule = new ArrayList<>();

        // 确保目录存在
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return allRule;
        }

        // 遍历所有JSON文件
        try (var stream = Files.newDirectoryStream(path, "*.json")) {
            for (Path file : stream) {
                allRule.addAll(loadRecipesFromFile(file));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return allRule;
    }

    private static Collection<? extends Rule> loadRecipesFromFile(Path file) {
        try (Reader reader = Files.newBufferedReader(file)) {
            return GSON.fromJson(reader, new TypeToken<List<Rule>>(){}.getType());
        } catch (IOException e) {
            e.printStackTrace();
            return List.of();
        }
    }
}
