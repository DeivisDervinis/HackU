package com.dhacks.hacku;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ibm.cloud.sdk.core.http.Response;
import com.ibm.cloud.sdk.core.http.ServiceCall;
import com.ibm.cloud.sdk.core.security.IamAuthenticator;
import com.ibm.watson.assistant.v2.Assistant;
import com.ibm.watson.assistant.v2.model.CreateSessionOptions;
import com.ibm.watson.assistant.v2.model.MessageInput;
import com.ibm.watson.assistant.v2.model.MessageOptions;
import com.ibm.watson.assistant.v2.model.MessageResponse;
import com.ibm.watson.assistant.v2.model.SessionResponse;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {
    //region Private Field Variables
    private Context mContext;
    private Assistant hackuWatsonAssistant;
    private Response<SessionResponse> hackuWatsonAssistantResponse;
    private boolean initialRequest;
    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = getApplicationContext();
        this.initialRequest = true;

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home,
                R.id.navigation_dashboard,
                R.id.navigation_notifications
        ).build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);

        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        // Initialize the WatsonService
        createWatsonService();
        testWatsonService();
    }

    /**
     * Private method that is responsible for setting up the IBM Watson service for HackU
     */
    private void createWatsonService() {
        // Initialize a new Assistant Instance via API Key and URL
        Log.d("IBM_WATSON","Initializing service");
        hackuWatsonAssistant = new Assistant("2020-01-25", new IamAuthenticator(mContext.getString(R.string.assistant_apikey)));
        if (hackuWatsonAssistant != null) {
            Log.d("IBM_WATSON", "Name: " + hackuWatsonAssistant.getName());
        }
        if (hackuWatsonAssistant.getName() != null || hackuWatsonAssistant.getName() != "") {
            Log.d("IBM_WATSON", "Assistant Initialized with API Key");
        }
        hackuWatsonAssistant.setServiceUrl(mContext.getString(R.string.assistant_url));
        Log.d("IBM_WATSON", "Service URL bound to Assistant instance");
    }

    private void testWatsonService() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    if (hackuWatsonAssistantResponse == null) {
                        ServiceCall<SessionResponse> call = hackuWatsonAssistant.createSession(new CreateSessionOptions.Builder().assistantId(mContext.getString(R.string.assistant_id)).build());
                        hackuWatsonAssistantResponse = call.execute();
                    }

                    MessageInput testInput = new MessageInput.Builder().text("Hello").build();
                    MessageOptions testOptions = new MessageOptions.Builder()
                            .assistantId(mContext.getString(R.string.assistant_id))
                            .input(testInput)
                            .sessionId(hackuWatsonAssistantResponse.getResult().getSessionId())
                            .build();
                    Response<MessageResponse> testResponse = hackuWatsonAssistant.message(testOptions).execute();
                    Log.d("IBM_WATSON", "Response: " + testResponse.getResult());

                } catch (Exception e) {
                    //Log.d("IBM_WATSON_ERR", "Error Occurred: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        });

        thread.start();
    }
}
