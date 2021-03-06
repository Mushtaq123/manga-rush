package com.mangarush.constants;

/** Abstract path containing path to files used by the game */
public abstract class Paths {
	/** Directories */
	public static final String atlasesDir = "atlases/";
	public static final String backgroundsDir = "backgrounds/";
	public static final String charactersDir = "characters/";
	public static final String fontDir = "fonts/";
	public static final String mapsDir = "maps/";
	public static final String soundsDir = "sounds/";

	/** Game font */
	public static final String defaultFont = fontDir + "font_01.fnt";

	/** Splash background */
	public static final String splashBackground = backgroundsDir + "splash.png";

	/** Stage cloud background */
	public static final String stageBackground = backgroundsDir + "stage_cloud.png";

	/** UI pack */
	public static final String uiPack = atlasesDir + "ui.pack";

	/** Loading pack */
	public static final String loadingPack = atlasesDir + "loading.pack";

	/** Characters pack : for selector */
	public static final String charactersPack = atlasesDir + "characters.pack";

	/** Map Tiles pack */
	public static final String tilesPack = atlasesDir + "tiles.pack";

	/** MapChunk's .tmx files */
	public static final String[] mapChunksTmx = {
			mapsDir + "chunk_01.tmx",
			mapsDir + "chunk_02.tmx",
			mapsDir + "chunk_03.tmx",
			mapsDir + "chunk_04.tmx"
	};

	/** Characters atlases */
	public static final String[] charactersAtlases;

	/** Construct charactersAtlases from Strings.characterNames */
	static {
		charactersAtlases = new String[Strings.characterNames.length];
		for (int i = 0; i < charactersAtlases.length; i++)
			charactersAtlases[i] = charactersDir + Strings.characterNames[i] + ".txt";
	}

	/** Player jump sound */
	public static final String jumpSound = soundsDir + "jump.wav";
}
