package buildcraft.api.mj;

import net.minecraft.core.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/** Provides a quick way to return all types of a single {@link IMjConnector} for all the different capabilities. */
public class MjCapabilityHelper implements ICapabilityProvider {

    @Nonnull
    private final IMjConnector connector;
    private final LazyOptional<IMjConnector> optionalConnector;


    @Nullable
    private final IMjReceiver receiver;
    private final LazyOptional<IMjReceiver> optionalReceiver;

    @Nullable
    private final IMjRedstoneReceiver rsReceiver;
    private final LazyOptional<IMjRedstoneReceiver> optionalRSReceiver;

    @Nullable
    private final IMjReadable readable;
    private final LazyOptional<IMjReadable> optionalReadable;

    @Nullable
    private final IMjPassiveProvider provider;
    private final LazyOptional<IMjPassiveProvider> optionalProviderr;

    public MjCapabilityHelper(@Nonnull IMjConnector mj) {
        this.connector = mj;
        this.optionalConnector = LazyOptional.of(() -> connector);
        this.receiver = mj instanceof IMjReceiver ? (IMjReceiver) mj : null;
        this.optionalReceiver = this.receiver == null ? LazyOptional.empty() : LazyOptional.of(() -> receiver);
        this.rsReceiver = mj instanceof IMjRedstoneReceiver ? (IMjRedstoneReceiver) mj : null;
        this.optionalRSReceiver = this.rsReceiver == null ? LazyOptional.empty() : LazyOptional.of(() -> rsReceiver);
        this.readable = mj instanceof IMjReadable ? (IMjReadable) mj : null;
        this.optionalReadable = this.readable == null ? LazyOptional.empty() : LazyOptional.of(() -> readable);
        this.provider = mj instanceof IMjPassiveProvider ? (IMjPassiveProvider) mj : null;
        this.optionalProviderr = this.provider == null ? LazyOptional.empty() : LazyOptional.of(() -> provider);
    }

    public boolean hasCapability(@Nonnull Capability<?> capability, @org.jetbrains.annotations.Nullable Direction facing) {
        return getCapability(capability, facing) != null;
    }

    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> capability, @org.jetbrains.annotations.Nullable Direction side) {
        if (capability == MjAPI.CAP_CONNECTOR) {
            return MjAPI.CAP_CONNECTOR.orEmpty(capability, optionalConnector);
        }
        if (capability == MjAPI.CAP_RECEIVER) {
            return MjAPI.CAP_RECEIVER.orEmpty(capability, optionalReceiver);
        }
        if (capability == MjAPI.CAP_REDSTONE_RECEIVER) {
            return MjAPI.CAP_REDSTONE_RECEIVER.orEmpty(capability, optionalRSReceiver);
        }
        if (capability == MjAPI.CAP_READABLE) {
            return MjAPI.CAP_READABLE.orEmpty(capability, optionalReadable);
        }
        if (capability == MjAPI.CAP_PASSIVE_PROVIDER) {
            return MjAPI.CAP_PASSIVE_PROVIDER.orEmpty(capability, optionalProviderr);
        }
        return LazyOptional.empty();
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap) {
        return (LazyOptional<T>) getCapability(cap, null);
    }
}
