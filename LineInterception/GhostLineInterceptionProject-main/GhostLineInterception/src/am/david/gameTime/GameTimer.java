package am.david.gameTime;

import am.david.game.Game;

public class GameTimer {
    private long lTime;
    private long lmsAlpha_Timer1;
    private long lmsAlpha_Timer2;
    private double dnsPerTick;
    private double dReciprocalNS_PerTick;
    private double dDelta;

    public int counter;
    public int counter2;

    public GameTimer() {
        lTime = System.nanoTime();
        dReciprocalNS_PerTick = 60.0E-9;
        dDelta = 0;
        lmsAlpha_Timer1 = System.currentTimeMillis();
        lmsAlpha_Timer2 = System.currentTimeMillis();

    }

    public static int inspection = 0;
    public void processTime(Game game) {
        game.init();
        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        while (true) {
            long lNow = System.nanoTime();
            dDelta += (dReciprocalNS_PerTick * (lNow - lTime));
            lTime = lNow;
            boolean bRender = false;
            double dbefore = 0;
            while (dDelta >= 1) {
                dbefore = dDelta;
                dDelta -= 1;
                bRender = true;
            }

            if (bRender) {
                inspection++;
                game.tick();
                game.render();
            }
            long lmsNowBeta = System.currentTimeMillis();
            double PulseTlWaitFor = 1.e3;
            PulseTlWaitFor = 0.5e3;
            if (lmsNowBeta - lmsAlpha_Timer1 >= PulseTlWaitFor) {
                lmsAlpha_Timer1 += PulseTlWaitFor;
                counter++;
                inspection = 0;
            }
            TWait();
            double PulseT2WaitFor = 10.e3; // wait 10 seconds
            if (lmsNowBeta - lmsAlpha_Timer2 >= PulseT2WaitFor) {
                lmsAlpha_Timer2 += PulseT2WaitFor;
                counter2++;
            }
        }
    }
        public void TWait()
        {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

}
/*
 * <p>For example, to measure how long some code takes to execute:
 * <pre> {@code
 * long startTime = System.nanoTime();
 * // ... the code being measured ...
 * long elapsedNanos = System.nanoTime() - startTime;}</pre>
 *
 * <p>To compare elapsed time against a timeout, use <pre> {@code
 * if (System.nanoTime() - startTime >= timeoutNanos) ...}</pre>
 * instead of <pre> {@code
 * if (System.nanoTime() >= startTime + timeoutNanos) ...}</pre>
 * because of the possibility of numerical overflow.
 *
 *
 *
 *
 */