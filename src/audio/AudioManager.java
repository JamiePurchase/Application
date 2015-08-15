package audio;

import audio.AudioPlayer;

public class AudioManager
{
	private static boolean audioActive = true;
	private static boolean audioMute = false;
	private static boolean musicActive = false;
	private static String musicFile;
	private static boolean soundActive = false;
	private static String soundFile;
	private static int soundTick;
	private static int volume = 100;

	public AudioManager()
	{
		AudioPlayer.init();
		initMusic();
		initSounds();
	}
	
	public void changeMusic(String music)
	{
		if(musicActive){stopMusic();}
		playMusic(music);
	}
	
	public boolean getAudioMute()
	{
		return audioMute;
	}

	public boolean getMusicActive()
	{
		return musicActive;
	}
	
	public boolean getSoundActive()
	{
		return soundActive;
	}
	
	public void initMusic()
	{
                AudioPlayer.load("music/theme1.wav", "MUSIC1");
		/*AudioPlayer.load("music/theme1.wav", "music1");
		AudioPlayer.load("music/battle1.wav", "battle1");
		AudioPlayer.load("music/battle2.wav", "battle2");
		AudioPlayer.load("music/battle3.wav", "battle3");
		AudioPlayer.load("music/battle4.wav", "battle4");
		AudioPlayer.load("music/battle5.wav", "battle5");
		AudioPlayer.load("music/battle6.wav", "battle6");*/
	}
	
	public void initSounds()
	{
            //AudioPlayer.load("sound/error.mp3", "ERROR");
	}
	
	public void playMusic(String music)
	{
		if(audioMute==false)
		{
			AudioPlayer.play(music);
			musicActive = true;
			musicFile = music;
		}
	}
	
	public void playSound(String sound)
	{
		if(audioMute==false)
		{
			AudioPlayer.play(sound);
			soundActive = true;
			soundFile = sound;
			soundTick = 0;
		}
	}

	public void setAudioMute(boolean mute)
	{
		audioMute = mute;
	}
	
	public void setVolume(int newVolume)
	{
		volume = newVolume;
		if(newVolume<1){audioActive = false;}
		else{audioActive = true;}
	}
	
	public void stopMusic()
	{
		AudioPlayer.stop(musicFile);
		musicActive = false;
		musicFile = "";
	}
	
	public void stopSound()
	{
		//AudioPlayer.stop(soundFile);
		soundActive = false;
		soundFile = "";
	}
	
	public void tick()
	{
		if(getSoundActive())
		{
			soundTick += 1;
			//if(soundTick>1){stopSound();}
		}
	}
}