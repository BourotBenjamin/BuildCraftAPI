package buildcraft.api.transport;

import buildcraft.api.core.EnumHandlerPriority;
import net.minecraft.client.renderer.FaceInfo;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public interface IStripesRegistry {
    /** Adds a handler with a {@link EnumHandlerPriority} of {@linkplain EnumHandlerPriority#NORMAL} */
    default void addHandler(IStripesHandlerItem handler) {
        addHandler(handler, EnumHandlerPriority.NORMAL);
    }

    void addHandler(IStripesHandlerItem handler, EnumHandlerPriority priority);

    /** Adds a handler with a {@link EnumHandlerPriority} of {@linkplain EnumHandlerPriority#NORMAL NORMAL} */
    default void addHandler(IStripesHandlerBlock handler) {
        addHandler(handler, EnumHandlerPriority.NORMAL);
    }

    void addHandler(IStripesHandlerBlock handler, EnumHandlerPriority priority);

    /** @param pos The position of the stripes pipe.
     * @return True if a handler handled the itemstack, false otherwise (and so nothing has been done) */
    boolean handleItem(Level world,
                       BlockPos pos,
                       FaceInfo direction,
                       ItemStack stack,
                       Player player,
                       IStripesActivator activator);

    /** @return True if a handler broke a block, false otherwise (and so nothing has been done) */
    boolean handleBlock(Level world,
                        BlockPos pos,
                        FaceInfo direction,
                        Player player,
                        IStripesActivator activator);
}
