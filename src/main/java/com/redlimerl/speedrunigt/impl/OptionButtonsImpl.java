package com.redlimerl.speedrunigt.impl;

import com.redlimerl.speedrunigt.SpeedRunIGT;
import com.redlimerl.speedrunigt.api.OptionButtonFactory;
import com.redlimerl.speedrunigt.api.SpeedRunIGTApi;
import com.redlimerl.speedrunigt.gui.screen.SpeedRunCategoryScreen;
import com.redlimerl.speedrunigt.gui.screen.SpeedRunIGTInfoScreen;
import com.redlimerl.speedrunigt.gui.screen.TimerCustomizeScreen;
import com.redlimerl.speedrunigt.option.SpeedRunOption;
import com.redlimerl.speedrunigt.option.SpeedRunOptions;
import net.fabricmc.loader.api.ModContainer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ConfirmScreen;
import net.minecraft.client.gui.screen.ScreenTexts;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.text.LiteralText;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Util;
import org.apache.commons.io.FileUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;

import static com.redlimerl.speedrunigt.SpeedRunIGTClient.TIMER_DRAWER;

public class OptionButtonsImpl implements SpeedRunIGTApi {

    @Override
    public Collection<OptionButtonFactory> createOptionButtons() {
        ArrayList<OptionButtonFactory> factories = new ArrayList<>();

        factories.add(screen -> new OptionButtonFactory.Builder()
                .setButtonWidget(
                        new ButtonWidget(0, 0, 150, 20, new TranslatableText("speedrunigt.option.timer_position"),
                                (ButtonWidget button) -> MinecraftClient.getInstance().openScreen(new TimerCustomizeScreen(screen)))
                )
                .setCategory("speedrunigt.option.category.general")
        );

        factories.add(screen -> new OptionButtonFactory.Builder()
                .setButtonWidget(
                        new ButtonWidget(0, 0, 150, 20, new TranslatableText("speedrunigt.option.timer_category"),
                                (ButtonWidget button) -> MinecraftClient.getInstance().openScreen(new SpeedRunCategoryScreen(screen)))
                )
                .setCategory("speedrunigt.option.category.general")
        );

        factories.add(screen -> new OptionButtonFactory.Builder()
                .setButtonWidget(
                        new ButtonWidget(0, 0, 150, 20, new TranslatableText("speedrunigt.option.check_info"),
                                (ButtonWidget button) -> MinecraftClient.getInstance().openScreen(new SpeedRunIGTInfoScreen(screen)))
                )
                .setCategory("speedrunigt.option.category.general")
        );

        factories.add(screen -> new OptionButtonFactory.Builder()
                .setButtonWidget(
                        new ButtonWidget(0, 0, 150, 20, new TranslatableText("speedrunigt.option.reload"),
                                (ButtonWidget button) -> MinecraftClient.getInstance().openScreen(new ConfirmScreen(boolean1 -> {
                                    if (boolean1) {
                                        SpeedRunOption.reload();
                                    }
                                    MinecraftClient.getInstance().openScreen(screen);
                                }, new TranslatableText("speedrunigt.message.reload_options"), LiteralText.EMPTY)))
                )
                .setCategory("speedrunigt.option.category.general")
        );

        factories.add(screen -> new OptionButtonFactory.Builder()
                .setButtonWidget(
                        new ButtonWidget(0, 0, 150, 20, new TranslatableText("speedrunigt.option.global_options").append(" : ").append(SpeedRunOption.isUsingGlobalConfig() ? ScreenTexts.ON : ScreenTexts.OFF),
                                (ButtonWidget button) -> {
                                    SpeedRunOption.setUseGlobalConfig(!SpeedRunOption.isUsingGlobalConfig());
                                    MinecraftClient.getInstance().openScreen(new ConfirmScreen(boolean1 -> {
                                        if (boolean1) {
                                            SpeedRunOption.reload();
                                        }
                                        MinecraftClient.getInstance().openScreen(screen);
                                    }, new TranslatableText("speedrunigt.message.reload_options"), LiteralText.EMPTY));
                                })
                )
                .setToolTip(() -> I18n.translate("speedrunigt.option.global_options.description", SpeedRunOption.getConfigPath()))
                .setCategory("speedrunigt.option.category.general")
        );

        factories.add(screen -> new OptionButtonFactory.Builder()
                .setButtonWidget(
                        new ButtonWidget(0, 0, 150, 20, new TranslatableText("speedrunigt.option.safe_font_mode").append(" : ").append(SpeedRunOption.getOption(SpeedRunOptions.CUSTOM_FONT_SAFE_MODE) ? ScreenTexts.ON : ScreenTexts.OFF),
                                (ButtonWidget button) -> {
                                    SpeedRunOption.setOption(SpeedRunOptions.CUSTOM_FONT_SAFE_MODE, !SpeedRunOption.getOption(SpeedRunOptions.CUSTOM_FONT_SAFE_MODE));
                                    button.setMessage(new TranslatableText("speedrunigt.option.safe_font_mode").append(" : ").append(SpeedRunOption.getOption(SpeedRunOptions.CUSTOM_FONT_SAFE_MODE) ? ScreenTexts.ON : ScreenTexts.OFF));
                                })
                )
                .setToolTip(() -> I18n.translate("speedrunigt.option.safe_font_mode.description"))
                .setCategory("speedrunigt.option.category.general")
        );

        factories.add(screen -> new OptionButtonFactory.Builder()
                .setButtonWidget(
                        new ButtonWidget(0, 0, 150, 20, new TranslatableText("speedrunigt.option.timer_position.toggle_timer").append(" : ").append(TIMER_DRAWER.isToggle() ? ScreenTexts.ON : ScreenTexts.OFF),
                                (ButtonWidget button) -> {
                                    TIMER_DRAWER.setToggle(!TIMER_DRAWER.isToggle());
                                    SpeedRunOption.setOption(SpeedRunOptions.TOGGLE_TIMER, TIMER_DRAWER.isToggle());
                                    button.setMessage(new TranslatableText("speedrunigt.option.timer_position.toggle_timer").append(" : ").append(TIMER_DRAWER.isToggle() ? ScreenTexts.ON : ScreenTexts.OFF));
                                })
                )
                .setCategory("speedrunigt.option.category.timer")
        );

        factories.add(screen -> new OptionButtonFactory.Builder()
                .setButtonWidget(
                        new ButtonWidget(0, 0, 150, 20, new TranslatableText("speedrunigt.option.hide_timer_in_options").append(" : ").append(SpeedRunOption.getOption(SpeedRunOptions.HIDE_TIMER_IN_OPTIONS) ? ScreenTexts.ON : ScreenTexts.OFF),
                                (ButtonWidget button) -> {
                                    SpeedRunOption.setOption(SpeedRunOptions.HIDE_TIMER_IN_OPTIONS, !SpeedRunOption.getOption(SpeedRunOptions.HIDE_TIMER_IN_OPTIONS));
                                    button.setMessage(new TranslatableText("speedrunigt.option.hide_timer_in_options").append(" : ").append(SpeedRunOption.getOption(SpeedRunOptions.HIDE_TIMER_IN_OPTIONS) ? ScreenTexts.ON : ScreenTexts.OFF));
                                })
                )
                .setCategory("speedrunigt.option.category.timer")
        );

        factories.add(screen -> new OptionButtonFactory.Builder()
                .setButtonWidget(
                        new ButtonWidget(0, 0, 150, 20, new TranslatableText("speedrunigt.option.hide_timer_in_debugs").append(" : ").append(SpeedRunOption.getOption(SpeedRunOptions.HIDE_TIMER_IN_DEBUGS) ? ScreenTexts.ON : ScreenTexts.OFF),
                                (ButtonWidget button) -> {
                                    SpeedRunOption.setOption(SpeedRunOptions.HIDE_TIMER_IN_DEBUGS, !SpeedRunOption.getOption(SpeedRunOptions.HIDE_TIMER_IN_DEBUGS));
                                    button.setMessage(new TranslatableText("speedrunigt.option.hide_timer_in_debugs").append(" : ").append(SpeedRunOption.getOption(SpeedRunOptions.HIDE_TIMER_IN_DEBUGS) ? ScreenTexts.ON : ScreenTexts.OFF));
                                })
                )
                .setCategory("speedrunigt.option.category.timer")
        );

        factories.add(screen -> new OptionButtonFactory.Builder()
                .setButtonWidget(
                        new ButtonWidget(0, 0, 150, 20, new TranslatableText("speedrunigt.option.timer_start_type").append(" : ").append(new TranslatableText("speedrunigt.option.timer_start_type." + SpeedRunOption.getOption(SpeedRunOptions.WAITING_FIRST_INPUT).name().toLowerCase(Locale.ROOT))),
                                (ButtonWidget button) -> {
                                    int order = SpeedRunOption.getOption(SpeedRunOptions.WAITING_FIRST_INPUT).ordinal() + 1;
                                    SpeedRunOptions.TimerStartType[] intervals = SpeedRunOptions.TimerStartType.values();
                                    SpeedRunOption.setOption(SpeedRunOptions.WAITING_FIRST_INPUT, intervals[order % intervals.length]);
                                    button.setMessage(new TranslatableText("speedrunigt.option.timer_start_type").append(" : ").append(new TranslatableText("speedrunigt.option.timer_start_type." + SpeedRunOption.getOption(SpeedRunOptions.WAITING_FIRST_INPUT).name().toLowerCase(Locale.ROOT))));
                                })
                )
                .setToolTip(() -> I18n.translate("speedrunigt.option.timer_start_type.description"))
                .setCategory("speedrunigt.option.category.timing")
        );

        factories.add(screen -> new OptionButtonFactory.Builder()
                .setButtonWidget(
                        new ButtonWidget(0, 0, 150, 20, new TranslatableText("speedrunigt.option.auto_toggle_coop").append(" : ").append(SpeedRunOption.getOption(SpeedRunOptions.AUTOMATIC_COOP_MODE) ? ScreenTexts.ON : ScreenTexts.OFF),
                                (ButtonWidget button) -> {
                                    SpeedRunOption.setOption(SpeedRunOptions.AUTOMATIC_COOP_MODE, !SpeedRunOption.getOption(SpeedRunOptions.AUTOMATIC_COOP_MODE));
                                    button.setMessage(new TranslatableText("speedrunigt.option.auto_toggle_coop").append(" : ").append(SpeedRunOption.getOption(SpeedRunOptions.AUTOMATIC_COOP_MODE) ? ScreenTexts.ON : ScreenTexts.OFF));
                                })
                )
                .setToolTip(() -> I18n.translate("speedrunigt.option.auto_toggle_coop.description"))
                .setCategory("speedrunigt.option.category.timer")
        );

        factories.add(screen -> new OptionButtonFactory.Builder()
                .setButtonWidget(
                        new ButtonWidget(0, 0, 150, 20, new TranslatableText("speedrunigt.option.start_old_worlds").append(" : ").append(SpeedRunOption.getOption(SpeedRunOptions.TIMER_START_GENERATED_WORLD) ? ScreenTexts.ON : ScreenTexts.OFF),
                                (ButtonWidget button) -> {
                                    SpeedRunOption.setOption(SpeedRunOptions.TIMER_START_GENERATED_WORLD, !SpeedRunOption.getOption(SpeedRunOptions.TIMER_START_GENERATED_WORLD));
                                    button.setMessage(new TranslatableText("speedrunigt.option.start_old_worlds").append(" : ").append(SpeedRunOption.getOption(SpeedRunOptions.TIMER_START_GENERATED_WORLD) ? ScreenTexts.ON : ScreenTexts.OFF));
                                })
                )
                .setToolTip(() -> I18n.translate("speedrunigt.option.start_old_worlds.description"))
                .setCategory("speedrunigt.option.category.timing")
        );

        factories.add(screen -> new OptionButtonFactory.Builder()
                .setButtonWidget(
                        new ButtonWidget(0, 0, 150, 20, new TranslatableText("speedrunigt.option.limitless_reset").append(" : ").append(SpeedRunOption.getOption(SpeedRunOptions.TIMER_LIMITLESS_RESET) ? ScreenTexts.ON : ScreenTexts.OFF),
                                (ButtonWidget button) -> {
                                    SpeedRunOption.setOption(SpeedRunOptions.TIMER_LIMITLESS_RESET, !SpeedRunOption.getOption(SpeedRunOptions.TIMER_LIMITLESS_RESET));
                                    button.setMessage(new TranslatableText("speedrunigt.option.limitless_reset").append(" : ").append(SpeedRunOption.getOption(SpeedRunOptions.TIMER_LIMITLESS_RESET) ? ScreenTexts.ON : ScreenTexts.OFF));
                                })
                )
                .setToolTip(() -> I18n.translate("speedrunigt.option.limitless_reset.description"))
                .setCategory("speedrunigt.option.category.timing")
        );

        if (Math.random() < 0.1) {
            factories.add(screen -> new OptionButtonFactory.Builder()
                    .setButtonWidget(new ButtonWidget(0, 0, 150, 20, new LiteralText("amongus"), (ButtonWidget button) -> {}))
                    .setCategory("sus")
            );
        }

        if (Math.random() < 0.05) {
            factories.add(screen -> new OptionButtonFactory.Builder()
                    .setButtonWidget(new ButtonWidget(0, 0, 150, 20, new LiteralText("Dream Luck : OFF"), (ButtonWidget button) -> button.setMessage(new LiteralText("HAHA no u"))))
                    .setCategory("Cheat")
            );
        }

        if (Math.random() < 0.01) {
            factories.add(screen -> new OptionButtonFactory.Builder()
                    .setButtonWidget(new ButtonWidget(0, 0, 150, 20, new LiteralText("no way LMAO").formatted(Formatting.OBFUSCATED), (ButtonWidget button) -> {}))
                    .setCategory("???")
            );
        }

        factories.add(screen -> new OptionButtonFactory.Builder()
                .setButtonWidget(
                        new ButtonWidget(0, 0, 150, 20, new TranslatableText("speedrunigt.option.current_extensions"),
                                (ButtonWidget button) -> {})
                )
                .setToolTip(() -> {
                    StringBuilder extension = new StringBuilder(I18n.translate("speedrunigt.option.current_extensions.description", SpeedRunIGTApi.getProviders().length));
                    extension.append("\n");
                    int auto = 0;
                    for (ModContainer provider : SpeedRunIGTApi.getProviders()) {
                        if (auto++ > 4) {
                            auto = 0;
                            extension.append("\n");
                        }
                        extension.append(String.format("%s v%s,", provider.getMetadata().getName(), provider.getMetadata().getVersion()));
                    }
                    return extension.substring(0, extension.length() - 1);
                })
                .setCategory("speedrunigt.option.category.general")
        );

        factories.add(screen -> new OptionButtonFactory.Builder()
                .setButtonWidget(
                        new ButtonWidget(0, 0, 150, 20, new TranslatableText("speedrunigt.option.legacy_igt_mode").append(" : ").append(SpeedRunOption.getOption(SpeedRunOptions.TIMER_LEGACY_IGT_MODE) ? ScreenTexts.ON : ScreenTexts.OFF),
                                (ButtonWidget button) -> {
                                    SpeedRunOption.setOption(SpeedRunOptions.TIMER_LEGACY_IGT_MODE, !SpeedRunOption.getOption(SpeedRunOptions.TIMER_LEGACY_IGT_MODE));
                                    button.setMessage(new TranslatableText("speedrunigt.option.legacy_igt_mode").append(" : ").append(SpeedRunOption.getOption(SpeedRunOptions.TIMER_LEGACY_IGT_MODE) ? ScreenTexts.ON : ScreenTexts.OFF));
                                })
                )
                .setToolTip(() -> I18n.translate("speedrunigt.option.legacy_igt_mode.description"))
                .setCategory("speedrunigt.option.category.timer")
        );

        factories.add(screen -> new OptionButtonFactory.Builder()
                .setButtonWidget(
                        new ButtonWidget(0, 0, 150, 20, new TranslatableText("speedrunigt.option.auto_save_interval").append(" : ").append(new TranslatableText("speedrunigt.option.auto_save_interval." + SpeedRunOption.getOption(SpeedRunOptions.TIMER_DATA_AUTO_SAVE).name().toLowerCase(Locale.ROOT))),
                                (ButtonWidget button) -> {
                                    int order = SpeedRunOption.getOption(SpeedRunOptions.TIMER_DATA_AUTO_SAVE).ordinal() + 1;
                                    SpeedRunOptions.TimerSaveInterval[] intervals = SpeedRunOptions.TimerSaveInterval.values();
                                    SpeedRunOption.setOption(SpeedRunOptions.TIMER_DATA_AUTO_SAVE, intervals[order % intervals.length]);
                                    button.setMessage(new TranslatableText("speedrunigt.option.auto_save_interval").append(" : ").append(new TranslatableText("speedrunigt.option.auto_save_interval." + SpeedRunOption.getOption(SpeedRunOptions.TIMER_DATA_AUTO_SAVE).name().toLowerCase(Locale.ROOT))));
                                })
                )
                .setToolTip(() -> I18n.translate("speedrunigt.option.auto_save_interval.description"))
                .setCategory("speedrunigt.option.category.timer")
        );

        factories.add(screen -> new OptionButtonFactory.Builder()
                .setButtonWidget(
                        new ButtonWidget(0, 0, 150, 20, new TranslatableText("speedrunigt.option.auto_retime").append(" : ").append(SpeedRunOption.getOption(SpeedRunOptions.AUTO_RETIME_FOR_GUIDELINE) ? ScreenTexts.ON : ScreenTexts.OFF),
                                (ButtonWidget button) -> {
                                    SpeedRunOption.setOption(SpeedRunOptions.AUTO_RETIME_FOR_GUIDELINE, !SpeedRunOption.getOption(SpeedRunOptions.AUTO_RETIME_FOR_GUIDELINE));
                                    button.setMessage(new TranslatableText("speedrunigt.option.auto_retime").append(" : ").append(SpeedRunOption.getOption(SpeedRunOptions.AUTO_RETIME_FOR_GUIDELINE) ? ScreenTexts.ON : ScreenTexts.OFF));
                                })
                )
                .setToolTip(() -> I18n.translate("speedrunigt.option.auto_retime.description"))
                .setCategory("speedrunigt.option.category.timer")
        );

        factories.add(screen -> new OptionButtonFactory.Builder()
                .setButtonWidget(
                        new ButtonWidget(0, 0, 150, 20, new TranslatableText("speedrunigt.option.generate_record").append(" : ").append(new TranslatableText("speedrunigt.option.generate_record." + SpeedRunOption.getOption(SpeedRunOptions.GENERATE_RECORD_FILE).name().toLowerCase(Locale.ROOT))),
                                (ButtonWidget button) -> {
                                    int order = SpeedRunOption.getOption(SpeedRunOptions.GENERATE_RECORD_FILE).ordinal() + 1;
                                    SpeedRunOptions.RecordGenerateType[] intervals = SpeedRunOptions.RecordGenerateType.values();
                                    SpeedRunOption.setOption(SpeedRunOptions.GENERATE_RECORD_FILE, intervals[order % intervals.length]);
                                    button.setMessage(new TranslatableText("speedrunigt.option.generate_record").append(" : ").append(new TranslatableText("speedrunigt.option.generate_record." + SpeedRunOption.getOption(SpeedRunOptions.GENERATE_RECORD_FILE).name().toLowerCase(Locale.ROOT))));
                                })
                )
                .setCategory("speedrunigt.option.category.records")
        );

        factories.add(screen -> new OptionButtonFactory.Builder()
                .setButtonWidget(
                        new ButtonWidget(0, 0, 150, 20, new TranslatableText("speedrunigt.option.open_records_folder"),
                                (ButtonWidget button) -> Util.getOperatingSystem().open(SpeedRunIGT.getRecordsPath().toFile()))
                )
                .setCategory("speedrunigt.option.category.records")
        );

        factories.add(screen -> new OptionButtonFactory.Builder()
                .setButtonWidget(
                        new ButtonWidget(0, 0, 150, 20, new TranslatableText("speedrunigt.option.delete_all_records"),
                                (ButtonWidget button) ->
                                    MinecraftClient.getInstance().openScreen(new ConfirmScreen(boolean1 -> {
                                        if (boolean1) {
                                            try {
                                                FileUtils.deleteDirectory(SpeedRunIGT.getRecordsPath().toFile());
                                                if (!SpeedRunIGT.getRecordsPath().toFile().mkdir()) {
                                                    SpeedRunIGT.error("Failed to make records directory");
                                                }
                                            } catch (IOException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                        MinecraftClient.getInstance().openScreen(screen);
                                    }, new TranslatableText("speedrunigt.option.delete_all_records.description"), LiteralText.EMPTY))
                                )
                )
                .setCategory("speedrunigt.option.category.records")
        );

        factories.add(screen -> new OptionButtonFactory.Builder()
                .setButtonWidget(
                        new ButtonWidget(0, 0, 150, 20, new TranslatableText("speedrunigt.option.debug_mode").append(" : ").append(SpeedRunIGT.IS_DEBUG_MODE ? ScreenTexts.ON : ScreenTexts.OFF),
                                (ButtonWidget button) -> {
                                    SpeedRunIGT.IS_DEBUG_MODE = !SpeedRunIGT.IS_DEBUG_MODE;
                                    SpeedRunIGT.error("Debug mode is " + (SpeedRunIGT.IS_DEBUG_MODE ? "enabled" : "disabled") + "!");
                                    button.setMessage(new TranslatableText("speedrunigt.option.debug_mode").append(" : ").append(SpeedRunIGT.IS_DEBUG_MODE ? ScreenTexts.ON : ScreenTexts.OFF));
                                })
                )
                .setToolTip(() -> I18n.translate("speedrunigt.option.debug_mode.description"))
                .setCategory("Debug")
        );

        return factories;
    }
}
