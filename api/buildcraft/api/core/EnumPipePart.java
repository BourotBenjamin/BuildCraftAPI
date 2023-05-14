package buildcraft.api.core;

import com.google.common.collect.Maps;
import net.minecraft.client.renderer.FaceInfo;
import net.minecraft.nbt.ByteTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;
import net.minecraft.util.StringRepresentable;

import java.util.Locale;
import java.util.Map;

public enum EnumPipePart implements StringRepresentable {
    DOWN(FaceInfo.DOWN),
    UP(FaceInfo.UP),
    NORTH(FaceInfo.NORTH),
    SOUTH(FaceInfo.SOUTH),
    WEST(FaceInfo.WEST),
    EAST(FaceInfo.EAST),
    /** CENTER, UNKNOWN and ALL are all valid uses of this. */
    CENTER(null);

    public static final EnumPipePart[] VALUES = values();

    private static final Map<FaceInfo, EnumPipePart> facingMap = Maps.newEnumMap(FaceInfo.class);
    private static final Map<String, EnumPipePart> nameMap = Maps.newHashMap();
    private static final int MAX_VALUES = values().length;

    public final FaceInfo face;

    static {
        for (EnumPipePart part : values()) {
            nameMap.put(part.name(), part);
            if (part.face != null) facingMap.put(part.face, part);
        }
    }

    private static EnumPipePart[] fromFacingArray(FaceInfo... faces) {
        EnumPipePart[] arr = new EnumPipePart[faces.length];
        for (int i = 0; i < faces.length; i++) {
            arr[i] = fromFacing(faces[i]);
        }
        return arr;
    }

    public static int ordinal(FaceInfo face) {
        return face == null ? 6 : face.ordinal();
    }

    public static EnumPipePart fromFacing(FaceInfo face) {
        if (face == null) {
            return EnumPipePart.CENTER;
        }
        return facingMap.get(face);
    }

    public static EnumPipePart fromMeta(int meta) {
        if (meta < 0 || meta >= MAX_VALUES) {
            return EnumPipePart.CENTER;
        }
        return VALUES[meta];
    }

    EnumPipePart(FaceInfo face) {
        this.face = face;
    }

    public int getIndex() {
        if (face == null) return 6;
        return face.ordinal();
    }

    @Override
    public String getSerializedName() {
        return name().toLowerCase(Locale.ROOT);
    }

    public EnumPipePart next() {
        return switch (this) {
            case DOWN -> EAST;
            case EAST -> NORTH;
            case NORTH -> SOUTH;
            case SOUTH -> UP;
            case UP -> WEST;
            case WEST -> DOWN;
            default -> DOWN;
        };
    }

    public FaceInfo opposite(FaceInfo face) {
        return switch (face) {
            case DOWN -> FaceInfo.UP;
            case UP -> FaceInfo.DOWN;
            case NORTH -> FaceInfo.SOUTH;
            case SOUTH -> FaceInfo.NORTH;
            case EAST -> FaceInfo.WEST;
            default -> FaceInfo.EAST;
        };
    }

    public EnumPipePart opposite() {
        if (this == CENTER) {
            return CENTER;
        }
        return fromFacing(opposite(face));
    }

    public static EnumPipePart readFromNBT(Tag base) {
        if (base == null) {
            return CENTER;
        }
        if (base instanceof StringTag) {
            StringTag nbtString = (StringTag) base;
            String string = nbtString.getAsString();
            return nameMap.getOrDefault(string, CENTER);
        } else {
            byte ord = ((ByteTag) base).getAsByte();
            if (ord < 0 || ord > 6) {
                return CENTER;
            }
            return values()[ord];
        }
    }

    public Tag writeToNBT() {
        return StringTag.valueOf(name());
    }
}
