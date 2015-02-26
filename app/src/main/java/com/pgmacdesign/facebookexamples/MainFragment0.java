package com.pgmacdesign.facebookexamples;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.facebook.widget.LoginButton;

/**
 * Created by PatrickSSD2 on 2/26/2015.
 */
public class MainFragment0 extends Fragment{

	//UI Helper, following guide from: https://developers.facebook.com/docs/android/login-with-facebook/v2.2
	private UiLifecycleHelper uiHelper;

	private static final String TAG = "Main Fragment: ";

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		//This would go into into the onCreate in the fragment
		uiHelper = new UiLifecycleHelper(getActivity(), callback);
		uiHelper.onCreate(savedInstanceState);

	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.activity_main, container, false);

		//This is used for the authentication login button. It should go in the onCreateView() of a fragment
		LoginButton authButton = (LoginButton) view.findViewById(R.id.facebook_login_button);
		authButton.setFragment(this);
		//authButton.setReadPermissions(Arrays.asList("user_likes", "user_status"));







		return view;
	}

	//Include all of these below into a fragment
	public void onResume() {
		super.onResume();
		uiHelper.onResume();

		/*
		For scenarios where the main activity is launched and user
		session is not null, the session state change notification
		may not be triggered. Trigger it if it's open/closed.
		 */
		Session session = Session.getActiveSession();
		if (session != null &&
				(session.isOpened() || session.isClosed()) ) {
			onSessionStateChange(session, session.getState(), null);
		}

		uiHelper.onResume();
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		uiHelper.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public void onPause() {
		super.onPause();
		uiHelper.onPause();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		uiHelper.onDestroy();
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		uiHelper.onSaveInstanceState(outState);
	}
	//End

	//This will go in the fragment, not the activity
	private void onSessionStateChange(Session session, SessionState state, Exception exception) {
		if (state.isOpened()) {
			Log.i(TAG, "Logged in...");
		} else if (state.isClosed()) {
			Log.i(TAG, "Logged out...");
		}
	}

	private Session.StatusCallback callback = new Session.StatusCallback() {
		@Override
		public void call(Session session, SessionState state, Exception exception) {
			onSessionStateChange(session, state, exception);
		}
	};


}
