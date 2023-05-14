package buildcraft.api.items;

import net.minecraft.world.item.ItemStack;

import javax.annotation.Nonnull;

public interface INamedItem {
    String getName(@Nonnull ItemStack stack);

    boolean setName(@Nonnull ItemStack stack, String name);
}
