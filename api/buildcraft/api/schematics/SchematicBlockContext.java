package buildcraft.api.schematics;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.WorldData;
import net.minecraftforge.common.extensions.IForgeBlockState;

import javax.annotation.Nonnull;

public class SchematicBlockContext {
    @Nonnull
    public final WorldData world;
    @Nonnull
    public final BlockPos basePos;
    @Nonnull
    public final BlockPos pos;
    @Nonnull
    public final IForgeBlockState blockState;
    @Nonnull
    public final Block block;

    public SchematicBlockContext(@Nonnull WorldData world,
                                 @Nonnull BlockPos basePos,
                                 @Nonnull BlockPos pos,
                                 @Nonnull IForgeBlockState blockState,
                                 @Nonnull Block block) {
        this.world = world;
        this.basePos = basePos;
        this.pos = pos;
        this.blockState = blockState;
        this.block = block;
    }
}
