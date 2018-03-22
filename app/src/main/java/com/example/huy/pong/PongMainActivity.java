package com.example.huy.pong;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * PongMainActivity
 * 
 * This is the activity for the Pong game. It attaches a PongAnimator to
 * an AnimationSurface.
 * 
 * @author Andrew Nuxoll
 * @author Steven R. Vegdahl
 * @version July 2013
 * 
 */
public class PongMainActivity extends Activity implements View.OnClickListener {

	/**
	 * creates an AnimationSurface containing a TestAnimator.
	 */
	BallAnimator ballAnimator = new BallAnimator();
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pong_main);



		// Connect the animation surface with the animator
		AnimationSurface mySurface = (AnimationSurface) this
				.findViewById(R.id.animationSurface);
		mySurface.setAnimator(ballAnimator);
		Button smallPaddleButton = (Button)findViewById(R.id.smallButton);
		Button bigPaddleButton = (Button)findViewById(R.id.bigButton);

		smallPaddleButton.setOnClickListener(this);
		bigPaddleButton.setOnClickListener(this);




	}

	@Override
	public void onClick(View v) {
		if(v.getId() == R.id.bigButton) {
			ballAnimator.setPaddleBig();
		}
		else if(v.getId() == R.id.smallButton) {
			ballAnimator.setPaddleSmall();
		}
	}


}
