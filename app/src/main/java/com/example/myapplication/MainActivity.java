package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.mopub.common.MoPub;
import com.mopub.common.SdkConfiguration;
import com.mopub.common.SdkInitializationListener;
import com.mopub.mobileads.MoPubErrorCode;
import com.mopub.mobileads.MoPubInterstitial;

public class MainActivity extends AppCompatActivity implements MoPubInterstitial.InterstitialAdListener {
    private MoPubInterstitial mInterstitial;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=findViewById(R.id.button);

     final SdkConfiguration.Builder configbuilder = new SdkConfiguration.Builder("24534e1901884e398f1253216226017e");
        MoPub.initializeSdk(this,configbuilder.build(),initSdkListner());
        mInterstitial=new MoPubInterstitial(this,"24534e1901884e398f1253216226017e");

        button=findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    if(mInterstitial.isReady()){
                        mInterstitial.show();
                    }
                    else{

                    }

            }
        });

    }

    private SdkInitializationListener initSdkListner() {
        return new SdkInitializationListener() {
            @Override
            public void onInitializationFinished() {
                mInterstitial.load();
            }
        };
    }



    @Override
    protected void onDestroy() {
        mInterstitial.destroy();
        super.onDestroy();
    }

    // Defined by your application, indicating that you're ready to show an interstitial ad.
    void yourAppsShowInterstitialMethod() {
        if (mInterstitial.isReady()) {
            mInterstitial.show();
        } else {
            // Caching is likely already in progress if `isReady()` is false.
            // Avoid calling `load()` here and instead rely on the callbacks as suggested below.
        }
    }

    // InterstitialAdListener methods
    @Override
    public void onInterstitialLoaded(MoPubInterstitial interstitial) {
        // The interstitial has been cached and is ready to be shown.
    }

    @Override
    public void onInterstitialFailed(MoPubInterstitial interstitial, MoPubErrorCode errorCode) {
        // The interstitial has failed to load. Inspect errorCode for additional information.
    }

    @Override
    public void onInterstitialShown(MoPubInterstitial interstitial) {
        // The interstitial has been shown. Pause / save state accordingly.
    }

    @Override
    public void onInterstitialClicked(MoPubInterstitial interstitial) {}

    @Override
    public void onInterstitialDismissed(MoPubInterstitial interstitial) {
        // The interstitial has being dismissed. Resume / load state accordingly.
    }

}

