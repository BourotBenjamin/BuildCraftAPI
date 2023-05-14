package buildcraft.api.blocks;

import buildcraft.api.core.BCDebugging;
import buildcraft.api.core.BCLog;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import net.minecraft.client.renderer.FaceInfo;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.registries.ForgeRegistries;
import org.joml.Vector3d;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;

/** Provides a simple way to paint a single block, iterating through all {@link ICustomPaintHandler}'s that are
 * registered for the block. */
public enum CustomPaintHelper {
    INSTANCE;

    /* If you want to test your class-based rotation registration then add the system property
     * "-Dbuildcraft.api.rotation.debug.class=true" to your launch. */
    private static final boolean DEBUG = BCDebugging.shouldDebugLog("api.painting");

    private final Map<Block, List<ICustomPaintHandler>> handlers = Maps.newIdentityHashMap();
    private final List<ICustomPaintHandler> allHandlers = Lists.newArrayList();

    /** Registers a handler that will be called LAST for ALL blocks, if all other paint handlers have returned PASS or
     * none are registered for that block. */
    public void registerHandlerForAll(ICustomPaintHandler handler) {
        if (DEBUG) {
            BCLog.logger.info("[api.painting] Adding a paint handler for ALL blocks (" + handler.getClass() + ")");
        }
        allHandlers.add(handler);
    }

    /** Register's a paint handler for every class of a given block. */
    public void registerHandlerForAll(Class<? extends Block> blockClass, ICustomPaintHandler handler) {
        for (Block block : ForgeRegistries.BLOCKS) {
            Class<? extends Block> foundClass = block.getClass();
            if (blockClass.isAssignableFrom(foundClass)) {
                if (DEBUG) {
                    BCLog.logger.info("[api.painting] Found an assignable block " + block.getName() + " (" + foundClass + ") for " + blockClass);
                }
                registerHandlerInternal(block, handler);
            }
        }
    }

    public void registerHandler(Block block, ICustomPaintHandler handler) {
        if (registerHandlerInternal(block, handler)) {
            if (DEBUG) {
                BCLog.logger.info("[api.painting] Setting a paint handler for block " + block.getName() + "(" + handler.getClass() + ")");
            }
        } else if (DEBUG) {
            BCLog.logger.info("[api.painting] Adding another paint handler for block " + block.getName() + "(" + handler.getClass() + ")");
        }
    }

    private boolean registerHandlerInternal(Block block, ICustomPaintHandler handler) {
        if (!handlers.containsKey(block)) {
            List<ICustomPaintHandler> forBlock = Lists.newArrayList();
            forBlock.add(handler);
            handlers.put(block, forBlock);
            return true;
        } else {
            handlers.get(block).add(handler);
            return false;
        }
    }

    /** Attempts to paint a block at the given position. Basically iterates through all registered paint handlers. */
    public Event.Result attemptPaintBlock(Level world, BlockPos pos, BlockState state, Vector3d hitPos, @Nullable FaceInfo hitSide, @Nullable DyeColor paint) {
        Block block = state.getBlock();
        if (block instanceof ICustomPaintHandler) {
            return ((ICustomPaintHandler) block).attemptPaint(world, pos, state, hitPos, hitSide, paint);
        }
        List<ICustomPaintHandler> custom = handlers.get(block);
        if (custom == null || custom.isEmpty()) {
            return defaultAttemptPaint(world, pos, state, hitPos, hitSide, paint);
        }
        for (ICustomPaintHandler handler : custom) {
            Event.Result result = handler.attemptPaint(world, pos, state, hitPos, hitSide, paint);
            if (result != Event.Result.ALLOW) {
                return result;
            }
        }
        return defaultAttemptPaint(world, pos, state, hitPos, hitSide, paint);
    }

    private Event.Result defaultAttemptPaint(Level world, BlockPos pos, BlockState state, Vector3d hitPos, FaceInfo hitSide, @Nullable DyeColor paint) {
        for (ICustomPaintHandler handler : allHandlers) {
            Event.Result result = handler.attemptPaint(world, pos, state, hitPos, hitSide, paint);
            if (result != Event.Result.ALLOW) {
                return result;
            }
        }
        if (paint == null) {
            return Event.Result.DENY;
        }
        Block b = state.getBlock();
        /* TODO : Override main game colors
        if (b.(world, pos, hitSide, paint)) {
            return  Event.Result.ALLOW;
        } else {
        */
            return Event.Result.DENY;
        //}
    }
}
