package buildcraft.api.transport.pipe;

import net.minecraft.client.renderer.FaceInfo;

/** Fired whenever a connection change is picked up by an {@link IPipe}. This even doesn't include the new value
 * (boolean isConnected) as it can be accessed via {@link IPipe#isConnected(FaceInfo)}. */
public class PipeEventConnectionChange extends PipeEvent {

    public final FaceInfo direction;

    public PipeEventConnectionChange(IPipeHolder holder, FaceInfo direction) {
        super(holder);
        this.direction = direction;
    }
}
