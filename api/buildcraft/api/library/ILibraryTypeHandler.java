package buildcraft.api.library;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;

public interface ILibraryTypeHandler {
    boolean isHandler(ItemStack stack, boolean store);

    String getFileExtension();

    int getTextColor();

    String getName(ItemStack stack);

    ItemStack load(ItemStack stack, CompoundTag compound);

    boolean store(ItemStack stack, CompoundTag compound);
}
