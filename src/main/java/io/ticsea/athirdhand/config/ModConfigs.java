
/*
 * Copyright (c) 2025 ticsea. All rights reserved.
 * This code is provided "as is" without warranty of any kind, express or implied.
 * You may copy, modify, and distribute this code exclusively for non-commercial
 * purposes (e.g., personal use, academic research, non-profit projects) without
 * prior permission, provided this copyright notice is retained in all copies or
 * derivatives.
 * Commercial use (including, but not limited to, selling the code, using it in
 * paid products/services, or monetizing it in any form) is strictly prohibited
 * without explicit written permission from the copyright holder.
 */

package io.ticsea.athirdhand.config;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;
import net.minecraftforge.fml.loading.FMLConfig;

import java.io.File;

public class ModConfigs {
    public static final ForgeConfigSpec SPEC;
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    private static final BooleanValue SWITCH;
    private static final BooleanValue CHECK_NBT;

    private static final String FILE_NAME = "athirdhand-client.toml";

    static {
        BUILDER.push("Third Hand Mod Config");

        SWITCH = BUILDER
                .comment("是否启用mod\n[true, false]")
                .define("mod_switch", true);

        CHECK_NBT = BUILDER
                .comment("是否检查物品的nbt\n[true, false]")
                .define("check_nbt", true);

        BUILDER.pop();
        SPEC = BUILDER.build();
    }

    public static void loadConfig() {
        CommentedFileConfig file = CommentedFileConfig.builder(new File(FMLConfig.defaultConfigPath(), FILE_NAME))
                .sync()
                .autosave()
                .writingMode(WritingMode.REPLACE)
                .build();
        file.load();
        SPEC.setConfig(file);
    }

    public static boolean isModEnbale() {
        return SWITCH.get();
    }

    public static boolean isCheckNBTEable() {
        return CHECK_NBT.get();
    }

    public static void toggleModEnable() {
        SWITCH.set(!isModEnbale());
    }

    public static void toggleCheckNBTButton() {
        CHECK_NBT.set(!CHECK_NBT.get());
    }
}
