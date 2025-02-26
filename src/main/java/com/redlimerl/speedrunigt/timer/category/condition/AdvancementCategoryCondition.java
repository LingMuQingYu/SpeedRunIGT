package com.redlimerl.speedrunigt.timer.category.condition;

import com.google.gson.JsonObject;
import com.redlimerl.speedrunigt.timer.category.InvalidCategoryException;
import net.minecraft.advancement.Advancement;

import java.util.Objects;

public class AdvancementCategoryCondition extends CategoryCondition.Condition<Advancement> {

    private final String advancement;

    public AdvancementCategoryCondition(JsonObject jsonObject) throws InvalidCategoryException {
        super(jsonObject);

        try {
            this.advancement = jsonObject.get("advancement").getAsString();
        } catch (Exception e) {
            throw new InvalidCategoryException(InvalidCategoryException.Reason.INVALID_JSON_DATA, "Failed to read condition \"advancement\"");
        }
    }

    @Override
    public boolean checkConditionComplete(Advancement obj) {
        return Objects.equals(obj.getId().toString(), advancement);
    }
}
