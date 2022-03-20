/**
 * Contains variables and methods pertaining to the overall game state.
 */
public class GameState {
    protected enum gameType {NES, MSX2, WONDERSWAN_COLOR, PLAYSTATION, I_MODE, GAME_BOY_ADVANCE,
        EZWEB, YAHOO, PLAYSTATION_PORTABLE, IOS, ANDROID, WINDOWS_PHONE,
        NINTENDO_3DS, PIXEL_REMASTER}
    protected Boolean bugs = false;

    // Mutator methods

    /**
     * Returns whether bugs are enabled in the game state
     * @return whether bugs are enabled in the game state
     */
    public Boolean getBugs() {
        return bugs;
    }
}
