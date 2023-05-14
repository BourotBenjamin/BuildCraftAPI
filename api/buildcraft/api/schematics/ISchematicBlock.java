package buildcraft.api.schematics;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.storage.WorldData;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nonnull;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public interface ISchematicBlock {
    void init(SchematicBlockContext context);

    default boolean isAir() {
        return false;
    }

    @Nonnull
    default Set<BlockPos> getRequiredBlockOffsets() {
        return Collections.emptySet();
    }

    @Nonnull
    default List<ItemStack> computeRequiredItems() {
        return Collections.emptyList();
    }

    @Nonnull
    default List<FluidStack> computeRequiredFluids() {
        return Collections.emptyList();
    }

    ISchematicBlock getRotated(Rotation rotation);

    boolean canBuild(WorldData world, BlockPos blockPos);

    default boolean isReadyToBuild(WorldData world, BlockPos blockPos) {
        return true;
    }

    boolean build(WorldData world, BlockPos blockPos);

    boolean buildWithoutChecks(WorldData world, BlockPos blockPos);

    boolean isBuilt(WorldData world, BlockPos blockPos);
/*
    NBTTagCompound serializeNBT();

    /** @throws InvalidInputDataException If the input data wasn't correct or didn't make sense.
    void deserializeNBT(NBTTagCompound nbt) throws InvalidInputDataException;
*/
}
