package sound;
 
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
 
public class SoundHandler
{
    Music gameMusic;
    Sound gameOver;
    Sound win;
    Sound gettingItem;
    Sound throwingItem;
    Sound doorOpened;
    Sound doorClosed;
    Sound teleported;
   
    /**
     * Constructor loading the sounds
     */
    public SoundHandler()
    {
        try
        {
            gameMusic = Gdx.audio.newMusic(Gdx.files.internal("music/gameMusic.wav"));
            gameMusic.setLooping(true);
            win = Gdx.audio.newSound(Gdx.files.internal("music/win.wav"));
            gameOver = Gdx.audio.newSound(Gdx.files.internal("music/gameOver.wav"));
            gettingItem = Gdx.audio.newSound(Gdx.files.internal("music/getItem.wav"));
            throwingItem = Gdx.audio.newSound(Gdx.files.internal("music/throwItem.wav"));
            doorOpened = Gdx.audio.newSound(Gdx.files.internal("music/doorOpen.wav"));
            doorClosed = Gdx.audio.newSound(Gdx.files.internal("music/doorClosed.wav"));
            teleported = Gdx.audio.newSound(Gdx.files.internal("music/teleporter.wav"));
        }
        catch (Exception e)
        {
        	System.err.println("Error when loading sound files");
            e.printStackTrace();
        }
    }
   
    /**
     * Start the game music
     */
    public void startGameMusic()
    {
        win.stop();
        gettingItem.stop();
        gameMusic.play();
    }
   
    /**
     * Stop the game music
     */
    public void stopGameMusic()
    {
        gameMusic.stop();
    }
   
    /**
     * Game over sound
     */
    public void gameOverSound()
    {
        gameMusic.stop();
        gameOver.play();
    }
   
    /**
     * Win sound
     */
    public void gameWinSound()
    {
        gameMusic.stop();
        win.play();
    }
   
    /**
     * Pick up item sound
     */
    public void pickItemSound()
    {
        gettingItem.play();    
    }
   
    /**
     * Throw item sound
     */
    public void throwItemSound()
    {
        throwingItem.play();
    }
   
    /**
     * Door open sound
     */
    public void doorOpenSound()
    {
        doorOpened.play();
    }
   
    /**
     * Door closed sound
     */
    public void doorClosedSound()
    {
        doorClosed.play();
    }
   
    /**
     * Teleporter sound
     */
    public void teleporterSound()
    {
        teleported.play();
    }
   
    /**
     * Reset all the sounds frame position and pause them
     */
    public void resetSounds()
    {
        gameMusic.stop();
        gameOver.stop();
        win.stop();
        gettingItem.stop();
        throwingItem.stop();
        doorOpened.stop();
        doorClosed.stop();
        teleported.stop();
    }
}