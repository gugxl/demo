package com.gugu.demo.util.text2video;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

import java.io.File;
import java.io.PrintWriter;

public class TextToSpeech {
    public static void main(String[] args) throws Exception {

        VoiceManager voiceManager = VoiceManager.getInstance();
        System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
        Voice chineseVoice = voiceManager.getVoice("kevin16");

        if (chineseVoice != null) {
            chineseVoice.allocate();
            chineseVoice.setRate(140);
            chineseVoice.speak("开心地唱起大风车，天空绽放出美丽色彩！hello Sing the big windmill happily, and the sky blooms with beautiful colors");
            File file = new File("output.wav");
            chineseVoice.dump(new PrintWriter(file), 1, "aa");
            chineseVoice.deallocate();
        } else {
            System.err.println("语音不可用");
        }
    }
}
