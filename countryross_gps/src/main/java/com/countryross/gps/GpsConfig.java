package com.countryross.gps;

import net.minecraftforge.common.ForgeConfigSpec;

/**
 * Configuracoes (arquivos TOML do Forge):
 *  - SERVER (valem para todos, o servidor sincroniza com os jogadores): world/serverconfig/countryross_gps-server.toml
 *  - CLIENT (preferencias de cada jogador): config/countryross_gps-client.toml
 */
public final class GpsConfig {
    public static final ForgeConfigSpec SERVER_SPEC;
    public static final ForgeConfigSpec CLIENT_SPEC;

    private static final ForgeConfigSpec.IntValue REVEAL_RADIUS;
    private static final ForgeConfigSpec.BooleanValue SURFACE_ONLY;
    private static final ForgeConfigSpec.BooleanValue REQUIRE_ITEM;

    private static final ForgeConfigSpec.BooleanValue BOOT_ANIMATION;
    private static final ForgeConfigSpec.BooleanValue RADAR_EFFECT;
    private static final ForgeConfigSpec.BooleanValue SOUNDS;
    private static final ForgeConfigSpec.DoubleValue SOUND_VOLUME;
    private static final ForgeConfigSpec.DoubleValue DEFAULT_ZOOM;
    private static final ForgeConfigSpec.BooleanValue SHOW_GRID;
    private static final ForgeConfigSpec.BooleanValue SCANLINES;
    private static final ForgeConfigSpec.BooleanValue SHOW_COORDINATES;

    static {
        ForgeConfigSpec.Builder s = new ForgeConfigSpec.Builder();
        s.comment("Mapa do CountryRoss GPS (vale para todos os jogadores do servidor)").push("mapa");
        REVEAL_RADIUS = s
                .comment("Raio, em blocos, ao redor do jogador que o GPS revela enquanto ele anda.",
                        "Menor = menos terreno visivel. Padrao: 32")
                .defineInRange("revealRadius", 32, 8, 128);
        SURFACE_ONLY = s
                .comment("true = o GPS so mapeia quando o jogador esta perto da superficie (nao revela o terreno",
                        "de cima enquanto ele esta em cavernas/minas).")
                .define("surfaceOnly", true);
        REQUIRE_ITEM = s
                .comment("true = o terreno so e mapeado enquanto o jogador carrega o item CountryRoss GPS no inventario.")
                .define("requireItem", true);
        s.pop();
        SERVER_SPEC = s.build();

        ForgeConfigSpec.Builder c = new ForgeConfigSpec.Builder();
        c.comment("Preferencias visuais e sonoras do CountryRoss GPS (so afetam voce)").push("gps");
        BOOT_ANIMATION = c.comment("Animacao de inicializacao do aparelho ao abrir (clique ou tecla pula).")
                .define("bootAnimation", true);
        RADAR_EFFECT = c.comment("Varredura de radar e pulso ao redor da setinha.")
                .define("radarEffect", true);
        SOUNDS = c.comment("Efeitos sonoros do aparelho.").define("sounds", true);
        SOUND_VOLUME = c.comment("Volume dos efeitos sonoros (0.0 a 1.0).")
                .defineInRange("soundVolume", 0.6D, 0.0D, 1.0D);
        DEFAULT_ZOOM = c.comment("Zoom ao abrir o mapa (pixels de tela por bloco).")
                .defineInRange("defaultZoom", 2.0D, 0.5D, 8.0D);
        SHOW_GRID = c.comment("Grade de coordenadas no mapa.").define("showGrid", true);
        SCANLINES = c.comment("Linhas de varredura de tela analogica.").define("scanlines", true);
        SHOW_COORDINATES = c.comment("Mostrar X/Z/ALT no rodape do aparelho.").define("showCoordinates", true);
        c.pop();
        CLIENT_SPEC = c.build();
    }

    private GpsConfig() {}

    public static int revealRadius() {
        try {
            return REVEAL_RADIUS.get();
        } catch (Exception e) {
            return 32;
        }
    }

    public static boolean surfaceOnly() {
        try {
            return SURFACE_ONLY.get();
        } catch (Exception e) {
            return true;
        }
    }

    public static boolean requireItem() {
        try {
            return REQUIRE_ITEM.get();
        } catch (Exception e) {
            return true;
        }
    }

    public static boolean bootAnimation() { return get(BOOT_ANIMATION, true); }
    public static boolean radarEffect() { return get(RADAR_EFFECT, true); }
    public static boolean sounds() { return get(SOUNDS, true); }
    public static boolean showGrid() { return get(SHOW_GRID, true); }
    public static boolean scanlines() { return get(SCANLINES, true); }
    public static boolean showCoordinates() { return get(SHOW_COORDINATES, true); }

    public static double soundVolume() {
        try {
            return SOUND_VOLUME.get();
        } catch (Exception e) {
            return 0.6D;
        }
    }

    public static double defaultZoom() {
        try {
            return DEFAULT_ZOOM.get();
        } catch (Exception e) {
            return 2.0D;
        }
    }

    private static boolean get(ForgeConfigSpec.BooleanValue v, boolean fallback) {
        try {
            return v.get();
        } catch (Exception e) {
            return fallback;
        }
    }
}
