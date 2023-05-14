package buildcraft.api.blocks;

import net.minecraft.client.renderer.FaceInfo;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.extensions.IForgeBlockState;
import net.minecraftforge.eventbus.api.Event;

public interface ICustomRotationHandler {
    Event.Result attemptRotation(Level world, BlockPos pos, IForgeBlockState state, FaceInfo sideWrenched);
}
