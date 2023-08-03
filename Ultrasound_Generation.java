
/////This code is to generate the ultrasounic waves by standalone system 




import javax.sound.sampled.*;

public class Ultrasound_Generation {

    public static void main(String[] args) {
        int duration = 2; // Duration of the tone in seconds
        int sampleRate = 44100; // Samples per second (standard for CD-quality audio)
        int frequency = 19000; // Frequency of the ultrasound in Hz

        generateUltrasound(duration, sampleRate, frequency);
    }

    public static void generateUltrasound(int duration, int sampleRate, int frequency) {
        try {
            AudioFormat format = new AudioFormat(sampleRate, 8, 1, true, false);
            SourceDataLine line = AudioSystem.getSourceDataLine(format);
            line.open(format);
            line.start();

            int numSamples = duration * sampleRate;
            byte[] buffer = new byte[numSamples];

            for (int i = 0; i < numSamples; i++) {
                double angle = 2.0 * Math.PI * frequency * i / sampleRate;
                buffer[i] = (byte) (Math.sin(angle) * 127); // Create a sine wave
            }

            line.write(buffer, 0, buffer.length);
            line.drain();
            line.close();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}

