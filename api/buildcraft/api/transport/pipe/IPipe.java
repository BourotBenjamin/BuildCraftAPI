package buildcraft.api.transport.pipe;

import net.minecraft.client.renderer.FaceInfo;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

public interface IPipe extends ICapabilityProvider {
    IPipeHolder getHolder();

    PipeDefinition getDefinition();

    PipeBehaviour getBehaviour();

    PipeFlow getFlow();

    DyeColor getColour();

    void setColour(DyeColor colour);

    void markForUpdate();

    BlockEntity getConnectedTile(FaceInfo side);

    IPipe getConnectedPipe(FaceInfo side);

    boolean isConnected(FaceInfo side);

    ConnectedType getConnectedType(FaceInfo side);

    enum ConnectedType {
        TILE,
        PIPE
    }
}
