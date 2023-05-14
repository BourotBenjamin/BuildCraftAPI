package buildcraft.api.transport.pipe;

import buildcraft.api.core.EnumPipePart;
import net.minecraft.client.renderer.FaceInfo;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.network.NetworkEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.io.IOException;

public abstract class PipeBehaviour implements ICapabilityProvider {
    public final IPipe pipe;

    public PipeBehaviour(IPipe pipe) {
        this.pipe = pipe;
    }

    public PipeBehaviour(IPipe pipe, CompoundTag nbt) {
        this.pipe = pipe;
    }

    public CompoundTag writeToNbt() {
        return new CompoundTag();
    }

    public void writePayload(FriendlyByteBuf buffer, Dist side) {}

    public void readPayload(FriendlyByteBuf buffer, Dist side, NetworkEvent.Context ctx) throws IOException {}

    /** @deprecated Replaced by {@link #getTextureData(FaceInfo)}. */
    @Deprecated
    public int getTextureIndex(FaceInfo face) {
        return 0;
    }

    public PipeFaceTex getTextureData(FaceInfo face) {
        return PipeFaceTex.get(getTextureIndex(face));
    }

    // Event handling

    public boolean canConnect(FaceInfo face, PipeBehaviour other) {
        return true;
    }

    public boolean canConnect(FaceInfo face, BlockEntity oTile) {
        return true;
    }

    /** Used to force a connection to a given tile, even if the {@link PipeFlow} wouldn't normally connect to it. */
    public boolean shouldForceConnection(FaceInfo face, BlockEntity oTile) {
        return false;
    }

    public boolean onPipeActivate(Player player, HitResult trace, float hitX, float hitY, float hitZ,
                                  EnumPipePart part) {
        return false;
    }

    public void onEntityCollide(Entity entity) {}

    public void onTick() {}

    public boolean hasCapability(@Nonnull Capability<?> capability, Direction side) {
        return getCapability(capability, side).isPresent();
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull final Capability<T> cap, final @Nullable Direction side) {
        return LazyOptional.empty();
    }

    public void addDrops(NonNullList<ItemStack> toDrop, int fortune) {}
}
