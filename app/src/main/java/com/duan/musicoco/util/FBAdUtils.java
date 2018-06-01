package com.duan.musicoco.util;

import android.content.Context;
import android.hardware.display.DisplayManager;
import android.os.PowerManager;
import android.view.Display;

import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.InterstitialAd;
import com.facebook.ads.InterstitialAdListener;

/**
 * Created by liyanju on 2018/6/1.
 */

public class FBAdUtils {

    private static InterstitialAd sInterstitialAd;

    private static Context sContext;

    public static final String CHA_YE_HIGH = "776386772565601_776388429232102";
    public static final String CHA_YE = "776386772565601_776388589232086";

    public static void init(Context context) {
        sContext = context;
    }

    public static void interstitialLoad(String aid, final FBInterstitialAdListener listener) {
        sInterstitialAd = new InterstitialAd(sContext, aid);
        sInterstitialAd.setAdListener(new InterstitialAdListener() {
            @Override
            public void onInterstitialDisplayed(Ad ad) {
                if (listener != null) {
                    listener.onInterstitialDisplayed(ad);
                }
            }

            @Override
            public void onInterstitialDismissed(Ad ad) {
                if (listener != null) {
                    listener.onInterstitialDismissed(ad);
                }
            }

            @Override
            public void onError(Ad ad, AdError adError) {
                if (listener != null) {
                    listener.onError(ad, adError);
                }
            }

            @Override
            public void onAdLoaded(Ad ad) {
                if (listener != null && sInterstitialAd != null) {
                    listener.onLoaded(sInterstitialAd);
                }
            }

            @Override
            public void onAdClicked(Ad ad) {
                if (listener != null) {
                    listener.onAdClicked(ad);
                }
            }

            @Override
            public void onLoggingImpression(Ad ad) {
                if (listener != null) {
                    listener.onLoggingImpression(ad);
                }
            }

        });
        sInterstitialAd.loadAd();
    }

    public static boolean isInterstitialLoaded() {
        return sInterstitialAd != null && sInterstitialAd.isAdLoaded();
    }

    public static void showInterstitial() {
        try {
            if (sInterstitialAd != null && sInterstitialAd.isAdLoaded() && isScreenOn()) {
                sInterstitialAd.show();
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public static boolean isScreenOn() {
        try {
            if (android.os.Build.VERSION.SDK_INT >= 20) {
                // I'm counting
                // STATE_DOZE, STATE_OFF, STATE_DOZE_SUSPENDED
                // all as "OFF"
                DisplayManager dm = (DisplayManager) sContext.getSystemService(Context.DISPLAY_SERVICE);
                Display[] displays = dm.getDisplays();
                for (Display display : displays) {
                    if (display.getState() == Display.STATE_ON
                            || display.getState() == Display.STATE_UNKNOWN) {
                        return true;
                    }
                }
                return false;
            }

            // If you use less than API20:
            PowerManager powerManager = (PowerManager) sContext.getSystemService(Context.POWER_SERVICE);
            if (powerManager.isScreenOn()) {
                return true;
            }
            return false;
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return true;
    }

    public static void destoryInterstitial() {
        try {
            if (sInterstitialAd != null) {
                sInterstitialAd.destroy();
                sInterstitialAd = null;
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public static class FBInterstitialAdListener implements InterstitialAdListener {

        public void onLoaded(InterstitialAd interstitialAd){

        }

        @Override
        public void onInterstitialDisplayed(Ad ad) {

        }

        @Override
        public void onInterstitialDismissed(Ad ad) {

        }

        @Override
        public void onError(Ad ad, AdError adError) {

        }

        @Override
        public void onAdLoaded(Ad ad) {

        }

        @Override
        public void onAdClicked(Ad ad) {

        }

        @Override
        public void onLoggingImpression(Ad ad) {

        }
    }
}
