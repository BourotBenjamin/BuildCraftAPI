package buildcraft.api.blocks;

import buildcraft.api.core.BCDebugging;
import buildcraft.api.core.BCLog;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import net.minecraft.client.renderer.FaceInfo;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;
import java.util.Map;

public enum CustomRotationHelper {
    INSTANCE;

    /* If you want to test your class-based rotation registration then add the system property
     * "-Dbuildcraft.api.rotation.debug.class=true" to your launch. */
    private static final boolean DEBUG = BCDebugging.shouldDebugLog("api.rotation");

    private final Map<Block, List<ICustomRotationHandler>> handlers = Maps.newIdentityHashMap();

    public void registerHandlerForAll(Class<? extends Block> blockClass, ICustomRotationHandler handler) {
        for (Block block : ForgeRegistries.BLOCKS) {
            Class<? extends Block> foundClass = block.getClass();
            if (blockClass.isAssignableFrom(foundClass)) {
                if (DEBUG) {
                    BCLog.logger.info("[api.rotation] Found an assignable block " + block.getName() + " (" + foundClass + ") for " + blockClass);
                }
                registerHandlerInternal(block, handler);
            }
        }
    }

    public void registerHandler(Block block, ICustomRotationHandler handler) {
        if (registerHandlerInternal(block, handler)) {
            if (DEBUG) {
                BCLog.logger.info("[api.rotation] Setting a rotation handler for block " + block.getName());
            }
        } else if (DEBUG) {
            BCLog.logger.info("[api.rotation] Adding another rotation handler for block " + block.getName());
        }
    }

    private boolean registerHandlerInternal(Block block, ICustomRotationHandler handler) {
        if (!handlers.containsKey(block)) {
            List<ICustomRotationHandler> forBlock = Lists.newArrayList();
            forBlock.add(handler);
            handlers.put(block, forBlock);
            return true;
        } else {
            handlers.get(block).add(handler);
            return false;
        }
    }

    public Event.Result attemptRotateBlock(Level world, BlockPos pos, BlockState state, FaceInfo sideWrenched) {
        Block block = state.getBlock();
        if (block instanceof ICustomRotationHandler) {
            return ((ICustomRotationHandler) block).attemptRotation(world, pos, state, sideWrenched);
        }
        if (!handlers.containsKey(block)) return Event.Result.ALLOW;
        for (ICustomRotationHandler handler : handlers.get(block)) {
            Event.Result result = handler.attemptRotation(world, pos, state, sideWrenched);
            if (result != Event.Result.ALLOW) {
                return result;
            }
        }
        return Event.Result.ALLOW;
    }
}
