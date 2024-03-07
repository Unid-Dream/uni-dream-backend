package pwh.coreStarter.unit;

import lombok.Getter;

public enum FileSizeUnit {
    BYTE(1L, "B"),
    KiB(BYTE.unitBase << 10, "KB"),
    MiB(KiB.unitBase << 10, "MB"),
    GiB(MiB.unitBase << 10, "GB"),
    TiB(GiB.unitBase << 10, "TB"),
    PiB(TiB.unitBase << 10, "PB"),
    EiB(PiB.unitBase << 10, "EB");

    @Getter
    private final Long unitBase;
    @Getter
    private final String unitName;

    FileSizeUnit(Long unitBase, String unitName) {
        this.unitBase = unitBase;
        this.unitName = unitName;
    }

    public static String byteToHumanReadable(long size) {
        if (size >= EiB.getUnitBase()) return formatSize(size, EiB);
        if (size >= PiB.getUnitBase()) return formatSize(size, PiB);
        if (size >= TiB.getUnitBase()) return formatSize(size, TiB);
        if (size >= GiB.getUnitBase()) return formatSize(size, GiB);
        if (size >= MiB.getUnitBase()) return formatSize(size, MiB);
        if (size >= KiB.getUnitBase()) return formatSize(size, KiB);
        return formatSize(size, BYTE);
    }

    public static long toBytes(long size, FileSizeUnit unit) {
        return size * unit.getUnitBase();
    }

    private static String formatSize(long size, FileSizeUnit unit) {
        return String.format("%s %s", size / unit.getUnitBase(), unit.getUnitName());
    }
}
