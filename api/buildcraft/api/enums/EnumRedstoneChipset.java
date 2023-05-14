package buildcraft.api.enums;

import buildcraft.api.BCItems;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;

public enum EnumRedstoneChipset implements StringRepresentable {
    RED,
    IRON,
    GOLD,
    QUARTZ,
    DIAMOND;

    private final String name = name().toLowerCase(Locale.ROOT);

    public ItemStack getStack(int stackSize) {
        Item chipset = BCItems.Silicon.REDSTONE_CHIPSET;
        if (chipset == null) {
            return ItemStack.EMPTY;
        }
        ItemStack itemStack = new ItemStack(chipset, stackSize);
        itemStack.setDamageValue(ordinal());
        return itemStack;
    }

    public ItemStack getStack() {
        return getStack(1);
    }

    public static EnumRedstoneChipset fromStack(ItemStack stack) {
        if (stack == null) {
            return RED;
        }
        return fromOrdinal(stack.getDamageValue());
    }

    public static EnumRedstoneChipset fromOrdinal(int ordinal) {
        if (ordinal < 0 || ordinal >= values().length) {
            return RED;
        }
        return values()[ordinal];
    }

    @Override
    public @NotNull String getSerializedName() {
        return name;
    }
}
