
package github.ticsea.athirdhand.config;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;

public class ModConfig {
    public static final ForgeConfigSpec SPEC;
    private static final ForgeConfigSpec.Builder BUILDER;
    private static final BooleanValue SWITCH;

    private static final String FILE_NAME = "athirdhand-client.toml";

    static {
        BUILDER = new ForgeConfigSpec.Builder();
        SWITCH = register(
                "是否启用mod\n[true, false]",
                "mod_switch"
        );

        SPEC = BUILDER.build();
    }

    private static BooleanValue register(String comment, String name) {
        
        return BUILDER
                .comment(comment)
                .define(name, true);
    }

    public static void loadConfig() {
        CommentedFileConfig file = CommentedFileConfig.builder(FILE_NAME)
                .sync()
                .autosave()
                .writingMode(WritingMode.REPLACE)
                .build();
        file.load();
        SPEC.setConfig(file);
    }

    public static BooleanValue getSwitch() {
        return SWITCH;
    }
}
